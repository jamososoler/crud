package com.moises.springboot.app.crud.repositories;

import com.moises.springboot.app.crud.entities.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role,Integer> {

}
