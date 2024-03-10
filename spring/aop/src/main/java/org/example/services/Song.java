package org.example.services;

public class Song {
    private String title;
    private String lyrics;

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLyrics() {
        return lyrics;
    }
}
