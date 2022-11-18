package com.example.BridgeAndCoCursach.Repository;

import com.example.BridgeAndCoCursach.Models.OrderShipment;
import com.example.BridgeAndCoCursach.Models.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<OrderShipment,Long> {
   OrderShipment findOrderShipmentByUsers(User user);
   List<OrderShipment> findAll(Sort sort);
   List<OrderShipment> findByStatus(String name);
   List<OrderShipment> findOrderShipmentByStoragesShipmentsShipmentnameAndPathingAdress(String ship,String adr);
   List<OrderShipment> findOrderShipmentByStoragesShipmentsShipmentname(String ship);

   List<OrderShipment> findOrderShipmentByPathingAdress(String adr);
}
