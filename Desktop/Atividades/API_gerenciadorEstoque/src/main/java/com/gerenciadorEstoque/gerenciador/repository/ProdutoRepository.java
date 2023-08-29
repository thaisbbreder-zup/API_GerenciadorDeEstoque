package com.gerenciadorEstoque.gerenciador.repository;

import com.gerenciadorEstoque.gerenciador.model.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long> {
}
