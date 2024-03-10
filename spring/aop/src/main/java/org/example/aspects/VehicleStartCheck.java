package org.example.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
@Order(1)
public class VehicleStartCheck {
    private Logger logger = Logger.getLogger(VehicleStartCheck.class.getName());

    @Before("execution(* org.example.services.*.*(..)) && args(vehicleStarted, ..)")
    // Accept a parameter to execute the target method
    public void checkVehicleStart(JoinPoint joinPoint, boolean vehicleStarted) throws Throwable {
        if (!vehicleStarted) {
            throw new RuntimeException("Vehicle not started");
        }
    }
}
