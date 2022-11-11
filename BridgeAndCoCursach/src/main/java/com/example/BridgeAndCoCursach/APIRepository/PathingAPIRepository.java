package com.example.BridgeAndCoCursach.APIRepository;

import com.example.BridgeAndCoCursach.Models.Account;
import com.example.BridgeAndCoCursach.Models.Pathing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PathingAPIRepository extends JpaRepository<Pathing,Long> {
}
