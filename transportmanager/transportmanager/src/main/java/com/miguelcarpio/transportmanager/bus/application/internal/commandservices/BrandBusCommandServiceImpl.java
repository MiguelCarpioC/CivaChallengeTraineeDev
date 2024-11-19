package com.miguelcarpio.transportmanager.bus.application.internal.commandservices;

import com.miguelcarpio.transportmanager.bus.domain.model.aggregates.BrandBus;
import com.miguelcarpio.transportmanager.bus.domain.model.commands.CreateBrandBusCommand;
import com.miguelcarpio.transportmanager.bus.domain.model.commands.DeleteBrandBusCommand;
import com.miguelcarpio.transportmanager.bus.domain.services.BrandBusCommandService;
import com.miguelcarpio.transportmanager.bus.infrastructure.persistance.jpa.repositories.BrandBusRepository;
import org.springframework.stereotype.Service;

@Service
public class BrandBusCommandServiceImpl implements BrandBusCommandService {
    private final BrandBusRepository brandBusRepository;

    public BrandBusCommandServiceImpl(BrandBusRepository brandBusRepository) {
        this.brandBusRepository = brandBusRepository;
    }

    @Override
    public Long handle(CreateBrandBusCommand command) {
        if (brandBusRepository.existsByBrandName(command.brandBus())) {
            throw new IllegalArgumentException("Bus with number " + command.brandBus() + " already exists");
        }

        // Crea un nuevo brandBus
        BrandBus brandBus = new BrandBus();

        try {
            brandBusRepository.save(brandBus);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving brand: " + e.getMessage());
        }
        return brandBus.getId();
    }

    @Override
    public void handle(DeleteBrandBusCommand command) {
        if (!brandBusRepository.existsById(command.brandBusId())) {
            throw new IllegalArgumentException("Bus with id " + command.brandBusId() + " not found");
        }
        try {
            brandBusRepository.deleteById(command.brandBusId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting bus: " + e.getMessage());
        }
    }
}
