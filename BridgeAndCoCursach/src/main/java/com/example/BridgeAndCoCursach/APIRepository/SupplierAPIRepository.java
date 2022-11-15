package com.example.BridgeAndCoCursach.APIRepository;

import com.example.BridgeAndCoCursach.Models.Account;
import com.example.BridgeAndCoCursach.Models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;


@Transactional
public interface SupplierAPIRepository extends JpaRepository<Supplier,Long> {
   public Supplier findSupplierById(Long id);

}
