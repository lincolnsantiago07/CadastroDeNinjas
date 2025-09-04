package com.lincoln.CadastroDeNinjas.ninjas.api;

import jakarta.validation.constraints.*;

public record NinjaUpdateRequest(
        @NotBlank String nome,
        @Email @NotBlank String email,
        @Min(5) int idade,
        @NotNull Long missaoId
) {}