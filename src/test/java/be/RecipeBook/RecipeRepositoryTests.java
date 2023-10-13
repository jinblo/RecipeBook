package be.RecipeBook;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import be.RecipeBook.domain.CategoryRepository;
import be.RecipeBook.domain.Recipe;
import be.RecipeBook.domain.RecipeRepository;

@DataJpaTest
class RecipeRepositoryTests {

	@Autowired
	RecipeRepository rRepository;
	
	@Autowired
	CategoryRepository cRepository;
	
	@Test
	void findAllRecipes() {
		Iterable<Recipe> recipes = rRepository.findAll();
		assertThat(recipes).hasSize(3);
	}
	
	@Test
	void findRecipeByName() {
		Iterable<Recipe> recipe = rRepository.findByNameIgnoreCase("yrttilohi");
		assertThat(recipe).isNotEmpty();
	}
	
	@Test
	void getNonExistingRecipe() {
		Optional<Recipe> recipe = rRepository.findById((long)100);
		assertThat(recipe).isEmpty();
	}
	
	@Test
	void saveRecipe() {
		Recipe recipe = new Recipe("Uunilohi", "Lohi, mausteita", "Levitä mausteet lohen pintaan. Paista uunissa kunnes kypsä.", cRepository.findByName("Pääruoka").get(0));
		rRepository.save(recipe);
		assertThat(recipe.getId()).isNotNull();
	}
	
	@Test
	void tryToSaveEmptyRecipeSouldError() {
		Recipe recipe = new Recipe();
		rRepository.save(recipe);
		assertThat(recipe.getId()).isNull();
	}
	
	@Test
	void updateRecipe() {
		Optional<Recipe> recipe = rRepository.findById((long) 1);
		recipe.get().setName("Testi");
		Iterable<Recipe> recipes = rRepository.findByNameIgnoreCase("Testi");
		assertThat(recipes).hasSize(1);
	}
	
	@Test
	void deleteRecipe() {
		Optional<Recipe> recipes = rRepository.findById((long) 1);
		Recipe recipe = recipes.get();
		rRepository.delete(recipe);
		Optional<Recipe> newRecipes = rRepository.findById((long) 1);
		assertThat(newRecipes).isEmpty();
	}
}
