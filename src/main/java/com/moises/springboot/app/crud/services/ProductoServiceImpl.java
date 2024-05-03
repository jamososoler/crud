package com.moises.springboot.app.crud.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moises.springboot.app.crud.entities.Producto;
import com.moises.springboot.app.crud.repositories.ProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService{

    
    @Autowired
    private ProductoRepository repositorio;

    @Transactional(readOnly = true)
    @Override
    public List<Producto> findAll() {
        return (List<Producto>) repositorio.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Producto> findById(Integer id) {
        return repositorio.findById(id);
    }

    @Transactional
    @Override
    public Producto save(Producto producto) {
        return repositorio.save(producto);
    }

    @Transactional
    @Override
    public Optional<Producto>  update(Integer id, Producto producto) {
        Optional<Producto> productOptional = repositorio.findById(id);
       if (productOptional.isPresent()){
           Producto prod = productOptional.orElseThrow();
            prod.setNombre(producto.getNombre());
            prod.setDescripcion(producto.getDescripcion());
            prod.setPrecio(producto.getPrecio());
            return Optional.of(repositorio.save(prod));
        }
        return productOptional;
    }

    @Transactional
    @Override
    public Optional<Producto> delete(Integer id) {
        Optional<Producto> productOptional = repositorio.findById(id);
        productOptional.ifPresent(prod -> {
            repositorio.delete(prod);
        });
        return productOptional;
    }

}
