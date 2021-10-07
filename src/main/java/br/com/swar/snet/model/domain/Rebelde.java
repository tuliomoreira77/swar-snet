package br.com.swar.snet.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import java.util.List;

import br.com.swar.snet.model.enums.GeneroEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
public class Rebelde extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@Column(name = "idade", nullable = false)
	private Integer idade;
	
	@Column(name = "genero", nullable = false)
	@Enumerated(EnumType.STRING)
	private GeneroEnum genero;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="idRebelde")
	private Localizacao localizacao;
	
	@OneToMany(mappedBy = "idRebelde", fetch = FetchType.LAZY)
	private List<Item> inventario;
	
	@Column(name = "advertencias", nullable = false)
	private Integer advertencias;
	
}
