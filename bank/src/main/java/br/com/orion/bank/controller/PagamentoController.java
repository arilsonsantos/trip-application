package br.com.orion.bank.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.orion.bank.model.dto.PagamentoDto;
import br.com.orion.bank.model.dto.RetornoDto;
import br.com.orion.bank.service.PagamentoService;

import lombok.AllArgsConstructor;

/**
 * PagamentoController
 */
@RestController
@RequestMapping(path = "pagamentos")
@AllArgsConstructor
public class PagamentoController {
    
    private final PagamentoService service;

    @PostMapping
    public ResponseEntity<RetornoDto> pagamento(@Validated @RequestBody PagamentoDto pagamentoDto){
        service.pagamento(pagamentoDto);
        RetornoDto retorno = RetornoDto.builder().mensagem("Pagamento registrado com sucesso").build();
        
        return new ResponseEntity<>(retorno, HttpStatus.OK);
    }
    
}