package com.example.BridgeAndCoCursach.Controllers;

import com.example.BridgeAndCoCursach.Models.*;
import com.example.BridgeAndCoCursach.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@PreAuthorize("hasAnyAuthority('Клиент')")
@Controller
@RequestMapping("/Client")
public class ClientController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    PathingRepository pathingRepository;
    @Autowired
    StorageRepository storageRepository;
    @Autowired
    OrderRepository orderRepository;
    public static HttpSession session() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest().getSession(true); // true == allow create
    }
    public String UserSession() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();


        // modelMap.addAttribute("username", name);
        return name;
    }
    @GetMapping("/Index")
    public String regView(Account user, Model model)
    {
        return "/Client/Index";
    }

    @GetMapping("/Shipments/View")
    public String ShipmentView(Account user, OrderShipment orderShipment, Pathing pathing,Model model)
    {
        model.addAttribute("storage",storageRepository.findAll());
        return "/Client/Shipments/View";
    }
    @GetMapping("/Orders/View")
    public String OrderView(Account account , OrderShipment orderShipment, Pathing pathing, Model model)
    {
        String currentuser=UserSession();
        account=accountRepository.findAccountByUsername(currentuser);
       User user=userRepository.findFirstByAccount(account);;
List <OrderShipment> orders= orderRepository.findAll();
        orders.removeIf(order -> !order.getUsers().contains(user));
        model.addAttribute("order",orders);
        return "/Client/Orders/View";
    }
    @PostMapping("/Order/Add")
    public String ShipmentAdd(@RequestParam Long currentid, User user,Account account, OrderShipment orderShipment, Pathing pathing, Storage storage, Model model)
    {
        Calendar cal = Calendar.getInstance();
        Date date=cal.getTime();
        storage=storageRepository.findById(currentid).orElseThrow();
            orderShipment.setStorages(storage);
pathing.setPath_time("Требует назначения");
        pathing.setTransport("Требует назначения");
        pathing.setPathcost(0.00);
            String currentuser=UserSession();
             account=accountRepository.findAccountByUsername(currentuser);
            orderShipment.setUsers( userRepository.findUserByAccount(account));
             orderShipment.setStatus("В обработке");
             orderShipment.setDate_of_order(date);
             orderShipment.setPathing(pathing);
             orderShipment.setSummary(storage.getAmount()*storage.getShipments().getCost());
        //    orderShipment.setUsers(user);
        pathingRepository.save(pathing);
            orderRepository.save(orderShipment);
        model.addAttribute("storage",storageRepository.findAll());
        return "redirect:/Client/Shipments/View";
    }
    @PostMapping("/Orders/Cancel/{id}")
    public String OrderCancel(@PathVariable Long id, Account account , OrderShipment orderShipment, Pathing pathing, Model model)
    {
        orderShipment=orderRepository.findById(id).orElseThrow();
        orderShipment.setStatus("Отменён");
        orderRepository.save(orderShipment);


        String currentuser=UserSession();
        account=accountRepository.findAccountByUsername(currentuser);
        User user=userRepository.findFirstByAccount(account);;
        List <OrderShipment> orders= orderRepository.findAll();
        orders.removeIf(order -> !order.getUsers().contains(user));
        model.addAttribute("order",orders);
        return "/Client/Orders/View";
    }
}
