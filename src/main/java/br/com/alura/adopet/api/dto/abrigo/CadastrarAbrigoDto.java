package br.com.alura.adopet.api.dto.abrigo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CadastrarAbrigoDto(@NotBlank String nome, @NotBlank String telefone, @NotBlank @Email String email) {
}
