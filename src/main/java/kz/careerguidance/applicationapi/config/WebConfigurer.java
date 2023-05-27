package kz.careerguidance.applicationapi.config;

import kz.careerguidance.applicationapi.config.jwt.JwtProperties;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class WebConfigurer {

  Logger log = LoggerFactory.getLogger(WebConfigurer.class);

  private final JwtProperties jwtProperties;

  public WebConfigurer(JwtProperties jwtProperties) {
    this.jwtProperties = jwtProperties;
  }

  @Bean
  public CorsFilter corsFilter() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = jwtProperties.getCors();
    if (!CollectionUtils.isEmpty(config.getAllowedOrigins()) || !CollectionUtils.isEmpty(
        config.getAllowedOriginPatterns())) {
      log.debug("Registering CORS filter");
      source.registerCorsConfiguration("/**", config);
//      source.registerCorsConfiguration("/api/**", config);
//      source.registerCorsConfiguration("/api/management/**", config);
//      source.registerCorsConfiguration("/api/v3/api-docs", config);
//      source.registerCorsConfiguration("/api/swagger-ui/**", config);
    }
    return new CorsFilter(source);
  }

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }
}
