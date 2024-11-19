package com.miguelcarpio.transportmanager.bus.domain.services;

import com.miguelcarpio.transportmanager.bus.domain.model.commands.CreateBusCommand;
import com.miguelcarpio.transportmanager.bus.domain.model.commands.DeleteBusCommand;

public interface BusCommandService {
    Long handle (CreateBusCommand command);
    void handle (DeleteBusCommand command);
}
