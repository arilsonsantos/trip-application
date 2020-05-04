package br.com.orion.buytrip.config;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.SubscribableChannel;

@Configuration
@EnableBinding(CompraBinding.ICompraOutputChannel.class)
public class CompraBinding {

    public static interface ICompraOutputChannel {
        String OUTPUT = "compraOut";

        @Output(ICompraOutputChannel.OUTPUT)
        public SubscribableChannel compra();
    }

}