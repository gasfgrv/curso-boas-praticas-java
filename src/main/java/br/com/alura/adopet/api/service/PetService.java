package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PetService {

    private final PetRepository repository;

    @Autowired
    public PetService(PetRepository repository) {
        this.repository = repository;
    }

    public List<Pet> listarTodosDisponiveis() {
        List<Pet> pets = repository.findAll();
        List<Pet> disponiveis = new ArrayList<>();
        for (Pet pet : pets) {
            if (!pet.getAdotado()) {
                disponiveis.add(pet);
            }
        }
        return disponiveis;
    }

}