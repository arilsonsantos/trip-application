package br.com.orion.buytripconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class BuyTripServerConfigApplication {

	public static void main(String[] args) {
		System.setProperty("spring.devtools.add-properties", "false");
		SpringApplication.run(BuyTripServerConfigApplication.class, args);
	}

}
