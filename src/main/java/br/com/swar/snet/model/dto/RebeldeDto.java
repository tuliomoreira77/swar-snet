package br.com.swar.snet.model.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.swar.snet.model.domain.Rebelde;
import br.com.swar.snet.model.enums.GeneroEnum;
import br.com.swar.snet.utils.SwarSnetCollectionUtils;
import br.com.swar.snet.utils.RebeldeConstants;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RebeldeDto {
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Long id;

	private String nome;
	
	private Integer idade;
	
	private GeneroEnum genero;	
	
	private Double latitude;
	
	private Double longitude;
	
	private String nomeLocalizacao;
	
	private List<ItemDto> inventario;
	
	@JsonIgnore
	private Integer advertencias = 0;
	
	public RebeldeDto(Rebelde rebelde) {
		this.id = rebelde.getId();
		this.nome = rebelde.getNome();
		this.idade = rebelde.getIdade();
		this.genero = rebelde.getGenero();
		this.latitude = rebelde.getLocalizacao().getLatitude();
		this.longitude = rebelde.getLocalizacao().getLongitude();
		this.nomeLocalizacao = rebelde.getLocalizacao().getNome();
		this.inventario = SwarSnetCollectionUtils.toStackList(rebelde.getInventario().stream().map(ItemDto::new).collect(Collectors.toList()));
		this.advertencias = rebelde.getAdvertencias();
	}
	
	@JsonIgnore
	public LocalizacaoDto getLocalizacao() {
		return new LocalizacaoDto(latitude, longitude, nomeLocalizacao);
	}
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	public boolean isTraidor() {
		return this.advertencias >= RebeldeConstants.LIMIAR_TRAICAO;
	}
	
}
