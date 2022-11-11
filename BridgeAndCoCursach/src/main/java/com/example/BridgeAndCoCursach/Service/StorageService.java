package com.example.BridgeAndCoCursach.Service;

import com.example.BridgeAndCoCursach.Models.Shipment;
import com.example.BridgeAndCoCursach.Models.Storage;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface StorageService {
    MessageResponse createStorage(Storage storageRequest);
    Optional<Storage> updateStorage(Long shipmentId, Storage storageRequestRequest);
    void deleteStorage(Long storageRequestId);
    Storage getASingleStorage(Long storageRequestId);
    List<Storage> getAllStorage();
}
