package br.com.orion.buyfeedback.config;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.SubscribableChannel;

import br.com.orion.buyfeedback.config.PagamentoBinding.IPagamentoChannel;

@Configuration
@EnableBinding(IPagamentoChannel.class)
public class PagamentoBinding {

    public interface IPagamentoChannel {
        String INPUT = "pagamentoIn";

        @Input(INPUT)
        SubscribableChannel input();
    }
    
}