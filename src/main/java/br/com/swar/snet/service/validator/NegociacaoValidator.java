package br.com.swar.snet.service.validator;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.swar.snet.model.domain.Item;
import br.com.swar.snet.model.domain.Rebelde;
import br.com.swar.snet.model.dto.NegociacaoDto;
import br.com.swar.snet.model.dto.ParteNegociacaoDto;
import br.com.swar.snet.model.exception.BadRequestException;
import br.com.swar.snet.model.exception.InvalidNegotiationException;
import br.com.swar.snet.model.exception.ResourceNotFoundException;
import br.com.swar.snet.utils.RebeldeConstants;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NegociacaoValidator {
	
	private final ItemValidator itemValidator;

	public void validarNegociacao(Rebelde parteA, Rebelde parteB, NegociacaoDto negociacaoDto) throws ResourceNotFoundException, InvalidNegotiationException {
		if(parteA == null || parteB == null) {
			throw new ResourceNotFoundException("Rebelde não encotrado");
		}
		if(parteA.getId() == parteB.getId()) {
			throw new InvalidNegotiationException("Negociação Inválida, as duas partes sao o mesmo rebelde");
		}
		if(verificarTraidor(parteA) || verificarTraidor(parteB)) {
			throw new InvalidNegotiationException("Negociação Inválida, pelo menos um dos rebeldes é um traidor");
		}
		
		validarItems(negociacaoDto.getParteA());
		validarItems(negociacaoDto.getParteB());
		
		if(calcularTotalPontos(negociacaoDto.getParteA()) != calcularTotalPontos(negociacaoDto.getParteB())) {
			throw new InvalidNegotiationException("Negociação Inválida, as partes devem enviar para trocas o mesmo total de pontos");
		}
		
		verificarPosseItems(parteA, negociacaoDto.getParteA());
		verificarPosseItems(parteB, negociacaoDto.getParteB());
	}
	
	private boolean verificarTraidor(Rebelde parte) {
		return parte.getAdvertencias() >= RebeldeConstants.LIMIAR_TRAICAO;
	}
	
	private Integer calcularTotalPontos(ParteNegociacaoDto parte) {
		Integer total = 0;
		for(var item : parte.getItems()) {
			total = total + (item.getItem().getValor() * item.getQuantidade());
		}
		return total;
	}
 
	private void verificarPosseItems(Rebelde rebelde, ParteNegociacaoDto parte) throws InvalidNegotiationException {
		for(var itemDto : parte.getItems()) {
			List<Item> item = rebelde.getInventario().stream().filter(el -> el.getNome().equals(itemDto.getItem().name())).collect(Collectors.toList());
			if(item.size() < itemDto.getQuantidade()) {
				throw new InvalidNegotiationException("Negociação Inválida, pelo menos uma das partes não possui os items informados.");
			}
		}
	}
	
	private void validarItems(ParteNegociacaoDto parte) throws InvalidNegotiationException {
		try {
			for(var i : parte.getItems()) {
				itemValidator.validar(i);
			}
		} catch (BadRequestException e) {
			throw new InvalidNegotiationException(e.getMessage());
		}
	}
}
