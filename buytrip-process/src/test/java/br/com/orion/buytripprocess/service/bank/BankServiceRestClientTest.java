package br.com.orion.buytripprocess.service.bank;

import static java.util.Objects.requireNonNull;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.cloud.contract.stubrunner.server.EnableStubRunnerServer;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerPort;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties.StubsMode;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.orion.buytripprocess.dto.PagamentoDto;
import br.com.orion.buytripprocess.dto.RetornoDto;

@ActiveProfiles("test")
@EnableStubRunnerServer
@ExtendWith(SpringExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
@AutoConfigureStubRunner(ids = "br.com.orion:bank:+", stubsMode = StubsMode.LOCAL)
public class BankServiceRestClientTest {

    private static String PAGAMENTOS_API;
    private TestRestTemplate restTemplate;

    @StubRunnerPort("bank")
    private int producerPort;


    @BeforeAll
    public void setUp() {
        restTemplate = new TestRestTemplate();
        PAGAMENTOS_API = "http://localhost:" + producerPort + "/pagamentos";
    }

    @Test
    public void validate_pagamento_com_sucesso() {
        PagamentoDto json = new PagamentoDto();
        json.setNumeroCartao(12345678);
        json.setCodigoSeguranca(90);
        json.setValorCompra(new BigDecimal(50));

        HttpHeaders headers = new HttpHeaders();
        List<Charset> charsetUtf8 = Collections.singletonList(StandardCharsets.UTF_8);

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAcceptCharset(charsetUtf8);
        HttpEntity<PagamentoDto> entity = new HttpEntity<>(json, headers);
        
        ResponseEntity<RetornoDto> retorno =
                restTemplate.postForEntity(PAGAMENTOS_API, entity, RetornoDto.class);

        assertThat(retorno.getStatusCode().value()).isEqualTo(200);
        assertThat(requireNonNull(retorno.getBody()).getMensagem()).isEqualTo("Pagamento registrado com sucesso");
        
    }


    @Test
    public void validate_sem_saldo_suficiente() {
        PagamentoDto json = new PagamentoDto();
        json.setNumeroCartao(12345678);
        json.setCodigoSeguranca(90);
        json.setValorCompra(new BigDecimal(100));

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PagamentoDto> entity = new HttpEntity<>(json, headers);

        ResponseEntity<RetornoDto> retorno =
                restTemplate.postForEntity(PAGAMENTOS_API, entity, RetornoDto.class);

        assertThat(retorno.getStatusCode().value()).isEqualTo(406);
        assertThat(retorno.getBody().getMensagem()).isEqualTo("Não há saldo suficiente");
    }

    @Test
    public void validate_numero_do_cartao_invalido() {
        PagamentoDto json = new PagamentoDto();
        json.setNumeroCartao(123);
        json.setCodigoSeguranca(90);
        json.setValorCompra(new BigDecimal(10));

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PagamentoDto> entity = new HttpEntity<>(json, headers);

        ResponseEntity<RetornoDto> retorno =
                restTemplate.postForEntity(PAGAMENTOS_API, entity, RetornoDto.class);

        assertThat(retorno.getStatusCode().value()).isEqualTo(406);
        assertThat(retorno.getBody().getMensagem()).isEqualTo("Cartão inválido");
    }

    @Test
    public void validate_numero_do_cartao_nulo() {
        PagamentoDto json = new PagamentoDto();
        json.setNumeroCartao(null);
        json.setCodigoSeguranca(90);
        json.setValorCompra(new BigDecimal(20));

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PagamentoDto> entity = new HttpEntity<>(json, headers);

        ResponseEntity<RetornoDto> retorno =
                restTemplate.postForEntity(PAGAMENTOS_API, entity, RetornoDto.class);

        assertThat(retorno.getStatusCode().value()).isEqualTo(400);
        assertThat(retorno.getBody().getMensagem()).isEqualTo("Argumentos inválidos");
        assertThat(retorno.getBody().getErros().size()).isEqualTo(1);
        assertThat(retorno.getBody().getErros().get("numeroCartao")).isEqualTo("Número do cartão não pode ser nulo");
    }

    @Test
    public void validate_codigo_de_seguranca_nulo() {
        PagamentoDto json = new PagamentoDto();
        json.setNumeroCartao(12345678);
        json.setCodigoSeguranca(null);
        json.setValorCompra(new BigDecimal(20));

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PagamentoDto> entity = new HttpEntity<>(json, headers);

        ResponseEntity<RetornoDto> retorno =
                restTemplate.postForEntity(PAGAMENTOS_API, entity, RetornoDto.class);

        assertThat(retorno.getStatusCode().value()).isEqualTo(400);
        assertThat(retorno.getBody().getMensagem()).isEqualTo("Argumentos inválidos");
        assertThat(retorno.getBody().getErros().size()).isGreaterThan(0);
        assertThat(retorno.getBody().getErros().get("codigoSeguranca")).isEqualTo("Código de segurança não pode ser nulo");

    }

}