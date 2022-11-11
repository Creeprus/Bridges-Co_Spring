package com.example.BridgeAndCoCursach.APIRepository;

import com.example.BridgeAndCoCursach.Models.Account;
import com.example.BridgeAndCoCursach.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAPIRepository extends JpaRepository<User,Long> {
}
