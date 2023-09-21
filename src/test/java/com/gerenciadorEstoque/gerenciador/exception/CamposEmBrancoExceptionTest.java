package com.gerenciadorEstoque.gerenciador.exception;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CamposEmBrancoExceptionTest {
    @Test
    public void testCamposEmBrancoException() {
        Exception exception = assertThrows(CamposEmBrancoException.class, () -> {
            throw new CamposEmBrancoException();
        });

        String expectedMessage = "Campos obrigatórios não informados.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}