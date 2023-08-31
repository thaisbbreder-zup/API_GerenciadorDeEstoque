package com.gerenciadorEstoque.gerenciador.exception;

public class ValorInvalidoException extends RuntimeException {
        public ValorInvalidoException() {
            super("O valor informado não pode ser menor que zero.");
        }
    }