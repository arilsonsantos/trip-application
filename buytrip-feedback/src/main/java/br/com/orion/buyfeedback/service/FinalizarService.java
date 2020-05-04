package br.com.orion.buyfeedback.service;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.orion.buyfeedback.dto.CompraFinalizadaJson;
import br.com.orion.buyfeedback.model.CompraRedis;
import br.com.orion.buyfeedback.repository.CompraRedisRepository;

/**
 * FinalizarService
 */
@Service
public class FinalizarService {

    @Autowired
    private CompraRedisRepository compraRedisRepository;

    @RabbitListener(queues = "${fila.finalizado}")
    public void onMessage(Message message) throws JsonParseException, JsonMappingException, IOException {

        String json = new String(message.getBody(), "UTF-8");

        System.out.println("Mensagem recebida:" + json);

        ObjectMapper mapper = new ObjectMapper();
        CompraFinalizadaJson compraChaveJson = mapper.readValue(json, CompraFinalizadaJson.class);

        CompraRedis credis = new CompraRedis();
        credis.setId(compraChaveJson.getCompraChaveJson().getChave());
        credis.setMensagem(compraChaveJson.getMensagem());
        credis.setNumeroCartao(compraChaveJson.getCompraChaveJson().getCompraJson().getNumeroCartao());
        credis.setValorPassagem(compraChaveJson.getCompraChaveJson().getCompraJson().getValorPassagem());
        credis.setCodigoPassagem(compraChaveJson.getCompraChaveJson().getCompraJson().getCodigoPassagem());
        credis.setPagamentoOk(compraChaveJson.isPagamentoOK());
        

        System.out.println("Gravando no redis....");
        compraRedisRepository.save(credis);
    }
}