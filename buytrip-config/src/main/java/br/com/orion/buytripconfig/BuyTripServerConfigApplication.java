package br.com.orion.buytripconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class BuyTripServerConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(BuyTripServerConfigApplication.class, args);
	}

}
