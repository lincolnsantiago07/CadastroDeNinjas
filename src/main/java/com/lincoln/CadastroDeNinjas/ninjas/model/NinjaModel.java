package com.lincoln.CadastroDeNinjas.ninjas.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.lincoln.CadastroDeNinjas.missoes.model.MissoesModel;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_cadastro_de_ninjas")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class NinjaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    private int idade;

    // cada ninja possui UMA missão
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "missao_id", nullable = false) // FK
    @JsonBackReference // evita recursão no JSON
    private MissoesModel missao;
}
