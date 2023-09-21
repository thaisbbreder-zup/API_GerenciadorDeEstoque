package com.gerenciadorEstoque.gerenciador.service;

import com.gerenciadorEstoque.gerenciador.exception.CamposEmBrancoException;
import com.gerenciadorEstoque.gerenciador.exception.NomeNotFoundException;
import com.gerenciadorEstoque.gerenciador.exception.IdNotFoundException;
import com.gerenciadorEstoque.gerenciador.exception.ValorInvalidoException;
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

    public ProdutoModel cadastrarProduto(ProdutoModel updateProduto) {
        // Verifica se algum dado obrigatório está em branco ou nulo
        if (updateProduto.getNome() == null || updateProduto.getNome().trim().isEmpty() ||
                updateProduto.getDescricao() == null || updateProduto.getDescricao().trim().isEmpty() ||
                updateProduto.getQuantidadeEstoque() == null || updateProduto.getPrecoUnitario() == null) {
            throw new CamposEmBrancoException();
        } else if (updateProduto.getQuantidadeEstoque() < 0 || updateProduto.getPrecoUnitario() < 0) {
            throw new ValorInvalidoException();
        } else {
            return produtoRepository.save(updateProduto);
        }
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
            if (updateProduto.getNome() == null || updateProduto.getNome().trim().isEmpty() ||
                    updateProduto.getDescricao() == null || updateProduto.getDescricao().trim().isEmpty() ||
                    updateProduto.getQuantidadeEstoque() == null || updateProduto.getPrecoUnitario() == null) {
                throw new CamposEmBrancoException();
            } else if (updateProduto.getQuantidadeEstoque() < 0 || updateProduto.getPrecoUnitario() < 0) {
                throw new ValorInvalidoException();
            }else{
                return updateProduto;
            }
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

