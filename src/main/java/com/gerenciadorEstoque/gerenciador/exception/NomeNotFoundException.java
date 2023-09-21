package com.gerenciadorEstoque.gerenciador.exception;

public class NomeNotFoundException extends RuntimeException {
    public NomeNotFoundException(String nome) {
        super("Produto não encontrado pelo nome '" + nome + "'.");
    }
}