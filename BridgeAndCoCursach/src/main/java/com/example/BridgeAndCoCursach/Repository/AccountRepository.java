package com.example.BridgeAndCoCursach.Repository;

import com.example.BridgeAndCoCursach.Models.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account,Long> {
   // Account findAccountByLogin(String login);
   public Account findById(int accId);
   public Account findAccountByUsername(String username);
}
