package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.adocao.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidacaoPetComAdocaoEmAndamento implements ValidacaoSolicitacaoAdocao {

    private final AdocaoRepository adocaoRepository;
    private final PetRepository petRepository;

    public ValidacaoPetComAdocaoEmAndamento(AdocaoRepository adocaoRepository, PetRepository petRepository) {
        this.adocaoRepository = adocaoRepository;
        this.petRepository = petRepository;
    }

    @Override
    public void validar(SolicitacaoAdocaoDto dto) {
        List<Adocao> adocoes = adocaoRepository.findAll();
        Pet pet = petRepository.getReferenceById(dto.idPet());
        adocoes.stream()
                .filter(a -> a.getPet() == pet && a.getStatus() == StatusAdocao.AGUARDANDO_AVALIACAO)
                .forEach(a -> {
                    throw new ValidacaoException("Pet já está aguardando avaliação para ser adotado!");
                });
    }

}
