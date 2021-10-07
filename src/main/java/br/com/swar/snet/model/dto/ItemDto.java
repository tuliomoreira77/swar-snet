package br.com.swar.snet.model.dto;

import br.com.swar.snet.model.domain.Item;
import br.com.swar.snet.model.enums.ItemEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemDto implements Stackable {
	
	private ItemEnum item;
	private Integer quantidade;
	
	public ItemDto(Item item) {
		this.item = ItemEnum.fromString(item.getNome());
		this.quantidade = 1;
	}

	@Override
	public String getKey() {
		return this.item.name();
	}

	@Override
	public Integer getQuantity() {
		return this.quantidade;
	}

	@Override
	public void addQuantity(Integer quantity) {
		this.quantidade = this.quantidade + quantity;
	}
}
