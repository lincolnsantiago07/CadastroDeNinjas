package com.lincoln.CadastroDeNinjas.missoes.repository;

import com.lincoln.CadastroDeNinjas.missoes.model.MissoesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MissoesRepository extends JpaRepository<MissoesModel, Long> {}