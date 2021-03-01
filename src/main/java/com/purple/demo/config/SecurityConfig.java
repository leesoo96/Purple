package com.purple.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.purple.demo.common.LoginFailHandler;
import com.purple.demo.common.LoginSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

		@Autowired
		private UserDetailsService userService;
		
		@Bean
		public PasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
		}
		
		@Override
		public void configure(WebSecurity web) throws Exception {
			web.ignoring().antMatchers("/resources/**");
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable();
			
			http.authorizeRequests()
//				.antMatchers("/user/**", "/feed/**", "/notice/**").hasRole("USER")
//				.antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/**").permitAll();
			
			http.formLogin()
					.loginPage("/welcome")
					.loginProcessingUrl("/login")
					.defaultSuccessUrl("/feed")
					.usernameParameter("user_id")
					.passwordParameter("user_pw")
					.successHandler(new LoginSuccessHandler())
					.failureHandler(new LoginFailHandler());
			
			http.logout()
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
					.logoutSuccessUrl("/welcome")
					.invalidateHttpSession(true) // 세션 제거
					.deleteCookies("JSESSIONID") // 쿠키 제거
					.clearAuthentication(true); // 권한정보 제거 
	
			http.sessionManagement()
					.maximumSessions(1) // 최대 세션 허용 수 
					.maxSessionsPreventsLogin(true) // 중복 로그인 시 x
					.expiredUrl("/welcome"); // 세션 만료 또는 중복 시 리다이렉트되는 url

//					.sessionRegistry(sessionRegistry); 
			
			http.exceptionHandling()
				.accessDeniedPage("/welcome");
		}
		
		@Bean
		public SessionRegistry sessionRegistry() {
			return new SessionRegistryImpl();
		}

		@Bean
		public ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
			return new ServletListenerRegistrationBean<HttpSessionEventPublisher>(
					new HttpSessionEventPublisher());
		}
		
		@Override
		public void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
		}
}
