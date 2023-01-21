package com.company.category;

import java.time.LocalDateTime;
import java.util.UUID;

public class Electronic extends Category{

    public Electronic(UUID id, String name) {
        super(id, name);
    }

    @Override
    public LocalDateTime findDeliveryDueDate() {
        LocalDateTime localDateTime = LocalDateTime.now();
        return localDateTime.plusDays(4);
    }

    @Override
    public String generateCategoryCode() {
        return "EL-" + getId().toString().substring(0,8);
    }
}
