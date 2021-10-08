package br.com.swar.snet.model.dto;

import lombok.Data;

@Data
public class RebeldesReport {
	
	private Long totalPessoas;
	private Long totalTraidoes;
	private Long pontosPerdidos;
	
	public Long getTotalRebeldes() {
		return totalPessoas - totalTraidoes;
	}
	
	public Double getPorcentagemTraidores() {
		if(totalPessoas == 0)
			return 0.0;
		
		return (totalTraidoes*1.0/totalPessoas) * 100;
	}
	
	public Double getPorcentagemRebeldes() {
		if(totalPessoas == 0)
			return 0.0;
		
		return (getTotalRebeldes()*1.0/totalPessoas*1.0) * 100;
	}
	
}
