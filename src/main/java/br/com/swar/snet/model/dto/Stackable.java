package br.com.swar.snet.model.dto;

public interface Stackable {
	
	String getKey();
	Integer getQuantity();
	void addQuantity(Integer quantity);
	
}
