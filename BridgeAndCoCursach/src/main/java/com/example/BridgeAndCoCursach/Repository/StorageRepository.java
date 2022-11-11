package com.example.BridgeAndCoCursach.Repository;

import com.example.BridgeAndCoCursach.Models.Shipment;
import com.example.BridgeAndCoCursach.Models.Storage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;


import java.util.List;

public interface StorageRepository extends PagingAndSortingRepository<Storage,Long> {
    Storage findStorageByShipments(Shipment shipmentname);
   Page< Storage> findAll(Pageable page);
}
