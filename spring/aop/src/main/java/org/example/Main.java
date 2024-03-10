package org.example;

import org.example.config.ProjectConfig;
import org.example.services.Song;
import org.example.services.VehicleServices;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
        var vehicleServices = context.getBean(VehicleServices.class);

        System.out.println(vehicleServices.getClass());
        Song song = new Song();
        song.setLyrics("Hello Dev, new song to the moon");
        song.setTitle("Blank space");

        boolean vehicleStarted = true;

        String moveVehicleStatus = vehicleServices.moveVehicle(vehicleStarted);
        String playMusicStatus = vehicleServices.playMusic(vehicleStarted, song);
        String applyBrakesStatus = vehicleServices.applyBrakes(vehicleStarted);
    }
}