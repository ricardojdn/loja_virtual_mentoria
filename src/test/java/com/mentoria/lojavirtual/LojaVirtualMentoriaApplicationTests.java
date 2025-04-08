package com.mentoria.lojavirtual;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mentoria.lojavirtual.controller.AcessoController;
import com.mentoria.lojavirtual.model.Acesso;

@SpringBootTest(classes = LojaVirtualMentoriaApplication.class)
class LojaVirtualMentoriaApplicationTests {
	
	@Autowired
	private AcessoController acessoController;

	@Test
	public void TesteCadastraAcesso() {
	 Acesso acesso = new Acesso();
	 acesso.setDescricao("ROLE_ADMIN");
	 acessoController.salvarAcesso(acesso);
	}

}
