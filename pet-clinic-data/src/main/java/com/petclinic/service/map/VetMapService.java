package com.petclinic.service.map;

import com.petclinic.model.Speciality;
import com.petclinic.model.Vet;
import com.petclinic.service.CrudService;
import com.petclinic.service.SpecialityService;
import com.petclinic.service.VetService;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public class VetMapService extends AbstractMapService<Vet, Long> implements VetService {
    private final SpecialityService specialityService;

    public VetMapService(SpecialityService specialityService) {
        this.specialityService = specialityService;
    }

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);

    }

    @Override
    public void delete(Vet object) {
        super.delete(object);

    }

    @Override
    public Vet save(Vet object) {
        if(object.getSpecialities().size() >0){
            object.getSpecialities().forEach(speciality -> {
                if(speciality.getId() == null){
                     Speciality saveSpeciality =  specialityService.save(speciality);
                     speciality.setId(saveSpeciality.getId());
                }
            });
        }

        return super.save(object);
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }
}
