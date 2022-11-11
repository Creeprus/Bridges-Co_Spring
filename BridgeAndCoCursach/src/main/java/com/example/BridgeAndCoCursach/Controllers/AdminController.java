package com.example.BridgeAndCoCursach.Controllers;

import com.example.BridgeAndCoCursach.Models.Account;
import com.example.BridgeAndCoCursach.Models.Role;
import com.example.BridgeAndCoCursach.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.Set;

@PreAuthorize("hasAnyAuthority('Администратор')")
@Controller
@RequestMapping("/Admin")
public class AdminController {
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
@Autowired
    AccountRepository accountRepository;
@GetMapping("/Index")
public String regView(Account user, Model model)
{
    return "/Admin/Index";
}
    @GetMapping("/Account/View")
    public String userView(Model model)
    {
String a=UserSession();
        model.addAttribute("listRole", Role.values());

        model.addAttribute("listUser",accountRepository.findAll());
        return "/Admin/Account/View";
    }
//    @GetMapping("/Account/{id}")
//    public String PutView(Account user,@PathVariable(name="id") Long id, Model model)
//    {
//
//        model.addAttribute("userOne",accountRepository.findById(id));
//        return "/Admin/Account/View";
//    }
@PostMapping("/AccountEdit/{id}")
public String userEditView(@PathVariable(name="id") Long id,

                           @RequestParam(name="role[]") String[] userRoles,
                           Model model)
{
    Account user =accountRepository.findById(id).orElseThrow();
    user.getRole().clear();
    for (String roleOne:
            userRoles)
    {
        user.getRole().add(Role.valueOf(roleOne));
    }
    accountRepository.save(user);
    return "redirect:/Admin/Account/View";
}
}
