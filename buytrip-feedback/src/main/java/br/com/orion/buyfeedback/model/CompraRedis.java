package br.com.orion.buyfeedback.model;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CompraRedis
 */
@RedisHash("compra")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompraRedis {

    @Id
    private String id;
    private String mensagem;
    private Integer codigoPassagem;
    private Integer numeroCartao;
    private BigDecimal valorPassagem;
    private boolean pagamentoOk;
}