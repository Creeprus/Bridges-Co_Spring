package com.example.BridgeAndCoCursach.API;

import com.example.BridgeAndCoCursach.Models.Pathing;
import com.example.BridgeAndCoCursach.Models.Shipment;
import com.example.BridgeAndCoCursach.Service.MessageResponse;
import com.example.BridgeAndCoCursach.Service.PathingService;
import com.example.BridgeAndCoCursach.Service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pathing/")
public class APIPathingController {

    @Autowired
    PathingService pathingService;

    @GetMapping("pathings")
    public ResponseEntity<List<Pathing>> getAll () {
        List<Pathing> employees = pathingService.getAll();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<Pathing> getShipmentById (@PathVariable("id") Long id) {
        Pathing employee = pathingService.getASingle(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
    @PutMapping("{id}")
    public ResponseEntity<Optional<Pathing>> updateEmployee(@PathVariable Long id, @RequestBody Pathing employee) {
        Optional<Pathing> updateEmployee = pathingService.update(id, employee);
        return new ResponseEntity<>(updateEmployee, HttpStatus.OK);
    }

}
