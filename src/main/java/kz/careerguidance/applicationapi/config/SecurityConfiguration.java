package kz.careerguidance.applicationapi.config;

import kz.careerguidance.applicationapi.config.jwt.JwtConfigurer;
import kz.careerguidance.applicationapi.config.jwt.JwtProperties;
import kz.careerguidance.applicationapi.config.jwt.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;
import org.springframework.web.filter.CorsFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration {

  private final JwtProperties jwtProperties;

  private final CorsFilter corsFilter;

  private final TokenProvider tokenProvider;

  public SecurityConfiguration(
      JwtProperties jwtProperties,
      CorsFilter corsFilter,
      TokenProvider tokenProvider) {
    this.jwtProperties = jwtProperties;
    this.corsFilter = corsFilter;
    this.tokenProvider = tokenProvider;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf()
        .disable()
        .cors().disable()
        .headers()
        .contentSecurityPolicy(jwtProperties.getContentSecurityPolicy())
        .and()
        .referrerPolicy(ReferrerPolicyHeaderWriter.ReferrerPolicy.STRICT_ORIGIN_WHEN_CROSS_ORIGIN)
        .and()
        .frameOptions().sameOrigin()
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests()
        .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
        .antMatchers("/swagger-ui/**").permitAll()
        .antMatchers("/api/**").authenticated()
        .and()
        .httpBasic()
        .and()
        .apply(securityConfigurerAdapter());
    return http.build();
  }

  private JwtConfigurer securityConfigurerAdapter() {
    return new JwtConfigurer(tokenProvider);
  }
}

