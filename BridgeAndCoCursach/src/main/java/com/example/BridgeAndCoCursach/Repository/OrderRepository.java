package com.example.BridgeAndCoCursach.Repository;

import com.example.BridgeAndCoCursach.Models.OrderShipment;
import com.example.BridgeAndCoCursach.Models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<OrderShipment,Long> {
   OrderShipment findOrderShipmentByUsers(User user);
   List<OrderShipment> findAll();
}
