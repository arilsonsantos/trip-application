package br.com.orion.buytrip.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompraDto {

	@NotNull(message = "Código da passagem é obrigatório")
	private Integer codigoPassagem;

	@NotNull(message = "Número do cartão é obrigatório")
	private Integer numeroCartao;

	@NotNull(message = "Código de segurança do cartão é obrigatório")
	private Integer codigoSeguranca;

	@NotNull(message = "Valor da compra é obrigatório")
	private BigDecimal valorPassagem;

}
