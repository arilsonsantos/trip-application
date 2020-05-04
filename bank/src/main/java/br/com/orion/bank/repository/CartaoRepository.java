package br.com.orion.bank.repository;

import java.math.BigDecimal;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import br.com.orion.bank.model.Cartao;

/**
 * CartaoRepository
 */
@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Long> {

    @Query("from Cartao c where c.numeroCartao = ?1 and c.codigoSeguranca = ?2")
    Optional<Cartao> findCartao(Integer numeroCartao, Integer codigoSegurancaCartao);

    @Modifying
    @Query("update Cartao set valorCredito = (valorCredito - ?3) where numeroCartao = ?1 and codigoSeguranca = ?2")
    void atualizaSaldo(Integer numeroCartao, Integer codigoSegurancaCartao, BigDecimal valorCompra);

    @Query("select count(*) from Cartao c where c.numeroCartao = ?1 and c.codigoSeguranca = ?2")
    Integer fndCartaoValido(Integer numeroCartao, Integer codigoSegurancaCartao);

}