package com.gerenciadorEstoque.gerenciador.service;

import com.gerenciadorEstoque.gerenciador.exception.CamposEmBrancoException;
import com.gerenciadorEstoque.gerenciador.exception.IdNotFoundException;
import com.gerenciadorEstoque.gerenciador.exception.NomeNotFoundException;
import com.gerenciadorEstoque.gerenciador.exception.ValorInvalidoException;
import com.gerenciadorEstoque.gerenciador.model.ProdutoModel;
import com.gerenciadorEstoque.gerenciador.repository.ProdutoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProdutoServiceTest {

    @InjectMocks
    private ProdutoService produtoService;

    @Mock
    private ProdutoRepository produtoRepositoryMock;

    //CADASTRO BEM SUCEDIDO
    @Test
    public void cadastrarBemSucedido() {
        ProdutoModel produtoEmBranco = new ProdutoModel();
        produtoEmBranco.setNome("Sabonete");
        produtoEmBranco.setDescricao("Sabonete Liquido Dove");
        produtoEmBranco.setQuantidadeEstoque(10);
        produtoEmBranco.setPrecoUnitario(8.89);

        when(produtoRepositoryMock.save(any(ProdutoModel.class))).thenReturn(produtoEmBranco);

        ProdutoModel produtoCadastrado = produtoService.cadastrarProduto(produtoEmBranco);

        assertEquals(produtoEmBranco, produtoCadastrado);

        verify(produtoRepositoryMock, times(1)).save(produtoEmBranco);
        verifyNoMoreInteractions(produtoRepositoryMock);
    }

    //CADASTRAR FALTANDO CAMPO OBRIGATORIO => CamposEmBrancoException
    @Test
    public void cadastrarComDadosNulos() {
        ProdutoService produtoService = new ProdutoService();
        ProdutoModel produtoNulo = new ProdutoModel();

        assertThrows(CamposEmBrancoException.class, () -> {
            produtoService.cadastrarProduto(produtoNulo);
        }, "Campos obrigatórios não informados.");
    }

    @Test
    public void cadastrarComDadosEmBranco() {
        ProdutoService produtoService = new ProdutoService();
        ProdutoModel produtoEmBranco = new ProdutoModel();
        produtoEmBranco.setNome("");
        produtoEmBranco.setDescricao("");
        produtoEmBranco.setQuantidadeEstoque(null);
        produtoEmBranco.setPrecoUnitario(null);

        assertThrows(CamposEmBrancoException.class, () -> {
            produtoService.cadastrarProduto(produtoEmBranco);
        }, "Campos obrigatórios não informados.");
    }

    //CADASTRO COM VALOR DO ESTOQUE INVALIDO (MENOR QUE ZERO) => ValorInvalidoException
    @Test
    public void testCadastrarProdutoComEstoqueInvalido() {
        ProdutoModel produtoModel = new ProdutoModel();
        produtoModel.setNome("Sabonete");
        produtoModel.setDescricao("Sabonete Liquido");
        produtoModel.setQuantidadeEstoque(-1);
        produtoModel.setPrecoUnitario(50.0);

        assertThrows(ValorInvalidoException.class, () -> produtoService.cadastrarProduto(produtoModel));
    }

    //CADASTRO COM PRECO INVALIDO (MENOR QUE ZERO) => ValorInvalidoException
    @Test
    public void testCadastrarProdutoComPrecoInvalido() {
        ProdutoModel produtoModel = new ProdutoModel();
        produtoModel.setNome("Sabonete");
        produtoModel.setDescricao("Sabonete Liquido");
        produtoModel.setQuantidadeEstoque(1);
        produtoModel.setPrecoUnitario(-50.0);

        assertThrows(ValorInvalidoException.class, () -> produtoService.cadastrarProduto(produtoModel));
    }

    //LISTAR QUANDO HOUVER PRODUTOS CADASTRADOS
    @Test
    @DisplayName("Listar quando houver produtos cadastrados")
    public void testarListarQuandoHouverProdutosCadastrados() {
        ProdutoModel produto1 = new ProdutoModel();
        produto1.setId(1L);
        produto1.setNome("Sabonete");
        produto1.setDescricao("Sabonete Liquido Dove");
        produto1.setQuantidadeEstoque(10);
        produto1.setPrecoUnitario(8.89);

        ProdutoModel produto2 = new ProdutoModel();
        produto2.setId(2L);
        produto2.setNome("Shampoo");
        produto2.setDescricao("Shampoo Pantene");
        produto2.setQuantidadeEstoque(5);
        produto2.setPrecoUnitario(12.99);
        //verificar o asList
        List<ProdutoModel> produtosCadastrados = Arrays.asList(produto1, produto2);

        when(produtoRepositoryMock.findAll()).thenReturn(produtosCadastrados);

        List<ProdutoModel> produtosRetornados = produtoService.getAll();

        assertEquals(produtosCadastrados.size(), produtosRetornados.size());
        assertEquals(produtosCadastrados, produtosRetornados);

        verify(produtoRepositoryMock, times(1)).findAll();
        verifyNoMoreInteractions(produtoRepositoryMock);
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

    // BUSCAR QUANDO O NOME EXISTE
    @Test
    @DisplayName("Buscar produtos por nome existente")
    public void testarBuscarProdutosPorNomeExistente() {
        String nome = "Sabonete";

        ProdutoModel produtoExistente = new ProdutoModel();
        produtoExistente.setId(1L);
        produtoExistente.setNome(nome);
        produtoExistente.setDescricao("Sabonete flor de tangerina - Dove");
        produtoExistente.setQuantidadeEstoque(10);
        produtoExistente.setPrecoUnitario(3.99);

        List<ProdutoModel> produtosEncontrados = new ArrayList<>();
        produtosEncontrados.add(produtoExistente);

        when(produtoRepositoryMock.findByNome(nome)).thenReturn(produtosEncontrados);

        List<ProdutoModel> produtosRetornados = produtoService.getByNome(nome);

        assertEquals(produtosEncontrados, produtosRetornados);

        verify(produtoRepositoryMock, times(1)).findByNome(nome);
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
        }, "Não existe produto com o nome '" + nome + "'.");

        verify(produtoRepositoryMock, times(1)).findByNome(nome);
        verifyNoMoreInteractions(produtoRepositoryMock);
    }

    //BUSCAR QUANDO ID EXISTE
    @Test
    @DisplayName("Buscar produto por ID existente")
    public void testarBuscarProdutoPorIdExistente() {
        Long id = 1L;
        ProdutoModel produtoExistente = new ProdutoModel();
        produtoExistente.setId(id);
        produtoExistente.setNome("Sabonete");
        produtoExistente.setDescricao("Sabonete flor de tangerina - Dove");
        produtoExistente.setQuantidadeEstoque(10);
        produtoExistente.setPrecoUnitario(3.99);

        when(produtoRepositoryMock.existsById(id)).thenReturn(true);
        when(produtoRepositoryMock.findById(id)).thenReturn(Optional.of(produtoExistente));

        Optional<ProdutoModel> produtoRetornado = produtoService.getById(id);

        assertTrue(produtoRetornado.isPresent());
        assertEquals(produtoExistente, produtoRetornado.get());

        verify(produtoRepositoryMock, times(1)).existsById(id);
        verify(produtoRepositoryMock, times(1)).findById(id);
        verifyNoMoreInteractions(produtoRepositoryMock);
    }

    //BUSCAR QUANDO O ID NAO EXISTE
    @Test
    @DisplayName("Buscar produtos por id inexistente")
    public void testarBuscarProdutosPorIdInexistente() {
        Long idInexistente = 999L;

        when(produtoRepositoryMock.existsById(idInexistente)).thenReturn(false);

        assertThrows(IdNotFoundException.class, () -> produtoService.getById(idInexistente));

        verify(produtoRepositoryMock, times(1)).existsById(idInexistente);
        verifyNoMoreInteractions(produtoRepositoryMock);
    }


    //ATUALIZAR QUANDO O ID EXISTE
