package com.miguelcarpio.transportmanager.bus.domain.services;

import com.miguelcarpio.transportmanager.bus.domain.model.commands.CreateBrandBusCommand;
import com.miguelcarpio.transportmanager.bus.domain.model.commands.DeleteBrandBusCommand;

public interface BrandBusCommandService {
    Long handle (CreateBrandBusCommand command);
    void handle (DeleteBrandBusCommand command);
}
