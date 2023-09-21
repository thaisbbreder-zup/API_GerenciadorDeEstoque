package com.gerenciadorEstoque.gerenciador.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gerenciadorEstoque.gerenciador.model.ProdutoModel;
import com.gerenciadorEstoque.gerenciador.service.ProdutoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class ProdutoControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProdutoService produtoService;

    //CADASTRAR PRODUTOS CORRETAMENTE
    @Test
    @DisplayName("Cadastrar produto")
    public void testarCadastrarProduto() throws Exception {
        ProdutoModel produtoModel = new ProdutoModel();
        produtoModel.setId(1L);
        produtoModel.setNome("Shampoo");
        produtoModel.setDescricao("Shampoo Elseve para cabelos ondulados");
        produtoModel.setPrecoUnitario(12.99);
        produtoModel.setQuantidadeEstoque(10);
        produtoModel.setDataCadastro(LocalDateTime.now());

        when(produtoService.cadastrarProduto(Mockito.any(ProdutoModel.class)))
                .thenReturn(produtoModel);

        mockMvc.perform(post("/produto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(produtoModel)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Shampoo"))
                .andExpect(jsonPath("$.descricao").value("Shampoo Elseve para cabelos ondulados"))
                .andExpect(jsonPath("$.precoUnitario").value(12.99))
                .andExpect(jsonPath("$.quantidadeEstoque").value(10));
    }

    //CADASTRAR CAMPOS EM BRANCO/NULO
    @Test
    @DisplayName("Cadastrar produto com campos em branco ou nulos")
    public void testarCadastrarProdutoComCamposEmBrancoOuNulos() throws Exception {
        ProdutoModel produtoModel = new ProdutoModel();
        produtoModel.setId(1L);
        produtoModel.setNome("");
        produtoModel.setDescricao(null);
        produtoModel.setPrecoUnitario(5.0);
        produtoModel.setQuantidadeEstoque(10);

        mockMvc.perform(post("/produto")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(produtoModel)));
    }

    //LISTAR TODOS OS PRODUTOS CORRETAMENTE
    @Test
    @DisplayName("Listar produtos sem filtro de nome")
    public void testarListarTodosProdutos() throws Exception {
        ProdutoModel produtoModel1 = new ProdutoModel();
        produtoModel1.setId(1L);
        produtoModel1.setNome("Shampoo");
        produtoModel1.setDescricao("Shampoo Elseve para cabelos ondulados");
        produtoModel1.setPrecoUnitario(12.99);
        produtoModel1.setQuantidadeEstoque(10);

        ProdutoModel produtoModel2 = new ProdutoModel();
        produtoModel2.setId(2L);
        produtoModel2.setNome("Sabonete");
        produtoModel2.setDescricao("Sabonete perfumado");
        produtoModel2.setPrecoUnitario(2.5);
        produtoModel2.setQuantidadeEstoque(20);

        List<ProdutoModel> produtos = new ArrayList<>();
        produtos.add(produtoModel1);
        produtos.add(produtoModel2);

        when(produtoService.getAll()).thenReturn(produtos);

        mockMvc.perform(get("/produto"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nome").value("Shampoo"))
                .andExpect(jsonPath("$[0].descricao").value("Shampoo Elseve para cabelos ondulados"))
                .andExpect(jsonPath("$[0].precoUnitario").value(12.99))
                .andExpect(jsonPath("$[0].quantidadeEstoque").value(10))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].nome").value("Sabonete"))
                .andExpect(jsonPath("$[1].descricao").value("Sabonete perfumado"))
                .andExpect(jsonPath("$[1].precoUnitario").value(2.5))
                .andExpect(jsonPath("$[1].quantidadeEstoque").value(20));

        verify(produtoService, times(1)).getAll();
    }

    //LISTAR COM FILTRO DE NOME
    @Test
    @DisplayName("Listar todos os produtos")
    public void testarListarTodosProdutosComNome() throws Exception {
        ProdutoModel produtoModel1 = new ProdutoModel();
        produtoModel1.setId(1L);
        produtoModel1.setNome("Shampoo");
        produtoModel1.setDescricao("Shampoo Elseve para cabelos ondulados");
        produtoModel1.setPrecoUnitario(12.99);
        produtoModel1.setQuantidadeEstoque(10);

        ProdutoModel produtoModel2 = new ProdutoModel();
        produtoModel2.setId(2L);
        produtoModel2.setNome("Sabonete");
        produtoModel2.setDescricao("Sabonete perfumado");
        produtoModel2.setPrecoUnitario(2.5);
        produtoModel2.setQuantidadeEstoque(20);

        List<ProdutoModel> produtos = new ArrayList<>();
        produtos.add(produtoModel1);
        produtos.add(produtoModel2);

        when(produtoService.getByNome("Shampoo")).thenReturn(produtos);

        mockMvc.perform(get("/produto")
                        .param("nome", "Shampoo"))
                .andExpect(status().isOk())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nome").value("Shampoo"))
                .andExpect(jsonPath("$[0].descricao").value("Shampoo Elseve para cabelos ondulados"))
                .andExpect(jsonPath("$[0].precoUnitario").value(12.99))
                .andExpect(jsonPath("$[0].quantidadeEstoque").value(10))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].nome").value("Sabonete"))
                .andExpect(jsonPath("$[1].descricao").value("Sabonete perfumado"))
                .andExpect(jsonPath("$[1].precoUnitario").value(2.5))
                .andExpect(jsonPath("$[1].quantidadeEstoque").value(20));

        verify(produtoService, times(1)).getByNome("Shampoo");
    }

    //BUSCA POR ID EXISTENTE
    @Test
    @DisplayName("Buscar produto por ID cadastrado")
    public void testarBuscarProdutoPorID() throws Exception {
        ProdutoModel produtoModel = new ProdutoModel();
        produtoModel.setId(1L);
        produtoModel.setNome("Shampoo");
        produtoModel.setDescricao("Shampoo Elseve para cabelos ondulados");
        produtoModel.setPrecoUnitario(12.99);
        produtoModel.setQuantidadeEstoque(10);

        when(produtoService.getById(1L)).thenReturn(Optional.of(produtoModel));

        mockMvc.perform(get("/produto/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Shampoo"))
                .andExpect(jsonPath("$.descricao").value("Shampoo Elseve para cabelos ondulados"))
                .andExpect(jsonPath("$.precoUnitario").value(12.99))
                .andExpect(jsonPath("$.quantidadeEstoque").value(10));
    }

    //ATUALIZA TODOS OS CAMPOS QUANDO ID EXISTE
    @Test
    @DisplayName("Atualizar produto existente")
    public void testarAtualizarProduto() throws Exception {
        Long produtoId = 1L;

        ProdutoModel produtoModel = new ProdutoModel();
        produtoModel.setId(produtoId);
        produtoModel.setNome("Novo Shampoo");
        produtoModel.setDescricao("Shampoo Elseve para cabelos ondulados - atualizado");
        produtoModel.setPrecoUnitario(15.99);
        produtoModel.setQuantidadeEstoque(20);

        when(produtoService.atualizarProdutoporId(eq(produtoId), Mockito.any(ProdutoModel.class)))
                .thenReturn(produtoModel);

        mockMvc.perform(patch("/produto/{id}", produtoId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(produtoModel)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(produtoId))
                .andExpect(jsonPath("$.nome").value("Novo Shampoo"))
                .andExpect(jsonPath("$.descricao").value("Shampoo Elseve para cabelos ondulados - atualizado"))
                .andExpect(jsonPath("$.precoUnitario").value(15.99))
                .andExpect(jsonPath("$.quantidadeEstoque").value(20))
                .andDo(print());
    }

    // APENAS ALGUNS CAMPOS SERÃO ATUALIZADOS, OS OUTROS PERMANECERAO IGUAIS
    @Test
    @DisplayName("Atualizar produto existente")
    public void testarAtualizarDoisProduto() throws Exception {
        Long produtoId = 1L;

        ProdutoModel produtoModel = new ProdutoModel();
        produtoModel.setId(produtoId);
        produtoModel.setNome("Novo Shampoo");
        produtoModel.setDescricao("Novo Shampoo Elseve para cabelos ondulados");
        produtoModel.setPrecoUnitario(15.99);
        produtoModel.setQuantidadeEstoque(20);

        when(produtoService.atualizarProdutoporId(eq(produtoId), Mockito.any(ProdutoModel.class)))
                .thenReturn(produtoModel);

        mockMvc.perform(patch("/produto/{id}", produtoId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(produtoModel)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(produtoId))
                .andExpect(jsonPath("$.nome").value("Novo Shampoo"))
                .andExpect(jsonPath("$.descricao").value("Novo Shampoo Elseve para cabelos ondulados"))
                .andExpect(jsonPath("$.precoUnitario").value(15.99))
                .andExpect(jsonPath("$.quantidadeEstoque").value(20))
                .andDo(print());
    }

    //DELETA ID EXISTENTE
    @Test
    @DisplayName("Deletar produto existente")
    public void testarDeletarProduto() throws Exception {
        Long produtoId = 2L;

        doNothing().when(produtoService).deletarProduto(produtoId);

        mockMvc.perform(delete("/produto/{id}", 2)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}