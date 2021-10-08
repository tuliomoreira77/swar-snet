package br.com.swar.snet.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Relatorio API")
@RestController
@RequestMapping(value = "/api/v1/relatorio")
@RequiredArgsConstructor
public class RelatorioController {

}
