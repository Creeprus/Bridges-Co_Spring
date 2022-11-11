package com.example.BridgeAndCoCursach.API;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/role/")
public class APIRoleController {


    @GetMapping("roles")
    public ResponseEntity<List<String>> getAll() {

         List<String> roles=new ArrayList<>();
         roles.add("Администратор");
        roles.add("Логист"); roles.add("Кладовщик"); roles.add("Курьер");roles.add("Клиент");roles.add("Поставщик");
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }


}