/*    @Test
    @DisplayName("Atualizar produtos quando o ID é inexistente")
    public void testarAtualizarProdutosComIdExistente() {
        Long id = 3L;

        when(produtoRepositoryMock.findById(id)).thenReturn(Optional.empty());

        assertThrows(IdNotFoundException.class, () -> {
            ProdutoModel produtoUpdate = new ProdutoModel();
            produtoUpdate.setNome("Shampoo Dove");
            produtoService.atualizarProdutoporId(id, produtoUpdate);
        });

        verify(produtoRepositoryMock, times(1)).findById(id);
        verifyNoMoreInteractions(produtoRepositoryMock);
    }*/

    @Test
    @DisplayName("Atualizar produto com dados válidos e ID existente")
    public void testarAtualizarProdutoComDadosValidosEIdExistente() {
        Long idExistente = 1L;

        ProdutoModel produtoExistente = new ProdutoModel();
        produtoExistente.setId(idExistente);
        produtoExistente.setNome("Sabonete");
        produtoExistente.setDescricao("Sabonete Liquido - Dove");
        produtoExistente.setQuantidadeEstoque(10);
        produtoExistente.setPrecoUnitario(5.99);

        ProdutoModel produtoUpdate = new ProdutoModel();
        produtoUpdate.setNome("Sabonete Liquido");
        produtoUpdate.setDescricao("Sabonete Liquido flor de cerejeira - Dove");
        produtoUpdate.setQuantidadeEstoque(15);
        produtoUpdate.setPrecoUnitario(6.99);

        when(produtoRepositoryMock.findById(idExistente)).thenReturn(Optional.of(produtoExistente));

        ProdutoModel produtoAtualizado = produtoService.atualizarProdutoporId(idExistente, produtoUpdate);

        assertEquals(produtoUpdate, produtoAtualizado);

        verify(produtoRepositoryMock, times(1)).findById(idExistente);
        verifyNoMoreInteractions(produtoRepositoryMock);
    }

    //ATUALIZAR COM DADOS EM BRANCO/NULO => CamposEmBrancoException
    @Test
    public void atualizarComDadosInvalidos() {
        Long id = 1L;
        ProdutoModel produtoExistente = new ProdutoModel();
        produtoExistente.setId(id);
        produtoExistente.setNome("Sabonete");
        produtoExistente.setDescricao("Sabonete flor de tangerina - Dove");
        produtoExistente.setQuantidadeEstoque(10);
        produtoExistente.setPrecoUnitario(3.99);

        when(produtoRepositoryMock.findById(id)).thenReturn(Optional.of(produtoExistente));

        ProdutoModel produtoUpdate = new ProdutoModel();
        produtoUpdate.setId(id);
        produtoUpdate.setNome("");
        produtoUpdate.setDescricao("Sabonete Liquido flor de tangerina - Dove");
        produtoUpdate.setQuantidadeEstoque(null);
        produtoUpdate.setPrecoUnitario(3.99);

        assertThrows(CamposEmBrancoException.class, () -> {
            produtoService.atualizarProdutoporId(id, produtoUpdate);
        }, "Campos obrigatórios não informados.");

        verify(produtoRepositoryMock, times(1)).findById(id);
        verifyNoMoreInteractions(produtoRepositoryMock);
    }

    //ATUALIZAR COM VALOR INVALIDO (MENOR QUE 0)
    @Test
    public void atualizarComValoresInvalidos() {
        Long id = 1L;

        ProdutoModel produtoExistente = new ProdutoModel();
        produtoExistente.setId(id);
        produtoExistente.setNome("Sabonete");
        produtoExistente.setDescricao("Sabonete flor de tangerina - Dove");
        produtoExistente.setQuantidadeEstoque(10);
        produtoExistente.setPrecoUnitario(3.99);

        when(produtoRepositoryMock.findById(id)).thenReturn(Optional.of(produtoExistente));

        ProdutoModel produtoUpdate = new ProdutoModel();
        produtoUpdate.setId(id);
        produtoUpdate.setNome("Sabonete");
        produtoUpdate.setDescricao("Sabonete cereja - Dove");
        produtoUpdate.setQuantidadeEstoque(-10);
        produtoUpdate.setPrecoUnitario(-3.99);

        assertThrows(ValorInvalidoException.class, () -> produtoService.atualizarProdutoporId(id, produtoUpdate));
    }

    //ATUALIZAR QUANDO O ID NAO EXISTE = IdNotFoundException
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

    //EXCLUIR QUANDO O ID EXISTE
    @Test
    @DisplayName("Excluir produto por ID existente")
    public void testarExcluirProdutoPorIdExistente() {
        long id = 3L;

        when(produtoRepositoryMock.existsById(id)).thenReturn(true);

        produtoService.deletarProduto(id);

        verify(produtoRepositoryMock, times(1)).existsById(id);
        verify(produtoRepositoryMock, times(1)).deleteById(id);
        verifyNoMoreInteractions(produtoRepositoryMock);
    }

    //EXCLUIR QUANDO O ID NAO EXISTE = IdNotFoundException
    @Test
    @DisplayName("Excluir produto por ID inexistente")
    public void testarExcluirProdutoPorIdInexistente() {
        long id = 999;

        when(produtoRepositoryMock.existsById(id)).thenReturn(false);

        assertThrows(IdNotFoundException.class, () -> {
            produtoService.deletarProduto(id);
        });

        verify(produtoRepositoryMock, times(1)).existsById(id);
        verifyNoMoreInteractions(produtoRepositoryMock);
    }

}