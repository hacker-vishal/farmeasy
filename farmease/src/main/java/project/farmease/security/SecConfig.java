package project.farmease.security;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import project.farmease.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity  
public class SecConfig extends WebSecurityConfigurerAdapter {    // this class holds the complete security configuration of our application

	@Autowired
	 private UserDetailsService userDetailsService;
	@Autowired
	 private JwtAuthFilter jwtAuthFilter;
	 Logger logger = LogManager.getLogger(SecConfig.class);
	 

	    @Bean(BeanIds.AUTHENTICATION_MANAGER)               // Bean created to provide implementation to AuthenticationManager
	    @Override
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	        return super.authenticationManagerBean();
	    }
	 
	 @Override
	    public void configure(HttpSecurity httpSecurity) throws Exception {    //we are configuring Spring to allow all the requests which match the endpoint “/api/auth/**” , as these endpoints are used for authentication and registration we don’t expect the user to be authenticated at that point of time.
	        httpSecurity.cors().and()
	                .csrf().disable()        
	                .authorizeRequests()
	                .antMatchers("/auth/**")       // authencticate all the request which doen't match this pattern
	                .permitAll().antMatchers(HttpMethod.GET, "/auth") .permitAll()
					.antMatchers("/services/**") .permitAll()     // these should be GET call so that spring will not authorize these everytime and guest can see these pages without login
					.antMatchers("/password/**") .permitAll()
					.antMatchers("/update/**") .permitAll()
					.antMatchers("/insert") .permitAll()
					.antMatchers("/booking/**") .permitAll()
					.antMatchers(HttpMethod.GET, "/wishlist/**") .permitAll()
					.antMatchers("/configuration/ui", "/configuration/security")                
					.permitAll()
	                .anyRequest()
	                .authenticated();
	                httpSecurity.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
	    }
	 
	    @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
	    	logger.info("Password encrypting started ");
	        authenticationManagerBuilder.userDetailsService(userDetailsService)
	                .passwordEncoder(passwordEncoder());          // here we are using passwordEncoder of spring, we can also use LDAP, memory based, Database based authentication
	    }

		
		 @Bean        // we used bean annotation because PasswordEncoder is a interface and whenver we are Autowiring this bean , we will get PasswordEncoder() object
		 PasswordEncoder passwordEncoder() { 
			 logger.info("Password is encrypted using BCrypt Hashing algorithm ");
			 return new BCryptPasswordEncoder();
		 }
}
