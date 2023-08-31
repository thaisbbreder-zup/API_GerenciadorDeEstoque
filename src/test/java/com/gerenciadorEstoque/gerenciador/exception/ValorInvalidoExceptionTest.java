package com.gerenciadorEstoque.gerenciador.exception;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ValorInvalidoExceptionTest {

    @Test
    public void testValorInvalidoExceptionMessage() {
        Exception exception = assertThrows(ValorInvalidoException.class, () -> {
            throw new ValorInvalidoException();
        });

        String expectedMessage = "O valor informado não pode ser menor que zero.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
