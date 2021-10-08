package br.com.swar.snet.test;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import br.com.swar.snet.model.dto.ItemDto;
import br.com.swar.snet.model.enums.ItemEnum;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestUtils {
	
	public static List<ItemDto> getInventarioA() {
		List<ItemDto> list = new ArrayList<>();
		list.add(getItemDto(ItemEnum.ARMA, 3));
		list.add(getItemDto(ItemEnum.AGUA, 2));
		return list;
	}
	
	public static List<ItemDto> getInventarioB() {
		List<ItemDto> list = new ArrayList<>();
		list.add(getItemDto(ItemEnum.COMIDA, 10));
		return list;
	}
	
	public static List<ItemDto> getTrocaA() {
		List<ItemDto> list = new ArrayList<>();
		list.add(getItemDto(ItemEnum.ARMA, 1));
		return list;
	}
	
	public static List<ItemDto> getTrocaB() {
		List<ItemDto> list = new ArrayList<>();
		list.add(getItemDto(ItemEnum.COMIDA, 4));
		return list;
	}
	
	public static ItemDto getItemDto(ItemEnum item, Integer quantidade) {
		var itemDto = new ItemDto();
		itemDto.setItem(item);
		itemDto.setQuantidade(quantidade);
		return itemDto;
	}
}
