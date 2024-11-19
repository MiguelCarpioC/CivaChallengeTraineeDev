package com.miguelcarpio.transportmanager.bus.interfaces.rest.transform;

import com.miguelcarpio.transportmanager.bus.domain.model.commands.CreateBusCommand;
import com.miguelcarpio.transportmanager.bus.interfaces.rest.resources.CreateBusResource;

public class CreateBusCommandFromResourceAssembler {
    public static CreateBusCommand toCommnandFromResource (CreateBusResource resource){
        return new CreateBusCommand(
                resource.busNumber(),
                resource.plate(),
                resource.feature(),
                resource.brandBus(),
                resource.status()

        );
    }
}
