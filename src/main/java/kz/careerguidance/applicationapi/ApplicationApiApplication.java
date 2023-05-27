package kz.careerguidance.applicationapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class ApplicationApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationApiApplication.class, args);
	}

}
