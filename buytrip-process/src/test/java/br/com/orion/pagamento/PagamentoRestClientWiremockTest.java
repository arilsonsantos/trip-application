package br.com.orion.pagamento;

import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.cloud.contract.stubrunner.server.EnableStubRunnerServer;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties.StubsMode;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;
import br.com.orion.buyprocess.dto.PagamentoJson;
import br.com.orion.buyprocess.dto.RetornoDto;


@EnableStubRunnerServer
@ExtendWith(SpringExtension.class)
@AutoConfigureStubRunner(ids = "br.com.orion:bank:+:9995", stubsMode = StubsMode.LOCAL)
@DirtiesContext
public class PagamentoRestClientWiremockTest {

    private static final String PAGAMENTOS_API = "http://localhost:9995/pagamentos";


    @Test
    public void validate_pagamento_com_sucesso() {
        RestTemplate restTemplate = new RestTemplate();

        PagamentoJson json = new PagamentoJson();
        json.setNumeroCartao(12345678);
        json.setCodigoSeguranca(90);
        json.setValorCompra(new BigDecimal(50));

        HttpHeaders headers = new HttpHeaders();
        List<Charset> charsetUtf8 = Collections.singletonList(StandardCharsets.UTF_8);

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAcceptCharset(charsetUtf8);
        HttpEntity<PagamentoJson> entity = new HttpEntity<PagamentoJson>(json, headers);

        ResponseEntity<RetornoDto> retorno =
                restTemplate.postForEntity(PAGAMENTOS_API, entity, RetornoDto.class);

        Assertions.assertThat(retorno.getStatusCode().value()).isEqualTo(200);
        Assertions.assertThat(retorno.getBody().getMensagem()).isEqualTo("Pagamento registrado com sucesso");
    }



    @Test
    public void validate_sem_saldo_suficiente() {
        TestRestTemplate restTemplate = new TestRestTemplate();

        PagamentoJson json = new PagamentoJson();
        json.setNumeroCartao(12345678);
        json.setCodigoSeguranca(90);
        json.setValorCompra(new BigDecimal(100));

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PagamentoJson> entity = new HttpEntity<PagamentoJson>(json, headers);

        ResponseEntity<RetornoDto> retorno =
                restTemplate.postForEntity(PAGAMENTOS_API, entity, RetornoDto.class);

        Assertions.assertThat(retorno.getStatusCode().value()).isEqualTo(406);
        Assertions.assertThat(retorno.getBody().getMensagem()).isEqualTo("Não há saldo suficiente");
    }

    @Test
    public void validate_numero_do_cartao_invalido() {
        TestRestTemplate restTemplate = new TestRestTemplate();

        PagamentoJson json = new PagamentoJson();
        json.setNumeroCartao(123);
        json.setCodigoSeguranca(90);
        json.setValorCompra(new BigDecimal(10));

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PagamentoJson> entity = new HttpEntity<PagamentoJson>(json, headers);

        ResponseEntity<RetornoDto> retorno =
                restTemplate.postForEntity(PAGAMENTOS_API, entity, RetornoDto.class);

        Assertions.assertThat(retorno.getStatusCode().value()).isEqualTo(406);
        Assertions.assertThat(retorno.getBody().getMensagem()).isEqualTo("Cartão inválido");
    }

    @Test
    public void validate_numero_do_cartao_nulo() {
        TestRestTemplate restTemplate = new TestRestTemplate();

        PagamentoJson json = new PagamentoJson();
        json.setNumeroCartao(null);
        json.setCodigoSeguranca(90);
        json.setValorCompra(new BigDecimal(20));

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PagamentoJson> entity = new HttpEntity<PagamentoJson>(json, headers);

        ResponseEntity<RetornoDto> retorno =
                restTemplate.postForEntity(PAGAMENTOS_API, entity, RetornoDto.class);

        Assertions.assertThat(retorno.getStatusCode().value()).isEqualTo(400);
        Assertions.assertThat(retorno.getBody().getMensagem()).isEqualTo("Argumentos inválidos");
    }

    @Test
    public void validate_codigo_de_seguranca_nulo() {
        TestRestTemplate restTemplate = new TestRestTemplate();

        PagamentoJson json = new PagamentoJson();
        json.setNumeroCartao(12345678);
        json.setCodigoSeguranca(null);
        json.setValorCompra(new BigDecimal(20));

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PagamentoJson> entity = new HttpEntity<PagamentoJson>(json, headers);

        ResponseEntity<RetornoDto> retorno =
                restTemplate.postForEntity(PAGAMENTOS_API, entity, RetornoDto.class);

        Assertions.assertThat(retorno.getStatusCode().value()).isEqualTo(400);
        Assertions.assertThat(retorno.getBody().getMensagem()).isEqualTo("Argumentos inválidos");
    }

}
