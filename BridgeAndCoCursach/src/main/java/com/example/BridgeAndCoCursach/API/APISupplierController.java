package com.example.BridgeAndCoCursach.API;

import com.example.BridgeAndCoCursach.APIRepository.SupplierAPIRepository;
import com.example.BridgeAndCoCursach.Models.Supplier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/supplier/")
public class APISupplierController {


    private final SupplierAPIRepository repository;

    APISupplierController(SupplierAPIRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/suppliers")
    List<Supplier> all() {
        return repository.findAll();
    }
    @GetMapping("/{id}")
    Supplier one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow();
    }
    @PutMapping("/{id}")
Supplier update(@PathVariable("id") long id, @RequestBody Supplier supplier) {
        Optional<Supplier> tutorialData = repository.findById(id);

        if (tutorialData.isPresent()) {
            Supplier _supplier = tutorialData.get();
            _supplier.setSuppliername(supplier.getSuppliername());
            _supplier.setCountry(supplier.getCountry());

            return repository.save(_supplier);
        }
        return supplier;
    }

    @PostMapping("/add")
    Supplier create(@RequestBody Supplier supplier) {
        try {

            Supplier _supplier = repository
                    .save(supplier);
            return _supplier;
        } catch (Exception e) {

        }
        return supplier;
    }
    @DeleteMapping("{id}")
    void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
