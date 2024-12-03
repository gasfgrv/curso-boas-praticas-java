package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetService {

    private final PetRepository repository;

    @Autowired
    public PetService(PetRepository repository) {
        this.repository = repository;
    }

    public List<Pet> listarTodosDisponiveis() {
        List<Pet> pets = repository.findAll();
        return pets.stream()
                .filter(pet -> !pet.getAdotado())
                .collect(Collectors.toList());
    }

}
