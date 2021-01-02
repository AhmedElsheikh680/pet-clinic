package com.petclinic.service;

import com.petclinic.model.Pet;
import com.petclinic.model.Vet;

import java.util.Set;

public interface VetService {
    Vet findById(Long id);
    Set<Pet> findAll();
    Vet save(Vet vet);
}
