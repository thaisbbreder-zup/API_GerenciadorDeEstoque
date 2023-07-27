package com.catalisa.gereciadorBiblioteca.repository;

import com.catalisa.gereciadorBiblioteca.model.BibliotecaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BibliotecaRepository extends JpaRepository<BibliotecaModel, Long> {

}
