package com.gerenciadorEstoque.gerenciador.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoRequestDTO {
    private String nome;
    private String descricao;
    private Double quantidadeEstoque;
    private Double precoUnitario;
}
