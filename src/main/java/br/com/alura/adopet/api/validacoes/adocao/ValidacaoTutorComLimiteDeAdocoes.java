package br.com.alura.adopet.api.validacoes.adocao;

import br.com.alura.adopet.api.dto.adocao.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoTutorComLimiteDeAdocoes implements ValidacaoSolicitacaoAdocao {

    private final AdocaoRepository adocaoRepository;

    public ValidacaoTutorComLimiteDeAdocoes(AdocaoRepository adocaoRepository) {
        this.adocaoRepository = adocaoRepository;
    }

    @Override
    public void validar(SolicitacaoAdocaoDto dto) {
        int contador = adocaoRepository.countByTutorId(dto.idTutor());
        if (contador > 4) {
            throw new ValidacaoException("Tutor chegou ao limite máximo de 5 adoções!");
        }
    }

}
