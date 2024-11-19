package com.miguelcarpio.transportmanager.bus.domain.exceptions;

public class BrandBusNotFoundException extends RuntimeException{
    public BrandBusNotFoundException(Long brandBusId) { super("Brand with id " + brandBusId + " not found :(" );}
}
