package kz.careerguidance.applicationapi.config.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "jwt",
    ignoreUnknownFields = false)
public class JwtProperties {

  private String secret;
  private String base64Secret;
  private long tokenValidityInSeconds;
  private long tokenValidityInSecondsForRememberMe;
  private String contentSecurityPolicy;
  private CorsConfiguration cors;

}
