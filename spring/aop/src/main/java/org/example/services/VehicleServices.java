package org.example.services;

import org.example.interfaces.LogAspect;
import org.example.services.speaker.Speaker;
import org.example.services.tyre.Tyre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class VehicleServices {
    private Logger logger = Logger.getLogger(VehicleServices.class.getName());

    private final Speaker speaker;
    private final Tyre tyre;

    @Autowired
    public VehicleServices(Speaker speaker, Tyre tyre) {
        this.speaker = speaker;
        this.tyre = tyre;
    }

    @LogAspect
    public String playMusic(boolean vehicleStarted, Song song) {
        return speaker.makeSound(song);
    }

    public String moveVehicle(boolean vehicleStarted) {
        return tyre.rotate();
    }

    public String applyBrakes(boolean vehicleStarted) {
        return tyre.rotate();
    }
}
