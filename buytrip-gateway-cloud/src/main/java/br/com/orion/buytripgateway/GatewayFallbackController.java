package br.com.orion.buytripgateway;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;


@Slf4j
@RestController
public class GatewayFallbackController {

    @RequestMapping("/fallBack")
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public Mono<String> serviceFallBack() {
        log.warn("O serviço não está respondendo");
        return Mono.just("Buytrip Service is taking too long to respond or is down. Please try again later");
    }
}