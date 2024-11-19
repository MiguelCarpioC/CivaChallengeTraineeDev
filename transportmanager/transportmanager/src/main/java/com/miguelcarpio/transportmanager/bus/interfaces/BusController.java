package com.miguelcarpio.transportmanager.bus.interfaces;

import com.miguelcarpio.transportmanager.bus.domain.model.queries.GetAllBusesQuery;
import com.miguelcarpio.transportmanager.bus.domain.model.queries.GetBusByIdQuery;
import com.miguelcarpio.transportmanager.bus.domain.services.BusCommandService;
import com.miguelcarpio.transportmanager.bus.domain.services.BusQueryService;
import com.miguelcarpio.transportmanager.bus.interfaces.rest.resources.BusResource;
import com.miguelcarpio.transportmanager.bus.interfaces.rest.transform.BusResourceFromEntityAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/buses")
public class BusController {
    private final BusCommandService busCommandService;
    private final BusQueryService busQueryService;

    public BusController(BusCommandService busCommandService, BusQueryService busQueryService){
        this.busCommandService = busCommandService;
        this.busQueryService = busQueryService;
    }

    @GetMapping("/{busId}")
    public ResponseEntity<BusResource> getBusById(@PathVariable Long busId){
        var getBusByIdQuery = new GetBusByIdQuery(busId);
        var busEntity = busQueryService.handle(getBusByIdQuery);
        if (busEntity.isEmpty()) return ResponseEntity.badRequest().build();
        var busResource = BusResourceFromEntityAssembler.toResourceFromEntity(busEntity.get());
        return ResponseEntity.ok(busResource);
    }
    @GetMapping
    public ResponseEntity<List<BusResource>> getAllBuses() {
        var getAllBusesQuery = new GetAllBusesQuery();
        var busEntities = busQueryService.handle(getAllBusesQuery);
        var busResources = busEntities.stream().map(BusResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(busResources);
    }
}
