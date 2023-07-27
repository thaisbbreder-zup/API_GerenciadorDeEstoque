package com.catalisa.agendaTelefonica.service;


import com.catalisa.agendaTelefonica.model.ContatosModel;
import com.catalisa.agendaTelefonica.repository.ContatosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


// anotação para indicar pro spring que essa classe é um serviço


// ESSA CLASSE VAI CONTER TODOS OS MÉTODOS E REGRAS DE NEGÓCIOS
@Service
public class ContatosService {


    // anotação para injetar a interface dentro da classe service
    @Autowired
    ContatosRepository contatosRepository;


    // método que busca todos os contatos do banco
    public List<ContatosModel> buscarTodos(){
        return contatosRepository.findAll();
    }


    // método que busca um contato pelo id
    public Optional<ContatosModel> buscarPorId(Long id){
        return contatosRepository.findById(id);
    }


    // método que cadastra um novo contato no banco
    public ContatosModel cadastrar(ContatosModel contatoModel){
// DTO
// contatoModel.getId();
// contatoModel.getNome();
// contatoModel.getTelefone();


        return contatosRepository.save(contatoModel);
    }


    // método para alterar um contato já existente
    public ContatosModel alterar(Long id, ContatosModel contatoModel){


        ContatosModel contatos = buscarPorId(id).get();


        if(contatoModel.getNome() != null){
            contatos.setNome(contatoModel.getNome());
        }
        if(contatoModel.getTelefone() != null){
            contatos.setTelefone(contatoModel.getTelefone());
        }
        return contatosRepository.save(contatos);
    }


    // método para deletar um contato já existente
    public void deletar(Long id){
        contatosRepository.deleteById(id);
    }


}
