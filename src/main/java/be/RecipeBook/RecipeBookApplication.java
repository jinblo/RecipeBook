package be.RecipeBook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import be.RecipeBook.domain.Category;
import be.RecipeBook.domain.CategoryRepository;
import be.RecipeBook.domain.Recipe;
import be.RecipeBook.domain.RecipeRepository;


@SpringBootApplication
public class RecipeBookApplication {
	
	private static final Logger log = LoggerFactory.getLogger(RecipeBookApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(RecipeBookApplication.class, args);
	}

	@Bean
	public CommandLineRunner demoData(RecipeRepository rRepository, CategoryRepository cRepository) {
		return (args) -> {
			cRepository.save(new Category("Alkuruoka"));
			cRepository.save(new Category("Pääruoka"));
			cRepository.save(new Category("Jälkiruoka"));
			
			log.info("Save a few recipes");
			rRepository.save(new Recipe("Yrttilohi", "Lohi, yrttejä", "Levitä yrtit lohen pintaan. Paista uunissa kunnes kypsä.", cRepository.findByName("Pääruoka").get(0)));
			rRepository.save(new Recipe("Tomaattikeitto", "Tomaattimurska, sipuli, valkosipuli, basilika", 
					"Kuullota hienonnetut sipulit. Lisää tomaattimurska ja keitä noin 30 minuuttia. Viimeistele basilikalla.", cRepository.findByName("Alkuruoka").get(0)));
			rRepository.save(new Recipe("Jäätelö kinuskikastikkeella", "Jäätelö, kerma, fariinisokeri", 
					"Keitä kermaa ja fariinisokeria kunnes kastike sakenee. Tarjoa lämpimänä jäätelön kanssa.", cRepository.findByName("Jälkiruoka").get(0)));
			
			for (Recipe recipe : rRepository.findAll()) {
				log.info(recipe.toString());
			}
			

		};
	}
}
