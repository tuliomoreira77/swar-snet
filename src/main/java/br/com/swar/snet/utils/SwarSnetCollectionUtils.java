package br.com.swar.snet.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.swar.snet.model.dto.Stackable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SwarSnetCollectionUtils {

	public static <T> List<T> toList(Iterable<T> iterable) {
		List<T> list = new ArrayList<>();
		for(var i : iterable) {
			list.add(i);
		}
		return list;
	}
	
	public static <T extends Stackable> List<T> toStackList(List<T> list) {
		Map<String, T> map = new HashMap<>();
		for(T element : list) {
			if(map.containsKey(element.getKey())) {
				map.get(element.getKey()).addQuantity(element.getQuantity());
			} else {
				map.put(element.getKey(), element);
			}
		}
		return new ArrayList<>(map.values());
	}
	
}
