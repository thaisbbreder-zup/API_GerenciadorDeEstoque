package com.gerenciadorEstoque.gerenciador.service;

import com.gerenciadorEstoque.gerenciador.exception.CamposEmBrancoException;
import com.gerenciadorEstoque.gerenciador.exception.NomeNotFoundException;
import com.gerenciadorEstoque.gerenciador.exception.IdNotFoundException;
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
        //impede cadastro faltando algum dado
        if (produtoModel.getNome() == null || produtoModel.getDescricao() == null ||
                produtoModel.getQuantidadeEstoque() == null || produtoModel.getPrecoUnitario() == null) {
            throw new CamposEmBrancoException();
        }
        return produtoRepository.save(produtoModel);
    }

    public List<ProdutoModel> getAll() {
        List<ProdutoModel> produtos = produtoRepository.findAll();
        return produtos;
    }

    public List<ProdutoModel> getByNome(String nome) {
        List<ProdutoModel> produtos = produtoRepository.findByNome(nome);
        if (produtos == null) {
            throw new NomeNotFoundException(nome);
        }
        return produtos;
    }

    public Optional<ProdutoModel> getById(Long id) {
        if (produtoRepository.existsById(id)) {
            return produtoRepository.findById(id);
        } else {
            throw new IdNotFoundException(id);
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
            throw new IdNotFoundException(id);
        }
    }

    public void deletarProduto(Long id) {
        if (produtoRepository.existsById(id)) {
            produtoRepository.deleteById(id);
        } else {
            throw new IdNotFoundException(id);
        }
    }
}

