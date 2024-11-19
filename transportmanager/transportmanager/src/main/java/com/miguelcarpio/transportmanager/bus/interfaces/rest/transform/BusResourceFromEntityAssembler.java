package com.miguelcarpio.transportmanager.bus.interfaces.rest.transform;

import com.miguelcarpio.transportmanager.bus.domain.model.aggregates.Bus;
import com.miguelcarpio.transportmanager.bus.interfaces.rest.resources.BusResource;

public class BusResourceFromEntityAssembler {
    public static BusResource toResourceFromEntity(Bus entity){
        return new BusResource(
                entity.getId(),
                entity.getBusNumber(),
                entity.getPlate(),
                entity.getFeature().toString(),
                entity.getBrandBus().getBrandName(),
                entity.getStatus().name()
        );
    }
}
