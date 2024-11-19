package com.miguelcarpio.transportmanager.bus.domain.exceptions;

public class BusNotFoundException extends RuntimeException{
    public BusNotFoundException(Long busId) { super("Bus with id " + busId + " not found :(" );}
}
