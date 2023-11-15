package org.game;

import org.gameSounds.SoundPlayer;
import org.graphics.Renderer;
import org.object.Cow;
import org.object.Fence.HorizontalFence;
import org.object.Fence.VerticalFence;
import org.object.Player;
import org.world.World;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Game {
    public static Object quit;
    public static void main(String[] args) {
        System.out.println("Starting!");
        Renderer.init();

        World.currentWorld = new World();
        World.currentWorld.addSprite(new Player(50, 150));


        World.currentWorld.addSprite(new VerticalFence(150, 50, 6, 50));
        World.currentWorld.addSprite(new VerticalFence(250, 50, 6, 50));

        //World.currentWorld.addSprite(new HorizontalFence(150, 50, 50, 18));
        //World.currentWorld.addSprite(new HorizontalFence(200, 50, 50, 18));


        SoundPlayer.playBackgroundMusic();


        World.currentWorld.addSprite(new Cow(600, 50));


    }

    public static void quit()   {

        System.exit(0);

    }
}