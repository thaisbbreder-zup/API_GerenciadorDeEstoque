package com.gerenciadorEstoque.gerenciador.exception;

public class NomeNotFoundException extends RuntimeException {
    public NomeNotFoundException(String nome) {
        super("Não existe produto com o nome '" + nome + "'.");
    }
}
