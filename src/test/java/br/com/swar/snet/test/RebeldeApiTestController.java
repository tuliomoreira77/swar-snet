package br.com.swar.snet.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import br.com.swar.snet.model.dto.NegociacaoDto;
import br.com.swar.snet.model.dto.ParteNegociacaoDto;
import br.com.swar.snet.model.dto.RebeldeDto;
import br.com.swar.snet.model.enums.GeneroEnum;

@SpringBootTest
@ActiveProfiles({ "dev" })
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureMockMvc
public class RebeldeApiTestController {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	@Order(1)
	public void testeAdicionarRebelde() throws Exception {
		RebeldeDto request = new RebeldeDto();
		request.setNome("Tulio");
		request.setGenero(GeneroEnum.M);
		request.setIdade(28);
		request.setLatitude(25.0);
		request.setLongitude(26.0);
		request.setNomeLocalizacao("Titan");
		request.setInventario(TestUtils.getInventarioA());
		
		this.mockMvc.perform(post("/api/v1/rebelde")
				.contentType(MediaType.APPLICATION_JSON)
				.content(JsonParser.toJson(request)))
			.andDo(print()).andExpect(status().is(201));
	}
	
	@Test
	@Order(2) 
	public void testeAdicionarRebeldePayloadInvalido() throws Exception {
		RebeldeDto request = new RebeldeDto();
		request.setNome("Tulio");
		request.setGenero(GeneroEnum.M);
		request.setIdade(0);
		request.setLatitude(25.0);
		request.setLongitude(26.0);
		request.setNomeLocalizacao("Titan");
		request.setInventario(TestUtils.getInventarioA());
		
		this.mockMvc.perform(post("/api/v1/rebelde")
				.contentType(MediaType.APPLICATION_JSON)
				.content(JsonParser.toJson(request)))
			.andDo(print()).andExpect(status().is(400));
	}
	
	@Test
	@Order(2)
	public void testeNegociacao() throws Exception {
		RebeldeDto request = new RebeldeDto();
		request.setNome("Marco");
		request.setGenero(GeneroEnum.M);
		request.setIdade(28);
		request.setLatitude(25.0);
		request.setLongitude(26.0);
		request.setNomeLocalizacao("Titan");
		request.setInventario(TestUtils.getInventarioB());
		
		this.mockMvc.perform(post("/api/v1/rebelde")
				.contentType(MediaType.APPLICATION_JSON)
				.content(JsonParser.toJson(request)))
			.andDo(print()).andExpect(status().is(201));
		
		NegociacaoDto negociacaoDto = new NegociacaoDto();
		ParteNegociacaoDto parteA = new ParteNegociacaoDto();
		ParteNegociacaoDto parteB = new ParteNegociacaoDto();
		
		parteA.setIdRebelde(1L);
		parteA.setItems(TestUtils.getTrocaA());
		
		parteB.setIdRebelde(2L);
		parteB.setItems(TestUtils.getTrocaB());
		
		negociacaoDto.setParteA(parteA);
		negociacaoDto.setParteB(parteB);
		
		this.mockMvc.perform(post("/api/v1/negociacao")
				.contentType(MediaType.APPLICATION_JSON)
				.content(JsonParser.toJson(negociacaoDto)))
			.andDo(print()).andExpect(status().is(201));
		
		this.mockMvc.perform(get("/api/v1/rebelde/1")
				.contentType(MediaType.APPLICATION_JSON))
			.andDo(print()).andExpect(status().is(200)).andExpect(content().string(containsString("COMIDA")));
	}
	
	@Test
	@Order(2)
	public void testeReportarTraidorApenasUmaVez() throws Exception {
		this.mockMvc.perform(post("/api/v1/rebelde/1/reportar")
				.contentType(MediaType.APPLICATION_JSON))
			.andDo(print()).andExpect(status().is(201));
		
		this.mockMvc.perform(get("/api/v1/rebelde/1")
				.contentType(MediaType.APPLICATION_JSON))
			.andDo(print()).andExpect(status().is(200)).andExpect(jsonPath("$.traidor").value("false"));
	}
	
	@Test
	@Order(3)
	public void testeReportarTraidor() throws Exception {
		this.mockMvc.perform(post("/api/v1/rebelde/1/reportar")
				.contentType(MediaType.APPLICATION_JSON))
			.andDo(print()).andExpect(status().is(201));
		
		this.mockMvc.perform(post("/api/v1/rebelde/1/reportar")
				.contentType(MediaType.APPLICATION_JSON))
			.andDo(print()).andExpect(status().is(201));
		
		this.mockMvc.perform(post("/api/v1/rebelde/1/reportar")
				.contentType(MediaType.APPLICATION_JSON))
			.andDo(print()).andExpect(status().is(201));
		
		this.mockMvc.perform(get("/api/v1/rebelde/1")
				.contentType(MediaType.APPLICATION_JSON))
			.andDo(print()).andExpect(status().is(200)).andExpect(jsonPath("$.traidor").value("true"));
	}
	
	
	@Test
	@Order(3)
	public void testeReportarTraidorInexistente() throws Exception {
		this.mockMvc.perform(post("/api/v1/rebelde/45/reportar")
				.contentType(MediaType.APPLICATION_JSON))
			.andDo(print()).andExpect(status().is(404));
	}
	
	
	@Test
	@Order(4)
	public void testeNegociacaoInvalida() throws Exception {
		NegociacaoDto negociacaoDto = new NegociacaoDto();
		ParteNegociacaoDto parteA = new ParteNegociacaoDto();
		ParteNegociacaoDto parteB = new ParteNegociacaoDto();
		
		parteA.setIdRebelde(1L);
		parteA.setItems(TestUtils.getTrocaA());
		
		parteB.setIdRebelde(2L);
		parteB.setItems(TestUtils.getTrocaB());
		
		negociacaoDto.setParteA(parteA);
		negociacaoDto.setParteB(parteB);
		
		
		this.mockMvc.perform(post("/api/v1/negociacao")
				.contentType(MediaType.APPLICATION_JSON)
				.content(JsonParser.toJson(negociacaoDto)))
			.andDo(print()).andExpect(status().is(400)).andExpect(jsonPath("$.rawMessage").value("Negociação Inválida, pelo menos um dos rebeldes é um traidor"));
	}
	
}
