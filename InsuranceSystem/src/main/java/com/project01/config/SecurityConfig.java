package com.project01.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.project01.component.LoginFailureHandler;
import com.project01.component.LoginSuccessHandler;
import com.project01.service.AdminDetailsService;
import com.project01.service.InsurerDetailsService;

@Configuration
public class SecurityConfig {
	
	@Autowired
	private AdminDetailsService adminDetailsService;
	@Autowired
	private InsurerDetailsService insurerDetailsService;
	
	private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
	
	public SecurityConfig() {
		logger.info("Security Config 配置成功...");
	}
	
	//HttpSecurity配置-admin
	@Bean
	public SecurityFilterChain AdminFilterChain(HttpSecurity http,LoginSuccessHandler successHandler,LoginFailureHandler failureHandler) throws Exception {
		
		//AuthenticationManager(認證管理器)-admin使用
		AuthenticationManagerBuilder authBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
		authBuilder
		.userDetailsService(adminDetailsService)
		.passwordEncoder(passwordEncoder());
		AuthenticationManager adminManager = authBuilder.build();
		
		http
			.securityMatcher("/insurancesystem/admin/login","/admin/page/**")
			.authenticationManager(adminManager)
			.csrf(csrf -> csrf
					.disable())
			.authorizeHttpRequests(auth -> auth
					.requestMatchers("/insurancesystem/home/**","/insurancesystem/*/login/**","/css/**","/vue/**","/sweetaert/**").permitAll()
					.requestMatchers("/admin/page/**").hasRole("ADMIN")
					.anyRequest().authenticated()
					)
			.formLogin(form -> form
					.loginPage("/insurancesystem/home")
					.loginProcessingUrl("/insurancesystem/admin/login")
					.usernameParameter("accountId")
					.successHandler(successHandler) //自定義的登入成功處理器
					.failureHandler(failureHandler) //自定義的登入失敗處理器
					.permitAll()
//					)
//			.logout(logout -> logout
//					.logoutUrl("/logout")
//					.logoutSuccessUrl("/login?logout=true")
//					.permitAll()
					);
		return http.build();
	}
	
	//HttpSecurity配置-insurer
	@Bean
	public SecurityFilterChain InsurerFilterChain(HttpSecurity http,LoginSuccessHandler successHandler,LoginFailureHandler failureHandler) throws Exception {
		
		//AuthenticationManager(認證管理器)-insurer使用
		AuthenticationManagerBuilder authBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
		authBuilder
		.userDetailsService(insurerDetailsService)
		.passwordEncoder(passwordEncoder());
		AuthenticationManager insurerManger = authBuilder.build();
		
		http
			.securityMatcher("/insurancesystem/insurer/login","/insurer/page/**","/insuranceCompany/page/**","/insuranceType/page/**","/policyPerson/page/**","/policy/page/**","/api/**")
			.authenticationManager(insurerManger)
			.csrf(csrf -> csrf
					.disable())
			.authorizeHttpRequests(auth -> auth
					.requestMatchers("/insurancesystem/home/**","/insurancesystem/*/login/**","/css/**","/vue/**","/sweetaert/**").permitAll()
					.requestMatchers("/insurer/page/**","/insuranceCompany/page/**","/insuranceType/page/**","/policyPerson/page/**","/policy/page/**","/api/**").hasRole("USER")
					.anyRequest().authenticated()
					)
			.formLogin(form -> form
					.loginPage("/insurancesystem/home")
					.loginProcessingUrl("/insurancesystem/insurer/login")
					.usernameParameter("accountId")
					.successHandler(successHandler) //自定義的登入成功處理器
					.failureHandler(failureHandler) //自定義的登入失敗處理器
					.permitAll()
//					)
//			.logout(logout -> logout
//					.logoutUrl("/logout")
//					.logoutSuccessUrl("/login?logout=true")
//					.permitAll()
					);
		return http.build();
	}
	
	//PasswordEncoder(密碼加密器)
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	//AuthenticationManager(認證管理器)
//	@Bean
//	public AuthenticationManager authManager(HttpSecurity http) throws Exception {
//		AuthenticationManagerBuilder authBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
//		authBuilder
//		.userDetailsService(adminDetailsService)
//		.passwordEncoder(passwordEncoder());
//		
//		return authBuilder.build();
//	}
	
}
