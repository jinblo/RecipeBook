package be.RecipeBook.domain;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotNull
	@Size(min=3, max=100)
	private String name;
	@Size(min=3)
	private String ingredients;
	@Size(min=30, message="lenght must be over 30")
	private String instructions;
	
	@ManyToOne
	@JoinColumn(name="categoryid")
	private Category category;
	
	@JsonIgnore
	@ManyToMany(cascade=CascadeType.ALL, mappedBy="likedRecipes")
	private Set<AppUser> likes;

	public Recipe() {}

	public Recipe(String name, String ingredients, String instructions) {
		this.name = name;
		this.ingredients = ingredients;
		this.instructions = instructions;
	}
	
	public Recipe(String name, String ingredients, String instructions, Category category) {
		this.name = name;
		this.ingredients = ingredients;
		this.instructions = instructions;
		this.category = category;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Set<AppUser> getLikes() {
		return likes;
	}

	public void setLikes(Set<AppUser> likes) {
		this.likes = likes;
	}

	@Override
	public String toString() {
		return "Recipe [id=" + id + ", name=" + name + ", ingredients=" + ingredients + ", instructions=" + instructions
				+ ", category=" + category + "]";
	}

	
}
