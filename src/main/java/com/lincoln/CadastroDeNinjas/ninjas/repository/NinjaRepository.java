package com.lincoln.CadastroDeNinjas.ninjas.repository;

import com.lincoln.CadastroDeNinjas.ninjas.model.NinjaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NinjaRepository extends JpaRepository<NinjaModel, Long> {
    List<NinjaModel> findByMissao_Id(Long missaoId);
    boolean existsByEmail(String email);
}
