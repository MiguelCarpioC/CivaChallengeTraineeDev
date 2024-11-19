package com.miguelcarpio.transportmanager.bus.interfaces.rest.resources;

public record CreateBusResource(
        String busNumber,
        String plate,
        String feature,
        String brandBus,
        String status
) {
}
