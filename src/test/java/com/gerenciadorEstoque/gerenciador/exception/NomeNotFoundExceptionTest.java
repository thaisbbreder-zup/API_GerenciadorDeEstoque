package com.gerenciadorEstoque.gerenciador.exception;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NomeNotFoundExceptionTest {
    @Test
    public void testNomeNotFoundException() {
        String nome = "ProdutoXYZ";

        Exception exception = assertThrows(NomeNotFoundException.class, () -> {
            throw new NomeNotFoundException(nome);
        });

        String expectedMessage = "Produto não encontrado pelo nome '" + nome + "'.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}