package org.game;

import org.graphics.Renderer;
import org.object.Fence;
import org.object.Player;
import org.test.TestSprite;
import org.world.World;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Game {
    public static Object quit;
    public static void main(String[] args) {
        System.out.println("Starting!");
        Renderer.init();

        World.currentWorld = new World();
        World.currentWorld.sprites.add(new Player(100, 100));
        //World.currentWorld.sprites.add(new TestSprite(150,150));
        World.currentWorld.sprites.add(new Fence(200, 200));

        try   {
            BufferedImage image = Renderer.loadImage("/Characters/Jeff/Jeff_Default.png");
        }
        catch (IOException e)   {
            e.printStackTrace();
        }
    }

    public static void quit()   {

        System.exit(0);

    }
}