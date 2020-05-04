package br.com.orion.buyprocess.service.bank;

import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import br.com.orion.buyprocess.dto.BankRetornoDto;
import br.com.orion.buyprocess.dto.CompraChaveDto;
import br.com.orion.buyprocess.dto.PagamentoJson;
import br.com.orion.buyprocess.dto.PagamentoRetornoDto;

/**
 * BankService
 */
@Service
public class BankService {

	@Value("${bank.link}")
	private String link;

	public PagamentoRetornoDto pagar(CompraChaveDto compraChaveJson) throws IOException {
		
		PagamentoJson json = new PagamentoJson();
		json.setNumeroCartao(compraChaveJson.getCompraDto().getNumeroCartao());
		json.setCodigoSeguranca(compraChaveJson.getCompraDto().getCodigoSeguranca());
		json.setValorCompra(compraChaveJson.getCompraDto().getValorPassagem());
		
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<PagamentoJson> entity = new HttpEntity<PagamentoJson>(json, headers);

		try {
			ResponseEntity<BankRetornoDto> bankRetorno = restTemplate.exchange(link, HttpMethod.POST, entity, BankRetornoDto.class);
			return new PagamentoRetornoDto(bankRetorno.getBody().getMensagem(), true);
		}catch(HttpClientErrorException ex){
			if( ex.getStatusCode() == HttpStatus.BAD_REQUEST ) {
				ObjectMapper mapper = new ObjectMapper();
				BankRetornoDto obj = mapper.readValue(ex.getResponseBodyAsString(), BankRetornoDto.class);
				return new PagamentoRetornoDto(obj.getMensagem(), false);
			}
			throw ex;
		}catch (RuntimeException ex) {
			throw ex;
		}

	}

}