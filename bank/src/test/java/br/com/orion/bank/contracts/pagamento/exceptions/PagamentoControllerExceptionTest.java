package br.com.orion.bank.contracts.pagamento.exceptions;

import static org.mockito.ArgumentMatchers.anyInt;
import java.math.BigDecimal;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import br.com.orion.bank.contracts.pagamento.PagamentoControllerBaseTest;
import br.com.orion.bank.controller.PagamentoController;
import br.com.orion.bank.exceptions.handler.ApiExceptionHandler;
import br.com.orion.bank.model.Cartao;
import br.com.orion.bank.service.CartaoService;
import br.com.orion.bank.service.PagamentoService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

public class PagamentoControllerExceptionTest
        extends PagamentoControllerBaseTest {

    @BeforeEach
    void setUp() {
        cartaoService = new CartaoService(cartaoRepository);
        pagamentoService = new PagamentoService(pagamentoRepository, cartaoService);
        
        Cartao cartao = Cartao.builder().numeroCartao(12345678).codigoSeguranca(90)
                .valorCredito(BigDecimal.ZERO).build();

        Mockito.when(cartaoRepository.findCartao(anyInt(), anyInt()))
                .thenAnswer((InvocationOnMock invocation) -> {
                    if (!((Integer) invocation.getArgument(0)).equals(cartao.getNumeroCartao())) {
                        return Optional.empty();
                    }
                    return Optional.of(cartao);
                });

        var mvcBuilder = MockMvcBuilders.standaloneSetup(new PagamentoController(pagamentoService))
                .setControllerAdvice(new ApiExceptionHandler());
                
        RestAssuredMockMvc.standaloneSetup(mvcBuilder);
    }

}
