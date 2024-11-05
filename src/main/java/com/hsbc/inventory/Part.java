package com.hsbc.inventory;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

@Entity
@Data
@EqualsAndHashCode
public class Part {
    @Id
    private Long partId;
    private String name;
    @Enumerated(EnumType.STRING)
    private Supplier supplier;
    private int thresholdQty;
    private int minOrderQty;
    private int availableQty;

}
