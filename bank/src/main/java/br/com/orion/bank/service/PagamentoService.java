package br.com.orion.bank.service;

import org.springframework.stereotype.Service;
import br.com.orion.bank.exceptions.PagamentoException;
import br.com.orion.bank.model.Pagamento;
import br.com.orion.bank.model.dto.PagamentoDto;
import br.com.orion.bank.repository.PagamentoRepository;
import lombok.AllArgsConstructor;

/**
 * PagamentoService
 */
@Service
@AllArgsConstructor
public class PagamentoService {

    private final PagamentoRepository repository;

    private final CartaoService service;

    public void pagamento(PagamentoDto dto) {
        if (!service.isSaldoSuficiente(dto.getNumeroCartao(), dto.getCodigoSeguranca(), dto.getValorCompra())) {
            throw new PagamentoException("Não há saldo suficiente");
        }

        Pagamento pagamento = Pagamento.builder().valorCompra(dto.getValorCompra())
                .cartao(service.getCartao(dto.getNumeroCartao(), dto.getCodigoSeguranca())).build();

        repository.save(pagamento);
        service.atualizaSaldo(dto.getNumeroCartao(), dto.getCodigoSeguranca(), dto.getValorCompra());
    }
}