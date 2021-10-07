package br.com.swar.snet.service.validator;

import org.springframework.stereotype.Component;

import br.com.swar.snet.model.domain.Item;
import br.com.swar.snet.model.domain.Rebelde;
import br.com.swar.snet.model.dto.ItemDto;
import br.com.swar.snet.model.dto.NegociacaoDto;
import br.com.swar.snet.model.dto.ParteNegociacaoDto;
import br.com.swar.snet.model.enums.ItemEnum;
import br.com.swar.snet.model.exception.InvalidNegotiationException;
import br.com.swar.snet.model.exception.ResourceNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class NegociacaoValidator {

	public void validarNegociacao(Rebelde parteA, Rebelde parteB, NegociacaoDto negociacaoDto) throws ResourceNotFoundException, InvalidNegotiationException {
		if(parteA == null || parteB == null) {
			throw new ResourceNotFoundException("Rebelde não encotrado");
		}
		if(calcularTotalPontos(negociacaoDto.getParteA()) != calcularTotalPontos(negociacaoDto.getParteB())) {
			throw new InvalidNegotiationException("Negociação Inválida, as partes devem enviar para trocas o mesmo total de pontos");
		}
		
		verificarPosseItems(parteA, negociacaoDto.getParteA());
		verificarPosseItems(parteB, negociacaoDto.getParteB());
	}
	
	private Integer calcularTotalPontos(ParteNegociacaoDto parte) {
		return parte.getItems().stream().map(ItemDto::getItem).map(ItemEnum::getValor).reduce(0, (subtotal, element) -> subtotal + element);
	}
 
	private void verificarPosseItems(Rebelde rebelde, ParteNegociacaoDto parte) throws InvalidNegotiationException {
		for(var itemDto : parte.getItems()) {
			List<Item> item = rebelde.getInventario().stream().filter(el -> el.getNome().equals(itemDto.getItem().name())).collect(Collectors.toList());
			if(item.size() < itemDto.getQuantidade()) {
				throw new InvalidNegotiationException("Negociação Inválida, pelo menos uma das partes não possui os items informados.");
			}
		}
	}
}
