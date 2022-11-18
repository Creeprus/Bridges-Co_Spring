package com.example.BridgeAndCoCursach.Repository;

import com.example.BridgeAndCoCursach.Models.Pathing;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PathingRepository extends CrudRepository<Pathing,Long> {
    List<Pathing> findAll(Sort sort);
}
