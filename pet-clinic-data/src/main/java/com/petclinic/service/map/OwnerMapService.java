package com.petclinic.service.map;

import com.petclinic.model.Owner;
import com.petclinic.model.Pet;
import com.petclinic.service.OwnerService;
import com.petclinic.service.PetSerivce;
import com.petclinic.service.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
@Profile({"default", "map"})
public class OwnerMapService extends AbstractMapService<Owner,Long> implements OwnerService {
    private final PetSerivce petSerivce;
    private final PetTypeService petTypeService;

    public OwnerMapService(PetSerivce petSerivce, PetTypeService petTypeService) {
        this.petSerivce = petSerivce;
        this.petTypeService = petTypeService;
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);

    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public Owner save(Owner object) {
        if(object != null) {
            if(object.getPets() != null) {
                object.getPets().forEach(pet ->{
                    if(pet.getPetType() !=null){
                        if(pet.getPetType().getId() ==null){
                            pet.setPetType(petTypeService.save(pet.getPetType()));
                        }
                    }else{
                        throw new RuntimeException("Pet type Is Required");
                    }
                    if(pet.getId() ==null){
                        Pet savedPet = petSerivce.save(pet);
                        pet.setId(savedPet.getId());
                    }
                });
            }
            return super.save( object);
        }else{
            return null;
        }
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return null;
    }
}
