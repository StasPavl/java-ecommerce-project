package com.company.category;

import java.time.LocalDateTime;
import java.util.UUID;

public class Furniture extends Category {

    public Furniture(UUID id, String name) {
        super(id, name);
    }

    @Override
    public LocalDateTime findDeliveryDueDate() {
        LocalDateTime localDateTime = LocalDateTime.now();
        return localDateTime.plusDays(1);
    }
}
