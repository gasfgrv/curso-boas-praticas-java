package br.com.alura.adopet.api.validacoes.adocao;

import br.com.alura.adopet.api.dto.adocao.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.PetRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoPetDisponivel implements ValidacaoSolicitacaoAdocao {

    private final PetRepository petRepository;

    public ValidacaoPetDisponivel(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public void validar(SolicitacaoAdocaoDto dto) {
        Pet pet = petRepository.getReferenceById(dto.idPet());
        if (Boolean.TRUE.equals(pet.getAdotado())) {
            throw new ValidacaoException("Pet já foi adotado!");
        }
    }

}
