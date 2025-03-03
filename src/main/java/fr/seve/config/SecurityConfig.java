package fr.seve.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import fr.seve.service.impl.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final AmapSlugValidationFilter amapSlugValidationFilter;
    
	@Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CustomAuthenticationEntryPoint customAuthenticationEntryPoint() {
    	return new CustomAuthenticationEntryPoint();
    }

    public SecurityConfig(AmapSlugValidationFilter amapSlugValidationFilter) {
        this.amapSlugValidationFilter = amapSlugValidationFilter;
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // Désactiver CSRF (à activer si nécessaire)
            .authorizeRequests()
                .antMatchers("/amap").hasRole("SAAS_CUSTOM") // Autorisation pour les utilisateurs SaaS
                .anyRequest().permitAll() // Permettre tout le reste
            .and()
            .addFilterBefore(amapSlugValidationFilter, UsernamePasswordAuthenticationFilter.class) // Ajout du filtre personnalisé
            .exceptionHandling()
                .authenticationEntryPoint(customAuthenticationEntryPoint()) // Gestion des exceptions avec point d'entrée personnalisé
            .and()
            .formLogin()
                .loginPage("/login") // Page de connexion personnalisée
                .defaultSuccessUrl("/profile", true) // Redirection après succès
                .failureUrl("/login?error=true") // Redirection en cas d'échec
                .permitAll()
            .and()
            .logout()
                .logoutUrl("/logout") // URL de déconnexion
                .logoutSuccessUrl("/login?logout=true") // Redirection après déconnexion
                .permitAll()
        	.and()
        	.exceptionHandling()
        	.accessDeniedPage("/error/403");
    }
}
