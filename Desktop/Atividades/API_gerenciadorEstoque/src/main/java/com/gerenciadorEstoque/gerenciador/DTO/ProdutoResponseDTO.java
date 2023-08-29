package com.gerenciadorEstoque.gerenciador.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoResponseDTO {
    private Long id;
    private String nome;
    private String descricao;
    private Double quantidadeEstoque;
    private Double precoUnitario;
}
