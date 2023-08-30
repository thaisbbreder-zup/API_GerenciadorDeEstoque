package com.gerenciadorEstoque.gerenciador.service;

import com.gerenciadorEstoque.gerenciador.HandleException.CamposEmBrancoException;
import com.gerenciadorEstoque.gerenciador.HandleException.ProdutoDeletionException;
import com.gerenciadorEstoque.gerenciador.HandleException.ProdutoNotFoundException;
import com.gerenciadorEstoque.gerenciador.model.ProdutoModel;
import com.gerenciadorEstoque.gerenciador.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

    public ProdutoModel cadastrarProduto(ProdutoModel produtoModel) {
        if (produtoModel.getNome() == null || produtoModel.getDescricao() == null ||
                produtoModel.getQuantidadeEstoque() == null || produtoModel.getPrecoUnitario() == null) {
            throw new CamposEmBrancoException();
        }
        return produtoRepository.save(produtoModel);
    }

    public List<ProdutoModel> listarTodosProdutos() {
        return produtoRepository.findAll();
    }

    public Optional<ProdutoModel> buscarProdutoPorId(Long id) {
        if (produtoRepository.existsById(id)) {
            return produtoRepository.findById(id);
        } else {
            throw new ProdutoNotFoundException(id);
        }
    }

    public ProdutoModel atualizarProdutoporId(Long id, ProdutoModel updateProduto) {
        Optional<ProdutoModel> produtoOptional = produtoRepository.findById(id);

        if (produtoOptional.isPresent()) {
            ProdutoModel produtoExistente = produtoOptional.get();

            if (updateProduto.getNome() != null) {
                produtoExistente.setNome(updateProduto.getNome());
            }

            if (updateProduto.getDescricao() != null) {
                produtoExistente.setDescricao(updateProduto.getDescricao());
            }

            if (updateProduto.getQuantidadeEstoque() != null) {
                produtoExistente.setQuantidadeEstoque(updateProduto.getQuantidadeEstoque());
            }

            if (updateProduto.getPrecoUnitario() != null) {
                produtoExistente.setPrecoUnitario(updateProduto.getPrecoUnitario());
            }

            return produtoRepository.save(produtoExistente);
        } else {
            throw new ProdutoNotFoundException(id);
        }
    }

    public void deletarProduto(Long id) {
        if (produtoRepository.existsById(id)) {
            produtoRepository.deleteById(id);
        } else {
            throw new ProdutoDeletionException(id);
        }
    }


}

