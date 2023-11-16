package org.world;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import org.constants.Constants;
import org.graphics.Renderer;
import org.object.Mobs.Cow;
import org.object.Mobs.Player;
import org.object.Sprite;

public class World {

    public static World currentWorld = null;
    private static long lastTime = System.nanoTime();
    public ArrayList<Sprite> sprites = new ArrayList<Sprite>();
    public ArrayList<Sprite> addSprites = new ArrayList<Sprite>();
    public ArrayList<Sprite> removeSprites = new ArrayList<Sprite>();


    private static BufferedImage backdrop = null;
    public static void update() throws IOException {
        float deltaTime = (System.nanoTime() - lastTime) / Constants.OneSecondNanoSecondFloat;
        lastTime = System.nanoTime();


        for(Sprite sprite : currentWorld.sprites)   {
            sprite.update(deltaTime);

            // Handle interactions between player and cow

            if(sprite instanceof Cow)   {
                if (sprite.posX < 5) {
                    sprite.setPosX(Renderer.gameWidth -5);
                }

                if (sprite.posX > Renderer.gameWidth -5) {
                    sprite.setPosX(5);
                }
            }

            if(sprite instanceof  Player) {

                if (sprite.posX < -2) {
                    sprite.setPosX(Renderer.gameWidth + 2);
                }

                if (sprite.posX > Renderer.gameWidth + 2) {
                    sprite.setPosX(-2);
                }
            }



        }

        for(Sprite sprite : currentWorld.addSprites)   {
            if(!currentWorld.sprites.contains(sprite))   {
                currentWorld.sprites.add(sprite);
            }
        }

        currentWorld.addSprites.clear();

        for(Sprite sprite : currentWorld.removeSprites)   {
            if(currentWorld.sprites.contains(sprite))   {
                currentWorld.sprites.remove(sprite);
            }
        }
        currentWorld.removeSprites.clear();





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

    public void addSprite(Sprite sprite)   {
        if(!addSprites.contains(sprite))   {
            addSprites.add(sprite);
        }
    }

    public void removeSprite(Sprite sprite)   {
        if(!removeSprites.contains(sprite))   {
            removeSprites.add(sprite);
        }
    }


}
