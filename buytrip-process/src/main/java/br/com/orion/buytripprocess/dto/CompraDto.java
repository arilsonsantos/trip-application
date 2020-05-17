package br.com.orion.buytripprocess.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CompraJson
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompraDto {

	private Integer codigoPassagem;

	private Integer numeroCartao;

	private Integer codigoSeguranca;

	private BigDecimal valorPassagem;

}
