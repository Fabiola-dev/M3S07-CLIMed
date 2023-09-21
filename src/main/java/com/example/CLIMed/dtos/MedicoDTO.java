package com.example.CLIMed.dtos;

import com.example.CLIMed.model.MedicoModel;
import jakarta.validation.constraints.*;

public record MedicoDTO(
        @NotEmpty String nome,
        @NotBlank @Email String email,
        @NotBlank @Size(min = 16, max = 16) String telefone,
        @NotNull int crm
        ) {

        public MedicoDTO(MedicoModel medico) {
                this(medico.getNome(), medico.getEmail(),
                        medico.getTelefone(), medico.getCrm());
        }
}
