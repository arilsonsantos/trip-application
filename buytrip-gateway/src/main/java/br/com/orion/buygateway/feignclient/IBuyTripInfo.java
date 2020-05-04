package br.com.orion.buygateway.feignclient;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("buytrip")
public interface IBuyTripInfo {

    @GetMapping("/actuator/info")
    Map<String, String> info();

}