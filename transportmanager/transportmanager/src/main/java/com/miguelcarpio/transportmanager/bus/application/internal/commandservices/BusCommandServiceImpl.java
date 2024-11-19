package com.miguelcarpio.transportmanager.bus.application.internal.commandservices;

import com.miguelcarpio.transportmanager.bus.domain.model.aggregates.BrandBus;
import com.miguelcarpio.transportmanager.bus.domain.model.aggregates.Bus;
import com.miguelcarpio.transportmanager.bus.domain.model.commands.CreateBusCommand;
import com.miguelcarpio.transportmanager.bus.domain.model.commands.DeleteBusCommand;
import com.miguelcarpio.transportmanager.bus.domain.model.valueobjects.Feature;
import com.miguelcarpio.transportmanager.bus.domain.model.valueobjects.Status;
import com.miguelcarpio.transportmanager.bus.domain.services.BusCommandService;
import com.miguelcarpio.transportmanager.bus.infrastructure.persistance.jpa.repositories.BrandBusRepository;
import com.miguelcarpio.transportmanager.bus.infrastructure.persistance.jpa.repositories.BusRepository;
import org.springframework.stereotype.Service;

@Service
public class BusCommandServiceImpl implements BusCommandService {
    private final BusRepository busRepository;
    private final BrandBusRepository brandBusRepository;

    public BusCommandServiceImpl(BusRepository busRepository, BrandBusRepository brandBusRepository) {
        this.busRepository = busRepository;
        this.brandBusRepository = brandBusRepository;
    }

    @Override
    public Long handle(CreateBusCommand command) {
        if (busRepository.existsByBusNumber(command.busNumber())) {
            throw new IllegalArgumentException("Bus with number " + command.busNumber() + " already exists");
        }
        if (busRepository.existsByPlate(command.plate())) {
            throw new IllegalArgumentException("Bus with plate: " + command.plate() + " already exists");
        }

        // Obtén la marca del bus desde la base de datos
        BrandBus brand = brandBusRepository.findByBrandName(command.brandBus())
                .orElseThrow(() -> new IllegalArgumentException("Brand " + command.brandBus() + " not found"));

        // Convierte los valores de características y estado
        Feature feature = Feature.valueOf(command.feature().toUpperCase());
        Status status = Status.valueOf(command.status().toUpperCase());

        // Crea un nuevo Bus
        Bus bus = new Bus(command.busNumber(), command.plate(), feature, brand, status);

        try {
            busRepository.save(bus);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving bus: " + e.getMessage());
        }
        return bus.getId();
    }

    @Override
    public void handle(DeleteBusCommand command) {
        if (!busRepository.existsById(command.busId())) {
            throw new IllegalArgumentException("Bus with id " + command.busId() + " not found");
        }
        try {
            busRepository.deleteById(command.busId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting bus: " + e.getMessage());
        }
    }
}