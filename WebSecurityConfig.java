package com.cinema.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.cinema.config.MySimpleUrlAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	
	@Autowired
	  private UserDetailsService userDetailsService;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	};
	
	 @Override
	  protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
	      
	      auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	      /*
	       * auth.inMemoryAuthentication()
	        .withUser("user1").password(passwordEncoder().encode("user1Pass")).roles("USER")
	        .and()
	        .withUser("user2").password(passwordEncoder().encode("user2Pass")).roles("USER")
	        .and()
	        .withUser("admin").password(passwordEncoder().encode("admin@123")).roles("ADMIN");
	        */
	  }

	 @Bean
	  public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
	      return new MySimpleUrlAuthenticationSuccessHandler();
	  }
	 
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder authenticationMgr) throws Exception {
//		authenticationMgr.inMemoryAuthentication()
//		.withUser("q")
//		.password(passwordEncoder().encode("q"))
//		.authorities("USER");/** burayı değiştirdin en son **/
//	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http.csrf().disable()
	      .authorizeRequests()
	      .antMatchers("/anonymous*").anonymous()
				  .antMatchers(HttpMethod.GET, "/ekle/**").hasAuthority("USER")
			      .antMatchers(HttpMethod.GET, "/SeansEkle/**").hasAuthority("USER")
			      .antMatchers(HttpMethod.GET, "/guncelle/**").hasAuthority("USER")
			      .antMatchers(HttpMethod.GET, "/guncelle1/**").hasAuthority("USER")
			      .antMatchers("/login*").permitAll()
			      .antMatchers("/register*").permitAll()
			      .anyRequest().authenticated()
			      .and()
				
				.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/")
				.failureUrl("/login?error")
			    .successHandler(myAuthenticationSuccessHandler())
				.usernameParameter("username")
				.passwordParameter("password")
				.and()
			    .exceptionHandling().accessDeniedPage("/accessDenied").and()
				.logout()
				.logoutSuccessUrl("/login?logout");

	}
}
