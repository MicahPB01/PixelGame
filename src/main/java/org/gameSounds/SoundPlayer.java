package org.gameSounds;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class SoundPlayer {

    private static Clip milkClip; // For short milk sound
    private static Clip backgroundMusicClip; // For background music

    private static Clip winClip;
    private static Clip grumbleClip;
    static {
        try {
            // Load the short milk sound
            URL milkSoundURL = SoundPlayer.class.getResource("/Characters/Cow/Moo.wav");
            AudioInputStream milkAudioInputStream = AudioSystem.getAudioInputStream(milkSoundURL);
            milkClip = AudioSystem.getClip();
            milkClip.open(milkAudioInputStream);

            // Load the background music
            URL musicURL = SoundPlayer.class.getResource("/Music/Pixel.wav");
            AudioInputStream musicAudioInputStream = AudioSystem.getAudioInputStream(musicURL);
            backgroundMusicClip = AudioSystem.getClip();
            backgroundMusicClip.open(musicAudioInputStream);

            // Load the win music
            URL winURL = SoundPlayer.class.getResource("/Music/Win.wav");
            AudioInputStream winAudioInputStream = AudioSystem.getAudioInputStream(winURL);
            winClip = AudioSystem.getClip();
            winClip.open(winAudioInputStream);

            //load grumble
            URL grumbleURL = SoundPlayer.class.getResource("/Characters/Grift/Grift_Grumble.wav");
            AudioInputStream grumbleAudioInputStream = AudioSystem.getAudioInputStream(grumbleURL);
            grumbleClip = AudioSystem.getClip();
            grumbleClip.open(grumbleAudioInputStream);


        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void playMilkSound() {
        if (milkClip != null) {
            milkClip.setFramePosition(0); // Rewind the clip to the beginning
            milkClip.start();
        }
    }

    public static void playBackgroundMusic() {
        if (backgroundMusicClip != null) {
            backgroundMusicClip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public static void stopBackgroundMusic() {
        if (backgroundMusicClip != null && backgroundMusicClip.isRunning()) {
            backgroundMusicClip.stop();
        }
    }

    public static void playWinSound()   {
        if(winClip != null)   {
            winClip.setFramePosition(0);
            winClip.start();
        }
    }

    public static void playGriftGrumble()   {
        if(grumbleClip != null)   {
           grumbleClip.setFramePosition(0);
            grumbleClip.start();

        }
    }
}
