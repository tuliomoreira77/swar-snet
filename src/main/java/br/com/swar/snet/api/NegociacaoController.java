package br.com.swar.snet.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.swar.snet.model.dto.NegociacaoDto;
import br.com.swar.snet.model.exception.InvalidNegotiationException;
import br.com.swar.snet.model.exception.ResourceNotFoundException;
import br.com.swar.snet.service.NegociacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Negociacao API")
@RestController
@RequestMapping(value = "/api/v1/negociacao")
@RequiredArgsConstructor
public class NegociacaoController {
	
	private final NegociacaoService negociacaoService;
	
	@Operation(summary = "Realiza uma negociacao")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Negociacao realizada"),
			@ApiResponse(responseCode = "400", description = "Negociacao invalida"),
			@ApiResponse(responseCode = "404", description = "Rebelde nao encontrado")
	})
	@PostMapping
	public ResponseEntity<String> adicionarRebelde(@RequestBody NegociacaoDto negociacaoDto) throws ResourceNotFoundException, InvalidNegotiationException {
		negociacaoService.tranferirItens(negociacaoDto);
		return ResponseEntity.status(201).body(null);
	}
	
}
