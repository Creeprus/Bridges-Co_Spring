package com.example.BridgeAndCoCursach.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Storage {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @NotNull
    public int amount;
    @JsonBackReference
    @ManyToOne(optional =true)
    private Shipment shipments;

    @JsonBackReference
    @ManyToOne(optional =true)
    private Supply supplies;




    public Storage(int amount, Shipment shipments, Supply supplies) {
        this.amount = amount;
        this.shipments = shipments;
        this.supplies = supplies;

    }

    public Storage() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Shipment getShipments() {
        return shipments;
    }

    public void setShipments(Shipment shipments) {
        this.shipments = shipments;
    }

    public Supply getSupplies() {
        return supplies;
    }

    public void setSupplies(Supply supplies) {
        this.supplies = supplies;
    }
}
