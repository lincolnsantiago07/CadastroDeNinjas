package com.lincoln.CadastroDeNinjas.ninjas.controller;

import com.lincoln.CadastroDeNinjas.ninjas.api.NinjaCreateRequest;
import com.lincoln.CadastroDeNinjas.ninjas.api.NinjaResponse;
import com.lincoln.CadastroDeNinjas.ninjas.model.NinjaModel;
import com.lincoln.CadastroDeNinjas.ninjas.api.NinjaUpdateRequest;
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

    // GET /ninjas -> lista TODOS (DTO)
    @GetMapping
    public ResponseEntity<List<NinjaResponse>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    // GET /ninjas/missao/{missaoId} -> também retorna DTO (padrão único)
    @GetMapping("/missao/{missaoId}")
    public ResponseEntity<List<NinjaResponse>> listarPorMissao(@PathVariable Long missaoId) {
        return ResponseEntity.ok(service.listarPorMissaoDTO(missaoId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NinjaResponse> atualizar(@PathVariable Long id,
                                                   @RequestBody @Valid NinjaUpdateRequest req) {
        return ResponseEntity.ok(service.atualizar(id, req));
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