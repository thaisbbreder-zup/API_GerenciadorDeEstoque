package com.gerenciadorEstoque.gerenciador.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_PRODUTOS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoModel {
    @Id //id = primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalDateTime dataCadastro;
    @Column(length = 20, nullable = false)
    private String nome;
    @Column(length = 80, nullable = false)
    private String descricao;
    @Column(nullable = false)
    private Double quantidadeEstoque;
    @Column(nullable = false)
    private Double precoUnitario;
}

