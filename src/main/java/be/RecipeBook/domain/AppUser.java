package be.RecipeBook.domain;

import java.util.Set;
import java.util.HashSet;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name="app_user")
public class AppUser {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", nullable=false, updatable=false)
	private Long id;
	@Column(name="username", nullable=false, unique=true)
	private String username;
	@Column(name="password", nullable=false)
	private String password;
	@Column(name="role", nullable=false)
	private String role;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(
			name="liked_recipes", 
			joinColumns = @JoinColumn(name="appuser_id"), 
			inverseJoinColumns = @JoinColumn(name="recipe_id"))
	private Set<Recipe> likedRecipes;
	
	public AppUser() {}
	
	public AppUser(String username, String password, String role) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
	}
	
	public AppUser(String username, String password, String role, Set<Recipe> likedRecipes) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
		this.likedRecipes = likedRecipes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Set<Recipe> getLikedRecipes() {
		return likedRecipes;
	}

	public void setLikedRecipes(Set<Recipe> likedRecipes) {
		this.likedRecipes = likedRecipes;
	}

	@Override
	public String toString() {
		return "AppUser [id=" + id + ", username=" + username + ", password=" + password + ", role=" + role + "]";
	}
	
}
