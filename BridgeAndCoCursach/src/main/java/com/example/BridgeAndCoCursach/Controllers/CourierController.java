package com.example.BridgeAndCoCursach.Controllers;

import com.example.BridgeAndCoCursach.Models.*;
import com.example.BridgeAndCoCursach.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@PreAuthorize("hasAnyAuthority('Курьер')")
@Controller
@RequestMapping("/Courier")
public class CourierController {

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

    @GetMapping("/Orders")
    public String OrderView(Account account , OrderShipment orderShipment, Pathing pathing, Model model)
    {
        String currentuser=UserSession();
        account=accountRepository.findAccountByUsername(currentuser);
        User user=userRepository.findFirstByAccount(account);;
        List<OrderShipment> orders= orderRepository.findAll();
        orders.removeIf(order -> !order.getUsers().contains(user));
        model.addAttribute("order",orders);
        return "/Courier/Orders";
    }
    @PostMapping("/Order/Finish/{id}")
    public String OrderFinish(@PathVariable(name="id") Long id, Account account , OrderShipment orderShipment, Pathing pathing, Model model)
    {
        orderShipment=orderRepository.findById(id).orElseThrow();
        orderShipment.setStatus("Доставлен");
        orderRepository.save(orderShipment);


        String currentuser=UserSession();
        account=accountRepository.findAccountByUsername(currentuser);
        User user=userRepository.findFirstByAccount(account);;
        List<OrderShipment> orders= orderRepository.findAll();
        orders.removeIf(order -> !order.getUsers().contains(user));
        model.addAttribute("order",orders);
        return "/Courier/Orders";
    }
}
