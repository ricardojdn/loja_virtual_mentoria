package com.mentoria.lojavirtual.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.mentoria.lojavirtual.ExceptionMentoriaJava;
import com.mentoria.lojavirtual.model.Acesso;
import com.mentoria.lojavirtual.repository.AcessoRepository;
import com.mentoria.lojavirtual.service.AcessoService;

@Controller
@RestController
public class AcessoController {
	@Autowired
	private AcessoService acessoService;

	@Autowired
	private AcessoRepository acessoRepository;

	@ResponseBody /* Retorno da API */
	@PostMapping(value = "**/salvarAcesso") /* Mapeando para receber o JSON de qq lugar do projeto ** */
	public ResponseEntity<Acesso> salvarAcesso(
			@RequestBody Acesso acesso) { /* Recebe o JSON e converte para o Objeto Acesso */
		Acesso acessoSalvo = acessoService.save(acesso);
		return new ResponseEntity<Acesso>(acessoSalvo, HttpStatus.OK);
	}

	@ResponseBody /* Retorno da API */
	@PostMapping(value = "**/deleteAcesso") /* Mapeando para receber o JSON de qq lugar do projeto ** */
	public ResponseEntity<String> deletarAcesso(
			@RequestBody Acesso acesso) { /* Recebe o JSON e converte para o Objeto Acesso */
		acessoService.delete(acesso);
		return new ResponseEntity<String>(new Gson().toJson("Acesso Removido"), HttpStatus.OK);
	}
	
	
	// @Secured({ "ROLE_GERENTE", "ROLE_ADMIN" })
	@ResponseBody
	@DeleteMapping(value = "**/deleteAcessoPorId/{id}")
	public ResponseEntity<String> deleteAcessoPorId(@PathVariable("id") Long id) {

		acessoRepository.deleteById(id);

		return new ResponseEntity<String>(new Gson().toJson("Acesso Removido"), HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping(value = "**/obterAcesso/{id}")
	public ResponseEntity<Acesso> obterAcesso(@PathVariable("id") Long id) throws ExceptionMentoriaJava {

		Acesso acesso = acessoRepository.findById(id).orElse(null);

		if (acesso == null) {
			throw new ExceptionMentoriaJava("Não encontrou Acesso com código: " + id);
		}

		return new ResponseEntity<Acesso>(acesso, HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping(value = "**/buscarPorDesc/{desc}")
	public ResponseEntity<List<Acesso>> buscarPorDesc(@PathVariable("desc") String desc) {

		List<Acesso> acesso = acessoRepository.buscarAcessoDesc(desc.toUpperCase());

		return new ResponseEntity<List<Acesso>>(acesso, HttpStatus.OK);
	}

	
	//@ResponseBody
	//@GetMapping(value = "**/listaPorPageAcesso/{idEmpresa}/{pagina}")
	//public ResponseEntity<List<Acesso>> page(@PathVariable("idEmpresa") Long idEmpresa,
	//		@PathVariable("pagina") Integer pagina) {

		//Pageable pageable = PageRequest.of(pagina, 5, Sort.by("descricao"));

		//List<Acesso> lista = acessoRepository.findPorPage(idEmpresa, pageable);

		//return new ResponseEntity<List<Acesso>>(lista, HttpStatus.OK);
	//}

	//@ResponseBody
	//@GetMapping(value = "**/qtdPaginaAcesso/{idEmpresa}")
	//public ResponseEntity<Integer> qtdPagina(@PathVariable("idEmpresa") Long idEmpresa) {

		//Integer qtdPagina = acessoRepository.qtdPagina(idEmpresa);

		//return new ResponseEntity<Integer>(qtdPagina, HttpStatus.OK);
	//}

	//@ResponseBody
	//@GetMapping(value = "**/buscarPorAcesso/{desc}/{empresa}")
	//public ResponseEntity<List<Acesso>> buscarPorDesc2(@PathVariable("desc") String desc,
		//	@PathVariable("empresa") Long empresa) {

		//List<Acesso> acesso = acessoRepository.buscarAcessoDes(desc.toUpperCase(), empresa);

		//return new ResponseEntity<List<Acesso>>(acesso, HttpStatus.OK);
	//}

}
