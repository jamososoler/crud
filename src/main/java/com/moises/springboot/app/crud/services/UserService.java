package com.moises.springboot.app.crud.services;

import com.moises.springboot.app.crud.entities.User;

import java.util.List;

public interface UserService {

    List<User> findAll();
    User save(User user);
}
