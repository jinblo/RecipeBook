package be.RecipeBook;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import be.RecipeBook.domain.CategoryRepository;
import be.RecipeBook.domain.Recipe;
import be.RecipeBook.domain.RecipeRepository;

@SpringBootTest
class RecipeRepositoryTests {

	@Autowired
	RecipeRepository rRepository;
	
	@Autowired
	CategoryRepository cRepository;
	
	@Test
	void deleteRecipe() {
		Recipe recipe = rRepository.findById((long) 4).get();
		assertThat(recipe).isNotNull();
		rRepository.delete(recipe);
		Optional<Recipe> newRecipes = rRepository.findById((long) 4);
		assertThat(newRecipes).isEmpty();
	}
	
	@Test
	void getNonExistingRecipe() {
		Optional<Recipe> recipe = rRepository.findById((long)100);
		assertThat(recipe).isEmpty();
	}	

	@Test
	void findRecipeByName() {
		Recipe recipe = rRepository.findByNameIgnoreCase("couscous salad");
		assertThat(recipe).isNotNull();
	}
	
	@Test
	void saveRecipe() {
		Recipe recipe = new Recipe("Uunilohi", "Lohi, mausteita", "Levitä mausteet lohen pintaan. Paista uunissa kunnes kypsä.", cRepository.findByName("main").get(0));
		rRepository.save(recipe);
		assertThat(rRepository.findByNameIgnoreCase("uunilohi").getId()).isNotNull();
	}
	
	@Test
	void updateRecipe() {
		Recipe recipe = new Recipe("Recipe", "Test", "Lorem ipsum dolor sit amet, consectetur adipiscing", cRepository.findByName("main").get(0));
		assertThat(recipe).isNotNull();
		recipe.setName("Testi");
		rRepository.save(recipe);
		Recipe newRecipe = rRepository.findByNameIgnoreCase("Testi");
		assertThat(newRecipe).isNotNull();
	}
	
	@Test
	void findAllRecipes() {
		Iterable<Recipe> recipes = rRepository.findAll();
		assertThat(recipes).hasSizeGreaterThan(3);
	}
	
}
