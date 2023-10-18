package be.RecipeBook.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import be.RecipeBook.domain.AppUser;
import be.RecipeBook.domain.AppUserRepository;
import be.RecipeBook.domain.CategoryRepository;
import be.RecipeBook.domain.Recipe;
import be.RecipeBook.domain.RecipeRepository;
import jakarta.validation.Valid;

@Controller
public class RecipeController {

	@Autowired
	private RecipeRepository rRepository;
	
	@Autowired
	private CategoryRepository cRepository;
	
	@Autowired 
	private AppUserRepository uRepository;
	
	@GetMapping("index")
	public String index() {
		return "index";
	}
	
	@GetMapping("login")
	public String login() {
		return "login";
	}
	
	@GetMapping("recipelist")
	public String recipeList(Model model) {
		model.addAttribute("recipes", rRepository.findAll());
		return "recipelist";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("add")
	public String addRecipe(Model model) {
		model.addAttribute("recipe", new Recipe());
		model.addAttribute("categories", cRepository.findAll());
		return "addrecipe";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("save")
	public String save(@Valid Recipe recipe, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			System.out.println("Error happened");
			model.addAttribute("categories", cRepository.findAll());
			return "addrecipe";
		} else {
		rRepository.save(recipe);
		return "redirect:recipelist";
		}
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("delete/{id}")
	public String deleteRecipe(@PathVariable("id") Long id, Model model) {
		rRepository.deleteById(id);
		return "redirect:../recipelist";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("edit/{id}")
	public String editRecipe(@PathVariable("id") Long id, Model model) {
		model.addAttribute("recipe", rRepository.findById(id));
		model.addAttribute("categories", cRepository.findAll());
		return "editrecipe";
	}
	
	@GetMapping("recipe/{id}")
	public String showRecipe(@PathVariable("id") Long id, Model model) {
		model.addAttribute("recipe", rRepository.findById(id));
		model.addAttribute("user", uRepository.findById((long) 1));
		return "recipe";
	}
	
	@GetMapping("favourites/{username}")
	public String showFavorites(@PathVariable("username") String username, Model model) {
		AppUser appUser = uRepository.findByUsername(username);
		model.addAttribute("likedRecipes", appUser.getLikedRecipes());
		model.addAttribute("user", appUser);
		return "favourites";
	}
	
	@PostMapping("addfavourite/{username}")
	public String addFavourite(@PathVariable("username") String username, Recipe recipe) {
		AppUser appUser = uRepository.findByUsername(username);
		Recipe newRecipe = rRepository.findById(recipe.getId()).get();
		appUser.getLikedRecipes().add(newRecipe);
		uRepository.save(appUser);
		return "redirect:../favourites/{username}";
	}
	
	@GetMapping("removefavourite/{username}/{id}")
	public String removeFavourite(@PathVariable("username") String username, @PathVariable("id") Long id) {
		AppUser appUser = uRepository.findByUsername(username);
		Recipe recipe = rRepository.findById(id).get();
		appUser.getLikedRecipes().remove(recipe);
		uRepository.save(appUser);
		return "redirect:../../favourites/{username}";
	}
}
