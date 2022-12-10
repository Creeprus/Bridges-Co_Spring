package com.example.BridgeAndCoCursach.Controllers;


import com.example.BridgeAndCoCursach.Models.*;
import com.example.BridgeAndCoCursach.Repository.*;
import com.example.BridgeAndCoCursach.Service.StorageService;
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
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@PreAuthorize("hasAnyAuthority('Кладовщик','Администратор')")
@Controller
@RequestMapping("/Storager")
public class StoragerController {
    @Autowired
    ShipmentRepository shipmentRepository;
    @Autowired
    SupplyRepository supplyRepository;
    @Autowired
    StorageRepository storageRepository;
    @Autowired
    SupplierRepository supplierRepository;

    @Autowired
    StorageService service;
@Autowired
    AccountRepository accountRepository;
@Autowired
UserRepository userRepository;
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
       Account user=accountRepository.findAccountByUsername(UserSession());
        model.addAttribute("currentaccount",user);
        return "/Storager/Index";
    }
    @GetMapping("/Storage/View")
    public String StoragerView(@RequestParam(defaultValue = "0") int currentpage, Storage storages, Shipment shipment, Supply supplies, Model model)
    {
        model.addAttribute("listSuppliers",supplierRepository.findAll());
        model.addAttribute("storages",storages);
        model.addAttribute("shipment",shipment);
        model.addAttribute("storage",storageRepository.findAll());
        return "/Storager/Storage/View";
    }
    @GetMapping("/Shipment/SortAsc")
    public String StoragerSortAscShipment(@RequestParam(defaultValue = "0") int currentpage, Storage storages, Shipment shipment, Supply supplies, Model model)
    {
        model.addAttribute("listSuppliers",supplierRepository.findAll());
        model.addAttribute("storages",storages);
        model.addAttribute("shipment",shipment);
        model.addAttribute("storage",storageRepository.findAll(Sort.by("shipments.shipmentname").ascending()));
        return "/Storager/Storage/View";
    }
    @GetMapping("/Shipment/SortDesc")
    public String StoragerSortDescShipment(@RequestParam(defaultValue = "0") int currentpage, Storage storages, Shipment shipment, Supply supplies, Model model)
    {
        model.addAttribute("listSuppliers",supplierRepository.findAll());
        model.addAttribute("storages",storages);
        model.addAttribute("shipment",shipment);
        model.addAttribute("storage",storageRepository.findAll(Sort.by("shipments.shipmentname").descending()));
        return "/Storager/Storage/View";
    }
    @GetMapping("/Date/SortDesc")
    public String StoragerSortDescDate(@RequestParam(defaultValue = "0") int currentpage, Storage storages, Shipment shipment, Supply supplies, Model model)
    {
        model.addAttribute("listSuppliers",supplierRepository.findAll());
        model.addAttribute("storages",storages);
        model.addAttribute("shipment",shipment);
        model.addAttribute("storage",storageRepository.findAll(Sort.by("supplies.dateofsupply").descending()));
        return "/Storager/Storage/View";
    }
    @GetMapping("/Date/SortAsc")
    public String StoragerSortAscDate(@RequestParam(defaultValue = "0") int currentpage, Storage storages, Shipment shipment, Supply supplies, Model model)
    {
        model.addAttribute("listSuppliers",supplierRepository.findAll());
        model.addAttribute("storages",storages);
        model.addAttribute("shipment",shipment);
        model.addAttribute("storage",storageRepository.findAll(Sort.by("supplies.dateofsupply").ascending()));
        return "/Storager/Storage/View";
    }
    @GetMapping("/Amount/SortAsc")
    public String StoragerSortAscAmount(@RequestParam(defaultValue = "0") int currentpage, Storage storages, Shipment shipment, Supply supplies, Model model)
    {
        model.addAttribute("listSuppliers",supplierRepository.findAll());
        model.addAttribute("storages",storages);
        model.addAttribute("shipment",shipment);
        model.addAttribute("storage",storageRepository.findAll(Sort.by("amount").ascending()));
        return "/Storager/Storage/View";
    }
    @GetMapping("/Amount/SortDesc")
    public String StoragerSortDescAmount(@RequestParam(defaultValue = "0") int currentpage, Storage storages, Shipment shipment, Supply supplies, Model model)
    {
        model.addAttribute("listSuppliers",supplierRepository.findAll());
        model.addAttribute("storages",storages);
        model.addAttribute("shipment",shipment);
        model.addAttribute("storage",storageRepository.findAll(Sort.by("amount").descending()));
        return "/Storager/Storage/View";
    }
    @PostMapping("/Storage/Edit/{id}")
    public String ShipmentEdit(@PathVariable Long id,
                               Shipment shipment,
                               Storage storage,
                               Supply supply,
                               @RequestParam Long listSuppliers,

                               Model model)
    {
        Storage storage1=storageRepository.findById(id).orElseThrow();
        storage1.getSupplies().setSupplier(supplierRepository.findById(listSuppliers).orElseThrow());
        storage.setShipments(storage1.getShipments());
        storage.setSupplies(storage1.getSupplies());
        storageRepository.save(storage);
        model.addAttribute("listSuppliers",supplierRepository.findAll());
        model.addAttribute("storages",storage);
        model.addAttribute("shipment",shipment);
        model.addAttribute("storage",storageRepository.findAll());
        return "redirect:/Storager/Storage/View";
    }
    @GetMapping("/Storage/Search")
    public String ShipmentSearch( @RequestParam(name="search_name") String name,Storage storages, Shipment shipment, Supply supplies,Model model)
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

        return "/Storager/Storage/View";
    }
    @GetMapping("/Suppliers/View")
    public String SuppliersView(Supplier supplier, Model model)
    {
        model.addAttribute("suppliers",supplierRepository.findAll());
        return "/Storager/Suppliers/View";
    }
    @GetMapping("/Supplier/SortAsc")
    public String SuppliersSortAscSupplier(Supplier supplier, Model model)
    {
        model.addAttribute("suppliers",supplierRepository.findAll(Sort.by("suppliername").ascending()));
        return "/Storager/Suppliers/View";
    }
    @GetMapping("/Supplier/SortDesc")
    public String SuppliersSortDescSupplier(Supplier supplier, Model model)
    {
        model.addAttribute("suppliers",supplierRepository.findAll(Sort.by("suppliername").descending()));
        return "/Storager/Suppliers/View";
    }
    @GetMapping("/Country/SortAsc")
    public String SuppliersSortAscCountry(Supplier supplier, Model model)
    {
        model.addAttribute("suppliers",supplierRepository.findAll(Sort.by("country").ascending()));
        return "/Storager/Suppliers/View";
    }
    @GetMapping("/Country/SortDesc")
    public String SuppliersSortDescCountry(Supplier supplier, Model model)
    {
        model.addAttribute("suppliers",supplierRepository.findAll(Sort.by("country").descending()));
        return "/Storager/Suppliers/View";
    }
    @PostMapping("/Suppliers/Add")
    public String SuppliersAdd( Supplier supplier,
                               Model model)
    {
        supplierRepository.save(supplier);
        model.addAttribute("suppliers",supplierRepository.findAll());
        return "redirect:/Storager/Suppliers/View";
    }
    @PostMapping("/Suppliers/Edit/{id}")
    public String SuppliersEdit( @PathVariable Long id,Supplier supplier,
                                Model model)
    {
        supplierRepository.save(supplier);
        model.addAttribute("suppliers",supplierRepository.findAll());
        return "redirect:/Storager/Suppliers/View";
    }
    @GetMapping("/Suppliers/Delete/{id}")
    public String SuppliersDelete( @PathVariable Long id,Supplier supplier,
                                 Model model)
    {
        supplier=supplierRepository.findById(id).orElseThrow();
        if(supplier.getSupplies().size()!=0)
        {
            model.addAttribute("errors","Нельзя удалить поставщика, учавствующего в поставках");
            model.addAttribute("suppliers",supplierRepository.findAll());
            return "/Storager/Suppliers/View";
        }
        supplierRepository.delete(supplier);
        model.addAttribute("suppliers",supplierRepository.findAll());
        return "redirect:/Storager/Suppliers/View";
    }
    @GetMapping("/Suppliers/Search")
    public String SuppliersSearch( Supplier supplier,@RequestParam(name="search_name") String name,
                                   Model model)
    {
        if(supplierRepository.findSupplierBySuppliernameContaining(name).size()==0)
        {
            model.addAttribute("suppliers",supplierRepository.findAll());
            return "/Storager/Suppliers/View";
        }
        model.addAttribute("suppliers",supplierRepository.findSupplierBySuppliernameContaining(name));
        return "/Storager/Suppliers/View";
    }
@GetMapping("/metrics")
public String metric(Model model)
{
    List<String> shipname=service.getAllStorage().stream().map(x->x.getShipments().getShipmentname()).collect(Collectors.toList());
    List<Date> expdate=service.getAllStorage().stream().map(x->x.getShipments().getExpirationdate()).collect(Collectors.toList());
    List<Double> cost=service.getAllStorage().stream().map(x->x.getShipments().getCost()).collect(Collectors.toList());
    List<Integer> amount=service.getAllStorage().stream().map(x->x.getAmount()).collect(Collectors.toList());
    List<Date> supplydate=service.getAllStorage().stream().map(x->x.getSupplies().getDateofsupply()).collect(Collectors.toList());
    model.addAttribute("shipments",shipname);
    model.addAttribute("expdate",expdate);
    model.addAttribute("cost",cost);
    model.addAttribute("amount",amount);
    model.addAttribute("supplydate",supplydate);
    return "/Storager/Metrics/metric";
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
        Account  account=accountRepository.findAccountByUsername(UserSession());
        model.addAttribute("currentaccount",account);
        return "redirect:/Storager/Index";
    }
}
