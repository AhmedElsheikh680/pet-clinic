package com.petclinic.bootstrap;

import com.petclinic.model.*;
import com.petclinic.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class Dataloader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;
    private final VisitService visitService;

    public Dataloader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {
           int count =  petTypeService.findAll().size();
           if(count ==0){
               loadData();

           }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType saveDogPetType =  petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType saveCatPetType = petTypeService.save(cat);

        Speciality radioLogy = new Speciality();
        radioLogy.setDescription("RadioLogy");
        Speciality saveRadioLogy =  specialityService.save(radioLogy);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality saveSurgery = specialityService.save(surgery);

        Speciality dentistry =new Speciality();
        dentistry.setDescription("Dentistry");
        Speciality saveDentistry = specialityService.save(dentistry);


        Owner owner1 = new Owner();
        owner1.setFirstName("Ahmed");
        owner1.setLastName("Elsheikh");
        owner1.setAddress("24 Abo Mazen");
        owner1.setCity("Miami");
        owner1.setTelephone("123123123");
//        Owner.builder().address("ASDF").build();

        Pet mikesPet = new Pet();
        mikesPet.setPetType(saveDogPetType);
        mikesPet.setOwner(owner1);
        mikesPet.setBirthDate(LocalDate.now());
        mikesPet.setName("Rosco");
        owner1.getPets().add(mikesPet);
        ownerService.save(owner1);

        Owner owner2 = new Owner();

        owner2.setFirstName("Michael");
        owner2.setLastName("Weston");
        owner2.setAddress("24 Misr");
        owner2.setCity("Karkook");
        owner2.setTelephone("24578452");

        Pet fionsCat = new Pet();
        fionsCat.setPetType(saveCatPetType);
        fionsCat.setOwner(owner2);
        fionsCat.setBirthDate(LocalDate.now());
        fionsCat.setName("Gelemene");
        owner2.getPets().add(fionsCat);
        ownerService.save(owner2);

        Visit catVisit = new Visit();
        catVisit.setPet(fionsCat);
        catVisit.setDate(LocalDate.now());
        catVisit.setDesription("Sneezy Kitty");
        visitService.save(catVisit);

        System.out.println("Loaded Owners........");

        Vet vet1 = new Vet();

        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vet1.getSpecialities().add(saveRadioLogy);
        vetService.save(vet1);

        Vet vet2 = new Vet();

        vet2.setFirstName("Jese");
        vet2.setLastName("Porter");
        vet2.getSpecialities().add(saveSurgery);
        vetService.save(vet2);
        System.out.println("Loaded Vets..........");
    }
}
