package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.tutor.CadastrarTutorDto;

public interface ValidacaoCadastroTutor {
    void validar(CadastrarTutorDto dto);
}
