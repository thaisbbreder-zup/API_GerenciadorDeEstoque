package com.catalisa.gerenciadorAdocao.service;

import com.catalisa.gerenciadorAdocao.model.AnimalModel;
import com.catalisa.gerenciadorAdocao.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {
    @Autowired
    AnimalRepository animalRepository;

    public AnimalModel cadastrarAnimal(AnimalModel updateAnimal) {
        return animalRepository.save(updateAnimal);
    }

    public Page<AnimalModel> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return animalRepository.findAll(pageable);
    }

    public Page<AnimalModel> getByNome(String nome, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return animalRepository.findByNome(nome, pageable);
    }

    public Page<AnimalModel> getByTamanho(String tamanho, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return animalRepository.findByTamanho(tamanho, pageable);
    }

    public Optional<AnimalModel> getById(Long id) {
        return animalRepository.findById(id);
    }

    public AnimalModel atualizarAnimalPorId(Long id, AnimalModel updateAnimal) {
        Optional<AnimalModel> animalOptional = animalRepository.findById(id);
        return updateAnimal;
    }

    public void deletarAnimal(Long id) {
        animalRepository.deleteById(id);
    }
}