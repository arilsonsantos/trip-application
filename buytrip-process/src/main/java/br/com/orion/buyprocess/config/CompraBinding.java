package br.com.orion.buyprocess.config;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.SubscribableChannel;

import br.com.orion.buyprocess.config.CompraBinding.ICompraProcessChannel;

@Configuration
@EnableBinding(ICompraProcessChannel.class)
public class CompraBinding {

    public interface ICompraProcessChannel {
        String INPUT = "compraIn";
        String OUTPUT = "compraOut";

        @Input(INPUT)
        SubscribableChannel input();

        @Output(OUTPUT)
        SubscribableChannel output();
    }
}