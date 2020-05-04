package br.com.orion.buyprocess.service;

import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import br.com.orion.buyprocess.config.CompraBinding.ICompraProcessChannel;
import br.com.orion.buyprocess.config.PagamentoBinding.IPagamentoChannel;
import br.com.orion.buyprocess.dto.CompraChaveDto;
import br.com.orion.buyprocess.dto.CompraFinalizadaDto;
import br.com.orion.buyprocess.dto.PagamentoRetornoDto;
import br.com.orion.buyprocess.service.bank.BankService;
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
        String json = message;

        log.info("Mensagem recebida: {}", json);

        ObjectMapper mapper = new ObjectMapper();
        CompraChaveDto compraChaveJson = mapper.readValue(json, CompraChaveDto.class);

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
