package be.RecipeBook.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
	
	@GetMapping("index")
	public String index() {
		return "index";
	}
	
	@GetMapping("recipelist")
	public String recipeList(Model model) {
		model.addAttribute("recipes", rRepository.findAll());
		return "recipelist";
	}
	
	@GetMapping("add")
	public String addRecipe(Model model) {
		model.addAttribute("recipe", new Recipe());
		model.addAttribute("categories", cRepository.findAll());
		return "addrecipe";
	}
	
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
	
	@GetMapping("delete/{id}")
	public String deleteRecipe(@PathVariable("id") Long id, Model model) {
		rRepository.deleteById(id);
		return "redirect:../recipelist";
	}
	
	@GetMapping("edit/{id}")
	public String editRecipe(@PathVariable("id") Long id, Model model) {
		model.addAttribute("recipe", rRepository.findById(id));
		model.addAttribute("categories", cRepository.findAll());
		return "editrecipe";
	}
}