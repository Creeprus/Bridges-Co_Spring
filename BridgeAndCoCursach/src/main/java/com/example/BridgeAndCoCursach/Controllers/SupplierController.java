package com.example.BridgeAndCoCursach.Controllers;

import com.example.BridgeAndCoCursach.Models.*;
import com.example.BridgeAndCoCursach.Repository.*;
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
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@PreAuthorize("hasAnyAuthority('Поставщик','Администратор')")
@Controller
@RequestMapping("/Supplier")
public class SupplierController {

    @Autowired
    ShipmentRepository shipmentRepository;
    @Autowired
    SupplyRepository supplyRepository;
    @Autowired
    StorageRepository storageRepository;
    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    UserRepository userRepository;
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

    @GetMapping("/Index")
    public String SupplierIndex(Model model)
    {
        Account   user=accountRepository.findAccountByUsername(UserSession());
        model.addAttribute("currentaccount",user);
        return "/Supplier/Index";
    }

    @GetMapping("/View")
    public String SupplierView(@RequestParam(defaultValue = "0") int currentpage,Storage storages, Shipment shipment, Supply supplies,Model model)
    {
        model.addAttribute("listSuppliers",supplierRepository.findAll());
        model.addAttribute("storages",storages);
        model.addAttribute("shipment",shipment);
        model.addAttribute("storage",storageRepository.findAll());
        return "/Supplier/View";
    }
    @GetMapping("/Amount/SortDesc")
    public String SupplierSortDescAmount(@RequestParam(defaultValue = "0") int currentpage, Storage storages, Shipment shipment, Supply supplies, Model model)
    {
        model.addAttribute("listSuppliers",supplierRepository.findAll());
        model.addAttribute("storages",storages);
        model.addAttribute("shipment",shipment);
        model.addAttribute("storage",storageRepository.findAll(Sort.by("amount").descending()));
        return "/Supplier/View";
    }
    @GetMapping("/Amount/SortAsc")
    public String SupplierSortAscAmount(@RequestParam(defaultValue = "0") int currentpage, Storage storages, Shipment shipment, Supply supplies, Model model)
    {
        model.addAttribute("listSuppliers",supplierRepository.findAll());
        model.addAttribute("storages",storages);
        model.addAttribute("shipment",shipment);
        model.addAttribute("storage",storageRepository.findAll(Sort.by("amount").ascending()));
        return "/Supplier/View";
    }
    @GetMapping("/Date/SortDesc")
    public String SupplierSortDescDate(@RequestParam(defaultValue = "0") int currentpage, Storage storages, Shipment shipment, Supply supplies, Model model)
    {
        model.addAttribute("listSuppliers",supplierRepository.findAll());
        model.addAttribute("storages",storages);
        model.addAttribute("shipment",shipment);
        model.addAttribute("storage",storageRepository.findAll(Sort.by("supplies.dateofsupply").descending()));
        return "/Supplier/View";
    }
    @GetMapping("/Date/SortAsc")
    public String SupplierSortAscDate(@RequestParam(defaultValue = "0") int currentpage, Storage storages, Shipment shipment, Supply supplies, Model model)
    {
        model.addAttribute("listSuppliers",supplierRepository.findAll());
        model.addAttribute("storages",storages);
        model.addAttribute("shipment",shipment);
        model.addAttribute("storage",storageRepository.findAll(Sort.by("supplies.dateofsupply").ascending()));
        return "/Supplier/View";
    }
    @GetMapping("/Shipment/SortAsc")
    public String SupplierSortAscShipment(@RequestParam(defaultValue = "0") int currentpage, Storage storages, Shipment shipment, Supply supplies, Model model)
    {
        model.addAttribute("listSuppliers",supplierRepository.findAll());
        model.addAttribute("storages",storages);
        model.addAttribute("shipment",shipment);
        model.addAttribute("storage",storageRepository.findAll(Sort.by("shipments.shipmentname").ascending()));
        return "/Supplier/View";
    }
    @GetMapping("/Shipment/SortDesc")
    public String SupplierSortDescShipment(@RequestParam(defaultValue = "0") int currentpage, Storage storages, Shipment shipment, Supply supplies, Model model)
    {
        model.addAttribute("listSuppliers",supplierRepository.findAll());
        model.addAttribute("storages",storages);
        model.addAttribute("shipment",shipment);
        model.addAttribute("storage",storageRepository.findAll(Sort.by("shipments.shipmentname").descending()));
        return "/Supplier/View";
    }
    @PostMapping("/Add")
    public String ShipmentAdd( Shipment shipment,
                                Storage storage,
                               Supply supply,
                               @RequestParam Long listSuppliers,

                               Model model)
    {
        Calendar cal = Calendar.getInstance();
        Date date=cal.getTime();
        supply.setSupplier(supplierRepository.findById(listSuppliers).orElseThrow());
        supply.setDateofsupply(date);
        storage.setSupplies(supply);
        storage.setShipments(shipment);
        shipmentRepository.save(shipment);
        supplyRepository.save(supply);
        storageRepository.save(storage);
        model.addAttribute("listSuppliers",supplierRepository.findAll());
        model.addAttribute("storages",storage);
        model.addAttribute("shipment",shipment);
        model.addAttribute("storage",storageRepository.findAll());
        return "redirect:/Supplier/View";
    }
    @GetMapping("/Search")
    public String SupplierSearch( @RequestParam(name="search_name") String name,Storage storages, Shipment shipment, Supply supplies,Model model)
    {
        List<Storage> storageslist = new ArrayList<>();
        if(name!="") {
            List<Shipment> shipmentList = shipmentRepository.findShipmentByShipmentnameContaining(name);
            for (Shipment shipment1 : shipmentList
            ) {
                storageslist.add(storageRepository.findStorageByShipments(shipment1));
            }
        }
            if (storageslist.size() != 0) {
                model.addAttribute("storage", storageslist);
            } else {

                model.addAttribute("storage", storageRepository.findAll());
            }
        model.addAttribute("listSuppliers",supplierRepository.findAll());
        model.addAttribute("storages",storages);
        model.addAttribute("shipment",shipment);
        return "/Supplier/View";
    }

