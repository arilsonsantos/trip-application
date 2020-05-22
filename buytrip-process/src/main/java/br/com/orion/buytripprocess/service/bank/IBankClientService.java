package br.com.orion.buytripprocess.service.bank;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.orion.buytripprocess.dto.BankRetornoDto;
import br.com.orion.buytripprocess.dto.PagamentoDto;

@FeignClient(url = "${bank.link}", name = "bank")
public interface IBankClientService {

    @GetMapping
    ResponseEntity<BankRetornoDto> pagamento(@RequestBody PagamentoDto pagamento);

}