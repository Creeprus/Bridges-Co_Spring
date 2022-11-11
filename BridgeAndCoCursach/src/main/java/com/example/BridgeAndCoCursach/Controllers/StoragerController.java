package com.example.BridgeAndCoCursach.Controllers;

import com.example.BridgeAndCoCursach.Models.Shipment;
import com.example.BridgeAndCoCursach.Models.Storage;
import com.example.BridgeAndCoCursach.Models.Supplier;
import com.example.BridgeAndCoCursach.Models.Supply;
import com.example.BridgeAndCoCursach.Repository.ShipmentRepository;
import com.example.BridgeAndCoCursach.Repository.StorageRepository;
import com.example.BridgeAndCoCursach.Repository.SupplierRepository;
import com.example.BridgeAndCoCursach.Repository.SupplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

    @GetMapping("/Index")
    public String SupplierIndex()
    {
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
        return "/Storager/Storage/View";
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

        return "/Storager/Storage/Search";
    }
    @GetMapping("/Suppliers/View")
    public String SuppliersView(Supplier supplier, Model model)
    {

        model.addAttribute("suppliers",supplierRepository.findAll());

        return "/Storager/Suppliers/View";
    }
    @PostMapping("/Suppliers/Add")
    public String SuppliersAdd( Supplier supplier,
                               Model model)
    {
        supplierRepository.save(supplier);
        model.addAttribute("suppliers",supplierRepository.findAll());
        return "/Storager/Suppliers/View";
    }
    @PostMapping("/Suppliers/Edit/{id}")
    public String SuppliersEdit( @PathVariable Long id,Supplier supplier,
                                Model model)
    {

        supplierRepository.save(supplier);
        model.addAttribute("suppliers",supplierRepository.findAll());
        return "/Storager/Suppliers/View";
    }
    @GetMapping("/Suppliers/Delete/{id}")
    public String SuppliersDelete( @PathVariable Long id,Supplier supplier,
                                 Model model)
    {
        supplier=supplierRepository.findById(id).orElseThrow();

        if(supplier.getSupplies().size()!=0)
        {
            model.addAttribute("error","Нельзя удалить поставщика, учавствующего в поставках");
            model.addAttribute("suppliers",supplierRepository.findAll());
            return "/Storager/Suppliers/View";
        }
         supplierRepository.delete(supplier);
        model.addAttribute("suppliers",supplierRepository.findAll());
        return "/Storager/Suppliers/View";
    }
    @GetMapping("/Suppliers/Search")
    public String SuppliersSearch( Supplier supplier,@RequestParam(name="search_name") String name,
                                   Model model)
    {
        model.addAttribute("suppliers",supplierRepository.findSupplierBySuppliernameContaining(name));
        return "/Storager/Suppliers/Search";
    }
}
