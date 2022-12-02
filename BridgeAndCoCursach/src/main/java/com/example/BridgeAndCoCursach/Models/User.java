package com.example.BridgeAndCoCursach.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import net.minidev.json.annotate.JsonIgnore;

import javax.annotation.Nullable;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotNull
    @NotEmpty(message="Поле не должно быть пустым")
    @Size(min=3,max=30,message="Поле должно содержать не менее 3х и не более 30 символов")
    private String name;
    @NotNull
    @NotEmpty(message="Поле не должно быть пустым")
    @Size(min=3,max=30,message="Поле должно содержать не менее 3х и не более 30 символов")
    private String surname;


    private String patronymic;

    @Email(message = "Неправильный формат почты")
    private String Email;
    @Nullable
    @Pattern(regexp="(^$|[0-9]{11})")
    private String PhoneNumber;

    @OneToOne(optional = true,cascade = CascadeType.ALL)
    @JoinColumn(name="account_id")
    private Account account;
    @JsonBackReference
    @ManyToMany
    @JoinTable(name="ordershipment_user",
            joinColumns=@JoinColumn(name = "user_id"),
            inverseJoinColumns=@JoinColumn(name = "ordershipment_id"))
    private List<OrderShipment> orders;



    public User() {
    }

    public User(String name, String surname, String patronymic, String email, @Nullable String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        Email = email;
        PhoneNumber = phoneNumber;
    }

    public User(String name, String surname, String patronymic, String email, String phoneNumber, Account account, List<OrderShipment> orders) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        Email = email;
        PhoneNumber = phoneNumber;
        this.account = account;
        this.orders = orders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<OrderShipment> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderShipment> orders) {
        this.orders = orders;
    }


}
