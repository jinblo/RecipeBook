package be.RecipeBook;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import be.RecipeBook.web.UserDetailServiceImpl;

@Configuration
@EnableMethodSecurity(securedEnabled=true)
public class WebSecurityConfig {

	@Autowired
	private UserDetailServiceImpl userDetailsService;
	
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests(authorize -> authorize
							.requestMatchers(antMatcher("/css/**")).permitAll()
							.requestMatchers(antMatcher("/index")).permitAll()
							.requestMatchers(antMatcher("/rest/**")).permitAll()	// not for production, just for development
							.requestMatchers(antMatcher("/add")).hasAuthority("ADMIN")
							.requestMatchers(antMatcher("/save")).hasAuthority("ADMIN")
							.requestMatchers(antMatcher("/edit/**")).hasAuthority("ADMIN")
							.requestMatchers(antMatcher("/delete/**")).hasAuthority("ADMIN")
							.anyRequest().authenticated()
							).formLogin(formlogin -> formlogin
										.loginPage("/login")
										.defaultSuccessUrl("/recipelist", true)
										.permitAll()
							).logout(logout -> logout
										.permitAll()
							).csrf(csrf -> csrf.disable()); // not for production, just for development  );
		
		return http.build();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
}
