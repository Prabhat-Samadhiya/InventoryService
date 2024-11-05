package com.hsbc.inventory.history;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PartHistoryService {

    @Autowired
    private PartHistoryRepository partHistoryRepository;

    public List<PartHistory> getPartHistory(Long partId){
        return partHistoryRepository.findByPartId(partId);
    }

}
