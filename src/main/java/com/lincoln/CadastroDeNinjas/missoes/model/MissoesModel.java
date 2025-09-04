package com.lincoln.CadastroDeNinjas.missoes.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.lincoln.CadastroDeNinjas.ninjas.model.NinjaModel;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_missoes")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class MissoesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private String dificuldade;

    // uma missão pode ter vários ninjas
    @OneToMany(mappedBy = "missao", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<NinjaModel> ninjas = new ArrayList<>();
}
