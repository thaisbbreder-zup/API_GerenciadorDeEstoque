package com.gerenciadorEstoque.gerenciador.service;

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
        return produtoRepository.save(produtoModel);
    }

    public List<ProdutoModel> listarTodosProdutos() {
        return produtoRepository.findAll();
    }

    public Optional<ProdutoModel> buscarProdutoPorId(Long id) {
            return produtoRepository.findById(id);
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
            return null;
        }
    }

    public void deletarProduto(Long id) {
        if (produtoRepository.existsById(id)) {
            produtoRepository.deleteById(id);
        }
    }


}

