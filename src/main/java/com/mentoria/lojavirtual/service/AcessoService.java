package com.mentoria.lojavirtual.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mentoria.lojavirtual.model.Acesso;
import com.mentoria.lojavirtual.repository.AcessoRepository;

@Service
public class AcessoService {
	@Autowired
	private AcessoRepository acessoRepository;
	
	public Acesso save(Acesso acesso) {
		/* Validações */
		return acessoRepository.save(acesso);
	}
	
	public void delete(Acesso acesso) {
		/* Validações */
		acessoRepository.deleteById(acesso.getId());
	}

}
