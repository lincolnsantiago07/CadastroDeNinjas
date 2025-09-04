package com.lincoln.CadastroDeNinjas.ninjas.service;

import com.lincoln.CadastroDeNinjas.missoes.model.MissoesModel;
import com.lincoln.CadastroDeNinjas.missoes.repository.MissoesRepository;
import com.lincoln.CadastroDeNinjas.ninjas.api.NinjaCreateRequest;
import com.lincoln.CadastroDeNinjas.ninjas.api.NinjaResponse;
import com.lincoln.CadastroDeNinjas.ninjas.api.NinjaUpdateRequest;
import com.lincoln.CadastroDeNinjas.ninjas.model.NinjaModel;
import com.lincoln.CadastroDeNinjas.ninjas.repository.NinjaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NinjaService {

    private final NinjaRepository ninjaRepo;
    private final MissoesRepository missoesRepo;

    public NinjaService(NinjaRepository ninjaRepo, MissoesRepository missoesRepo) {
        this.ninjaRepo = ninjaRepo;
        this.missoesRepo = missoesRepo;
    }

    //Cria
    public NinjaResponse criar(NinjaCreateRequest req) {
        if (ninjaRepo.existsByEmail(req.email()))
            throw new IllegalArgumentException("E-mail já cadastrado");

        MissoesModel missao = missoesRepo.findById(req.missaoId())
                .orElseThrow(() -> new IllegalArgumentException("Missão não encontrada"));

        NinjaModel ninja = new NinjaModel(null, req.nome(), req.email(), req.idade(), missao);
        NinjaModel salvo = ninjaRepo.save(ninja);
        return new NinjaResponse(salvo.getId(), salvo.getNome(), salvo.getEmail(),
                salvo.getIdade(), missao.getId(), missao.getNome());
    }

    //Atualiza
    public NinjaResponse atualizar(Long id, NinjaUpdateRequest req) {
        NinjaModel ninja = ninjaRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ninja não encontrado"));

        MissoesModel missao = missoesRepo.findById(req.missaoId())
                .orElseThrow(() -> new IllegalArgumentException("Missão não encontrada"));

        // se email mudou, valida unicidade
        if (!ninja.getEmail().equals(req.email()) && ninjaRepo.existsByEmail(req.email())) {
            throw new IllegalArgumentException("E-mail já cadastrado");
        }

        ninja.setNome(req.nome());
        ninja.setEmail(req.email());
        ninja.setIdade(req.idade());
        ninja.setMissao(missao);

        NinjaModel salvo = ninjaRepo.save(ninja);
        return new NinjaResponse(salvo.getId(), salvo.getNome(), salvo.getEmail(),
                salvo.getIdade(), missao.getId(), missao.getNome());
    }

    public List<NinjaModel> listarPorMissao(Long missaoId) {
        return ninjaRepo.findByMissao_Id(missaoId);
    }

    public void deletar(Long id) {
        ninjaRepo.deleteById(id);
    }

    public boolean existeNinja(Long id) {
        return ninjaRepo.existsById(id);
    }

    // listar todos como DTO (com nome/ID da missão)
    public List<NinjaResponse> listarTodos() {
        return ninjaRepo.findAll().stream().map(n -> new NinjaResponse(
                n.getId(), n.getNome(), n.getEmail(), n.getIdade(),
                n.getMissao().getId(), n.getMissao().getNome()
        )).toList();
    }

    public List<NinjaResponse> listarPorMissaoDTO(Long missaoId) {
        return ninjaRepo.findByMissao_Id(missaoId).stream().map(n -> new NinjaResponse(
                n.getId(), n.getNome(), n.getEmail(), n.getIdade(),
                n.getMissao().getId(), n.getMissao().getNome()
        )).toList();
    }

}