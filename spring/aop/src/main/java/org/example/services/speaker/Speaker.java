package org.example.services.speaker;

import org.example.services.Song;

public interface Speaker {
    String makeSound(Song song);

    String getName();
}
