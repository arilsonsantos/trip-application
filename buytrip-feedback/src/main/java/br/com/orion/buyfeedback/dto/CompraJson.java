package br.com.orion.buyfeedback.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CompraJson
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompraJson {

	private Integer codigoPassagem;
	
	private Integer numeroCartao;
	
	private Integer codigoSegurancaCartao;
	
	private BigDecimal valorPassagem;

}