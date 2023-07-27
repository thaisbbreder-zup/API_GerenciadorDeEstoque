package com.catalisa.agendaTelefonica.controller;

// anotação para indicar que o controlador é do tipo rest com os 4 endpoints do CRUD

import com.catalisa.agendaTelefonica.model.ContatosModel;
import com.catalisa.agendaTelefonica.service.ContatosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;


@RestController
public class ContatosController {


    @Autowired
    ContatosService contatosService;


// ENDPOINTS


// REQUISIÇÃO GET - buscar contatos dentro do banco de dados


    //mapeando os metodos como get
    @GetMapping(path = "/contatos")
    public List<ContatosModel> buscaTodosContatos(){
        return contatosService.buscarTodos();
    }


    //mapeando os metodos como get
    @GetMapping(path = "/contatos/{id}")
    public Optional<ContatosModel> buscarContatoPorId(@PathVariable Long id){
        return contatosService.buscarPorId(id);
    }


    // ENDPOINT DE INSERÇÃO
// Requisição post - Insere algum dado dentro do banco
    @PostMapping(path = "/contatos")
// anotação para mostrar resposta de criação pro usuario
    @ResponseStatus(HttpStatus.CREATED)
    public ContatosModel cadastrarNovoContato(@RequestBody ContatosModel contatoModel){
        return contatosService.cadastrar(contatoModel);
    }


    // ENDPOINT DE ALTERAÇÃO
// Requisição PUT
    @PutMapping(path = "/contatos/{id}")
    public ContatosModel alteraContato(@PathVariable Long id, @RequestBody ContatosModel contatoModel){
        return contatosService.alterar(id, contatoModel);
    }


    // ENDPOINT PARA DELETAR
// Requisição do tipo DELETE - deleta algum dado dentro do banco
    @DeleteMapping(path = "/contatos/{id}")
    public void deletaContato(@PathVariable Long id){
        contatosService.deletar(id);
    }




}
