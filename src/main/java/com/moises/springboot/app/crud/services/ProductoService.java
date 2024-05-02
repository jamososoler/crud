package com.moises.springboot.app.crud.services;

import java.util.List;
import java.util.Optional;

import com.moises.springboot.app.crud.entities.Producto;

public interface ProductoService {

    List<Producto> findAll();

    Optional<Producto> findById(Integer id);

    Producto save(Producto producto);

    Optional<Producto> delete(Producto producto);


}
