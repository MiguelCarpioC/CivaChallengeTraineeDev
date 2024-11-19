package com.miguelcarpio.transportmanager.bus.domain.model.commands;

import com.miguelcarpio.transportmanager.bus.domain.model.valueobjects.Feature;
import com.miguelcarpio.transportmanager.bus.domain.model.valueobjects.Status;

public record CreateBusCommand(
        String busNumber,
        String plate,
        String feature,
        String brandBus,
        String status
) {
    public CreateBusCommand {
        if (feature == null || feature.isBlank()) {
            throw new IllegalArgumentException("Caracteristicas is required");
        }
        try {
            Feature.valueOf(feature.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid caracteristicas value. Must be one of: " + java.util.Arrays.toString(Feature.values()));
        }

        if (brandBus == null || brandBus.isBlank()) {
            throw new IllegalArgumentException("Marca del bus is required");
        }

        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("Activo status is required");
        }
        try {
            Status.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid activo value. Must be one of: " + java.util.Arrays.toString(Status.values()));
        }
    }
}
