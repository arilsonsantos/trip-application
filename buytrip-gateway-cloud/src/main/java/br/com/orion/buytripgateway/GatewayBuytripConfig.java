package br.com.orion.buytripgateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayBuytripConfig {

	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
		return builder.routes()
			.route(r -> r.path("/")
			.filters(f -> f.hystrix(x -> x.setName("cmd").setFallbackUri("forward:/fallBack")))
			.uri("lb://buytrip"))
			.build();
		}

}