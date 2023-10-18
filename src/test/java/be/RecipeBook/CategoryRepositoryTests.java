package be.RecipeBook;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import be.RecipeBook.domain.Category;
import be.RecipeBook.domain.CategoryRepository;

@SpringBootTest
class CategoryRepositoryTests {
	
	@Autowired
	CategoryRepository cRepository;
	
	@Test
	void findAllCategories() {
		Iterable<Category> categories = cRepository.findAll();
		assertThat(categories).hasSizeGreaterThan(3);
	}
	
	@Test
	void deleteCategory() {
		cRepository.save(new Category("Test"));
		List<Category> category = cRepository.findByName("Test");
		assertThat(category.get(0).getId()).isNotNull();
		cRepository.deleteById(category.get(0).getId());
		Optional<Category> newCategory = cRepository.findById(category.get(0).getId());
		assertThat(newCategory).isEmpty();
	}	
	
	@Test
	void findCategoryByName() {
		List<Category> category = cRepository.findByName("Test");
		assertThat(category.get(0).getId()).isNotNull();
	}
	
	@Test
	void saveNewCategory() {
		Category category = new Category("Test");
		cRepository.save(category);
		assertThat(category.getId()).isNotNull();
	}
	
}
