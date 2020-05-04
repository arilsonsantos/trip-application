package br.com.orion.buyprocess.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * PagamentoJson
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoJson {

	private Integer numeroCartao;
	private Integer codigoSeguranca;
	private BigDecimal valorCompra;

}