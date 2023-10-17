package be.RecipeBook.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import be.RecipeBook.domain.AppUser;
import be.RecipeBook.domain.AppUserRepository;
import be.RecipeBook.domain.Recipe;
import be.RecipeBook.domain.RecipeRepository;

@RestController
public class RecipeRestController {
	
	@Autowired
	RecipeRepository rRepository;
	
	@Autowired
	AppUserRepository uRepository;
	
	@GetMapping("rest/recipes")
	public @ResponseBody List<Recipe> recipeListRest() {
		return (List<Recipe>) rRepository.findAll();
	}

	@GetMapping("rest/recipe/{id}")
	public Optional<Recipe> findRecipeRest(@PathVariable("id") Long id) {
		return (rRepository.findById(id));
	}
	
	@PostMapping("rest/recipes")
	public Recipe newRecipe(@RequestBody Recipe newRecipe) {
		return rRepository.save(newRecipe);
	}
	
	@PutMapping("rest/recipe/{id}")
	public Recipe editRecipe(@RequestBody Recipe editRecipe, @PathVariable Long id) {
		editRecipe.setId(id);
		return rRepository.save(editRecipe);
	}
	
	@DeleteMapping("rest/recipe/{id}")
	public void deleteRecipe(@PathVariable("id") Long id) {
		rRepository.deleteById(id);
	}
	
	@GetMapping("rest/user/{id}")
	public Optional<AppUser> findUser(@PathVariable("id") Long id) {
		return (uRepository.findById(id));
	}
}
