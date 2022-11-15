package com.example.BridgeAndCoCursach.Service;

import com.example.BridgeAndCoCursach.Models.OrderShipment;
import com.example.BridgeAndCoCursach.Models.User;
import com.example.BridgeAndCoCursach.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
   @Autowired
    private UserRepository repo;

   public List<User> listAll()
   {

       return repo.findAll();
   }

}
