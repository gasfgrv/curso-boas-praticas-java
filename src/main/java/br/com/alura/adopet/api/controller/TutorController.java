package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.tutor.AlterarTutorDto;
import br.com.alura.adopet.api.dto.tutor.CadastrarTutorDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.service.TutorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tutores")
public class TutorController {

    private final TutorService service;

    @Autowired
    public TutorController(TutorService service) {
        this.service = service;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastrar(@RequestBody @Valid CadastrarTutorDto dto) {
        try {
            service.cadastrar(dto);
            return ResponseEntity.noContent().build();
        } catch (ValidacaoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    @Transactional
    public ResponseEntity<String> atualizar(@RequestBody @Valid AlterarTutorDto dto) {
        service.atualizar(dto);
        return ResponseEntity.noContent().build();
    }

}
