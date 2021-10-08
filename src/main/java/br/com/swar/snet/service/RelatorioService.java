package br.com.swar.snet.service;

import org.springframework.stereotype.Service;

import br.com.swar.snet.model.dto.ItemReport;
import br.com.swar.snet.model.dto.RebeldesReport;
import br.com.swar.snet.model.enums.ItemEnum;
import br.com.swar.snet.repository.ItemRepository;
import br.com.swar.snet.repository.RebeldeRepository;
import br.com.swar.snet.utils.RebeldeConstants;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RelatorioService {
	
	private final RebeldeRepository rebeldeRepository;
	private final ItemRepository itemRepository;
	
	public RebeldesReport buscarReportRebeldes() {
		RebeldesReport report = new RebeldesReport();
		report.setTotalPessoas(rebeldeRepository.contarRebeldes());
		report.setTotalTraidoes(rebeldeRepository.contarTraidores(RebeldeConstants.LIMIAR_TRAICAO));
		report.setPontosPerdidos(rebeldeRepository.buscarTotalDePontoEmTraidores(RebeldeConstants.LIMIAR_TRAICAO));
		
		return report;
	}
	
	public List<ItemReport> buscarReportItems() {
		List<ItemReport> report = new ArrayList<>();
		var totalRebeldes = rebeldeRepository.contarRebeldes();
		
		for(var itemEnum : ItemEnum.values()) {
			var totalItem = itemRepository.countItemPorTipo(itemEnum.name());
			ItemReport itemReport = new ItemReport();
			itemReport.setItem(itemEnum);
			
			var media = totalRebeldes == 0 ? 0 : totalItem*1.0/totalRebeldes;
			itemReport.setMedia(media);
			report.add(itemReport);
		}
		
		return report;
	}
}
