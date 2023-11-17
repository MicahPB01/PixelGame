package org.gameSounds;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class SoundPlayer {

    private static Clip milkClip; // For short milk sound
    private static Clip backgroundMusicClip; // For background music

    private static Clip winClip;
    private static Clip keyClip;
    private static Clip grumbleClip;
    private static Clip doorOpenClip;


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

            //load key sound
            URL keyURL = SoundPlayer.class.getResource("/Sounds/Key.wav");
            AudioInputStream keyAudioInputStream = AudioSystem.getAudioInputStream(keyURL);
            keyClip = AudioSystem.getClip();
            keyClip.open(keyAudioInputStream);

            //load door open sound
            URL doorOpenURL = SoundPlayer.class.getResource("/Sounds/Door_Open.wav");
            AudioInputStream doorOpenAudioInputStream = AudioSystem.getAudioInputStream(doorOpenURL);
            doorOpenClip = AudioSystem.getClip();
            doorOpenClip.open(doorOpenAudioInputStream);



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

    public static void playKeySound()   {
        if(keyClip != null)   {
            keyClip.setFramePosition(0);
            keyClip.start();
        }
    }

    public static void playDoorOpenSound()   {
        if(doorOpenClip != null)   {
            doorOpenClip.setFramePosition(0);
            doorOpenClip.start();
        }
    }

}
