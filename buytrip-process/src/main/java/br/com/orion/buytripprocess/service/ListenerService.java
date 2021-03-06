package br.com.orion.buytripprocess.service;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import br.com.orion.buytripprocess.config.CompraBinding.ICompraProcessChannel;
import br.com.orion.buytripprocess.config.PagamentoBinding.IPagamentoChannel;
import br.com.orion.buytripprocess.dto.CompraChaveDto;
import br.com.orion.buytripprocess.dto.CompraFinalizadaDto;
import br.com.orion.buytripprocess.dto.PagamentoRetornoDto;
import br.com.orion.buytripprocess.service.bank.BankService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ListenerService {

    private final BankService bank;
    private final ICompraProcessChannel compraProcessChannel;
    private final IPagamentoChannel pagamentoChannel;

    @HystrixCommand(fallbackMethod = "reliable")
    @StreamListener(ICompraProcessChannel.INPUT)
    public void readingList(String message) throws IOException {
        log.info("Mensagem recebida: {}", message);

        ObjectMapper mapper = new ObjectMapper();
        CompraChaveDto compraChaveJson = mapper.readValue(message, CompraChaveDto.class);
        PagamentoRetornoDto pg = bank.pagar(compraChaveJson);

        CompraFinalizadaDto compraFinalizadaJson = new CompraFinalizadaDto();
        compraFinalizadaJson.setCompraChaveDto(compraChaveJson);
        compraFinalizadaJson.setPagamentoOK(pg.isPagamentoOK());
        compraFinalizadaJson.setMensagem(pg.getMensagem());
        String jsonFinalizado = mapper.writeValueAsString(compraFinalizadaJson);

        pagamentoChannel.output().send(MessageBuilder.withPayload(jsonFinalizado).build());
        
        log.info(compraFinalizadaJson.getMensagem());
    }
    
    public void reliable(String message) {
        compraProcessChannel.output().send(MessageBuilder.withPayload(message).build());
    }

}
