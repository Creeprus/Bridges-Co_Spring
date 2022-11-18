package com.example.BridgeAndCoCursach.Repository;

import com.example.BridgeAndCoCursach.Models.Account;
import com.example.BridgeAndCoCursach.Models.Supply;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SupplyRepository extends CrudRepository<Supply,Long> {
    List<Supply> findAll(Sort sort);
}
