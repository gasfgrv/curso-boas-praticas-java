package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.abrigo.CadastrarAbrigoDto;
import br.com.alura.adopet.api.dto.pet.CadastrarPetDto;
import br.com.alura.adopet.api.exception.NaoEncontradoException;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbrigoService {

    private final AbrigoRepository abrigoRepository;
    private final PetRepository petRepository;

    @Autowired
    public AbrigoService(AbrigoRepository abrigoRepository, PetRepository petRepository) {
        this.abrigoRepository = abrigoRepository;
        this.petRepository = petRepository;
    }

    public List<Abrigo> listar() {
        return abrigoRepository.findAll();
    }

    public void cadastrar(@Valid CadastrarAbrigoDto dto) {
        boolean nomeJaCadastrado = abrigoRepository.existsByNome(dto.nome());
        boolean telefoneJaCadastrado = abrigoRepository.existsByTelefone(dto.telefone());
        boolean emailJaCadastrado = abrigoRepository.existsByEmail(dto.email());

        if (nomeJaCadastrado || telefoneJaCadastrado || emailJaCadastrado) {
            throw new ValidacaoException("Dados já cadastrados para outro abrigo!");
        }

        Abrigo abrigo = new Abrigo(dto.nome(), dto.telefone(), dto.email());
        abrigoRepository.save(abrigo);
    }

    public List<Pet> listarPets(String idOuNome) {
        try {
            Long id = Long.parseLong(idOuNome);
            return abrigoRepository.getReferenceById(id).getPets();
        } catch (EntityNotFoundException enfe) {
            throw new NaoEncontradoException("Abrigo não encontrado");
        } catch (NumberFormatException e) {
            try {
                return abrigoRepository.findByNome(idOuNome).getPets();
            } catch (EntityNotFoundException enfe) {
                throw new NaoEncontradoException("Abrigo não encontrado");
            }
        }
    }

    public void cadastrarPet(String idOuNome, @Valid CadastrarPetDto dto) {
        Pet pet = new Pet(dto.tipo(), dto.nome(), dto.raca(), dto.idade(), dto.cor(), dto.peso());
        petRepository.save(pet);

        try {
            Long id = Long.parseLong(idOuNome);
            Abrigo abrigo = abrigoRepository.getReferenceById(id);
            pet.adicionarAoAbrigo(abrigo);
            abrigo.getPets().add(pet);
            abrigoRepository.save(abrigo);
        } catch (EntityNotFoundException enfe) {
            throw new NaoEncontradoException("Abrigo não encontrado");
        } catch (NumberFormatException nfe) {
            try {
                Abrigo abrigo = abrigoRepository.findByNome(idOuNome);
                pet.adicionarAoAbrigo(abrigo);
                abrigo.getPets().add(pet);
                abrigoRepository.save(abrigo);
            } catch (EntityNotFoundException enfe) {
                throw new NaoEncontradoException("Abrigo não encontrado");
            }
        }
    }

}
