package com.shortenurl;

import com.shortenurl.model.APIKeyEntity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import com.shortenurl.repository.APIKeyRepository;

@SpringBootApplication
@EnableCaching
@EnableScheduling //required for @Scheduled to work
public class UrlShortenerApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrlShortenerApplication.class, args);
	}

	@Bean
	public CommandLineRunner seedApiKey(APIKeyRepository apiKeyRepository) {
		return args -> {
			if (apiKeyRepository.findByApiKey("test-1234").isEmpty()) {
				APIKeyEntity apiKey = new APIKeyEntity();
				apiKey.setApiKey("test-1234");
				apiKey.setActive(true);
				apiKey.setOwner("default seeder");
				apiKeyRepository.save(apiKey);
				System.out.println("✅ Sample API Key seeded: test-1234");
			} else {
				System.out.println("ℹ️ Sample API Key already exists");
			}
		};
	}
}
