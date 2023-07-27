package com.catalisa.gereciadorBiblioteca.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TB_LIVROS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BibliotecaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(length = 100, nullable = false)
    private String autor;

    @Column(nullable = false)
    private Date dataLancamento;

    @Column(length = 10, nullable = false)
    private String codigoLivro;
}
