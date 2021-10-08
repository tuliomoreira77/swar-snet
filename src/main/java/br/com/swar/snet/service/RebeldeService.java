package br.com.swar.snet.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.swar.snet.model.domain.Item;
import br.com.swar.snet.model.domain.Localizacao;
import br.com.swar.snet.model.domain.Rebelde;
import br.com.swar.snet.model.dto.ItemDto;
import br.com.swar.snet.model.dto.LocalizacaoDto;
import br.com.swar.snet.model.dto.RebeldeDto;
import br.com.swar.snet.model.exception.BadRequestException;
import br.com.swar.snet.model.exception.ResourceNotFoundException;
import br.com.swar.snet.repository.ItemRepository;
import br.com.swar.snet.repository.LocalizacaoRepository;
import br.com.swar.snet.repository.RebeldeRepository;
import br.com.swar.snet.service.validator.AdicionarRebeldeValidator;
import br.com.swar.snet.service.validator.ItemValidator;
import br.com.swar.snet.utils.SwarSnetCollectionUtils;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RebeldeService {

	private final RebeldeRepository rebeldeRepository;
	private final LocalizacaoRepository localizacaoRepository;
	private final ItemRepository itemRepository;
	private final AdicionarRebeldeValidator adicionarRebeldeValidator;
	private final ItemValidator itemValidator;
	
	public List<RebeldeDto> buscarRebeldes(Integer page, Integer size) {
		size = size > 100 ? 100 : size;
		var rebeldes = rebeldeRepository.findAll(PageRequest.of(page, size));
		return rebeldes.stream().map(RebeldeDto::new).collect(Collectors.toList());
	}
	
	public RebeldeDto buscarRebelde(Long idRebelde) throws ResourceNotFoundException {
		return new RebeldeDto(encontrarRebelde(idRebelde));
	}
	
	public Rebelde reportarTraidor(Long idRebelde) throws ResourceNotFoundException {
		var rebelde = encontrarRebelde(idRebelde);
		rebelde.setAdvertencias(rebelde.getAdvertencias() + 1);
		return rebeldeRepository.save(rebelde);
	}
	
	private Rebelde encontrarRebelde(Long idRebelde) throws ResourceNotFoundException {
		var rebelde = rebeldeRepository.findById(idRebelde).orElse(null);
		if(rebelde == null) {
			throw new ResourceNotFoundException("Rebelde não encontrado");
		}
		return rebelde;
	}
	
	@Transactional
	public Rebelde salvarRebelde(RebeldeDto rebeldeDto) throws BadRequestException {
		adicionarRebeldeValidator.validar(rebeldeDto);
		
		Rebelde rebelde = new Rebelde();
		rebelde.setAtualizacao();
		rebelde.setNome(rebeldeDto.getNome());
		rebelde.setGenero(rebeldeDto.getGenero());
		rebelde.setIdade(rebeldeDto.getIdade());
		rebelde.setAdvertencias(0);
		
		rebelde = rebeldeRepository.save(rebelde);
		rebelde.setLocalizacao(criarLocalizacao(rebeldeDto.getLocalizacao(), rebelde.getId()));
		rebelde.setInventario(salvarInventario(rebeldeDto.getInventario(), rebelde.getId()));
		
		return rebelde;
	}
	
	public Localizacao atualizarLocalizacao(LocalizacaoDto localizacaoDto, Long idRebelde) throws ResourceNotFoundException, BadRequestException {
		adicionarRebeldeValidator.validarLocalizacao(localizacaoDto);
		var localizacaoAnterior = localizacaoRepository.findByIdRebelde(idRebelde);
		if(localizacaoAnterior == null) {
			throw new ResourceNotFoundException("Rebelde não encontrado");
		}
		
		return salvarLocalizacao(localizacaoAnterior, localizacaoDto, idRebelde);
	}
	
	public Localizacao criarLocalizacao(LocalizacaoDto localizacaoDto, Long idRebelde) throws BadRequestException {
		adicionarRebeldeValidator.validarLocalizacao(localizacaoDto);
		Localizacao localizacao = new Localizacao();
		return salvarLocalizacao(localizacao, localizacaoDto, idRebelde);
	}
	
	private Localizacao salvarLocalizacao(Localizacao localizacao, LocalizacaoDto localizacaoDto, Long idRebelde) {
		localizacao.setAtualizacao();
		localizacao.setIdRebelde(idRebelde);
		localizacao.setLatitude(localizacaoDto.getLatitude());
		localizacao.setLongitude(localizacaoDto.getLongitude());
		localizacao.setNome(localizacaoDto.getNomeLocalizacao());
		
		return localizacaoRepository.save(localizacao);
	}
	
	private List<Item> salvarInventario(List<ItemDto> itemsDto, Long idRebelde) throws BadRequestException {
		List<Item> items = new ArrayList<>();
		for(var itemDto : itemsDto) {
			itemValidator.validar(itemDto);
			for(int i=0; i<itemDto.getQuantidade(); i++) {
				Item item = new Item();
				item.setAtualizacao();
				item.setIdRebelde(idRebelde);
				item.setNome(itemDto.getItem().name());
				item.setValor(itemDto.getItem().getValor());
				items.add(item);
			}
		}
		return SwarSnetCollectionUtils.toList(itemRepository.saveAll(items));
	}
}
