package principal.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import principal.config.CustomAccesDeniedHandler;
import principal.security.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	AccessDeniedHandler accessDeniedHandler() {
		return new CustomAccesDeniedHandler();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/","/error","/forbidden",  "/layouts","/login","/usuario/registro","/usuario/save",
					"/imgs/**","/js/**","/css/**","/pelicula/ver-comentarios/**","/cargar-peli").permitAll()
		.anyRequest().authenticated()
		.and()
			.formLogin().loginProcessingUrl("/signin").loginPage("/login").permitAll().defaultSuccessUrl("/index")
			.failureUrl("/login?error=true")
			.usernameParameter("username").passwordParameter("password")
			.and()
			.logout().logoutUrl("/logout").logoutSuccessUrl("/").permitAll(); 
	}
	
}
