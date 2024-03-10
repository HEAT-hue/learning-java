package org.example.services.tyre;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component(value = "BridgeStoneTyre")
public class BridgeStoneTyre implements Tyre {
    private String name;

    @Override
    public String rotate() {
        return "BridgeStone tyre moving";
    }

    @Override
    public String stop() {
        return "BridgeStone tyre has stopped moving";
    }

    @PostConstruct
    public void init() {
        this.name = "BridgeStone";
    }

    @Override
    public String toString() {
        return name;
    }
}
