package com.gerenciadorEstoque.gerenciador.exception;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IdNotFoundExceptionTest {
    @Test
    public void testIdNotFoundException() {
        Long id = 123L;

        Exception exception = assertThrows(IdNotFoundException.class, () -> {
            throw new IdNotFoundException(id);
        });

        String expectedMessage = "Produto não encontrado pelo ID: " + id;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}