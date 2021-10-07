package br.com.swar.snet.model.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Data;

@Data
@MappedSuperclass
public abstract class BaseEntity {

	@Column(name="data_criacao")
	private Date dataCriacao;
	
	@Column(name="data_alteracao")
	private Date dataAlteracao;
	
	public BaseEntity() {
		dataCriacao = new Date();
		dataAlteracao = new Date();
	}
	
	public void setAtualizacao() {
		dataAlteracao = new Date();
	}
}
