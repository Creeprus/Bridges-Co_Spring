package com.example.BridgeAndCoCursach.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ordershipment")
public class OrderShipment {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotNull
    public Date date_of_order;
    //@NotNull
    public Date date_of_depart;
    @NotNull
    public int amount;
    @NotNull
    public Double summary;
    public String status;
    @JsonBackReference
    @OneToOne(optional = true,cascade = CascadeType.ALL)
    @JoinColumn(name="pathing_id")
    private Pathing pathing;
    @JsonBackReference
    @ManyToOne(optional =true)
    private Shipment shipments;
    @JsonBackReference
    @ManyToMany
    @JoinTable(name="ordershipment_user",
            joinColumns=@JoinColumn(name = "ordershipment_id"),
            inverseJoinColumns=@JoinColumn(name = "user_id"))
    private List<User> users;


    public OrderShipment(Date date_of_order, Date date_of_depart, int amount, Double summary, String status, Pathing pathing, Shipment shipments, List<User> users) {
        this.date_of_order = date_of_order;
        this.date_of_depart = date_of_depart;
        this.amount = amount;
        this.summary = summary;
        this.status = status;
        this.pathing = pathing;
        this.shipments = shipments;
        this.users = users;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Shipment getShipments() {
        return shipments;
    }

    public void setShipments(Shipment shipments) {
        this.shipments = shipments;
    }

    public OrderShipment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate_of_order() {
        return date_of_order;
    }

    public void setDate_of_order(Date date_of_order) {
        this.date_of_order = date_of_order;
    }

    public Date getDate_of_depart() {
        return date_of_depart;
    }

    public void setDate_of_depart(Date date_of_depart) {
        this.date_of_depart = date_of_depart;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Double getSummary() {
        return summary;
    }

    public void setSummary(Double summary) {
        this.summary = summary;
    }

    public Pathing getPathing() {
        return pathing;
    }

    public void setPathing(Pathing pathing) {
        this.pathing = pathing;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}

