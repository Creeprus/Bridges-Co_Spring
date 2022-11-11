package com.example.BridgeAndCoCursach.Controllers;

import com.example.BridgeAndCoCursach.Models.Shipment;
import com.example.BridgeAndCoCursach.Models.Storage;
import com.example.BridgeAndCoCursach.Models.Supply;
import com.example.BridgeAndCoCursach.Repository.ShipmentRepository;
import com.example.BridgeAndCoCursach.Repository.StorageRepository;
import com.example.BridgeAndCoCursach.Repository.SupplierRepository;
import com.example.BridgeAndCoCursach.Repository.SupplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.time.LocalDate;
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

    @GetMapping("/Index")
    public String SupplierIndex()
    {
        return "/Supplier/Index";
    }

    @GetMapping("/View")
    public String SupplierView(@RequestParam(defaultValue = "0") int currentpage,Storage storages, Shipment shipment, Supply supplies,Model model)
    {

        model.addAttribute("listSuppliers",supplierRepository.findAll());
        model.addAttribute("storages",storages);
        model.addAttribute("shipment",shipment);
//        Page<Storage> page=storageRepository.findAll(PageRequest.of(0, 5));
//        int totalpages=page.getTotalPages();
//        long totalitems=page.getTotalElements();
//        model.addAttribute("totalpages",totalpages);
//        model.addAttribute("totalitems",totalitems);
        model.addAttribute("storage",storageRepository.findAll());
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
        return "/Supplier/View";
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

        return "/Supplier/Filter";
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

        return "/Supplier/Sort";
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

        return "/Supplier/Sort";
    }
}
