package com.petclinic.service;

import com.petclinic.model.Owner;



public interface OwnerService  extends CrudService<Owner, Long>{
    Owner findByLastName(String lastName);

}
