package com.example.BridgeAndCoCursach.Controllers;

import com.example.BridgeAndCoCursach.Models.*;
import com.example.BridgeAndCoCursach.Repository.*;
import com.example.BridgeAndCoCursach.Securing.DBManaging;
import com.example.BridgeAndCoCursach.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        return name;
    }
    @Autowired
    UserService service;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ShipmentRepository shipmentRepository;
    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

@GetMapping("/Index")
public String regView(Account user, Model model)
{
    user=accountRepository.findAccountByUsername(UserSession());
    model.addAttribute("currentaccount",user);
    model.addAttribute("state","");
    return "/Admin/Index";
}
    @GetMapping("/Account/Filter/{search_name}")
    public String userViewFilter(@PathVariable(name="search_name") Role name,Model model)
    {
        String a=UserSession();
        model.addAttribute("listRole", Role.values());
        model.addAttribute("listUser",accountRepository.findAccountByRole(name));
        return "/Admin/Account/View";
    }
    @GetMapping("/Account/Search")
    public String userViewSearch(@RequestParam(name="search_name") String name,Model model)
    {
        String a=UserSession();
        model.addAttribute("listRole", Role.values());
        if(name.equals(""))
        {
            model.addAttribute("listUser",accountRepository.findAll());
            return "/Admin/Account/View";
        }
        else
        {
            model.addAttribute("listUser",accountRepository.findAccountByUsername(name));
        }
        return "/Admin/Account/View";
    }

    @GetMapping("/Account/SortAsc")
    public String userViewSortAsc(Model model)
    {
        String a=UserSession();
        model.addAttribute("listRole", Role.values());
        model.addAttribute("listUser",accountRepository.findAll(Sort.by("username").ascending()));
        return "/Admin/Account/View";
    }
    @GetMapping("/Account/SortDesc")
    public String userViewSortDesc(Model model)
    {
        String a=UserSession();
        model.addAttribute("listRole", Role.values());
        model.addAttribute("listUser",accountRepository.findAll(Sort.by("username").descending()));
        return "/Admin/Account/View";
    }
    @GetMapping("/Account/View")
    public String userView(Model model)
    {
        String a=UserSession();
        model.addAttribute("listRole", Role.values());
        model.addAttribute("listUser",accountRepository.findAll());
        return "/Admin/Account/View";
    }

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
@GetMapping ("/exportusers")
    public void exportEmpExcel(HttpServletResponse response) throws IOException
{response.setContentType("text/csv");
   String filename="users.csv";
   String headerKey="Content-Disposition";
   String headerValue="attachment; filename="+filename;
   response.setHeader(headerKey,headerValue);
   List<User> listUsers=service.listAll();
   Locale locale=new Locale("ru","RU");
   response.setLocale(locale);
   response.setCharacterEncoding("UTF-8");
   ICsvBeanWriter csvBeanWriter=new CsvBeanWriter(response.getWriter(), CsvPreference.EXCEL_PREFERENCE);
    String[] csvHeader={"id Пользователя","Имя","Фамилия","Отчество","Почта","Номер телефона"};
    String[] nameMappingus={"id","name","surname","patronymic","Email","PhoneNumber"};
    csvBeanWriter.writeHeader(csvHeader);
    for (User user:listUsers)
    {
        csvBeanWriter.write(user,nameMappingus);
    }
    csvBeanWriter.close();
}
    @GetMapping ("/exportshipments")
    public void exportShipment(HttpServletResponse response) throws IOException
    {
        response.setContentType("text/csv");
        String filename="shipment.csv";
        String headerKey="Content-Disposition";
        String headerValue="attachment; filename="+filename;
        response.setHeader(headerKey,headerValue);
        List<Shipment> shipments=shipmentRepository.findAll();
        Locale locale=new Locale("ru","RU");
        response.setLocale(locale);
        response.setCharacterEncoding("UTF-8");
        ICsvBeanWriter csvBeanWriter=new CsvBeanWriter(response.getWriter(), CsvPreference.EXCEL_PREFERENCE);
        String[] csvHeader={"id Товара","Наименование товара","Срок годности","Стоимость"};
        String[] nameMappingus={"id","shipmentname","expirationdate","Cost"};
        csvBeanWriter.writeHeader(csvHeader);
        for (Shipment shipment:shipments)
        {
            csvBeanWriter.write(shipment,nameMappingus);
        }
        csvBeanWriter.close();
    }
    @GetMapping ("/exportsuppliers")
    public void exportSuppliers(HttpServletResponse response) throws IOException
    {

        response.setContentType("text/csv");
        String filename="suppliers.csv";
        String headerKey="Content-Disposition";
        String headerValue="attachment; filename="+filename;
        response.setHeader(headerKey,headerValue);
        List<Supplier> suppliers= (List<Supplier>) supplierRepository.findAll();
        Locale locale=new Locale("ru","RU");
        response.setLocale(locale);
        response.setCharacterEncoding("UTF-8");
        ICsvBeanWriter csvBeanWriter=new CsvBeanWriter(response.getWriter(), CsvPreference.EXCEL_PREFERENCE);
        String[] csvHeader={"id Поставщика","Наименование поставщика","Страна"};
        String[] nameMappingus={"id","suppliername","Country"};
        csvBeanWriter.writeHeader(csvHeader);
        for (Supplier supplier:suppliers)
        {
            csvBeanWriter.write(supplier,nameMappingus);
        }
        csvBeanWriter.close();
    }
    @GetMapping("/backup")
    public String backup(String dbName, Model model) {
        try {
            String folderPath = System.getProperty("user.dir") + "\\backup\\";
            File temp = new File(folderPath);
            temp.mkdir();
            Account user=accountRepository.findAccountByUsername(UserSession());
            model.addAttribute("currentaccount",user);
            String savePath = folderPath + "backup.sql";
            dbName="BridgesAndCo";
            String pathtodump="C:\\Program Files\\MySQL\\MySQL Server 8.0\\bin\\";
            String executeCmd = "cmd.exe /c mysqldump --port=3306 --column_statistics=0 -uroot " + dbName + " -r " + savePath;
            String state;
            Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();
            if (processComplete == 0) {
                state="Резервное копирование базы данных прошло успешно";
                model.addAttribute("state",state);
                return "/Admin/Index";
            } else {
                state="Не удалось сохранить резервную копию базы данных";
                model.addAttribute("state",state);
                return "/Admin/Index";

            }

        } catch (IOException | InterruptedException ex) {
            String state;
            state="Не удалось сохранить резервную копию базы данных"+ ex.getMessage();
            model.addAttribute("state",state);
            return "/Admin/Index";
        }
    }

    @GetMapping("/restore")

    public String restore(String dbName, Model model){
        try {
            dbName="BridgesAndCo";
            String executeCmd = "cmd.exe /c mysql --port=3306 -uroot " + dbName + " < " + System.getProperty("user.dir") + "\\backup\\backup.sql";
            Account user=accountRepository.findAccountByUsername(UserSession());
            model.addAttribute("currentaccount",user);
            Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();
            if (processComplete == 0) {
                String state="БД успешно восстановлена из файла: " + System.getProperty("user.dir") + "\\backup\\backup.sql";
                model.addAttribute("state",state);
                return "/Admin/Index";
            } else {
                String state="Ошибка при восстановлении БД из файла: " + System.getProperty("user.dir") + "\\backup\\backup.sql";
                model.addAttribute("state",state);
                return "/Admin/Index";
            }
        } catch(IOException | InterruptedException | HeadlessException e){
            String state="Ошибка при восстановлении БД из файла: " + System.getProperty("user.dir") + "\\backup\\backup.sql" + " | " + e.getMessage();
            model.addAttribute("state",state);
            return "/Admin/Index";
        }
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
        return "redirect:/Admin/Index";
    }

}
