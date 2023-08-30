package com.gerenciadorEstoque.gerenciador.controller;

import com.gerenciadorEstoque.gerenciador.model.ProdutoModel;
import com.gerenciadorEstoque.gerenciador.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    //CADASTRA NOVOS PRODUTOS
    @PostMapping
    public ResponseEntity<ProdutoModel> cadastrarProduto(@RequestBody ProdutoModel produtoModel) {
        ProdutoModel novoProduto = produtoService.cadastrarProduto(produtoModel);
        return new ResponseEntity<>(produtoModel, HttpStatus.CREATED);
    }

    //LISTA TODOS PRODUTOS CADASTRADOS
    @GetMapping
    public ResponseEntity<List<ProdutoModel>> listarTodosProdutos() {
        List<ProdutoModel> produtos = produtoService.getAll();
        return ResponseEntity.ok(produtos);
    }

    //BUSCA PRODUTOS POR NOME
    @GetMapping("/buscarPorNome/{nome}")
    public ResponseEntity<List<ProdutoModel>> buscarProdutosPorNome(@PathVariable String nome) {
        List<ProdutoModel> produto = produtoService.getByNome(nome);
        return ResponseEntity.ok(produto);
    }

    //BUSCA PRODUTOS POR ID
    @GetMapping(path = "{id}")
    public ResponseEntity<ProdutoModel> buscarProdutoPorId(@PathVariable Long id) {
        Optional<ProdutoModel> produto = produtoService.getById(id);
        return ResponseEntity.ok(produto.get());
    }

    //ATUALIZA INFORMACOES DO PRODUTO: nome, descricao, preco, quantidade no estoque
    @PatchMapping(path = "{id}")
    public ResponseEntity<ProdutoModel> atualizarProduto(@PathVariable Long id, @RequestBody ProdutoModel produtoUpdate) {
        ProdutoModel atualizarProduto = produtoService.atualizarProdutoporId(id, produtoUpdate);
        return ResponseEntity.ok(atualizarProduto);
    }

    //EXCLUI PRODUTO POR ID
    @DeleteMapping("{id}")
    public ResponseEntity<?> deletarProdutoPorID(@PathVariable Long id) {
        produtoService.deletarProduto(id);
        return ResponseEntity.ok().body("Produto(a) excluído(a) com sucesso!");

    }
}
