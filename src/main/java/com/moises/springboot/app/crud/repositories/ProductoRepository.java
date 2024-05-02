package com.moises.springboot.app.crud.repositories;


import org.springframework.data.repository.CrudRepository;

import com.moises.springboot.app.crud.entities.Producto;

public interface ProductoRepository  extends CrudRepository<Producto, Integer>{


}
