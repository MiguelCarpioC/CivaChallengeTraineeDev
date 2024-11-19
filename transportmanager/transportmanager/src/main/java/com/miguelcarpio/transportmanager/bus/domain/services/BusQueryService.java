package com.miguelcarpio.transportmanager.bus.domain.services;

import com.miguelcarpio.transportmanager.bus.domain.model.aggregates.Bus;
import com.miguelcarpio.transportmanager.bus.domain.model.queries.GetAllBusesQuery;
import com.miguelcarpio.transportmanager.bus.domain.model.queries.GetBusByIdQuery;

import java.util.List;
import java.util.Optional;

public interface BusQueryService {
    List<Bus> handle(GetAllBusesQuery query);
    Optional<Bus> handle(GetBusByIdQuery query);
}
