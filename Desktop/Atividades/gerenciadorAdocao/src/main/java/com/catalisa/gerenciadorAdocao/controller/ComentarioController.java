/* package com.catalisa.gerenciadorAdocao.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping("/animais")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @PostMapping("/{id}/comentarios")
    public ResponseEntity<ComentarioModel> adicionarComentario(@PathVariable Long id, @RequestBody @Valid ComentarioModel comentario) {
        ComentarioModel novoComentario = comentarioService.adicionarComentario(id, comentario);
        return new ResponseEntity<>(novoComentario, HttpStatus.CREATED);
    }

    @GetMapping("/{id}/comentarios")
    public ResponseEntity<List<ComentarioModel>> listarComentarios(@PathVariable Long id) {
        List<ComentarioModel> comentarios = comentarioService.listarComentarios(id);
        return ResponseEntity.ok(comentarios);
    }
}
 */