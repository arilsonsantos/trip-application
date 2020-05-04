package br.com.orion.buyfeedback.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.orion.buyfeedback.exceptions.NaoFinalizadoException;
import br.com.orion.buyfeedback.model.CompraRedis;
import br.com.orion.buyfeedback.repository.CompraRedisRepository;

/**
 * CompraController
 */
@RestController
@RequestMapping
public class CompraController {

    @Autowired
	private CompraRedisRepository compraRedisRepository;
	
    @GetMapping("/{chave}")
	public CompraRedis status(@PathVariable("chave") String chave){
		
		Optional<CompraRedis> compra = compraRedisRepository.findById(chave);
		
		if( !compra.isPresent()){
			throw new NaoFinalizadoException("A compra não foi finalizada");
		}
		
		return compra.get();
	}
	
    @GetMapping("/meunome")
	public String status(){
		return "Estou na máquina do: Arilson";
	}
}