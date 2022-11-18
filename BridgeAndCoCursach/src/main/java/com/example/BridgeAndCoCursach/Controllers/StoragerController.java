package com.example.BridgeAndCoCursach.Controllers;


import com.example.BridgeAndCoCursach.Models.Shipment;
import com.example.BridgeAndCoCursach.Models.Storage;
import com.example.BridgeAndCoCursach.Models.Supplier;
import com.example.BridgeAndCoCursach.Models.Supply;
import com.example.BridgeAndCoCursach.Repository.ShipmentRepository;
import com.example.BridgeAndCoCursach.Repository.StorageRepository;
import com.example.BridgeAndCoCursach.Repository.SupplierRepository;
import com.example.BridgeAndCoCursach.Repository.SupplyRepository;
import com.example.BridgeAndCoCursach.Service.ShipmentService;
import com.example.BridgeAndCoCursach.Service.StorageService;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Calendar;
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
        if(supplierRepository.findSupplierBySuppliernameContaining(name).size()==0)
        {
            model.addAttribute("suppliers",supplierRepository.findAll());
            return "/Storager/Suppliers/View";
        }
        model.addAttribute("suppliers",supplierRepository.findSupplierBySuppliernameContaining(name));
        return "/Storager/Suppliers/View";
    }
//    @Timed(value = "Storage.time", description = "Time taken to return Storage")
//    public Storage getStorage() {
//        return new Storage();
//    }
//    @Timed(value = "Supplier.time", description = "Time taken to return Supplier")
//    public Supplier getSupplier() {
//        return new Supplier();
//    }
//    @Timed(value = "Shipment.time", description = "Time taken to return Shipment")
//    public Shipment getShipment() {
//        return new Shipment();
//    }
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
}
