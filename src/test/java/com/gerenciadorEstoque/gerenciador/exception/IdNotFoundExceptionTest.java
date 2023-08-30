package com.gerenciadorEstoque.gerenciador.exception;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IdNotFoundExceptionTest {
    @Test
    public void testIdNotFoundException() {
        Long id = 123L;
        IdNotFoundException exception = new IdNotFoundException(id);
        assertEquals("Não existe produto com o ID: " + id, exception.getMessage());
    }
}