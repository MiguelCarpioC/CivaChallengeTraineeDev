package com.miguelcarpio.transportmanager.bus.domain.model.aggregates;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Getter
@EntityListeners(AuditingEntityListener.class)
@Entity
public class BrandBus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @JsonProperty("brandBus") // Esto expone el nombre de la marca correctamente
    private String brandName;

    @OneToMany(mappedBy = "brandBus")
    @JsonIgnore // Evita la serialización de la lista de buses
    private List<Bus> buses;

    public BrandBus() {}

    public BrandBus(String brandBus) {
        this.brandName = brandBus;
    }
}
