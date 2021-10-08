package br.com.swar.snet.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.swar.snet.model.domain.Item;
import br.com.swar.snet.model.domain.Rebelde;
import br.com.swar.snet.model.dto.ItemDto;
import br.com.swar.snet.model.dto.NegociacaoDto;
import br.com.swar.snet.model.exception.InvalidNegotiationException;
import br.com.swar.snet.model.exception.ResourceNotFoundException;
import br.com.swar.snet.repository.ItemRepository;
import br.com.swar.snet.repository.RebeldeRepository;
import br.com.swar.snet.service.validator.NegociacaoValidator;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NegociacaoService {
	
	private final RebeldeRepository rebeldeRepository;
	private final ItemRepository itemRepository;
	private final NegociacaoValidator negociacaoValidator;
	
	@Transactional
	public void tranferirItens(NegociacaoDto negociacaoDto) throws ResourceNotFoundException, InvalidNegotiationException {
		var parteA = rebeldeRepository.findByIdFetchIventario(negociacaoDto.getParteA().getIdRebelde());
		var parteB = rebeldeRepository.findByIdFetchIventario(negociacaoDto.getParteB().getIdRebelde());
		
		negociacaoValidator.validarNegociacao(parteA, parteB, negociacaoDto);
		
		transferirItems(parteA, parteB, negociacaoDto.getParteA().getItems());
		transferirItems(parteB, parteA, negociacaoDto.getParteB().getItems());
	}
	
	private void transferirItems(Rebelde de, Rebelde para, List<ItemDto> itemsParaTransferenciaDto) {
		List<Item> itemsTransferidos = new ArrayList<>();
		for(var itemDto : itemsParaTransferenciaDto) {
			List<Item> items = de.getInventario().stream().filter(el -> el.getNome().equals(itemDto.getItem().name())).collect(Collectors.toList());
			for(int i=0; i < itemDto.getQuantidade(); i++) {
				var item = items.get(i);
				item.setIdRebelde(para.getId());
				itemsTransferidos.add(item);
			}
		}
		itemRepository.saveAll(itemsTransferidos);
	}
}
