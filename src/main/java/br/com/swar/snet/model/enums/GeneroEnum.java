package br.com.swar.snet.model.enums;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GeneroEnum {
	M("Masculino"),
	F("Feminino"),
	O("Outro");
	
	private String descricao;
	
	@JsonCreator
    public static GeneroEnum fromString(String valor) {   
        return Arrays.stream(values()).filter(v -> v.getValorJson().equalsIgnoreCase(valor)).findFirst().orElse(null);
    }
    
    @JsonValue
    public String getValorJson() {
        return name();
    }	
}
