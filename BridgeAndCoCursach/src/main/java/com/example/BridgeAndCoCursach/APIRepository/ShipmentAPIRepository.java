package com.example.BridgeAndCoCursach.APIRepository;

import com.example.BridgeAndCoCursach.Models.Account;
import com.example.BridgeAndCoCursach.Models.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentAPIRepository extends JpaRepository<Shipment,Long> {
}
