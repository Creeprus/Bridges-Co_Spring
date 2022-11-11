package com.example.BridgeAndCoCursach.Service;

import com.example.BridgeAndCoCursach.Models.OrderShipment;
import com.example.BridgeAndCoCursach.Models.Shipment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface OrderShipmentService {
    MessageResponse create(OrderShipment Request);
    Optional<OrderShipment> update(Long Id, OrderShipment Request);
    void delete(Long shipmentId);
    OrderShipment getASingle(Long Id);
    List<OrderShipment> getAll();
}
