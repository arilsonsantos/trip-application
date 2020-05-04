package br.com.orion.buyfeedback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@EnableRedisRepositories
@SpringBootApplication
public class BuyFeedbackApplication {

	public static void main(String[] args) {
		SpringApplication.run(BuyFeedbackApplication.class, args);
	}

}
