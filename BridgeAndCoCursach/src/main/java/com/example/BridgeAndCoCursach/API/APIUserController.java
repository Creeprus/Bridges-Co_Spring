package com.example.BridgeAndCoCursach.API;

import com.example.BridgeAndCoCursach.APIRepository.AccountAPIRepository;
import com.example.BridgeAndCoCursach.APIRepository.UserAPIRepository;
import com.example.BridgeAndCoCursach.Models.Account;
import com.example.BridgeAndCoCursach.Models.User;
import com.example.BridgeAndCoCursach.Securing.Sha512PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user/")
public class APIUserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final UserAPIRepository repository;

    APIUserController(UserAPIRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/users")
    List<User> all() {
        return repository.findAll();
    }
    @GetMapping("/{id}")
    User one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow();
    }
    @PutMapping("{id}")
    public ResponseEntity<User> updateAccount(@PathVariable("id") long id, @RequestBody User user) {
        Optional<User> tutorialData = repository.findById(id);

        if (tutorialData.isPresent()) {
            User _user = tutorialData.get();
            _user.setName(user.getName());
            _user.setSurname(user.getSurname());
            _user.setPatronymic(user.getPatronymic());
            _user.setEmail(user.getEmail());
            _user.setPhoneNumber(user.getPhoneNumber());
            _user.setOrders(user.getOrders());
            user.getAccount().setPassword(passwordEncoder.encode(user.getAccount().getPassword()));
            _user.setAccount(user.getAccount());
            return new ResponseEntity<>(repository.save(_user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<User> createTutorial(@RequestBody User user) {
        try {
            user.getAccount().setPassword(passwordEncoder.encode(user.getAccount().getPassword()));
            User _user = repository
                    .save(user);
            return new ResponseEntity<>(_user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("{id}")
    void deleteUser(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
