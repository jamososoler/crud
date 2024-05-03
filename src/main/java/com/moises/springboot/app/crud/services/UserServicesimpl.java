package com.moises.springboot.app.crud.services;

import com.moises.springboot.app.crud.entities.Role;
import com.moises.springboot.app.crud.entities.User;
import com.moises.springboot.app.crud.repositories.RoleRepository;
import com.moises.springboot.app.crud.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServicesimpl implements UserService{

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Transactional(readOnly = true)
    @Override
    public List<User> findAll() {
        return (List<User>) repository.findAll();
    }

    @Transactional
    @Override
    public User save(User user) {
        Optional<Role> optionalRolUser= roleRepository.findByName("ROLE_USER");
        List<Role> roles = new ArrayList<>();

        optionalRolUser.ifPresent(roles::add);

        if(user.isAdmin()){
            Optional<Role> optionalRoleAdmin=roleRepository.findByName("ROLE_ADMIN");
            optionalRoleAdmin.ifPresent(roles::add);
        }
        user.setRoles(roles);
        String passwordEncoded =passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordEncoded);

        return repository.save(user);
    }
}
