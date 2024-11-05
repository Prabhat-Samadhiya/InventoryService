package com.hsbc.inventory.history;

import com.hsbc.inventory.Part;
import com.hsbc.inventory.PartRepository;
import jakarta.persistence.PreUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;
import java.util.random.RandomGenerator;

@Component
public class PartAuditListener {

    @Autowired
    private PartHistoryRepository partHistoryRepository;

    @Autowired
    private PartRepository partRepository;

    @PreUpdate
    public void preUpdate(Part part) {
        String requestID = UUID.randomUUID().toString();
        // Compare each field to see if it changed, and if so, log it
        Part oldPart = partRepository.findById(part.getPartId()).get();

        if (oldPart.getAvailableQty() != part.getAvailableQty()) {
            createHistoryRecord(part.getPartId(), "UPDATE", "AVAILBLE_QUANTITY",
                    String.valueOf(oldPart.getAvailableQty()), String.valueOf(part.getAvailableQty()),
                    requestID);
        }

        if (oldPart.getMinOrderQty() != part.getMinOrderQty()) {
            createHistoryRecord(part.getPartId(), "UPDATE", "MIN_ORDER_QUANTITY",
                    String.valueOf(oldPart.getMinOrderQty()), String.valueOf(part.getMinOrderQty()),
                    requestID);
        }
        // Repeat for other fields if needed
    }

    private void createHistoryRecord(Long partId, String changeType, String fieldName, String oldValue, String newValue, String requestId) {
        PartHistory history = new PartHistory();
        history.setPartId(partId);
        history.setChangeType(changeType);
        history.setFieldName(fieldName);
        history.setOldValue(oldValue);
        history.setNewValue(newValue);
        history.setTimestamp(LocalDateTime.now());
        history.setRequestId(requestId);

        partHistoryRepository.save(history);
    }
}

