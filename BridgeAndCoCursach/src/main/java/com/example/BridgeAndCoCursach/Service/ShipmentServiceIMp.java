package com.example.BridgeAndCoCursach.Service;

import com.example.BridgeAndCoCursach.APIRepository.ShipmentAPIRepository;
import com.example.BridgeAndCoCursach.Models.Shipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShipmentServiceIMp implements ShipmentService{

    @Autowired
    ShipmentAPIRepository shipmentAPIRepository;
    @Override
    public MessageResponse createEmployee(Shipment shipmentRequest) {
        Shipment shipment=new Shipment();
        shipment.setExpirationdate(shipmentRequest.getExpirationdate());
        shipment.setShipmentname(shipmentRequest.getShipmentname());

        shipment.setCost(shipmentRequest.getCost());
        shipmentAPIRepository.save(shipment);
        return new MessageResponse("Товар был создан");

    }

    @Override
    public Optional<Shipment> updateEmployee(Long shipmentId, Shipment shipmentRequest) {
        Optional<Shipment> shipment = shipmentAPIRepository.findById(shipmentId);
        if (shipment.isEmpty()){
            return null;
        }
        else {
            shipment.get().setExpirationdate(shipmentRequest.getExpirationdate());
            shipment.get().setShipmentname(shipmentRequest.getShipmentname());

            shipment.get().setCost(shipmentRequest.getCost());
            shipmentAPIRepository.save(shipment.get());
            return shipment;
        }
    }

    @Override
    public void deleteEmployee(Long shipmentId) {

            shipmentAPIRepository.deleteById(shipmentId);




    }

    @Override
    public Shipment getASingleEmployee(Long shipmentId) {
        return shipmentAPIRepository.findById(shipmentId).orElseThrow();
    }

    @Override
    public List<Shipment> getAllEmployee() {
        return shipmentAPIRepository.findAll();
    }
}
