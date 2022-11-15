package com.example.BridgeAndCoCursach.Controllers;

import com.example.BridgeAndCoCursach.Models.*;
import com.example.BridgeAndCoCursach.Repository.OrderRepository;
import com.example.BridgeAndCoCursach.Repository.PathingRepository;
import com.example.BridgeAndCoCursach.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

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
        model.addAttribute("couriers", userRepository.findAll());
        model.addAttribute("roleClient", Role.values());
        Iterable<OrderShipment> orders= orderRepository.findAll();
        model.addAttribute("order",orders);
            return "Logist/Pathing/View";
    }
    @PostMapping("/Pathing/Edit/{id}")
    public  ModelAndView pathingEdit(@PathVariable(name="id") Long id, Account account , OrderShipment orderShipment, Pathing pathing, Model model)
    {
        orderShipment = orderRepository.findById(id).orElseThrow();

       Pathing path=  pathingRepository.findById(orderShipment.getPathing().getId()).orElseThrow();
       pathing.setAdress(path.getAdress());
       pathing.setId(path.getId());
        pathingRepository.save(pathing);
        model.addAttribute("roleClient", Role.values());
        Iterable<OrderShipment> orders= orderRepository.findAll();
        model.addAttribute("order",orders);
        return new ModelAndView("/Logist/Pathing/View");
    }
    @PostMapping("/Courier/Order/{id}")
    public  RedirectView SetCourier(@PathVariable(name="id") Long id,
                              @RequestParam(name="users[]") Long[] couriers, Account account , OrderShipment orderShipment, Pathing pathing, Model model)
    {
        OrderShipment ordercurrent=orderRepository.findById(id).orElseThrow();
        List<User> userclient=ordercurrent.getUsers();
       User user=userRepository.findById(couriers[0]).orElseThrow();
       if (userclient.size()==1)
       {
           userclient.add(user);
       }
       else {
           userclient.set(1, user);
       }
        ordercurrent.setUsers(userclient);
       ordercurrent.setStatus("Отправлен в доставку");
      orderRepository.save(ordercurrent);
        return new RedirectView("/Logist/Pathing/View");
    }
}
