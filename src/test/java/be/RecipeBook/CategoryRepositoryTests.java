package be.RecipeBook;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import be.RecipeBook.domain.Category;
import be.RecipeBook.domain.CategoryRepository;

@DataJpaTest
class CategoryRepositoryTests {
	
	@Autowired
	CategoryRepository cRepository;
	
	@Test
	void findAllCategories() {
		Iterable<Category> categories = cRepository.findAll();
		assertThat(categories).hasSize(3);
	}
	
	@Test
	void findCategoryByName() {
		Iterable<Category> category = cRepository.findByName("Alkuruoka");
		assertThat(category).isNotNull();
	}

	@Test
	void saveNewCategory() {
		Category category = new Category("Drinkki");
		cRepository.save(category);
		assertThat(category.getId()).isNotNull();
	}
	
	@Test
	void deleteCategory() {
		Optional<Category> categories = cRepository.findById((long) 1);
		Category category = categories.get();
		cRepository.delete(category);
		Optional<Category> newCategories = cRepository.findById((long) 1);
		assertThat(newCategories).isEmpty();
	}
}
