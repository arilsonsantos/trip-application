package br.com.orion.buytrip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@EnableDiscoveryClient
@SpringBootApplication
public class BuyTripApplication {

	public static void main(String[] args) {
		SpringApplication.run(BuyTripApplication.class, args);
		
	}

	@Bean
	CommonsRequestLoggingFilter commonsRequestLoggingFilter(){
		var filter = new CommonsRequestLoggingFilter();

		filter.setIncludeClientInfo(true);
		filter.setIncludeQueryString(true);
		filter.setIncludeHeaders(true);
		filter.setIncludeClientInfo(true);

		return filter;
	}

}
