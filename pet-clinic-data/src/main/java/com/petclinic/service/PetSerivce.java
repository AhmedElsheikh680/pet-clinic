package com.petclinic.service;

import com.petclinic.model.Pet;

import java.util.Set;

public interface PetSerivce {
    Pet findById(Long id);
    Set<Pet> findAll();
    Pet save(Pet pet);
}
