package com.moises.springboot.app.crud.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
@RequestMapping("/api/producto")
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
    public ResponseEntity<?> create(@Valid @RequestBody Producto product, BindingResult result) {
        if (result.hasFieldErrors()){
            return validation(result);

    }
     return ResponseEntity.status(HttpStatus.CREATED).

    body(service.save(product));
}


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Producto product,BindingResult result, @PathVariable Integer id) {
        if(result.hasFieldErrors()){
            return validation(result);
        }
        Optional<Producto> productoOptional = service.update(id, product);
        if (productoOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(productoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        Optional<Producto> productOptional = service.delete(id);
        if (productOptional.isPresent()) {
            return ResponseEntity.ok(productOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String,String> errors= new HashMap<>();
            result.getFieldErrors().forEach(err -> {
                errors.put(err.getField(), " el campo " + err.getField() + " " + err.getDefaultMessage());
            });
        return ResponseEntity.badRequest().body(errors);
    }

}
