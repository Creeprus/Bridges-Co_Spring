package com.example.BridgeAndCoCursach.Repository;

import com.example.BridgeAndCoCursach.Models.Shipment;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface ShipmentRepository extends CrudRepository<Shipment,Long> {
    List<Shipment> findShipmentByShipmentnameContaining(String name);
    List<Shipment> findAll(Sort sort);
    ArrayList<Shipment> findAll();
    List<Shipment> findShipmentByShipmentnameContainingOrderByShipmentnameAsc(String name);
}
