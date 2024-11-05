package com.hsbc.inventory.history;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class PartHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long historyId;

    private Long partId;
    private String requestId;
    private String changeType;   // e.g., ADD, UPDATE, DELETE
    private String fieldName;    // e.g., "available_qty", "threshold_qty"
    private String oldValue;
    private String newValue;
    private LocalDateTime timestamp;

}

