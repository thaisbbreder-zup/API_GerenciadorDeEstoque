package com.catalisa.gerenciadorAdocao.controller;

import com.catalisa.gerenciadorAdocao.model.AnimalModel;
import com.catalisa.gerenciadorAdocao.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;

@RestController
//@Api(value = "AnimalController")
@RequestMapping("/animais")
public class AnimalController {

    @Autowired
    AnimalService animalService;

    @Autowired
    private Validator validator;

    //CADASTRAR NOVO ANIMAL
    @PostMapping
    public ResponseEntity<AnimalModel> cadastrarAnimal(@RequestBody @Valid AnimalModel animalModel) {
        AnimalModel novoCadastro = animalService.cadastrarAnimal(animalModel);
        return new ResponseEntity<>(animalModel, HttpStatus.CREATED);
    }

    //LISTAR TODOS ANIMAIS E POR NOME , TAMANHO E POR DISPONIVEL
    @GetMapping
    public ResponseEntity<Page<AnimalModel>> listarTodosAnimals(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String tamanho,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        Page<AnimalModel> animaisPage;

        if (StringUtils.hasText(nome)) {
            animaisPage = animalService.getByNome(nome, page, size);
        } else if (StringUtils.hasText(tamanho)) {
            animaisPage = animalService.getByTamanho(tamanho, page, size);
        } else {
            animaisPage = animalService.getAll(page, size);
        }
        return ResponseEntity.ok(animaisPage);
    }


    //BUSCAR POR ID
    @GetMapping(path = "{id}")
    public ResponseEntity<AnimalModel> listarAnimalPorId(@PathVariable Long id) {
        Optional<AnimalModel> animal = animalService.getById(id);
        return ResponseEntity.ok(animal.get());
    }

    //ATUALIZAR INFORMACOES
    @PatchMapping(path = "{id}")
    public ResponseEntity<AnimalModel> atualizarAnimal(@PathVariable Long id, @RequestBody AnimalModel animalUpdate) {
        AnimalModel atualizarAnimal = animalService.atualizarAnimalPorId(id, animalUpdate);
        return ResponseEntity.ok(atualizarAnimal);
    }

    //EXCLUIR CADASTRO
    @DeleteMapping("{id}")
    public ResponseEntity<?> deletarAnimalPorID(@PathVariable Long id) {
        animalService.deletarAnimal(id);
        return ResponseEntity.ok().body("Animal(a) excluído(a) com sucesso!");
    }

}
