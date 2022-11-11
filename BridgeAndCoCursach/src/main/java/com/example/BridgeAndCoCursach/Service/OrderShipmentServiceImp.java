package com.example.BridgeAndCoCursach.Service;

import com.example.BridgeAndCoCursach.APIRepository.OrderShipmentAPIRepository;
import com.example.BridgeAndCoCursach.APIRepository.ShipmentAPIRepository;
import com.example.BridgeAndCoCursach.Models.OrderShipment;
import com.example.BridgeAndCoCursach.Models.Pathing;
import com.example.BridgeAndCoCursach.Models.Shipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class OrderShipmentServiceImp implements OrderShipmentService{
    @Autowired
    OrderShipmentAPIRepository orderShipmentAPIRepository;
    @Override
    public MessageResponse create(OrderShipment Request) {
        OrderShipment orderShipment=new OrderShipment();
        orderShipment.setDate_of_order(Request.getDate_of_order());
        orderShipment.setDate_of_depart(Request.getDate_of_depart());
        orderShipment.setAmount(Request.getAmount());
        orderShipment.setPathing(Request.getPathing());
        orderShipment.setSummary(Request.getSummary());
        orderShipment.setUsers(Request.getUsers());

        orderShipmentAPIRepository.save(orderShipment);
        return new MessageResponse("Товар был создан");

    }

    @Override
    public Optional<OrderShipment> update(Long Id, OrderShipment Request) {
        Optional<OrderShipment> orderShipment = orderShipmentAPIRepository.findById(Id);
        if (orderShipment.isEmpty()){
            return null;
        }
        else {
            orderShipment.get().setDate_of_order(Request.getDate_of_order());
            orderShipment.get().setDate_of_depart(Request.getDate_of_depart());
            orderShipment.get().setAmount(Request.getAmount());
            orderShipment.get().setPathing(Request.getPathing());
            orderShipment.get().setSummary(Request.getSummary());
            orderShipment.get().setUsers(Request.getUsers());
            orderShipmentAPIRepository.save(orderShipment.get());
            return orderShipment;
        }
    }

    @Override
    public void delete(Long shipmentId) {
        orderShipmentAPIRepository.deleteById(shipmentId);
    }

    @Override
    public OrderShipment getASingle(Long Id) {
        return orderShipmentAPIRepository.findById(Id).orElseThrow();
    }

    @Override
    public List<OrderShipment> getAll() {
        return orderShipmentAPIRepository.findAll();
    }
}
