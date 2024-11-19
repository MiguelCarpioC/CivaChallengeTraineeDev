package com.miguelcarpio.transportmanager.config;

import com.miguelcarpio.transportmanager.bus.domain.model.aggregates.BrandBus;
import com.miguelcarpio.transportmanager.bus.domain.model.aggregates.Bus;
import com.miguelcarpio.transportmanager.bus.domain.model.valueobjects.Feature;
import com.miguelcarpio.transportmanager.bus.domain.model.valueobjects.Status;
import com.miguelcarpio.transportmanager.bus.infrastructure.persistance.jpa.repositories.BrandBusRepository;
import com.miguelcarpio.transportmanager.bus.infrastructure.persistance.jpa.repositories.BusRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(BrandBusRepository brandBusRepository, BusRepository busRepository) {
        return args -> {
            // Verificar si las tablas ya contienen datos antes de insertar
            if (brandBusRepository.count() == 0) {
                // Crear 10 marcas de buses si no hay datos en la tabla
                List<String> brandNames = Arrays.asList("Volvo", "Scania", "Fiat", "Mercedes", "Toyota", "Ford", "Chevrolet", "MAN", "Iveco", "Hyundai");
                List<BrandBus> brands = brandNames.stream().map(BrandBus::new).toList();
                brandBusRepository.saveAll(brands);

                // Generar 50 buses con datos aleatorios
                Random random = new Random();
                Feature[] features = Feature.values();
                Status[] statuses = Status.values();

                for (int i = 1; i <= 50; i++) {
                    String busNumber = String.format("%04d", i);
                    String plate = "ABC-" + (100 + i); // Placa en formato ABC-100, ABC-101, etc.
                    Feature feature = features[random.nextInt(features.length)];
                    BrandBus brand = brands.get(random.nextInt(brands.size()));
                    Status status = statuses[random.nextInt(statuses.length)];

                    Bus bus = new Bus(busNumber, plate, feature, brand, status);
                    busRepository.save(bus);
                }
            }
        };
    }
}
