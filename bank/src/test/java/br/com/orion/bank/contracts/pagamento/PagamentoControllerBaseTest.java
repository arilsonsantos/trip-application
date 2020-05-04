package br.com.orion.bank.contracts.pagamento;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import br.com.orion.bank.repository.CartaoRepository;
import br.com.orion.bank.repository.PagamentoRepository;
import br.com.orion.bank.service.CartaoService;
import br.com.orion.bank.service.PagamentoService;

@ExtendWith(SpringExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
public class PagamentoControllerBaseTest {

    @MockBean
    protected PagamentoRepository pagamentoRepository;

    @MockBean
    protected CartaoRepository cartaoRepository;

    protected PagamentoService pagamentoService;

    protected CartaoService cartaoService;

    @BeforeAll
    void commonSetUp() {
        cartaoService = new CartaoService(cartaoRepository);
        pagamentoService = new PagamentoService(pagamentoRepository, cartaoService);
    }

}
