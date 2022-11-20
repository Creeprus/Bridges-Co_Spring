package com.example.BridgeAndCoCursach.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import net.minidev.json.annotate.JsonIgnore;

import javax.annotation.RegEx;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty(message="Поле не должно быть пустым")
    @Pattern(regexp = "^[a-zA-Z0-9]{3,30}$",
            message = "Логин должен быть не более 30 и не менее 3 символов")
    private String username;
    @NotNull
    @NotEmpty(message="Поле не должно быть пустым")


    private String password;

    @JsonIgnore
    public Boolean active;

    public Boolean getActive() {
        return active;
    }
    @ElementCollection(targetClass = Role.class,
            fetch = FetchType.EAGER)
    @CollectionTable(name="role",
            joinColumns = @JoinColumn(name="user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> role;
    public void setActive(Boolean active) {
        this.active = active;
    }

    @JsonBackReference
    @OneToOne(optional = true,mappedBy = "account")
    private User user;



    public Set<Role> getRole() {
        return role;
    }
    public String getRoleName(){return role.toString();}

    public void setRole(Set<Role> role) {
        this.role = role;
    }

    public Account(String username, String password, Boolean active, Set<Role> role, User user) {
        this.username = username;
        this.password = password;
        this.active = active;
        this.role = role;
        this.user = user;
    }

    public Account() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
@JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
