package com.example.BridgeAndCoCursach.Service;

import com.example.BridgeAndCoCursach.Models.Shipment;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Optional;

@Component
public interface ShipmentService {
    MessageResponse createEmployee(Shipment shipmentRequest);
    Optional<Shipment> updateEmployee(Long shipmentId, Shipment shipmentRequest);
    void deleteEmployee(Long shipmentId);
    Shipment getASingleEmployee(Long shipmentId);
    List<Shipment> getAllEmployee();
}
