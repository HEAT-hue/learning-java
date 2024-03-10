package org.example.config;

import org.example.services.VehicleServices;
import org.example.services.speaker.BoseSpeaker;
import org.example.services.speaker.SonySpeaker;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = {"org.example.services", "org.example.aspects"})
@ComponentScan(basePackageClasses = {VehicleServices.class, SonySpeaker.class, BoseSpeaker.class})
@EnableAspectJAutoProxy // Enable AOP
public class ProjectConfig {

}