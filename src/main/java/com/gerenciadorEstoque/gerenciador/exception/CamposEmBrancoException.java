package com.gerenciadorEstoque.gerenciador.exception;

public class CamposEmBrancoException extends RuntimeException {
    public CamposEmBrancoException() {
        super("Campos obrigatórios não informados.");
    }
}

