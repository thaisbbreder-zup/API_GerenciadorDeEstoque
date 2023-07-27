package com.catalisa.gereciadorBiblioteca.service;


import com.catalisa.gereciadorBiblioteca.model.BibliotecaModel;
import com.catalisa.gereciadorBiblioteca.repository.BibliotecaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BibliotecaService {

    @Autowired
    private BibliotecaRepository livroRepository;

    public List<BibliotecaModel> buscarTodos() {
        return livroRepository.findAll();
    }

    public Optional<BibliotecaModel> buscarPorId(Long id) {
        return livroRepository.findById(id);
    }

    public BibliotecaModel cadastrar(BibliotecaModel livroModel) {
        return livroRepository.save(livroModel);
    }

    public BibliotecaModel alterar(Long id, BibliotecaModel livroModel) {
        BibliotecaModel livroExistente = livroRepository.findById(id).orElse(null);
        if (livroExistente != null) {
            livroExistente.setNome(livroModel.getNome());
            livroExistente.setAutor(livroModel.getAutor());
            livroExistente.setDataLancamento(livroModel.getDataLancamento());
            livroExistente.setCodigoLivro(livroModel.getCodigoLivro());
            return livroRepository.save(livroExistente);
        }
        return null;
    }

    public void deletar(Long id) {
        livroRepository.deleteById(id);
    }
}
