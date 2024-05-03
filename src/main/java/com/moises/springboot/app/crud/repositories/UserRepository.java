package com.moises.springboot.app.crud.repositories;

import com.moises.springboot.app.crud.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer> {
}
