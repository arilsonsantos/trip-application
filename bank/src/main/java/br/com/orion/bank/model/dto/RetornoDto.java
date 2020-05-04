package br.com.orion.bank.model.dto;

import java.util.Map;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * RetornoDto
 */
@Builder
@JsonInclude(Include.NON_NULL)
public class RetornoDto {

    @Getter
    @Setter
    private String mensagem;

    @Getter
    Map<String, String> erros;

}