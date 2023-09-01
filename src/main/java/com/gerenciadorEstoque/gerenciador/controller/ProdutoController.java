package com.gerenciadorEstoque.gerenciador.controller;

import com.gerenciadorEstoque.gerenciador.model.ProdutoModel;
import com.gerenciadorEstoque.gerenciador.service.ProdutoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@Api(value = "ProdutoController")
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    //CADASTRA NOVOS PRODUTOS
    @ApiOperation(value = "Cadastra um novo produto")
    @PostMapping
    public ResponseEntity<ProdutoModel> cadastrarProduto(@RequestBody @Valid ProdutoModel produtoModel) {
        ProdutoModel novoProduto = produtoService.cadastrarProduto(produtoModel);
        return new ResponseEntity<>(produtoModel, HttpStatus.CREATED);
    }

    //LISTA TODOS PRODUTOS CADASTRADOS E POR NOME
    @ApiOperation(value = "Lista todos os produtos cadastrados e/ou com filtro de nome")
    @GetMapping
    public ResponseEntity<List<ProdutoModel>> listarTodosProdutos(@RequestParam(required = false) String nome) {
        List<ProdutoModel> produtos;
        if (StringUtils.hasText(nome)) {
            produtos = produtoService.getByNome(nome);
        } else {
            produtos = produtoService.getAll();
        }
        return ResponseEntity.ok(produtos);
    }

    //BUSCA PRODUTOS POR ID
    @ApiOperation(value = "Lista produtos cadastrados com filtro de ID")
    @GetMapping(path = "{id}")
    public ResponseEntity<ProdutoModel> listarProdutoPorId(@PathVariable Long id) {
        Optional<ProdutoModel> produto = produtoService.getById(id);
        return ResponseEntity.ok(produto.get());
    }

    //ATUALIZA INFORMACOES DO PRODUTO: nome, descricao, preco, quantidade no estoque
    @ApiOperation(value = "Atualiza nome, descrição, preço ou quantidade de estoque de pelo ID")
    @PatchMapping(path = "{id}")
    public ResponseEntity<ProdutoModel> atualizarProduto(@PathVariable Long id, @RequestBody ProdutoModel produtoUpdate) {
        ProdutoModel atualizarProduto = produtoService.atualizarProdutoporId(id, produtoUpdate);
        return ResponseEntity.ok(atualizarProduto);
    }

    //EXCLUI PRODUTO POR ID
    @ApiOperation(value = "Exclui pelo ID do produto")
    @DeleteMapping("{id}")
    public ResponseEntity<?> deletarProdutoPorID(@PathVariable Long id) {
        produtoService.deletarProduto(id);
        return ResponseEntity.ok().body("Produto(a) excluído(a) com sucesso!");

    }

}
