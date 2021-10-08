package br.com.swar.snet.service.validator;

import org.springframework.stereotype.Component;

import br.com.swar.snet.model.dto.LocalizacaoDto;
import br.com.swar.snet.model.dto.RebeldeDto;
import br.com.swar.snet.model.exception.BadRequestException;

@Component
public class AdicionarRebeldeValidator extends BaseValidator {
	
	public void validar(RebeldeDto rebeldeDto) throws BadRequestException {
		if(!validadeNullOrEmpty(rebeldeDto.getNome()))
			throw new BadRequestException("Nome invalido");
		
		if(!validadeNullOrEmpty(rebeldeDto.getGenero()))
			throw new BadRequestException("Genero invalido");
		
		if(!validadeNullOrPositive(rebeldeDto.getIdade()))
			throw new BadRequestException("Idade invalida");
		
		if(!validadeList(rebeldeDto.getInventario()))
			throw new BadRequestException("Informe pelo menos um array vazio para inventario");
		
		validarLocalizacao(rebeldeDto.getLocalizacao());
	}
	
	public void validarLocalizacao(LocalizacaoDto localizacaoDto) throws BadRequestException {
		if(!validadeNullOrEmpty(localizacaoDto.getLatitude()))
			throw new BadRequestException("Localizacao invalida");
		
		if(!validadeNullOrEmpty(localizacaoDto.getLongitude()))
			throw new BadRequestException("Localizacao invalida");
		
		if(!validadeNullOrEmpty(localizacaoDto.getNomeLocalizacao()))
			throw new BadRequestException("Localizacao invalida");
	}
	
	
}
