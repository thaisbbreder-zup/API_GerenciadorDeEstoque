package com.gerenciadorEstoque.gerenciador.exception;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CamposEmBrancoExceptionTest {
    @Test
    public void testCamposEmBrancoException() {
        CamposEmBrancoException exception = new CamposEmBrancoException();
        assertEquals("Verifique se todos os campos obrigatórios (dataCadastro, nome, descricao, quantidadeEstoque, precoUnitario) foram informados!", exception.getMessage());
    }

}