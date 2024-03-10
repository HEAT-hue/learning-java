package org.example.services.speaker;

import jakarta.annotation.PostConstruct;
import org.example.services.Song;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component(value = "SonySpeaker")
public class SonySpeaker implements Speaker {
    private String name;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String makeSound(Song song) {
        return "Sony Music zzz! " + song.getLyrics();
    }

    @PostConstruct
    void init() {
        this.name = "Sony speaker";
    }
}