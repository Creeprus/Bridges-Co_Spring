package com.example.BridgeAndCoCursach.API;

import com.example.BridgeAndCoCursach.Models.OrderShipment;
import com.example.BridgeAndCoCursach.Models.Shipment;
import com.example.BridgeAndCoCursach.Service.MessageResponse;
import com.example.BridgeAndCoCursach.Service.OrderShipmentService;
import com.example.BridgeAndCoCursach.Service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ordershipment/")
public class APIOrderShipmentController {
    @Autowired
    OrderShipmentService orderShipmentService;

    @GetMapping("ordershipments")
    public ResponseEntity<List<OrderShipment>> getAll () {
        List<OrderShipment> employees = orderShipmentService.getAll();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<OrderShipment> getById (@PathVariable("id") Long id) {
        OrderShipment employee = orderShipmentService.getASingle(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
    @PutMapping("{id}")
    public ResponseEntity<Optional<OrderShipment>> updateEmployee(@PathVariable Long id, @RequestBody OrderShipment employee) {
        Optional<OrderShipment> updateEmployee = orderShipmentService.update(id, employee);
        return new ResponseEntity<>(updateEmployee, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<MessageResponse> add(@RequestBody OrderShipment employee) {
        MessageResponse newEmployee = orderShipmentService.create(employee);
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }
    @DeleteMapping("{id}")
    void delete(@PathVariable Long id) {
        orderShipmentService.delete(id);
    }
}
