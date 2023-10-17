package be.RecipeBook;

import java.util.Set;
import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import be.RecipeBook.domain.AppUser;
import be.RecipeBook.domain.AppUserRepository;
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
/*
	@Bean
	public CommandLineRunner demoData(RecipeRepository rRepository, CategoryRepository cRepository, AppUserRepository uRepository) {
		return (args) -> {
			cRepository.save(new Category("Alkuruoka"));
			cRepository.save(new Category("Pääruoka"));
			cRepository.save(new Category("Jälkiruoka"));
			
			log.info("Save a few recipes");
			Recipe recipe1 = new Recipe("Yrttilohi", "Lohi, yrttejä", "Levitä yrtit lohen pintaan. Paista uunissa kunnes kypsä.", 
					cRepository.findByName("Pääruoka").get(0));
			Recipe recipe2 = new Recipe("Tomaattikeitto", "Tomaattimurska, sipuli, valkosipuli, basilika", 
					"Kuullota hienonnetut sipulit. Lisää tomaattimurska ja keitä noin 30 minuuttia. Viimeistele basilikalla.", 
					cRepository.findByName("Alkuruoka").get(0));
			Recipe recipe3 = new Recipe("Jäätelö kinuskikastikkeella", "Jäätelö, kerma, fariinisokeri", 
					"Keitä kermaa ja fariinisokeria kunnes kastike sakenee. Tarjoa lämpimänä jäätelön kanssa.", 
					cRepository.findByName("Jälkiruoka").get(0));
			rRepository.save(recipe1);
			rRepository.save(recipe2);
			rRepository.save(recipe3);
			
			for (Recipe recipe : rRepository.findAll()) {
				log.info(recipe.toString());
			}
			
			Set<Recipe> recipes1 = new HashSet<>();
			recipes1.add(recipe1);
			recipes1.add(recipe3);
	
			AppUser user1 = new AppUser("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			AppUser user2 = new AppUser("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			uRepository.save(user1);
			uRepository.save(user2);
			
			user1.setLikedRecipes(recipes1);
			uRepository.save(user1);
			
			for(AppUser appUser : uRepository.findAll()) {
				log.info(appUser.toString());
			}
			
			user1.getLikedRecipes().forEach(System.out::println);
		};
	}
	*/
}
