package br.com.alura.adopet.api.dto.tutor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CadastrarTutorDto(@NotBlank String nome, @NotBlank String telefone, @NotBlank @Email String email) {
}
