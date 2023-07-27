package com.catalisa.agendaTelefonica.repository;
// anotação para o spring identificar que é um repositório


import com.catalisa.agendaTelefonica.model.ContatosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ContatosRepository extends JpaRepository<ContatosModel, Long> { //possar a classe modelo e o tipo do ID


}
