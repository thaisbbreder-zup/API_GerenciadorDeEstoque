package com.gerenciadorEstoque.gerenciador.exception;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NomeNotFoundExceptionTest {
    @Test
    public void testNomeNotFoundException() {
        String nome = "Produto";
        NomeNotFoundException exception = new NomeNotFoundException(nome);
        assertEquals("Não existe produto com o nome '" + nome + "'.", exception.getMessage());
    }
}