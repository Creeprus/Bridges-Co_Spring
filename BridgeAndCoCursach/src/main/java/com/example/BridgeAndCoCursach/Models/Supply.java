package com.example.BridgeAndCoCursach.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;

@Entity
public class Supply {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @NotNull
    public Date dateofsupply;

    @ManyToOne(optional =true)
    private Supplier supplier;

    @OneToMany(mappedBy = "supplies", fetch =FetchType.EAGER)
    private Collection<Storage> storages;

    public Supply(Date dateofsupply, Supplier supplier, Collection<Storage> storages) {
        this.dateofsupply = dateofsupply;
        this.supplier = supplier;
        this.storages = storages;
    }

    public Supply() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateofsupply() {
        return dateofsupply;
    }

    public void setDateofsupply(Date dateofsupply) {
        this.dateofsupply = dateofsupply;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Collection<Storage> getStorages() {
        return storages;
    }

    public void setStorages(Collection<Storage> storages) {
        this.storages = storages;
    }
}
