package com.example.BridgeAndCoCursach.Controllers;

import com.example.BridgeAndCoCursach.Models.Account;
import com.example.BridgeAndCoCursach.Models.OrderShipment;
import com.example.BridgeAndCoCursach.Models.Pathing;
import com.example.BridgeAndCoCursach.Repository.OrderRepository;
import com.example.BridgeAndCoCursach.Repository.PathingRepository;
import com.example.BridgeAndCoCursach.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@PreAuthorize("hasAnyAuthority('Логист','Администратор')")
@Controller
@RequestMapping("/Logist")
public class LogistController {

    @Autowired
    PathingRepository pathingRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    OrderRepository orderRepository;
    @GetMapping("/Index")
    public String regView(Account user, Model model)
    {
        return "/Logist/Index";
    }

    @GetMapping("/Pathing/View")
    public  String pathingView(Account account , OrderShipment orderShipment, Pathing pathing, Model model)
    {
        List<OrderShipment> orders= orderRepository.findAll();

        model.addAttribute("order",orders);
            return "Logist/Pathing/View";
    }
}
