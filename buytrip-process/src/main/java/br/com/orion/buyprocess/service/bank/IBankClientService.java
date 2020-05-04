package br.com.orion.buyprocess.service.bank;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import br.com.orion.buyprocess.dto.BankRetornoDto;
import br.com.orion.buyprocess.dto.PagamentoJson;

@FeignClient(url = "${bank.link}", name = "bank")
public interface IBankClientService {


    @GetMapping
    ResponseEntity<BankRetornoDto> pagamento(@RequestBody PagamentoJson pagamento);

}