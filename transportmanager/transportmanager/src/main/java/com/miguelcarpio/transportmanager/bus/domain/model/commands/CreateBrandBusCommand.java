package com.miguelcarpio.transportmanager.bus.domain.model.commands;

import com.miguelcarpio.transportmanager.bus.domain.model.valueobjects.Feature;
import com.miguelcarpio.transportmanager.bus.domain.model.valueobjects.Status;

public record CreateBrandBusCommand (
        String brandBus
){
    public CreateBrandBusCommand {
        if (brandBus == null || brandBus.isBlank()) {
            throw new IllegalArgumentException("Marca del bus is required");
        }
    }
}
