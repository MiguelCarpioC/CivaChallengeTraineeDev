package com.miguelcarpio.transportmanager.bus.infrastructure.persistance.jpa.repositories;

import com.miguelcarpio.transportmanager.bus.domain.model.aggregates.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {
    boolean existsByBusNumber(String busNumber);
    boolean existsByPlate(String plate);
}
