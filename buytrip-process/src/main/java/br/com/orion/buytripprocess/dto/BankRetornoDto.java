package br.com.orion.buytripprocess.dto;

import com.netflix.discovery.provider.ISerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * RetornoJson
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankRetornoDto  {

	private String mensagem;

}