    @GetMapping("/SortAsc")
    public String SupplierSortAsc( Storage storages, Shipment shipment, Supply supplies,Model model)
    {
        List<Storage> storageslist = new ArrayList<>();
        List<Shipment> shipmentList = shipmentRepository.findAll(Sort.by("shipmentname").ascending());
        for (Shipment shipment1 : shipmentList
        ) {
            storageslist.add(storageRepository.findStorageByShipments(shipment1));
        }
        if (storageslist.size() != 0) {
            model.addAttribute("storage", storageslist);
        } else {

            model.addAttribute("storage", storageRepository.findAll());
        }
        model.addAttribute("listSuppliers",supplierRepository.findAll());
        model.addAttribute("storages",storages);
        model.addAttribute("shipment",shipment);
        return "/Supplier/View";
    }
    @GetMapping("/SortDesc")
    public String SupplierSortDesc( Storage storages, Shipment shipment, Supply supplies,Model model)
    {
        List<Storage> storageslist = new ArrayList<>();
        List<Shipment> shipmentList = shipmentRepository.findAll(Sort.by("shipmentname").descending());
        for (Shipment shipment1 : shipmentList
        ) {
            storageslist.add(storageRepository.findStorageByShipments(shipment1));
        }
        if (storageslist.size() != 0) {
            model.addAttribute("storage", storageslist);
        } else {
            model.addAttribute("storage", storageRepository.findAll());
        }
        model.addAttribute("listSuppliers",supplierRepository.findAll());
        model.addAttribute("storages",storages);
        model.addAttribute("shipment",shipment);
        return "/Supplier/View";
    }
    @PostMapping("/AccountUpdate{id}")
    public String updateAccount(@PathVariable(name="id")Long id, User useredit, Model model)
    {
        User user=userRepository.findFirstByAccount(accountRepository.findById(id).orElseThrow());
        if(useredit.getAccount().getPassword()!="")
        {
            user.getAccount().setPassword(passwordEncoder.encode(useredit.getAccount().getPassword()));
        }
        user.setPhoneNumber(useredit.getPhoneNumber());
        user.setEmail(useredit.getEmail());
        userRepository.save(user);
        Account account=accountRepository.findAccountByUsername(UserSession());
        model.addAttribute("currentaccount",account);
        return "redirect:/Supplier/Index";
    }
}
