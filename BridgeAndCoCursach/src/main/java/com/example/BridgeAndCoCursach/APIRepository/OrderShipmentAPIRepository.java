package com.example.BridgeAndCoCursach.APIRepository;

import com.example.BridgeAndCoCursach.Models.Account;
import com.example.BridgeAndCoCursach.Models.OrderShipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderShipmentAPIRepository extends JpaRepository<OrderShipment,Long> {
}
