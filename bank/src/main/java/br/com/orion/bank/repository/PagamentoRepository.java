package br.com.orion.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.orion.bank.model.Pagamento;

/**
 * PagmentoRepository
 */
@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long>{

    
}