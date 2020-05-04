package br.com.orion.bank.contracts.pagamento.ok;

import static org.mockito.ArgumentMatchers.anyInt;
import java.math.BigDecimal;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import br.com.orion.bank.contracts.pagamento.PagamentoControllerBaseTest;
import br.com.orion.bank.controller.PagamentoController;
import br.com.orion.bank.model.Cartao;
import br.com.orion.bank.service.CartaoService;
import br.com.orion.bank.service.PagamentoService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

public class PagamentoControllerOk extends PagamentoControllerBaseTest {

    @BeforeEach
	void setUp(){
        cartaoService = new CartaoService(cartaoRepository);
		pagamentoService = new PagamentoService(pagamentoRepository, cartaoService);
        RestAssuredMockMvc.standaloneSetup(new PagamentoController(pagamentoService));

        Mockito.when(cartaoRepository.fndCartaoValido(anyInt(), anyInt())).thenReturn(1);
        Mockito.when(cartaoRepository.findCartao(anyInt(), anyInt()))
        .thenAnswer((InvocationOnMock invocation) -> {
            Cartao cartao = Cartao.builder()
                .numeroCartao(invocation.getArgument(0))
                    .codigoSeguranca(invocation.getArgument(1))
                    .valorCredito(new BigDecimal(5000))
                    .build();
            return Optional.of(cartao);
        });
	}
}