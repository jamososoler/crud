package com.moises.springboot.app.crud.repositories;

import com.moises.springboot.app.crud.entities.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role,Integer> {

    Optional<Role> findByName(String name);

}
