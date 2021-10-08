package br.com.swar.snet.model.dto;

import br.com.swar.snet.model.enums.ItemEnum;
import lombok.Data;

@Data
public class ItemReport {
	
	private ItemEnum item;
	private Double media;
	
}
