package com.catalisa.agendaTelefonica.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;


// indicando que essa classe é uma entidade do banco de dados e uma tabela
@Entity
@Table(name = "TB_CONTATOS")
// anotações de getters, setters e construtores
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContatosModel {


    //anotações para fazer o id ser uma chave primaria e incrementada
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    // anotação para configurar a coluna de nome
    @Column (length = 50, nullable = false)
    private String nome;


    // anotação para configurar a coluna de telefone
    @Column(length = 15, nullable = false)
    private String telefone;




}


