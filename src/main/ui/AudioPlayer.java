package ui;

import sun.audio.AudioStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Can play sound from file
 */
public class AudioPlayer {

    private File audioFile;

    // REQUIRES: well-formed .wav file
    // EFFECTS: constructs a new audio player with given file
    public AudioPlayer(File audioFile) {
        this.audioFile = audioFile;
    }

    // EFFECTS: plays the audio file
    public void play() {
        InputStream sound;
        try {
            sound = new FileInputStream(audioFile);
            AudioStream audioStream = new AudioStream(sound);
            sun.audio.AudioPlayer.player.start(audioStream);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
