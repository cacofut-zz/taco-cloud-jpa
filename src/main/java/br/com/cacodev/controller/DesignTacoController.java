/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cacodev.controller;

import br.com.cacodev.model.repository.IngredientRepository;
import br.com.cacodev.model.repository.TacoRepository;
import br.com.cacodev.tacocloudjpa.model.entity.Ingredient;
import br.com.cacodev.tacocloudjpa.model.entity.Order;
import br.com.cacodev.tacocloudjpa.model.entity.Taco;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author cristianoca
 */
@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {
    
    private final IngredientRepository ingredientRepo;
    private TacoRepository tacoRepo;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepo, 
        TacoRepository tacoRepo) {
        this.ingredientRepo = ingredientRepo;
        this.tacoRepo = tacoRepo;
    }
    
    @ModelAttribute(name = "order")
    public Order order(){
        log.info( "carregou o objeto order ");
        return new Order();
    }
    
    @ModelAttribute("design")
    public Taco design(){
        log.info( "carregou o objeto taco ");
        return new Taco();
    }
    
    @GetMapping
    public String showDesignForm(Model model, @ModelAttribute Order order){
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepo.findAll().forEach( i -> ingredients.add(i));
        
        Ingredient.Type[] types = Ingredient.Type.values();
        for( Ingredient.Type type : types ){
            model.addAttribute(type.toString().toLowerCase(), 
                filterByType(ingredients, type));
        }
        log.info( "carregou design ");
        log.info("order: " + order);
        return "design";
    }
    
    @PostMapping
    public String processDesign( @Valid Taco taco, Errors errors, 
        @ModelAttribute Order order ){
        if( errors.hasErrors() ){
            return "design";
        }
        Taco saved = tacoRepo.save(taco);
        order.addDesign(saved);
        
        return "redirect:/orders/current";
    }
    
    /**
     * 
     * @param ingredients
     * @param type
     * @return 
     */
    private List<Ingredient> filterByType(List<Ingredient> ingredients, 
        Ingredient.Type type){
        return ingredients
            .stream()
            .filter( x -> x.getType().equals(type))
            .collect(Collectors.toList());
    }

}
