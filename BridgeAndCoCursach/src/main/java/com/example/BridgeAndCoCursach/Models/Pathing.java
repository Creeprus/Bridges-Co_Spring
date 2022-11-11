package com.example.BridgeAndCoCursach.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Pathing {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message="Поле не должно быть пустым")
    public String path_time;
    @NotNull
    @NotBlank(message="Поле не должно быть пустым")
    @Size(min=10,max=200,message="Поле должно содержать не менее 10 и не более 200 символов")
    public String adress;


    @NotBlank(message="Поле не должно быть пустым")
    @Size(min=3,max=200,message="Поле должно содержать не менее 3 и не более 200 символов")
    public String transport;

    @JsonBackReference
    @OneToOne(optional = true,mappedBy = "pathing")
    private OrderShipment order;

    public Pathing(String path_time, String adress, String transport, OrderShipment order) {
        this.path_time = path_time;
        this.adress = adress;
        this.transport = transport;
        this.order = order;
    }

    public Pathing() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath_time() {
        return path_time;
    }

    public void setPath_time(String path_time) {
        this.path_time = path_time;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public OrderShipment getOrder() {
        return order;
    }

    public void setOrder(OrderShipment order) {
        this.order = order;
    }
}
