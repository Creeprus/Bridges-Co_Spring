package com.example.BridgeAndCoCursach.Repository;

import com.example.BridgeAndCoCursach.Models.Supplier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SupplierRepository extends CrudRepository<Supplier,Long> {

    List<Supplier> findSupplierBySuppliernameContaining(String name);
}
