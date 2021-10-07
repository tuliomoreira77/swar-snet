package br.com.swar.snet.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiErrorResponse {
	
	private Integer code;
	private String message;
	private String rawMessage;
	
}
