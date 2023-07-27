package com.catalisa.gereciadorBiblioteca.controller;


import com.catalisa.gereciadorBiblioteca.model.BibliotecaModel;
import com.catalisa.gereciadorBiblioteca.service.BibliotecaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/livros")
public class BibliotecaController {

    @Autowired
    private BibliotecaService livroService;

    @GetMapping
    public List<BibliotecaModel> buscaTodosLivros() {
        return livroService.buscarTodos();
    }

    @GetMapping("/{id}")
    public Optional<BibliotecaModel> buscarLivroPorId(@PathVariable Long id) {
        return livroService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BibliotecaModel cadastrarNovoLivro(@RequestBody BibliotecaModel livroModel) {
        return livroService.cadastrar(livroModel);
    }

    @PutMapping("/{id}")
    public BibliotecaModel alterarLivro(@PathVariable Long id, @RequestBody BibliotecaModel livroModel) {
        return livroService.alterar(id, livroModel);
    }

    @DeleteMapping("/{id}")
    public void deletarLivro(@PathVariable Long id) {
        livroService.deletar(id);
    }
}
