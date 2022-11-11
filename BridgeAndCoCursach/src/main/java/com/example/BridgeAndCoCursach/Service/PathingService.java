package com.example.BridgeAndCoCursach.Service;

import com.example.BridgeAndCoCursach.Models.Pathing;
import com.example.BridgeAndCoCursach.Models.Shipment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface PathingService {
    Optional<Pathing> update(Long Id, Pathing Request);
    Pathing getASingle(Long Id);
    List<Pathing> getAll();
}
