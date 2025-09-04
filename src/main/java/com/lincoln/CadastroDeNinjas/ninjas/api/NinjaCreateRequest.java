package com.lincoln.CadastroDeNinjas.ninjas.api;

import jakarta.validation.constraints.*;

public record NinjaCreateRequest(
        @NotBlank String nome,
        @Email @NotBlank String email,
        @Min(5) int idade,
        @NotNull Long missaoId
) {}
