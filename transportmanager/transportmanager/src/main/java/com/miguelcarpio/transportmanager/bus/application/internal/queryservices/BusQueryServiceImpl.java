package com.miguelcarpio.transportmanager.bus.application.internal.queryservices;

import com.miguelcarpio.transportmanager.bus.domain.model.aggregates.Bus;
import com.miguelcarpio.transportmanager.bus.domain.model.queries.GetAllBusesQuery;
import com.miguelcarpio.transportmanager.bus.domain.model.queries.GetBusByIdQuery;
import com.miguelcarpio.transportmanager.bus.domain.services.BusQueryService;
import com.miguelcarpio.transportmanager.bus.infrastructure.persistance.jpa.repositories.BusRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusQueryServiceImpl implements BusQueryService {
    private final BusRepository busRepository;
    public BusQueryServiceImpl(BusRepository busRepository){
        this.busRepository = busRepository;
    }
    @Override
    public List<Bus> handle(GetAllBusesQuery query) {
        return busRepository.findAll();
    }
    @Override
    public Optional<Bus> handle(GetBusByIdQuery query) {return busRepository.findById(query.busId());
    }
}
