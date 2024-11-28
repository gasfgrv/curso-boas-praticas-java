package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.adocao.AprovacaoAdocaoDto;
import br.com.alura.adopet.api.dto.adocao.ReprovacaoAdocaoDto;
import br.com.alura.adopet.api.dto.adocao.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.service.AdocaoService;
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
@RequestMapping("/adocoes")
public class AdocaoController {

    private final AdocaoService service;

    @Autowired
    public AdocaoController(AdocaoService service) {
        this.service = service;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> solicitar(@RequestBody @Valid SolicitacaoAdocaoDto dto) {
        try {
            service.solicitar(dto);
            return ResponseEntity.noContent().build();
        } catch (ValidacaoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/aprovar")
    @Transactional
    public ResponseEntity<Void> aprovar(@RequestBody @Valid AprovacaoAdocaoDto dto) {
        service.aprovar(dto);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/reprovar")
    @Transactional
    public ResponseEntity<Void> reprovar(@RequestBody @Valid ReprovacaoAdocaoDto dto) {
        service.reprovar(dto);
        return ResponseEntity.noContent().build();
    }

}
