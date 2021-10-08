package br.com.swar.snet.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.swar.snet.model.dto.IdRebeldeResponseDto;
import br.com.swar.snet.model.dto.LocalizacaoDto;
import br.com.swar.snet.model.dto.RebeldeDto;
import br.com.swar.snet.model.exception.BadRequestException;
import br.com.swar.snet.model.exception.ResourceNotFoundException;
import br.com.swar.snet.service.RebeldeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Rebelde API")
@RestController
@RequestMapping(value = "/api/v1/rebelde")
@RequiredArgsConstructor
public class RebeldeController {

	private final RebeldeService rebeldeService;
	
	@Operation(summary = "Busca os rebeldes cadastrados com paginação (MAX 100)")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Rebeldes")
	})
	@GetMapping
	public ResponseEntity<List<RebeldeDto>> buscarRebeldes(@RequestParam(defaultValue = "0", name = "page") Integer page, @RequestParam(defaultValue = "100", name = "size") Integer size) {
		var result = rebeldeService.buscarRebeldes(page, size);
		return ResponseEntity.status(200).body(result);
	}
	
	@Operation(summary = "Busca um rebelde por id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Rebelde"),
			@ApiResponse(responseCode = "404", description = "Rebelde nao encontrado")
	})
	@GetMapping("/{idRebelde}")
	public ResponseEntity<RebeldeDto> buscarRebelde(@PathVariable(value="idRebelde") Long idRebelde) throws ResourceNotFoundException {
		var result = rebeldeService.buscarRebelde(idRebelde);
		return ResponseEntity.status(200).body(result);
	}
	
	@Operation(summary = "Adiciona um rebelde")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Adicionado"),
			@ApiResponse(responseCode = "400", description = "Payload invalido")
	})
	@PostMapping
	public ResponseEntity<IdRebeldeResponseDto> adicionarRebelde(@RequestBody RebeldeDto rebeldeDto) throws BadRequestException {
		var rebelde = rebeldeService.salvarRebelde(rebeldeDto);
		return ResponseEntity.status(201).body(new IdRebeldeResponseDto(rebelde.getId()));
	}
	
	@Operation(summary = "Atualiza localizacao do rebelde")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Atualizado"),
			@ApiResponse(responseCode = "400", description = "Payload invalido"),
			@ApiResponse(responseCode = "404", description = "Rebelde nao encontrado")
	})
	@PutMapping("/{idRebelde}/localizacao")
	public ResponseEntity<String> atualizarLocalizacao(@RequestBody LocalizacaoDto localizacaoDto, @PathVariable(value="idRebelde") Long idRebelde) throws ResourceNotFoundException, BadRequestException {
		rebeldeService.atualizarLocalizacao(localizacaoDto, idRebelde);
		return ResponseEntity.status(201).body(null);
	}
	
	@Operation(summary = "Reporta um rebelde como traidor")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Reportado"),
			@ApiResponse(responseCode = "404", description = "Rebelde nao encontrado")
	})
	@PostMapping("/{idRebelde}/reportar")
	public ResponseEntity<String> reportarTraidor(@PathVariable(value="idRebelde") Long idRebelde) throws ResourceNotFoundException {
		rebeldeService.reportarTraidor(idRebelde);
		return ResponseEntity.status(201).body(null);
	}
}
