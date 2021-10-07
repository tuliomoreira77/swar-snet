package br.com.swar.snet.model.dto;

import java.util.List;

import lombok.Data;

@Data
public class ParteNegociacaoDto {
	
	private Long idRebelde;
	private List<ItemDto> items;
	
}
