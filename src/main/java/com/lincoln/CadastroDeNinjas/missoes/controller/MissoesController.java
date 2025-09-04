package com.lincoln.CadastroDeNinjas.missoes.controller;

import com.lincoln.CadastroDeNinjas.missoes.model.MissoesModel;
import com.lincoln.CadastroDeNinjas.missoes.repository.MissoesRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missoes")
public class MissoesController {
    private final MissoesRepository repo;
    public MissoesController(MissoesRepository repo){ this.repo = repo; }

    @PostMapping
    public ResponseEntity<MissoesModel> criar(@RequestBody MissoesModel m){
        return ResponseEntity.ok(repo.save(m));
    }

    @GetMapping
    public ResponseEntity<List<MissoesModel>> listar(){
        return ResponseEntity.ok(repo.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
