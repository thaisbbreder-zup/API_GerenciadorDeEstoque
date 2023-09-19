/*package com.catalisa.gerenciadorAdocao.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.persistence.*;

    @Entity
    @Table(name = "TB_COMENTARIO")
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public class ComentarioModel {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String texto;

        @ManyToOne
        @JoinColumn(name = "animal_id", nullable = false)
        private AnimalModel animal;

    }

 */
