package com.example.BridgeAndCoCursach.API;

import com.example.BridgeAndCoCursach.APIRepository.SupplierAPIRepository;
import com.example.BridgeAndCoCursach.APIRepository.SupplyAPIRepository;
import com.example.BridgeAndCoCursach.Models.Supplier;
import com.example.BridgeAndCoCursach.Models.Supply;
import com.example.BridgeAndCoCursach.Repository.SupplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/supply/")
public class APISupplyController {

    @Autowired
    SupplierAPIRepository supplierAPIRepository;
    @Autowired
    SupplyRepository supplyRepository;
    private final SupplyAPIRepository repository;

    APISupplyController(SupplyAPIRepository repository) {
        this.repository = repository;
    }

    @GetMapping("supplies")
    Iterable<Supply> all() {
        return repository.findAll();
    }
    @GetMapping("/{id}")
    Supply one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow();
    }

    @PutMapping("{id}")
    public ResponseEntity<Supply> update(@PathVariable("id") long id, @RequestBody Supply supply) {
        Optional<Supply> tutorialData = repository.findById(id);

        if (tutorialData.isPresent()) {
            Supply _supply = tutorialData.get();
            _supply.setDateofsupply(supply.getDateofsupply());
            _supply.setSupplier(supply.getSupplier());



            return new ResponseEntity<>(repository.save(_supply), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    @PostMapping("add")
    public ResponseEntity<Supply> create(@RequestBody Supply supply) {
        try {
                Supplier supplier=supplierAPIRepository.findSupplierById(supply.getSupplier().getId());
                Supply _supply = repository
                        .save(new Supply(supply.getDateofsupply(),supplier,supply.getStorages()));

                return new ResponseEntity<>(_supply, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("{id}")
    void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
