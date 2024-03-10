package org.example.services.tyre;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component(value = "MichelinTyre")
public class MichelinTyre implements Tyre {
    private String name;

    @Override
    public String rotate() {
        return "Michelin tyre moving";
    }

    @Override
    public String stop() {
        return "Michelin tyre stopped moving";
    }

    @PostConstruct
    public void init() {
        this.name = "Michelin";
    }

    @Override
    public String toString() {
        return name;
    }
}
