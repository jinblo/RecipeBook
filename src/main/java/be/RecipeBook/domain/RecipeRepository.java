package be.RecipeBook.domain;

import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
	Recipe findByNameIgnoreCase(String name);
}
