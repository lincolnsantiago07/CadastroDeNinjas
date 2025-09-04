package com.lincoln.CadastroDeNinjas.ninjas.api;

public record NinjaResponse(
        Long id, String nome, String email, int idade,
        Long missaoId, String missaoNome
) {}
