package com.miguelcarpio.transportmanager.bus.interfaces.rest.resources;

public record BusResource(
        Long id,
        String busNumber,
        String plate,
        String feature,
        String brandBus,
        String status
) {
}
