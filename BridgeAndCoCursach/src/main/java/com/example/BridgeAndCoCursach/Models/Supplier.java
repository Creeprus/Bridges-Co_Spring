package com.example.BridgeAndCoCursach.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
public class Supplier {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @NotNull
    @NotBlank(message="Поле не должно быть пустым")
    @Size(min=2,max=50,message="Поле должно содержать не менeе 2 и не более 50 символов")
    public String Country;
    @NotNull
    @NotBlank(message="Поле не должно быть пустым")
    @Size(min=2,max=100,message="Поле должно содержать не менeе 2 и не более 50 символов")
    public String suppliername;
    @JsonBackReference
    @OneToMany(mappedBy = "supplier", fetch =FetchType.LAZY)
    private Collection<Supply> supplies;

    public Supplier(String country, String supplier_Name, Collection<Supply> supplies) {
        Country = country;
        suppliername = supplier_Name;
        this.supplies = supplies;
    }

    public Supplier() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getSuppliername() {
        return suppliername;
    }

    public void setSuppliername(String supplierName) {
        this.suppliername = supplierName;
    }

    public Collection<Supply> getSupplies() {
        return supplies;
    }

    public void setSupplies(Collection<Supply> supplies) {
        this.supplies = supplies;
    }
}
