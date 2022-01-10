package com.jmontanez.challenge.bcp.configuration;

import com.jmontanez.challenge.bcp.common.security.JWTAuthorizationFilter;
import com.jmontanez.challenge.bcp.common.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static java.lang.String.format;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Value("${springdoc.api-docs.path}")
  private String restApiDocPath;
  @Value("${springdoc.swagger-ui.path}")
  private String swaggerPath;

  @Autowired
  private JWTAuthorizationFilter jwtAuthorizationFilter;
  
  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.csrf().disable();

    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    http.addFilterAfter(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
            .authorizeRequests()
            .antMatchers("/").permitAll()
            .antMatchers(format("%s/**", restApiDocPath)).permitAll()
            .antMatchers(format("%s/**", swaggerPath)).permitAll()
            .antMatchers("/api/users/authenticate").permitAll()
            .antMatchers("/h2-console/**/**").permitAll()
            .anyRequest()
            .authenticated();

    http.exceptionHandling().accessDeniedPage("/login");

  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
