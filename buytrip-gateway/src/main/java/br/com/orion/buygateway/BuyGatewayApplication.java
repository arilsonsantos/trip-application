package br.com.orion.buygateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaClient
@EnableZuulProxy
@EnableFeignClients
@SpringBootApplication
public class BuyGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(BuyGatewayApplication.class, args);
	}


}
