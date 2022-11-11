package com.example.BridgeAndCoCursach.API;

import com.example.BridgeAndCoCursach.Models.Shipment;
import com.example.BridgeAndCoCursach.Models.Storage;
import com.example.BridgeAndCoCursach.Service.MessageResponse;
import com.example.BridgeAndCoCursach.Service.ShipmentService;
import com.example.BridgeAndCoCursach.Service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/storage/")
public class APIStorageController {
    @Autowired
    StorageService storageService;

    @GetMapping("storages")
    public ResponseEntity<List<Storage >> getAllStorage () {
        List<Storage> employees = storageService.getAllStorage();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<Storage > getStorageById (@PathVariable("id") Long id) {
        Storage  employee = storageService.getASingleStorage(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
    @PutMapping("{id}")
    public ResponseEntity<Optional<Storage >> updateEmployee(@PathVariable Long id, @RequestBody Storage  employee) {
        Optional<Storage > updateEmployee = storageService.updateStorage(id, employee);
        return new ResponseEntity<>(updateEmployee, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<MessageResponse> addShipment(@RequestBody Storage  employee) {
        MessageResponse newEmployee = storageService.createStorage(employee);
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }
    @DeleteMapping("{id}")
    void delete(@PathVariable Long id) {
        storageService.deleteStorage(id);
    }
}
