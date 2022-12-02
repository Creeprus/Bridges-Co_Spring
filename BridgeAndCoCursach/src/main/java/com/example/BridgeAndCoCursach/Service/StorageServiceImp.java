package com.example.BridgeAndCoCursach.Service;

import com.example.BridgeAndCoCursach.APIRepository.StorageAPIRepository;
import com.example.BridgeAndCoCursach.Models.Shipment;
import com.example.BridgeAndCoCursach.Models.Storage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StorageServiceImp implements StorageService{
    @Autowired
    StorageAPIRepository storageAPIRepository;
    @Override
    public Storage createStorage(Storage storageRequest) {
        Storage storage=new Storage();
        storage.setAmount(storageRequest.getAmount());
        storage.setShipments(storageRequest.getShipments());
        storage.setSupplies(storageRequest.getSupplies());
        BeanUtils.copyProperties(storageRequest,storage);
        storageAPIRepository.save(storage);
        return storage;
    }

    @Override
    public Optional<Storage> updateStorage(Long shipmentId, Storage storageRequestRequest) {
        Optional<Storage> storage = storageAPIRepository.findById(shipmentId);
        if (storage.isEmpty()){
            return null;
        }
        else {
            storage.get().setAmount(storageRequestRequest.getAmount());
            storage.get().setShipments(storageRequestRequest.getShipments());
            storage.get().setSupplies(storageRequestRequest.getSupplies());

            storageAPIRepository.save(storage.get());
            return storage;
        }
    }

    @Override
    public void deleteStorage(Long storageRequestId) {
        storageAPIRepository.deleteById(storageRequestId);
    }

    @Override
    public Storage getASingleStorage(Long storageRequestId) {
        return storageAPIRepository.findById(storageRequestId).orElseThrow();
    }

    @Override
    public List<Storage> getAllStorage() {
        return storageAPIRepository.findAll();
    }
}
