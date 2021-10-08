package br.com.swar.snet.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.swar.snet.model.dto.ItemReport;
import br.com.swar.snet.model.dto.RebeldesReport;
import br.com.swar.snet.service.RelatorioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Relatorio API")
@RestController
@RequestMapping(value = "/api/v1/relatorio")
@RequiredArgsConstructor
public class RelatorioController {
	
	private final RelatorioService relatorioService;
	
	@Operation(summary = "Exibe um relatorio dos Rebeldes da resistencia")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Relatorio")
	})
	@GetMapping("/rebeldes")
	public ResponseEntity<RebeldesReport> buscarReportRebeldes() {
		var result = relatorioService.buscarReportRebeldes();
		return ResponseEntity.status(200).body(result);
	}
	
	@Operation(summary = "Exibe um relatorio dos items da resistencia")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Relatorio")
	})
	@GetMapping("/items")
	public ResponseEntity<List<ItemReport>> buscarReportItems() {
		var result = relatorioService.buscarReportItems();
		return ResponseEntity.status(200).body(result);
	}
}
