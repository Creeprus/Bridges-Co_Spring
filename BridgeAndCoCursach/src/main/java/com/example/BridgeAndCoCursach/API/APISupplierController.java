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
    @PutMapping("{id}")
    public ResponseEntity<Supplier> update(@PathVariable("id") long id, @RequestBody Supplier supplier) {
        Optional<Supplier> tutorialData = repository.findById(id);

        if (tutorialData.isPresent()) {
            Supplier _supplier = tutorialData.get();
            _supplier.setSuppliername(supplier.getSuppliername());
            _supplier.setCountry(supplier.getCountry());

            return new ResponseEntity<>(repository.save(_supplier), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Supplier> create(@RequestBody Supplier supplier) {
        try {

            Supplier _supplier = repository
                    .save(supplier);
            return new ResponseEntity<>(_supplier, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("{id}")
    void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
