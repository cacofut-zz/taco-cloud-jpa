
package br.com.cacodev.controller;

import br.com.cacodev.model.repository.OrderRepository;
import br.com.cacodev.tacocloudjpa.model.entity.Order;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

/**
 *
 * @author cristianoca
 */
@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {
    
    private OrderRepository orderRepo;

    @Autowired
    public OrderController(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }
    
    @GetMapping("/current")
    public String orderForm(){
        return "orderForm";
    }
    
    @PostMapping
    public String processOrder(@Valid Order order, Errors errors, 
        SessionStatus sessionStatus){
        if( errors.hasErrors() ){
            return "orderForm";
        }        
        orderRepo.save(order);
        sessionStatus.setComplete();
        return "redirect:/";
    }
    
}
