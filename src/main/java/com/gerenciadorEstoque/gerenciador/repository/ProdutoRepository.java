package com.gerenciadorEstoque.gerenciador.repository;

import com.gerenciadorEstoque.gerenciador.model.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long> {
    List<ProdutoModel> findByNome(String nome);
}



