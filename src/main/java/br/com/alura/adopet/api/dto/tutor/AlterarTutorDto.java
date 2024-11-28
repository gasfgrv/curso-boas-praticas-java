package br.com.alura.adopet.api.dto.tutor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AlterarTutorDto(@NotNull Long id, @NotBlank String nome, @NotBlank String telefone, @NotBlank @Email String email) {
}
