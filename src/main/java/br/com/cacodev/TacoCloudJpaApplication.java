package br.com.cacodev;

import br.com.cacodev.model.repository.IngredientRepository;
import br.com.cacodev.model.repository.TacoRepository;
import br.com.cacodev.tacocloudjpa.model.entity.Ingredient;
import br.com.cacodev.tacocloudjpa.model.entity.Taco;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class TacoCloudJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(TacoCloudJpaApplication.class, args);                        
    }

    @Bean
    public CommandLineRunner dataLoader(IngredientRepository ingredienteRepo, TacoRepository tacoRepo){
        
        return new CommandLineRunner() {
            
            @Override
            public void run(String... args) throws Exception {  
                
                Ingredient i1  = new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP);
                Ingredient i2  = new Ingredient("COTO", "Corn Tortilla",  Ingredient.Type.WRAP);
                Ingredient i3  = new Ingredient("GRBF", "Ground Beef",    Ingredient.Type.PROTEIN);
                Ingredient i4  = new Ingredient("CARN", "Carnitas",       Ingredient.Type.PROTEIN);
                Ingredient i5  = new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES);
                Ingredient i6  = new Ingredient("LETC", "Lettuce",        Ingredient.Type.VEGGIES);
                Ingredient i7  = new Ingredient("CHED", "Cheddar",        Ingredient.Type.CHEESE);
                Ingredient i8  = new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE);
                Ingredient i9  = new Ingredient("SLSA", "Salsa",          Ingredient.Type.SAUCE);
                Ingredient i10 = new Ingredient("SRCR", "Sour Cream",     Ingredient.Type.SAUCE);
                
                List<Ingredient> ingredients = 
                    Arrays.asList(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10);
                
                ingredienteRepo.saveAll(ingredients);
                
                Taco t1 = new Taco();
                t1.setName("Novo taco super super");
                t1.setIngredients(ingredients);
                
                tacoRepo.save(t1);
                
                
            }
        };
    }
}
