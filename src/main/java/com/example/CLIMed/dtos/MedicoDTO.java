package com.example.CLIMed.dtos;

import jakarta.validation.constraints.*;

public record MedicoDTO(
        @NotEmpty String nome,
        @NotBlank @Email String email,
        @NotBlank @Size(min = 16, max = 16) String telefone,
        @NotNull int crm
        ) {
}
