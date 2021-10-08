package br.com.swar.snet.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
public class Item extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "id_rebelde", nullable = false)
	private Long idRebelde;
	
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@Column(name = "valor", nullable = false)
	private Integer valor;
}
