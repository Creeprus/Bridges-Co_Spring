package com.example.BridgeAndCoCursach.Controllers;

import com.example.BridgeAndCoCursach.Models.Account;
import com.example.BridgeAndCoCursach.Models.Role;
import com.example.BridgeAndCoCursach.Models.User;
import com.example.BridgeAndCoCursach.Repository.AccountRepository;
import com.example.BridgeAndCoCursach.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/Authorization")
public class RegistrationController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/Registration")
    public String regView(Account account, User user, Model model)
    {
        Iterable<User> listUser= userRepository.findAll();
        Iterable<Account> listAccount=accountRepository.findAll();
        model.addAttribute("listUser",listUser);
        model.addAttribute("listAccount",listAccount);
        return "Authorization/Registration";
    }

    @PostMapping("/Registration")
    public String regUser(
            @Valid Account account,
            BindingResult bindingResultAcc,
            @Valid User user,
            BindingResult bindingResultUs,
            Model model

    )

    {
        String regexp =
//                "^(?=.*[0-9])" +
                        "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=\\S+$).{3,50}$";
        Pattern p=Pattern.compile(regexp);
        Matcher match=p.matcher(account.getPassword());
        if ((bindingResultAcc.hasErrors() || bindingResultUs.hasErrors()) || !match.matches())
        {
         model.addAttribute("user", user);
            model.addAttribute("account", account);
            if(!match.matches())
            {
                bindingResultAcc.rejectValue("password","123","Пароль должен содержать одну заглавную, одну незаглавную английскую букву. Пароль от 3 до 50 символов ");
            }
            return "Authorization/Registration";
        }
        if( accountRepository.findAccountByUsername(account.getUsername())!=null)
        {
            model.addAttribute("error","Такой пользователь уже существует");
            return "Authorization/Registration";
        }

            account.setActive(true);
        if(accountRepository.findAll()==null)
        {
            account.setRole(Collections.singleton(Role.Администратор));
        }
        else {
            account.setRole(Collections.singleton(Role.Клиент));
        }
            account.setPassword(passwordEncoder.encode(account.getPassword()));

            user.setAccount(account);

            userRepository.save(user);
//        accountRepository.save(account);


        return "redirect:/Login";
    }

}
