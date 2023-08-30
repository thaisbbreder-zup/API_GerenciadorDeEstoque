package com.gerenciadorEstoque.gerenciador.service;

import com.gerenciadorEstoque.gerenciador.exception.CamposEmBrancoException;
import com.gerenciadorEstoque.gerenciador.exception.IdNotFoundException;
import com.gerenciadorEstoque.gerenciador.exception.NomeNotFoundException;
import com.gerenciadorEstoque.gerenciador.model.ProdutoModel;
import com.gerenciadorEstoque.gerenciador.repository.ProdutoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProdutoServiceTest {

    @InjectMocks
    private ProdutoService produtoService;

    @Mock
    private ProdutoRepository produtoRepositoryMock;

    //CADASTRAR FALTANDO CAMPO OBRIGATORIO => CamposEmBrancoException
    @Test
    public void cadastrarComDadosNulos() {
        ProdutoService produtoService = new ProdutoService();
        ProdutoModel produtoNulo = new ProdutoModel(); //vazio

        assertThrows(CamposEmBrancoException.class, () -> {
            produtoService.cadastrarProduto(produtoNulo);
        }, "Verifique se todos os campos obrigatórios (dataCadastro, nome, descricao, quantidadeEstoque, precoUnitario) foram informados!");
    }

    //LISTAR QUANDO NAO HOUVER PRODUTOS CADASTRADOS
    @Test
    @DisplayName("Listar quando não houver produtos cadastrados")
    public void testarListarQuandoNaoHouverProdutosCadastrados() {
        when(produtoRepositoryMock.findAll()).thenReturn(Collections.emptyList());

        List<ProdutoModel> produtos = produtoService.getAll();

        assertTrue(produtos.isEmpty());

        verify(produtoRepositoryMock, times(1)).findAll();
        verifyNoMoreInteractions(produtoRepositoryMock);
    }

    //BUSCAR QUANDO O NOME NAO EXISTE = NomeNotFoundException
    @Test
    @DisplayName("Buscar produtos por nome inexistente")
    public void testarBuscarProdutosPorNomeInexistente() {
        String nome = "Livro";

        when(produtoRepositoryMock.findByNome(nome)).thenReturn(null);

        assertThrows(NomeNotFoundException.class, () -> {
                    produtoService.getByNome(nome);
                }, "Não existe produto com o nome '" + nome + "'."
        );

        verify(produtoRepositoryMock, times(1)).findByNome(nome);
        verifyNoMoreInteractions(produtoRepositoryMock);
    }

/*    //BUSCAR QUANDO O ID NAO EXISTE
    @Test
    @DisplayName("Buscar produtos por id inexistente")
    public void testarBuscarProdutosPorIdInexistente() {
        Long id = 999L;

        when(produtoService.getById(id)).thenReturn(Optional.empty());

        assertThrows(IdNotFoundException.class, () -> {
            produtoService.getById(id);
        }, "Não existe produto com o ID: " + id);

        verify(produtoRepositoryMock, times(1)).existsById(id);
        verifyNoMoreInteractions(produtoRepositoryMock);
    }*/

    //ATUALIZAR QUANDO O ID NAO EXISTE
    @Test
    @DisplayName("Atualizar produtos quando o ID é inexistente")
    public void testarAtualizarProdutosComIdInexistente() {
        Long id = 999L;

        when(produtoRepositoryMock.findById(id)).thenReturn(Optional.empty());

        assertThrows(IdNotFoundException.class, () -> {
            ProdutoModel produtoUpdate = new ProdutoModel();
            produtoUpdate.setNome("Shampoo Dove");
            produtoService.atualizarProdutoporId(id, produtoUpdate);
        });

        verify(produtoRepositoryMock, times(1)).findById(id);
        verifyNoMoreInteractions(produtoRepositoryMock);
    }

    //EXCLUIR QUANDO O ID NAO EXISTE
    @Test
    @DisplayName("Excluir produto por ID inexistente")
    public void testarExcluirProdutoPorId() {
        long id = 999;

        when(produtoRepositoryMock.existsById(id)).thenReturn(false);

        assertThrows(IdNotFoundException.class, () -> {
            produtoService.deletarProduto(id);
        });

        verify(produtoRepositoryMock, times(1)).existsById(id);
        verifyNoMoreInteractions(produtoRepositoryMock);
    }

}