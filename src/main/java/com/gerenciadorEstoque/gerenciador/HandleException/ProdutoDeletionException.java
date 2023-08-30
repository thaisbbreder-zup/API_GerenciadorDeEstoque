package com.gerenciadorEstoque.gerenciador.HandleException;

public class ProdutoDeletionException extends RuntimeException {
    public ProdutoDeletionException(Long id) {
        super("Erro ao excluir o produto com ID: " + id + ". Verifique se esse ID existe.");
    }
}
