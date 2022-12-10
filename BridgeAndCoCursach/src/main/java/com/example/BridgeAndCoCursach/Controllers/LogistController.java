package com.example.BridgeAndCoCursach.Controllers;

import com.example.BridgeAndCoCursach.Models.*;
import com.example.BridgeAndCoCursach.Repository.AccountRepository;
import com.example.BridgeAndCoCursach.Repository.OrderRepository;
import com.example.BridgeAndCoCursach.Repository.PathingRepository;
import com.example.BridgeAndCoCursach.Repository.UserRepository;
import com.example.BridgeAndCoCursach.Service.OrderShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    OrderShipmentService orderShipmentService;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    public static HttpSession session() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest().getSession(true); // true == allow create
    }
    public String UserSession() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        return name;
    }
    @PostMapping("/AccountUpdate{id}")
    public String updateAccount(@PathVariable(name="id")Long id,User useredit,Model model)
    {
        User user=userRepository.findFirstByAccount(accountRepository.findById(id).orElseThrow());
        if(useredit.getAccount().getPassword()!="")
        {
            user.getAccount().setPassword(passwordEncoder.encode(useredit.getAccount().getPassword()));
        }
        user.setPhoneNumber(useredit.getPhoneNumber());
        user.setEmail(useredit.getEmail());
        userRepository.save(user);
        Account  account=accountRepository.findAccountByUsername(UserSession());
        model.addAttribute("currentaccount",account);
        return "redirect:/Logist/Index";
    }
    @GetMapping("/Index")
    public String regView(Account user, Model model)
    {
        user=accountRepository.findAccountByUsername(UserSession());
        model.addAttribute("currentaccount",user);
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
    @GetMapping("/Filter/{search_name}")
    public String userViewFilter(@PathVariable(name="search_name") String name,Model model)
    {
        model.addAttribute("couriers", userRepository.findAll());
        model.addAttribute("roleClient", Role.values());
        Iterable<OrderShipment> orders= orderRepository.findByStatus(name);
        model.addAttribute("order",orders);
        return "Logist/Pathing/View";
    }
    @GetMapping("/Search")
    public String ShipmentSearch(@RequestParam(name="search_name1") String name1,@RequestParam(name="search_name2") String name2,Model model)
    {
        model.addAttribute("couriers", userRepository.findAll());
        model.addAttribute("roleClient", Role.values());
        Iterable<OrderShipment> orders= orderRepository.findOrderShipmentByStoragesShipmentsShipmentnameAndPathingAdress(name1,name2);
        model.addAttribute("order",orders);
        return "Logist/Pathing/View";
    }
    @GetMapping("/SearchShip")
    public String ShipmentSearch(@RequestParam(name="search_name3") String name3,Model model)
    {
        model.addAttribute("couriers", userRepository.findAll());
        model.addAttribute("roleClient", Role.values());
        Iterable<OrderShipment> orders= orderRepository.findOrderShipmentByStoragesShipmentsShipmentname(name3);
        model.addAttribute("order",orders);
        return "Logist/Pathing/View";
    }
    @GetMapping("/SearchAdr")
    public String AdressSearch(@RequestParam(name="search_name4") String name4,Model model)
    {
        model.addAttribute("couriers", userRepository.findAll());
        model.addAttribute("roleClient", Role.values());
        Iterable<OrderShipment> orders= orderRepository.findOrderShipmentByPathingAdress(name4);
        model.addAttribute("order",orders);
        return "Logist/Pathing/View";
    }
    @PostMapping("/Pathing/Edit/{id}")
    public  RedirectView pathingEdit(@PathVariable(name="id") Long id, Account account , OrderShipment orderShipment, Pathing pathing, Model model)
    {
        orderShipment = orderRepository.findById(id).orElseThrow();
       Pathing path=  pathingRepository.findById(orderShipment.getPathing().getId()).orElseThrow();
       pathing.setAdress(path.getAdress());
       pathing.setId(path.getId());
        pathingRepository.save(pathing);
        model.addAttribute("roleClient", Role.values());
        Iterable<OrderShipment> orders= orderRepository.findAll();
        model.addAttribute("order",orders);
        return new RedirectView("/Logist/Pathing/View");
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
    @GetMapping("/metrics")
    public String metric(Model model)
    {
         List<String> status=orderShipmentService.getAll().stream().map(x->x.getStatus()).collect(Collectors.toList());

      int a1=0,a2=0,a3=0,a4=0;
        for(String s:status)
        {
            if(s.equals("В обработке"))
            {
                a1++;
            }
            if(s.equals("Отправлен в доставку"))
            {
                a2++;
            }
            if(s.equals("Доставлен"))
            {
                a3++;
            }
            if(s.equals("Отменён"))
            {
                a4++;
            }
        }
        List <Integer>status_amount=new ArrayList<>();
        status_amount.add(a1);
        status_amount.add(a2);
        status_amount.add(a3);
        status_amount.add(a4);
List<String> statuses=new ArrayList<>();
statuses.add("В обработке");statuses.add("Отправлен в доставку");statuses.add("Доставлен");statuses.add("Отменён");
        model.addAttribute("status_amount",status_amount);
        return "/Logist/metric";
    }
}
