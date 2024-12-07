package br.com.alura.adopet.api.validacoes.tutor;

import br.com.alura.adopet.api.dto.tutor.CadastrarTutorDto;

public interface ValidacaoCadastroTutor {
    void validar(CadastrarTutorDto dto);
}
