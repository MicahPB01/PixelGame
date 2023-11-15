package org.world;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import org.constants.Constants;
import org.graphics.Renderer;
import org.object.Sprite;

public class World {

    public static World currentWorld = null;
    private static long lastTime = System.nanoTime();
    public ArrayList<Sprite> sprites = new ArrayList<Sprite>();
    private static BufferedImage backdrop = null;
    public static void update() throws IOException {
        float deltaTime = (System.nanoTime() - lastTime) / Constants.OneSecondNanoSecondFloat;
        lastTime = System.nanoTime();
        for(Sprite sprite : currentWorld.sprites)   {
            sprite.update(deltaTime);
        }

    }

    public static void render(Graphics g)   {

        if(backdrop != null)   {
            //
            int x = 0;
            int y = 0;
            g.drawImage(backdrop, x, y , Renderer.gameWidth, Renderer.gameHeight, null);
        }
        else   {
            try {
                backdrop = Renderer.loadImage("/worlds/Default_World.png");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        //render sprites
        for(Sprite sprite : currentWorld.sprites)   {
            sprite.render(g);
        }



    }


}
