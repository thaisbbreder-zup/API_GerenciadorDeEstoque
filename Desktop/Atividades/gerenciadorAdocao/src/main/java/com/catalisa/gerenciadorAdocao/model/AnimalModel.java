package com.catalisa.gerenciadorAdocao.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TB_ANIMAIS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnimalModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "O nome do animal é obrigatório")
    private String nome;
    @NotNull(message = "A idade do animal é obrigatório")
    private int idade;
    @NotBlank(message = "O tamanho do animal é obrigatório")
    private String tamanho;
    @NotBlank(message = "O sexo do animal é obrigatório")
    private String sexo;
    private String descricao;
    private String comentario;
    @NotNull(message = "A disponibilidade do animal é obrigatório")
    private boolean disponivelAdocao;
}
