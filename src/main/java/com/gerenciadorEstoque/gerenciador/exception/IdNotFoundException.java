package com.gerenciadorEstoque.gerenciador.exception;

public class IdNotFoundException extends RuntimeException {
    public IdNotFoundException(Long id) {
        super("Produto não encontrado pelo ID: " + id);
    }
}
