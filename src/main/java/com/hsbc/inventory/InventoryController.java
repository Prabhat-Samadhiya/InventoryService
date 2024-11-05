package com.hsbc.inventory;

import com.hsbc.inventory.history.PartHistory;
import com.hsbc.inventory.history.PartHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parts")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private PartHistoryService partHistoryService;

    @PostMapping
    public ResponseEntity<HttpStatus> addPart(@RequestBody Part part) {
        inventoryService.addPart(part);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updatePart(@PathVariable Long id, @RequestBody Part part) {
        inventoryService.updatePart(id, part);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/update-quantity")
    public ResponseEntity<Part> updateQuantity(@PathVariable Long id, @RequestParam int qty) {
        return ResponseEntity.ok(inventoryService.updateQuantity(id, qty));
    }



    @GetMapping("/{id}/history")
    public ResponseEntity<List<PartHistory>> getPartHistory(@PathVariable Long id) {
        List<PartHistory> history = partHistoryService.getPartHistory(id);
        return ResponseEntity.ok(history);
    }
}
