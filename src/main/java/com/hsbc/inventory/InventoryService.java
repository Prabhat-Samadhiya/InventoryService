package com.hsbc.inventory;

import com.hsbc.inventory.history.PartAuditListener;
import com.hsbc.inventory.history.PartHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {
    @Autowired
    private PartRepository partRepository;


    @Autowired
    private PartHistoryRepository partHistoryRepository;

    @Autowired
    private PartAuditListener auditListener;


    public void addPart(Part part) { // insert into Part table
       partRepository.save(part);
     }

    public void updatePart(Long id, Part part) {

        auditListener.preUpdate(part);
        partRepository.saveAndFlush(part);

    }

    public Part updateQuantity(Long id, int qty) {
        Part part = partRepository.findById(id).orElseThrow();
        if (qty < part.getThresholdQty()) {
            // call order service to put in new order with supplier.
        }
        return partRepository.save(part);
    }
}
