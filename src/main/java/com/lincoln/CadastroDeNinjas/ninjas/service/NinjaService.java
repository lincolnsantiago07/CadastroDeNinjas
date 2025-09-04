package com.lincoln.CadastroDeNinjas.ninjas.service;

import com.lincoln.CadastroDeNinjas.missoes.model.MissoesModel;
import com.lincoln.CadastroDeNinjas.missoes.repository.MissoesRepository;
import com.lincoln.CadastroDeNinjas.ninjas.api.NinjaCreateRequest;
import com.lincoln.CadastroDeNinjas.ninjas.api.NinjaResponse;
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

    public List<NinjaModel> listarPorMissao(Long missaoId) {
        return ninjaRepo.findByMissao_Id(missaoId);
    }

    public void deletar(Long id) {
        ninjaRepo.deleteById(id);
    }

    public boolean existeNinja(Long id) {
        return ninjaRepo.existsById(id);
    }

}