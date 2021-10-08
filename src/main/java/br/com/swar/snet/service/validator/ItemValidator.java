package br.com.swar.snet.service.validator;

import org.springframework.stereotype.Component;

import br.com.swar.snet.model.dto.ItemDto;
import br.com.swar.snet.model.exception.BadRequestException;

@Component
public class ItemValidator extends BaseValidator {
	
	public void validar(ItemDto itemDto) throws BadRequestException {
		if(!validadeNullOrEmpty(itemDto.getItem()))
			throw new BadRequestException("Item deve fazer parte da enumeracao");
		if(!validadeNullOrPositive(itemDto.getQuantidade()))
			throw new BadRequestException("Quantidade item Invalida");
	}
	
}
