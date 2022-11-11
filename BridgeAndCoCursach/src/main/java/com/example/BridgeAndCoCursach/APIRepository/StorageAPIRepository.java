package com.example.BridgeAndCoCursach.APIRepository;

import com.example.BridgeAndCoCursach.Models.Account;
import com.example.BridgeAndCoCursach.Models.Storage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StorageAPIRepository extends JpaRepository<Storage,Long> {
}
