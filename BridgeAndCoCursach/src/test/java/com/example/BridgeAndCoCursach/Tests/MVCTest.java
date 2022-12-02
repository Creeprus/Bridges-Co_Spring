package com.example.BridgeAndCoCursach.Tests;

import com.example.BridgeAndCoCursach.APIRepository.*;
import com.example.BridgeAndCoCursach.Models.*;
import com.example.BridgeAndCoCursach.Securing.Sha512PasswordEncoder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.core.parameters.P;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.AssertFalse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class MVCTest {

    @Autowired
    TestEntityManager entityManager;
    @Autowired
    AccountAPIRepository accountRepository;
    @Autowired
    UserAPIRepository userRepository;
    @Autowired
    StorageAPIRepository storageRepository;
    @Autowired
    SupplierAPIRepository supplierRepository;
    @Autowired
    OrderShipmentAPIRepository orderRepository;
    @Autowired
     PathingAPIRepository pathingRepository;


    Sha512PasswordEncoder passwordEncoder=new Sha512PasswordEncoder();


    @Test
    void user_edit_success(){

        Account tut1 = accountRepository.findById(16L).orElseThrow();
        entityManager.persist(tut1);

        Account updatedtur=new Account("TestedUpdated", tut1.getPassword(), true,tut1.getRole(),tut1.getUser());
        updatedtur.setId(tut1.getId());
        updatedtur.getRole().clear();
        updatedtur.getRole().add(Role.valueOf("Администратор"));


        accountRepository.save(updatedtur);
        Account checkTut = accountRepository.findById(updatedtur.getId()).get();
        assertThat(checkTut.getId()).isEqualTo(tut1.getId());
        assertThat(checkTut.getPassword()).isEqualTo(tut1.getPassword());
        assertThat(checkTut.getUsername()).isEqualTo(tut1.getUsername());
        assertThat(checkTut.getRoleName()).isEqualTo(tut1.getRoleName());

    }

    @Test
    void user_registrated_success()
    {
        User registrated_user=new User("Виталий","Виталик","Витальевич","vitalya@pepe.ru","89451623985");
        Account tosave=new Account();
        tosave=new Account("testeduser", passwordEncoder.encode("Oofpepegalul"),true,tosave.getRole(),registrated_user);
        tosave.setRole(Collections.singleton(Role.Клиент));
        accountRepository.save(tosave);
        assertThat(tosave).hasFieldOrPropertyWithValue("id", tosave.getId());
    }
    @Test
    void user_registrated_fail()
    {
        User registrated_user=new User("Виталий","Виталик","","vitalya@pepe.ru","894591623985");
        Account tosave=new Account();
        tosave=new Account("", passwordEncoder.encode("Oofpepegalul"),true,tosave.getRole(),registrated_user);
        tosave.setRole(Collections.singleton(Role.Клиент));
        try {
            accountRepository.save(tosave);
        }
       catch (Exception e)
       {
           fail("Validation failed");
       }
        assertThat(tosave).hasFieldOrPropertyWithValue("id", tosave.getId());
    }

    @Test
    void user_registrated_fail_1()
    {
        User registrated_user=new User("Виталий","","","vitalya@pepe.ru","894591623985");
        Account tosave=new Account();
        tosave=new Account("", passwordEncoder.encode("Oofpepegalul"),true,tosave.getRole(),registrated_user);
        tosave.setRole(Collections.singleton(Role.Клиент));
        try {
            accountRepository.save(tosave);
        }
        catch (Exception e)
        {
            fail("Validation failed");
        }
        assertThat(tosave).hasFieldOrPropertyWithValue("id", tosave.getId());
    }
    @Test
    void shipment_add_success()
    {
        Supplier supplier=new Supplier();
        supplier.setCountry("Ад");
        supplier.setSuppliername("Адская коалиция имени Дьяволов МПТ");
        Shipment shipment=new Shipment();
        shipment.setShipmentname("Тестовая мазь");
        shipment.setCost(25.50);
        shipment.setExpirationdate(Date.valueOf("2019-01-26"));
        Supply supply=new Supply();
        supply.setDateofsupply(Date.valueOf("2019-01-26"));
        supply.setSupplier(supplier);
       Storage storage=new Storage(12,shipment,supply);
       storage.setShipments(shipment);
       storage.setSupplies(supply);
        storageRepository.save(storage);

        assertThat(storage).hasFieldOrPropertyWithValue("id", storage.getId());
    }
    @Test
    void shipment_edit_success()
    {
        Storage storage=storageRepository.findById(140L).orElseThrow();
        entityManager.persist(storage);
        Storage storageupdt=storageRepository.findById(140L).orElseThrow();
        Supplier supplier=supplierRepository.findSupplierById(200L);

        storageupdt.setAmount(90);
        storageupdt.getSupplies().setSupplier(supplier);

        storageRepository.save(storageupdt);
        Storage checkTut = storageRepository.findById(storageupdt.getId()).get();
        assertThat(checkTut.getId()).isEqualTo(storage.getId());
        assertThat(checkTut.getAmount()).isEqualTo(storage.getAmount());
        assertThat(checkTut.getSupplies().getSupplier()).isEqualTo(storage.getSupplies().getSupplier());

    }
    @Test
    void shipment_edit_fail()
    {
        Storage storage=storageRepository.findById(140L).orElseThrow();
        entityManager.persist(storage);
        Storage storageupdt=storageRepository.findById(140L).orElseThrow();
        Supplier supplier=supplierRepository.findSupplierById(200L);

        storageupdt.setAmount(-20);
        storageupdt.getSupplies().setSupplier(supplier);
if (storageupdt.getAmount()<0)
{
    fail("Validation failed");
}
        storageRepository.save(storageupdt);
        Storage checkTut = storageRepository.findById(storageupdt.getId()).get();
        assertThat(checkTut.getId()).isEqualTo(storage.getId());
        assertThat(checkTut.getAmount()).isEqualTo(storage.getAmount());
        assertThat(checkTut.getSupplies().getSupplier()).isEqualTo(storage.getSupplies().getSupplier());

    }
    @Test
    void supplier_add_success()
    {
        Supplier supplier=      supplierRepository.save(new Supplier("Ад","Адская коалиция имени Дьяволов МПТ",null));


        if(supplier.getSuppliername().length()<10 || supplier.getCountry().length()<2)
        {
            fail("Validation failed");
        }
        assertThat(supplier).hasFieldOrPropertyWithValue("id", supplier.getId());
    }
    @Test
    void supplier_add_fail()
    {
        Supplier supplier=supplierRepository.save(new Supplier("","Адская коалиция имени Дьяволов МПТ",null));


        if(supplier.getSuppliername().length()<10 || supplier.getCountry().length()<2)
        {
            fail("Validation failed");
        }
        assertThat(supplier).hasFieldOrPropertyWithValue("id", supplier.getId());
    }
    @Test
    void supplier_edit_success()
    {
        Supplier tut1 = supplierRepository.findById(64L).orElseThrow();
        entityManager.persist(tut1);
        Supplier supplier=new Supplier();
        supplier.setId(tut1.getId());
        supplier.setCountry(tut1.getCountry());
        supplier.setSuppliername("Унитожительные вещи Шимбирёва");

        supplierRepository.save(supplier);
        Supplier checkTut = supplierRepository.findById(supplier.getId()).get();
        assertThat(supplier).hasFieldOrPropertyWithValue("id", tut1.getId());

    }
    @Test
    void supplier_delete_success()
    {
        Supplier supplier=supplierRepository.findSupplierById(200L);
        if(supplier.getSupplies().size()==0)
        {
            supplierRepository.delete(supplier);
        }
        List<Supplier> supplierList=supplierRepository.findAll();

       assertThat(supplier).isNotIn(supplierList);

    }
    @Test
    void supplier_delete_fail()
    {
        Supplier supplier=supplierRepository.findSupplierById(1L);
        if(supplier.getSupplies().size()==0)
        {
            supplierRepository.delete(supplier);
        }
        List<Supplier> supplierList=supplierRepository.findAll();

        assertThat(supplier).isNotIn(supplierList);

    }
    @Test
    void order_success()
    {
        User client=userRepository.findById(148L).orElseThrow();
        List<User> users=new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        java.util.Date date=cal.getTime();
       Storage storage=storageRepository.findById(140L).orElseThrow();
       Pathing pathing=new Pathing();
        pathing.setPath_time("Требует назначения");
        pathing.setAdress("Ул. Пушкино д. Колотушкино");
        pathing.setTransport("Требует назначения");
        pathing.setPathcost(0.00);
        OrderShipment order=new OrderShipment();
        order.setDate_of_order(date);
        order.setUsers(users);
        order.setAmount(15);
        order.setPathing(pathing);
        order.setSummary(order.getAmount()*storage.getShipments().getCost());
        order.setStatus("В обработке");
        orderRepository.save(order);
        assertThat(order).hasFieldOrPropertyWithValue("id", order.getId());
    }
    @Test
    void order_cancel()
    {
OrderShipment order=orderRepository.findById(145L).orElseThrow();
        entityManager.persist(order);
        OrderShipment orderupd=order;
        orderupd.setId(order.getId());
        orderupd.setStatus("Отменён");

        orderRepository.save(orderupd);
        OrderShipment checkTut = orderRepository.findById(orderupd.getId()).get();
        assertThat(orderupd).hasFieldOrPropertyWithValue("id", order.getId());
    }
    @Test
    void pathing_edit_success()
    {
        Pathing pathing=pathingRepository.findById(16L).orElseThrow();
        entityManager.persist(pathing);

        Pathing pathupdate=new Pathing("00:45","Ул. Боли и страданий", "Адская колесница",0.00,pathing.getOrder());
        if(pathupdate.getAdress().length()<10 || pathupdate.getTransport().length()<3 || pathupdate.getPathcost()<0.00)
        {
            fail("Validation failed");
        }
        pathupdate.setId(pathing.getId());

        pathingRepository.save(pathupdate);

        Pathing checkTut = pathingRepository.findById(pathupdate.getId()).get();
        assertThat(pathupdate).hasFieldOrPropertyWithValue("id", pathing.getId());

    }
    @Test
    void pathing_edit_fail()
    {
        Pathing pathing=pathingRepository.findById(16L).orElseThrow();
        entityManager.persist(pathing);

        Pathing pathupdate=new Pathing("00:45","Нету", "А",-0.01,null);
    if(pathupdate.getAdress().length()<10 || pathupdate.getTransport().length()<3 || pathupdate.getPathcost()<0.00)
    {
        fail("Validation failed");
    }
        pathupdate.setId(pathing.getId());

        pathingRepository.save(pathupdate);

        Pathing checkTut = pathingRepository.findById(pathupdate.getId()).get();
        assertThat(pathupdate).hasFieldOrPropertyWithValue("id", pathing.getId());

    }
    @Test
    void account_update_success()
    {
        User user=userRepository.findById(148L).orElseThrow();

        User newdata=new User(user.getName(),user.getSurname(),user.getPatronymic(),"pepega@a.ru","",
                new Account(user.getAccount().getUsername(),passwordEncoder.encode("newPassword"),true,user.getAccount().getRole(),null)
                ,user.getOrders());
        newdata.setId(user.getId());
        userRepository.save(newdata);
        assertThat(newdata).hasFieldOrPropertyWithValue("id", user.getId());
    }
    @Test
    void account_update_fail()
    {
        User user=userRepository.findById(148L).orElseThrow();

        User newdata=new User(user.getName(),user.getSurname(),user.getPatronymic(),"pepega@a.ru","",
                new Account("","",true,user.getAccount().getRole(),null)
                ,user.getOrders());
        newdata.setId(user.getId());
        try
            {

                userRepository.save(newdata);
            }catch(Exception e)
            {
                fail("Validation failed");
            }
        assertThat(newdata).hasFieldOrPropertyWithValue("id", user.getId());
    }
    @Test
    void order_delivered()
    {
        OrderShipment order=orderRepository.findById(145L).orElseThrow();
        entityManager.persist(order);
        OrderShipment orderupd=order;
        orderupd.setId(order.getId());
        orderupd.setStatus("Доставлен");

        orderRepository.save(orderupd);
        OrderShipment checkTut = orderRepository.findById(orderupd.getId()).get();
        assertThat(orderupd).hasFieldOrPropertyWithValue("id", order.getId());
    }
}