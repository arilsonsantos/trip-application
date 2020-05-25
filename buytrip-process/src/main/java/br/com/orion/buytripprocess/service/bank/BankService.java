package br.com.orion.buytripprocess.service.bank;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;

import br.com.orion.buytripprocess.dto.BankRetornoDto;
import br.com.orion.buytripprocess.dto.CompraChaveDto;
import br.com.orion.buytripprocess.dto.PagamentoDto;
import br.com.orion.buytripprocess.dto.PagamentoRetornoDto;

import feign.FeignException;
import lombok.AllArgsConstructor;

/**
 * BankService
 */
@Service
@AllArgsConstructor
public class BankService {

    IBankClientService clientService;
    ObjectMapper mapper;

    public PagamentoRetornoDto pagar(CompraChaveDto compraChaveJson) throws IOException {
        PagamentoDto json = new PagamentoDto();
        json.setNumeroCartao(compraChaveJson.getCompraDto().getNumeroCartao());
        json.setCodigoSeguranca(compraChaveJson.getCompraDto().getCodigoSeguranca());
        json.setValorCompra(compraChaveJson.getCompraDto().getValorPassagem());

        try {
            final var executaPagamento = clientService.pagamento(json);
            return new PagamentoRetornoDto(executaPagamento.getBody().getMensagem(), true);
        } catch (FeignException ex) {
            BankRetornoDto obj = mapper.readValue(ex.contentUTF8(), BankRetornoDto.class);
            return new PagamentoRetornoDto(obj.getMensagem(), false);
        }
    }

}