package com.example.BridgeAndCoCursach.API;

import com.example.BridgeAndCoCursach.APIRepository.ShipmentAPIRepository;
import com.example.BridgeAndCoCursach.Models.Shipment;
import com.example.BridgeAndCoCursach.Service.MessageResponse;
import com.example.BridgeAndCoCursach.Service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/shipment/")
public class APIShipmentController {

    @Autowired
    ShipmentService shipmentService;

    @GetMapping("shipments")
    public ResponseEntity<List<Shipment>> getAllShipment () {
        List<Shipment> employees = shipmentService.getAllEmployee();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<Shipment> getShipmentById (@PathVariable("id") Long id) {
        Shipment employee = shipmentService.getASingleEmployee(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
    @PutMapping("{id}")
    public ResponseEntity<Optional<Shipment>> updateEmployee( @PathVariable Long id, @RequestBody Shipment employee) {
        Optional<Shipment> updateEmployee = shipmentService.updateEmployee(id, employee);
        return new ResponseEntity<>(updateEmployee, HttpStatus.OK);
    }


}
