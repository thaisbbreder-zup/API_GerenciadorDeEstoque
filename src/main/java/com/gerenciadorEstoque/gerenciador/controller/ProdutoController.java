package com.gerenciadorEstoque.gerenciador.controller;

import com.gerenciadorEstoque.gerenciador.HandleException.ProdutoNotFoundException;
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

    //cadastra novos produtos
    @PostMapping
    public ResponseEntity<ProdutoModel> cadastrarProduto(@RequestBody ProdutoModel produtoModel) {
        ProdutoModel novoProduto = produtoService.cadastrarProduto(produtoModel);
        return new ResponseEntity<>(produtoModel, HttpStatus.CREATED);
    }

    //lista todos os produtos cadastrados ou por nome (/produtos?nome=sabonete)
    @GetMapping
    public ResponseEntity<List<ProdutoModel>> listarTodosProdutos() {
        List<ProdutoModel> produtos = produtoService.listarTodosProdutos();
        return ResponseEntity.ok(produtos);
    }

    //busca produtos por id
    @GetMapping(path = "{id}")
    public ResponseEntity<ProdutoModel> buscarProdutoPorId(@PathVariable Long id) {
        Optional<ProdutoModel> produto = produtoService.buscarProdutoPorId(id);

        return produto.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
        //tirar notfound
    }

    //atualiza informacoes do produto: nome, descricao, preco, quantidade no estoque
    @PatchMapping(path = "{id}")
    public ResponseEntity<ProdutoModel> atualizarProduto(@PathVariable Long id, @RequestBody ProdutoModel produtoUpdate) {
        ProdutoModel atualizarProduto = produtoService.atualizarProdutoporId(id, produtoUpdate);
        return ResponseEntity.ok(atualizarProduto);
    }

    //exclui produto por id
    @DeleteMapping("{id}")
    public ResponseEntity<?> deletarProdutoPorID(@PathVariable Long id) {
            produtoService.deletarProduto(id);
        return ResponseEntity.ok().body("Produto(a) excluído(a) com sucesso!");

    }
}
