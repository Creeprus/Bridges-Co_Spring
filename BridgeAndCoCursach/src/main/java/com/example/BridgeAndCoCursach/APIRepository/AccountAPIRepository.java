package com.example.BridgeAndCoCursach.APIRepository;

import com.example.BridgeAndCoCursach.Models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountAPIRepository extends JpaRepository<Account,Long> {
}
