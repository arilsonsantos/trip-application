package br.com.orion.buytripprocess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableCircuitBreaker
@SpringBootApplication
public class BuytripProcessApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(BuytripProcessApplication.class, args);
	}

}
