package com.mentoria.lojavirtual;

import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mentoria.lojavirtual.controller.AcessoController;
import com.mentoria.lojavirtual.model.Acesso;
import com.mentoria.lojavirtual.model.PessoaJuridica;
import com.mentoria.lojavirtual.repository.AcessoRepository;

import junit.framework.TestCase;

@Profile("test")
@SpringBootTest(classes = LojaVirtualMentoriaApplication.class)
class LojaVirtualMentoriaApplicationTests extends TestCase {

	@Autowired
	private AcessoController acessoController;

	@Autowired
	private AcessoRepository acessoRepository;
	
	@Autowired
	private WebApplicationContext wac;
	
	@Test
	public void testRestApiCadastroAcesso() throws JsonProcessingException, Exception {
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
		MockMvc mockMvc = builder.build();
		
		Acesso acesso = new Acesso();
		acesso.setDescricao("ROLE_COMPRADOR");
		
		ObjectMapper objectMapper =new ObjectMapper();
		
		
		ResultActions retornoApi = mockMvc
				.perform(MockMvcRequestBuilders.post("/salvarAcesso")
				.content(objectMapper.writeValueAsString(acesso))
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				);
		System.out.println("Retorno da API: "+retornoApi.andReturn().getResponse().getContentAsString());
		
		Acesso objetoRetorno = objectMapper
					.readValue(retornoApi.andReturn().getResponse().getContentAsString(), Acesso.class);
		assertEquals(acesso.getDescricao(),objetoRetorno.getDescricao());
	}
	
	@Test
	public void testRestApiDeleteAcesso() throws JsonProcessingException, Exception {
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
		MockMvc mockMvc = builder.build();
		
		Acesso acesso = new Acesso();
		acesso.setDescricao("ROLE_TESTE");
		acesso= acessoRepository.save(acesso);
		
		ObjectMapper objectMapper =new ObjectMapper();
		
		
		ResultActions retornoApi = mockMvc
				.perform(MockMvcRequestBuilders.post("/deleteAcesso")
				.content(objectMapper.writeValueAsString(acesso))
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				);
		System.out.println("Retorno da API: "+retornoApi.andReturn().getResponse().getContentAsString());

		assertEquals("\"Acesso Removido\"", retornoApi.andReturn().getResponse().getContentAsString());
		assertEquals(200, retornoApi.andReturn().getResponse().getStatus());
	}
	
	@Test
	public void testRestApiDeleteAcessoPorId() throws JsonProcessingException, Exception {
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
		MockMvc mockMvc = builder.build();
		
		Acesso acesso = new Acesso();
		acesso.setDescricao("ROLE_TESTE_ID");
		acesso= acessoRepository.save(acesso);
		
		ObjectMapper objectMapper =new ObjectMapper();
		
		
		ResultActions retornoApi = mockMvc
				.perform(MockMvcRequestBuilders.delete("/deleteAcessoPorId/"+acesso.getId())
				.content(objectMapper.writeValueAsString(acesso))
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				);
		System.out.println("Retorno da API: "+retornoApi.andReturn().getResponse().getContentAsString());

		assertEquals("\"Acesso Removido\"", retornoApi.andReturn().getResponse().getContentAsString());
		assertEquals(200, retornoApi.andReturn().getResponse().getStatus());
	}
	
	@Test
	public void testRestApiObterAcessoPorId() throws JsonProcessingException, Exception {
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
		MockMvc mockMvc = builder.build();
		
		Acesso acesso = new Acesso();
		acesso.setDescricao("ROLE_OBTER_ACESSO_ID");
		acesso= acessoRepository.save(acesso);
		
		ObjectMapper objectMapper =new ObjectMapper();
		
		
		ResultActions retornoApi = mockMvc
				.perform(MockMvcRequestBuilders.get("/obterAcesso/"+acesso.getId())
				.content(objectMapper.writeValueAsString(acesso))
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				);
		System.out.println("Retorno da API: "+retornoApi.andReturn().getResponse().getContentAsString());
		
		assertEquals(200, retornoApi.andReturn().getResponse().getStatus());
		
		Acesso objetoRetorno = objectMapper
				.readValue(retornoApi.andReturn().getResponse().getContentAsString(), Acesso.class);
	assertEquals(acesso.getDescricao(),objetoRetorno.getDescricao());
	assertEquals(acesso.getId(),objetoRetorno.getId());

	
	}

	@Test
	public void TesteCadastraAcesso() {
		String descacesso = "ROLE_ADMIN" + Calendar.getInstance().getTimeInMillis();

		Acesso acesso = new Acesso();
		PessoaJuridica empresa = new PessoaJuridica();
		empresa.setId(1L);


		acesso.setDescricao(descacesso);
	

		assertEquals(true, acesso.getId() == null);

		/* Gravou no banco de dados */
		acesso = acessoController.salvarAcesso(acesso).getBody();

		assertEquals(true, acesso.getId() > 0);

		/* Validar dados salvos da forma correta */
		assertEquals(descacesso, acesso.getDescricao());

		/* Teste de carregamento */

		Acesso acesso2 = acessoRepository.findById(acesso.getId()).get();

		assertEquals(acesso.getId(), acesso2.getId());

		/* Teste de delete */

		acessoRepository.deleteById(acesso2.getId());

		acessoRepository.flush(); /* Roda esse SQL de delete no banco de dados */

		Acesso acesso3 = acessoRepository.findById(acesso2.getId()).orElse(null);

		assertEquals(true, acesso3 == null);

		/* Teste de query */

		acesso = new Acesso();

		acesso.setDescricao("ROLE_ALUNO");
		

		acesso = acessoController.salvarAcesso(acesso).getBody();

		List<Acesso> acessos = acessoRepository.buscarAcessoDesc("ALUNO".trim().toUpperCase());

		assertEquals(1, acessos.size());

		acessoRepository.deleteById(acesso.getId());

	}

}
