package com.miguelcarpio.transportmanager.bus.domain.model.aggregates;

import com.miguelcarpio.transportmanager.bus.domain.model.aggregates.BrandBus;
import com.miguelcarpio.transportmanager.bus.domain.model.valueobjects.Status;
import com.miguelcarpio.transportmanager.bus.domain.model.valueobjects.Feature;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Número de bus es requerido")
    @Size(max = 20, message = "El número de bus debe tener menos de 20 caracteres")
    private String busNumber;

    @NotBlank(message = "Placa es requerida")
    @Size(max = 15, message = "La placa debe tener menos de 15 caracteres")
    @Column(unique = true)
    private String plate;

    @Column(updatable = false)
    private LocalDateTime creationDate = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private Feature feature;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "brand_bus_id", nullable = false)
    private BrandBus brandBus;

    @Enumerated(EnumType.STRING)
    private Status status;

    // Constructores
    public Bus() {}

    public Bus(String busNumber, String plate, Feature feature, BrandBus brandBus, Status status) {
        this.busNumber = busNumber;
        this.plate = plate;
        this.feature = feature;
        this.brandBus = brandBus;
        this.status = status;
    }
}
