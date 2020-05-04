package br.com.orion.bank.service;

import java.math.BigDecimal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.orion.bank.exceptions.PagamentoException;
import br.com.orion.bank.model.Cartao;
import br.com.orion.bank.repository.CartaoRepository;
import lombok.RequiredArgsConstructor;

/**
 * CartaoService
 */
@Service
@RequiredArgsConstructor
public class CartaoService {

    private final CartaoRepository repository;

    public Cartao getCartao(Integer numeroCartao, Integer codigoSeguranca) {
        return repository.findCartao(numeroCartao, codigoSeguranca)
                .orElseThrow(() -> new PagamentoException("Cartão inválido"));
    }

    @Transactional
    public void atualizaSaldo(Integer numeroCartao, Integer codigoSeguranca,
            BigDecimal valorCompra) {
        repository.atualizaSaldo(numeroCartao, codigoSeguranca, valorCompra);
    }

    public boolean isSaldoSuficiente(Integer numeroCartao, Integer codigoSeguranca,
            BigDecimal valorCompra) {
                
        getCartao(numeroCartao, codigoSeguranca);
        Cartao cartao = getCartao(numeroCartao, codigoSeguranca);
        return cartao.getValorCredito().compareTo(valorCompra) >= 0 ? true : false;
    }

}
