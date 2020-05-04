package br.com.orion.bank.model.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * PagamentoDto
 */
@Getter
@Setter
public class PagamentoDto {

    @NotNull(message = "Número do cartão não pode ser nulo")
    private Integer numeroCartao;

    @NotNull(message = "Código de segurança não pode ser nulo")
    private Integer codigoSeguranca;

    @NotNull(message = "Valor da compra é obrigatório")
    private BigDecimal valorCompra;


}