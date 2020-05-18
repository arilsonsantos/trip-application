package br.com.orion.buytripprocess.config;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.SubscribableChannel;

import br.com.orion.buytripprocess.config.PagamentoBinding.IPagamentoChannel;


@Configuration
@EnableBinding(IPagamentoChannel.class)
public class PagamentoBinding {

    public interface IPagamentoChannel {
        String OUTPUT = "pagamentoOut";

        @Output(OUTPUT)
        SubscribableChannel output();
        
    }

}