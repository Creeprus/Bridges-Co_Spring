package com.example.BridgeAndCoCursach.Repository;

import com.example.BridgeAndCoCursach.Models.Account;
import com.example.BridgeAndCoCursach.Models.Role;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepository extends CrudRepository<Account,Long> {
   // Account findAccountByLogin(String login);
   public Account findById(int accId);
   List<Account> findAll(Sort sort);
   public Account findAccountByUsername(String username);
   public List<Account> findAccountByRole(Role role);
}
