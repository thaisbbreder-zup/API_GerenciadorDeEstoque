package com.gerenciadorEstoque.gerenciador.exception;

public class IdNotFoundException extends RuntimeException {
    public IdNotFoundException(Long id) {
        super("Não existe produto com o ID: " + id);
    }
}
