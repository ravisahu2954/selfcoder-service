/*
 * package com.selfcoder.selfcoder.config;
 * 
 * import org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.http.HttpMethod; import
 * org.springframework.security.authentication.AuthenticationProvider; import
 * org.springframework.security.authentication.dao.DaoAuthenticationProvider;
 * import
 * org.springframework.security.config.annotation.web.builders.HttpSecurity;
 * import org.springframework.security.config.annotation.web.configuration.
 * EnableWebSecurity; import
 * org.springframework.security.core.userdetails.UserDetailsService; import
 * org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; import
 * org.springframework.security.crypto.password.PasswordEncoder; import
 * org.springframework.security.web.SecurityFilterChain;
 * 
 * @Configuration
 * 
 * @EnableWebSecurity public class WebSecurity {
 * 
 * 
 * // Authentication
 * 
 * @Bean UserDetailsService userDetailsService() { // UserDetails admin =
 * User.withUsername("ravi").password(encoder.encode("python123")).roles("ADMIN"
 * ).build(); // UserDetails user =
 * User.withUsername("raj").password(encoder.encode("python123")).roles("USER").
 * build(); return new UserInfoServiceImpl();
 * 
 * }
 * 
 * // Authorization
 * 
 * @Bean SecurityFilterChain securityFilterChain(HttpSecurity http) throws
 * Exception {
 * 
 * return http.csrf().disable() .authorizeHttpRequests()
 * .requestMatchers("/selfcoder/v1/users") .permitAll() .and()
 * .authorizeHttpRequests() .requestMatchers("/selfcoder/v1/problems")
 * .authenticated() .and() .formLogin() .and() .build();
 * 
 * return http.csrf().disable() .authorizeHttpRequests()
 * .requestMatchers("/selfcoder/v1/users") .permitAll() .and()
 * .authorizeHttpRequests() .requestMatchers("/selfcoder/v1/courses")
 * .permitAll() .and() .authorizeHttpRequests()
 * .requestMatchers("/selfcoder/v1/blogs") .permitAll() .and()
 * .authorizeHttpRequests() .requestMatchers("/selfcoder/v1/problems")
 * .permitAll() .and() .build(); }
 * 
 * @Bean PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder();
 * }
 * 
 * @Bean AuthenticationProvider authenticationProvider() {
 * DaoAuthenticationProvider dauAuthenticationProvider = new
 * DaoAuthenticationProvider();
 * dauAuthenticationProvider.setUserDetailsService(userDetailsService());
 * dauAuthenticationProvider.setPasswordEncoder(passwordEncoder()); return
 * dauAuthenticationProvider; }
 * 
 * }
 */