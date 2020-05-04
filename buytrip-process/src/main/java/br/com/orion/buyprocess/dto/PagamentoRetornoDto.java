package br.com.orion.buyprocess.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * PagamentoRetorno
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

public class PagamentoRetornoDto {
    private String mensagem;
    private boolean pagamentoOK;

}