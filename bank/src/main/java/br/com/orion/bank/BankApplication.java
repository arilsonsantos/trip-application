package br.com.orion.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankApplication {

	public static void main(String[] args) {
		//System.setProperty("spring.devtools.add-properties", "false");
		SpringApplication.run(BankApplication.class, args);
	}

}
