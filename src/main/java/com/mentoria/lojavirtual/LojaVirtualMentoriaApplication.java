package com.mentoria.lojavirtual;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EntityScan(basePackages = "com.mentoria.lojavirtual.model")
@ComponentScan(basePackages = {"com.*"}) //injeção de dependência, onde varrer
@EnableJpaRepositories(basePackages = "com.mentoria.lojavirtual.repository")
@EnableTransactionManagement //banco não ficar travando
public class LojaVirtualMentoriaApplication {

	public static void main(String[] args) {
		SpringApplication.run(LojaVirtualMentoriaApplication.class, args);
	}

}
