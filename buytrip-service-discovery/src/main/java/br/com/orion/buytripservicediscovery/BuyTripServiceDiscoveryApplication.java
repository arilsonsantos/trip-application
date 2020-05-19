package br.com.orion.buytripservicediscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class BuyTripServiceDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(BuyTripServiceDiscoveryApplication.class, args);
	}

}
