package com.lincoln.CadastroDeNinjas.ninjas.controller;

import com.lincoln.CadastroDeNinjas.ninjas.api.NinjaCreateRequest;
import com.lincoln.CadastroDeNinjas.ninjas.api.NinjaResponse;
import com.lincoln.CadastroDeNinjas.ninjas.model.NinjaModel;
import com.lincoln.CadastroDeNinjas.ninjas.service.NinjaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    private final NinjaService service;
    public NinjaController(NinjaService service){ this.service = service; }

    @PostMapping
    public ResponseEntity<NinjaResponse> criar(@RequestBody @Valid NinjaCreateRequest req) {
        return ResponseEntity.ok(service.criar(req));
    }

    @GetMapping("/missao/{missaoId}")
    public ResponseEntity<List<NinjaModel>> listarPorMissao(@PathVariable Long missaoId) {
        return ResponseEntity.ok(service.listarPorMissao(missaoId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!service.existeNinja(id)) {
            return ResponseEntity.notFound().build();
        }
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

}