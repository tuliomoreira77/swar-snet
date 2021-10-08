package br.com.swar.snet.service.validator;

import java.util.List;

public abstract class BaseValidator {

	public boolean validadeList(List<?> list) {
		return list != null;
	}
	
	public boolean validadeNullOrEmpty(Double d) {
		return d != null;
	}
	
	public boolean validadeNullOrPositive(Integer i) {
		return i != null && i > 0;
	}
	
	public boolean validadeNullOrEmpty(String str) {
		return str != null && !str.isEmpty();
	}
	
	public boolean validadeNullOrEmpty(Object obj) {
		return obj != null;
	}
	
}
