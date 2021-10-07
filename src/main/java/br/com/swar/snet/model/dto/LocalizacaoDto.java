package br.com.swar.snet.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocalizacaoDto {
	
	private Double latitude;
	
	private Double longitude;
	
	private String nomeLocalizacao;
}
