package br.com.orion.bank.contracts.pagamento;

import br.com.orion.bank.controller.PagamentoController;
import br.com.orion.bank.exceptions.handler.ApiExceptionHandler;
import br.com.orion.bank.model.Cartao;
import br.com.orion.bank.repository.CartaoRepository;
import br.com.orion.bank.repository.PagamentoRepository;
import br.com.orion.bank.service.CartaoService;
import br.com.orion.bank.service.PagamentoService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class PagamentoControllerBaseTest {

    @MockBean
    protected PagamentoRepository pagamentoRepository;

    @MockBean
    protected CartaoRepository cartaoRepository;

    protected PagamentoService pagamentoService;

    protected CartaoService cartaoService;

    @BeforeEach
    void setUp() {
        cartaoService = new CartaoService(cartaoRepository);
        pagamentoService = new PagamentoService(pagamentoRepository, cartaoService);

        Cartao cartao = Cartao.builder().numeroCartao(12345678).codigoSeguranca(90)
                .valorCredito(new BigDecimal(50)).build();

        Mockito.when(cartaoRepository.findCartao(anyInt(), anyInt()))
                .thenAnswer((InvocationOnMock invocation) -> {
                    if (!(invocation.getArgument(0))
                            .equals(cartao.getNumeroCartao())) {
                        return Optional.empty();
                    }
                    return Optional.of(cartao);
                });

        var mvcBuilder = MockMvcBuilders
                .standaloneSetup(new PagamentoController(pagamentoService))
                .setControllerAdvice(new ApiExceptionHandler());

        RestAssuredMockMvc.standaloneSetup(mvcBuilder);
    }

}
