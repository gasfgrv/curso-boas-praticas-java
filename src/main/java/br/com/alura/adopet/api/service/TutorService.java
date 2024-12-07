package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.tutor.AlterarTutorDto;
import br.com.alura.adopet.api.dto.tutor.CadastrarTutorDto;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.TutorRepository;
import br.com.alura.adopet.api.validacoes.tutor.ValidacaoCadastroTutor;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TutorService {

    private final TutorRepository repository;
    private final List<ValidacaoCadastroTutor> validacoes;

    @Autowired
    public TutorService(TutorRepository repository, List<ValidacaoCadastroTutor> validacoes) {
        this.repository = repository;
        this.validacoes = validacoes;
    }

    public void cadastrar(@Valid CadastrarTutorDto dto) {
        validacoes.forEach(validador -> validador.validar(dto));

        Tutor tutor = new Tutor(dto.nome(), dto.telefone(), dto.email());
        repository.save(tutor);
    }

    public void atualizar(@Valid AlterarTutorDto dto) {
        Tutor tutor = repository.getReferenceById(dto.id());
        tutor.atualizarDados(dto.nome(), dto.telefone(), dto.email());
        repository.save(tutor);
    }

}
