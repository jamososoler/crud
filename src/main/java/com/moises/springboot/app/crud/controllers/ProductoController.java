package com.moises.springboot.app.crud.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moises.springboot.app.crud.entities.Producto;
import com.moises.springboot.app.crud.services.ProductoService;

@RestController
@RequestMapping("/api/produc")
public class ProductoController {

    @Autowired
    private ProductoService service;

    @GetMapping
    public List<Producto> list() {
        return service.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Integer id) {
        Optional<Producto> productOptional = service.findById(id);
        if (productOptional.isPresent()) {
            return ResponseEntity.ok(productOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    
    @PostMapping
    public ResponseEntity<Producto> create(@RequestBody Producto product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(product));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Producto> update(@PathVariable Integer id, @RequestBody Producto product) {
        product.setId(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(product));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        Producto product = new Producto();
        product.setId(id);
        Optional<Producto> productOptional = service.delete(product);
        if (productOptional.isPresent()) {
            return ResponseEntity.ok(productOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

}
