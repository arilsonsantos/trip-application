package br.com.orion.buygateway.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.orion.buygateway.feignclient.IBuyTripInfo;
import lombok.RequiredArgsConstructor;

/**
 * PagamentoController
 */
@RestController
@RequestMapping("/buytripinfo")
@RequiredArgsConstructor
public class PagamentoController {

    private final IBuyTripInfo buyTripInfo;

    @GetMapping
    Map<String, String> info() {
        return buyTripInfo.info();
    }

}
