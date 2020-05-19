package br.com.orion.buytrip.controller;


import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.orion.buytrip.config.CompraBinding;
import br.com.orion.buytrip.dto.CompraChaveDto;
import br.com.orion.buytrip.dto.CompraDto;
import br.com.orion.buytrip.dto.RetornoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping(path = "/")
@RequiredArgsConstructor
public class CompraController {

    //private final Source source;
    private final CompraBinding.ICompraOutputChannel compraChannel;

    @PostMapping
    public ResponseEntity<RetornoDto> pagamento(@Valid @NotNull @RequestBody CompraDto compraDto)
            throws Exception {

        CompraChaveDto compraChaveDto = new CompraChaveDto();
        compraChaveDto.setCompraDto(compraDto);
        compraChaveDto.setChave(UUID.randomUUID().toString());

        ObjectMapper obj = new ObjectMapper();
        String json = obj.writeValueAsString(compraChaveDto);

        compraChannel.compra().send(MessageBuilder.withPayload(json).build());

        RetornoDto retorno = new RetornoDto();
        retorno.setMensagem("Compra registrada com sucesso. Aguarda a confirmação do pagamento.");
        retorno.setChavePesquisa(compraChaveDto.getChave());

        log.info(retorno.getChavePesquisa());

        return new ResponseEntity<>(retorno, HttpStatus.OK);
    }

}
