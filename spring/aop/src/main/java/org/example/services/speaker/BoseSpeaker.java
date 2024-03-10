package org.example.services.speaker;

import jakarta.annotation.PostConstruct;
import org.example.services.Song;
import org.springframework.stereotype.Component;

@Component(value = "BoseSpeaker")
public class BoseSpeaker implements Speaker {
    private String name;

    @PostConstruct
    void init() {
        this.name = "Bose Speaker";
    }

    @Override
    public String makeSound(Song song) {
        return "Bose music zzz! " + song.getLyrics();
    }

    @Override
    public String getName() {
        return this.name;
    }
}
