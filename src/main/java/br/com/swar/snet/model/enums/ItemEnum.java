package br.com.swar.snet.model.enums;

import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ItemEnum {
	
	ARMA("Arma", 4),
	MUNICAO("Munição", 3),
	AGUA("Água", 2),
	COMIDA("Comida", 1);
	
	private String descricao;
	private Integer valor;
	
	@JsonCreator
    public static ItemEnum fromString(String valor) {   
        return Arrays.stream(values()).filter(v -> v.getValorJson().equalsIgnoreCase(valor)).findFirst().orElse(null);
    }
    
    @JsonValue
    public String getValorJson() {
        return name();
    }	
}
