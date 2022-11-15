package com.example.BridgeAndCoCursach.Repository;

import com.example.BridgeAndCoCursach.Models.Account;
import com.example.BridgeAndCoCursach.Models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User,Long> {
   List<User> findByName(String username);
   List<User> findUserByAccount(Account account) ;
   User findFirstByAccount(Account account);
   List<User> findUsersById(Long id);

}
