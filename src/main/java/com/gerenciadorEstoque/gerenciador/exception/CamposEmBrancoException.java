package com.gerenciadorEstoque.gerenciador.exception;

public class CamposEmBrancoException extends RuntimeException {
    public CamposEmBrancoException() {
        super("Verifique se todos os campos obrigatórios (dataCadastro, nome, descricao, quantidadeEstoque, precoUnitario) foram informados!");
    }
}

