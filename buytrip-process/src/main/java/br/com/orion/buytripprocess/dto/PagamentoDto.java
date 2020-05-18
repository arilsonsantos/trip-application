package br.com.orion.buytripprocess.dto;

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
public class PagamentoDto {

	private Integer numeroCartao;
	private Integer codigoSeguranca;
	private BigDecimal valorCompra;

}