package com.mentoria.lojavirtual.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mentoria.lojavirtual.model.Acesso;

//Repositório
@Repository
//gerenciar as transações com o banco
@Transactional
public interface AcessoRepository extends JpaRepository<Acesso, Long> {

}
