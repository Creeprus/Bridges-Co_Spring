package com.example.BridgeAndCoCursach.Models;



import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Date;

@Entity
public class Shipment {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @NotNull
    @NotBlank(message="Поле не должно быть пустым")
    @Size(min=10,max=150,message="Поле должно содержать не менeе 10 и не более 150 символов")
    public String shipmentname;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    public Date expirationdate;



    @Column(precision = 10,scale=2)
    public Double Cost;


    @JsonIgnore
    @OneToMany(mappedBy = "shipments", fetch =FetchType.EAGER)
    private Collection<Storage> storages;


    public Shipment(String shipmentname, Date expirationdate, Double cost, Collection<Storage> storages) {
        this.shipmentname = shipmentname;
        this.expirationdate = expirationdate;
        Cost = cost;
        this.storages = storages;
    }

    public Shipment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getShipmentname() {
        return shipmentname;
    }

    public void setShipmentname(String shipmentname) {
        this.shipmentname = shipmentname;
    }

    public Date getExpirationdate() {
        return expirationdate;
    }

    public void setExpirationdate(Date expirationdate) {
        this.expirationdate = expirationdate;
    }

    public Double getCost() {
        return Cost;
    }

    public void setCost(Double cost) {
        Cost = cost;
    }

    @JsonIgnore
    public Collection<Storage> getStorages() {
        return storages;
    }

    public void setStorages(Collection<Storage> storages) {
        this.storages = storages;
    }

}
