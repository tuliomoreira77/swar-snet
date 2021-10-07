package br.com.swar.snet.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.swar.snet.model.dto.RebeldeDto;
import br.com.swar.snet.model.exception.ResourceNotFoundException;
import br.com.swar.snet.service.RebeldeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import java.util.List;

import javax.websocket.server.PathParam;

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
	public ResponseEntity<RebeldeDto> buscarRebelde(@PathParam(value="idRebelde") Long idRebelde) throws ResourceNotFoundException {
		var result = rebeldeService.buscarRebelde(idRebelde);
		return ResponseEntity.status(200).body(result);
	}
}
