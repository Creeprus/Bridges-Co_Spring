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
import java.io.IOException;
import java.util.*;

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
    UserService service;
@Autowired
    AccountRepository accountRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ShipmentRepository shipmentRepository;
    @Autowired
    SupplierRepository supplierRepository;

@GetMapping("/Index")
public String regView(Account user, Model model)
{
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
@GetMapping ("/exportusers")
    public void exportEmpExcel(HttpServletResponse response) throws IOException
{response.setContentType("text/csv");
   String filename="users.csv";
   String headerKey="Content-Disposition";
   String headerValue="attachment; filename="+filename;

   response.setHeader(headerKey,headerValue);

   List<User> listUsers=service.listAll();



    // closing writer connection

   Locale locale=new Locale("ru","RU");
    response.setLocale(locale);
    response.setCharacterEncoding("UTF-8");
    ICsvBeanWriter csvBeanWriter=new CsvBeanWriter(response.getWriter(), CsvPreference.EXCEL_PREFERENCE);

    String[] csvHeader={"id Пользователя","Имя","Фамилия","Отчество","Почта","Номер телефона"};
   // String[] csvHeader_acc={"Логин"};
    String[] nameMappingus={"id","name","surname","patronymic","Email","PhoneNumber"};

   // String[] nameMappingac={"username"};
    csvBeanWriter.writeHeader(csvHeader);
   // csvBeanWriter.writeHeader(csvHeader_acc);
    for (User user:listUsers)
    {
        csvBeanWriter.write(user,nameMappingus);

       // csvBeanWriter.write(user.getAccount(),nameMappingac);
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



        // closing writer connection

        Locale locale=new Locale("ru","RU");
        response.setLocale(locale);
        response.setCharacterEncoding("UTF-8");
        ICsvBeanWriter csvBeanWriter=new CsvBeanWriter(response.getWriter(), CsvPreference.EXCEL_PREFERENCE);

        String[] csvHeader={"id Товара","Наименование товара","Срок годности","Стоимость"};
        // String[] csvHeader_acc={"Логин"};
        String[] nameMappingus={"id","shipmentname","expirationdate","Cost"};

        // String[] nameMappingac={"username"};
        csvBeanWriter.writeHeader(csvHeader);
        // csvBeanWriter.writeHeader(csvHeader_acc);
        for (Shipment shipment:shipments)
        {
            csvBeanWriter.write(shipment,nameMappingus);

            // csvBeanWriter.write(user.getAccount(),nameMappingac);
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



        // closing writer connection

        Locale locale=new Locale("ru","RU");
        response.setLocale(locale);
        response.setCharacterEncoding("UTF-8");
        ICsvBeanWriter csvBeanWriter=new CsvBeanWriter(response.getWriter(), CsvPreference.EXCEL_PREFERENCE);

        String[] csvHeader={"id Поставщика","Наименование поставщика","Страна"};
        // String[] csvHeader_acc={"Логин"};
        String[] nameMappingus={"id","suppliername","Country"};

        // String[] nameMappingac={"username"};
        csvBeanWriter.writeHeader(csvHeader);
        // csvBeanWriter.writeHeader(csvHeader_acc);
        for (Supplier supplier:suppliers)
        {
            csvBeanWriter.write(supplier,nameMappingus);

            // csvBeanWriter.write(user.getAccount(),nameMappingac);
        }


        csvBeanWriter.close();
    }
    @GetMapping ("/backup")
    public String backup(DBManaging.DatabaseUtilBackup dbManaging) throws IOException, InterruptedException {
        DBManaging.DatabaseUtilBackup.backup();
        return "redirect:/Admin/Index";
    }

}
