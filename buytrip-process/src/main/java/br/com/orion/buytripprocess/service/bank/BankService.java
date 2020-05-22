package br.com.orion.buytripprocess.service.bank;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import feign.FeignException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import br.com.orion.buytripprocess.dto.BankRetornoDto;
import br.com.orion.buytripprocess.dto.CompraChaveDto;
import br.com.orion.buytripprocess.dto.PagamentoDto;
import br.com.orion.buytripprocess.dto.PagamentoRetornoDto;

/**
 * BankService
 */
@Service
public class BankService {

    @Value("${bank.link}")
    private String link;

    @Autowired
    IBankClientService clientService;

    public PagamentoRetornoDto pagar(CompraChaveDto compraChaveJson) throws IOException {
        PagamentoDto json = new PagamentoDto();
        json.setNumeroCartao(compraChaveJson.getCompraDto().getNumeroCartao());
        json.setCodigoSeguranca(compraChaveJson.getCompraDto().getCodigoSeguranca());
        json.setValorCompra(compraChaveJson.getCompraDto().getValorPassagem());

        ObjectMapper mapper = new ObjectMapper();
        try {
            final var executaPagamento = clientService.pagamento(json);
            return new PagamentoRetornoDto(executaPagamento.getBody().getMensagem(), true);
        } catch (FeignException ex) {
            BankRetornoDto obj = mapper.readValue(ex.contentUTF8(), BankRetornoDto.class);
            return new PagamentoRetornoDto(obj.getMensagem(), false);
        }
    }
}