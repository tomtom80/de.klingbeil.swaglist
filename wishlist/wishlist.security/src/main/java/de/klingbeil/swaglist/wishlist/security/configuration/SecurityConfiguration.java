package de.klingbeil.swaglist.wishlist.security.configuration;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
//		      .antMatchers(HttpMethod.POST, "/login/**").authenticated()
//		      .antMatchers(HttpMethod.DELETE, "/admin/**").authenticated()
//		      .antMatchers(HttpMethod.GET, "/wish/**").authenticated()
			.anyRequest().permitAll()
			.and().httpBasic()
			.and().csrf().disable();
	}	

}
