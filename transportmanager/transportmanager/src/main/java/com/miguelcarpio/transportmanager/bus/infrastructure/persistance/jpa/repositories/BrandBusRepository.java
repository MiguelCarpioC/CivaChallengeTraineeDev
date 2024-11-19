package com.miguelcarpio.transportmanager.bus.infrastructure.persistance.jpa.repositories;

import com.miguelcarpio.transportmanager.bus.domain.model.aggregates.BrandBus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandBusRepository extends JpaRepository<BrandBus, Long> {
    boolean existsByBrandName(String brandName); // Cambia brandBus a brandName
    Optional<BrandBus> findByBrandName(String brandName); // Cambia brand a brandName
}
