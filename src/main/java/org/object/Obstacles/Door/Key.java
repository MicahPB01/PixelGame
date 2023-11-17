package org.object.Obstacles.Door;

import org.gameSounds.SoundPlayer;
import org.graphics.Renderer;
import org.object.Mobs.Player;
import org.object.Sprite;
import org.world.World;

import java.awt.*;
import java.io.IOException;

public class Key  extends Sprite {


    private boolean isPickedUp = false;
    private int keyHealth = 2;

    public Key(float posX, float posY) {
        super(posX, posY);

        isPickedUp = false;
        width = 7;
        height = 3;
        isSolid = false;

        try   {
            image = Renderer.loadImage("/Obstacles/Door/Key.png");
        }
        catch(IOException e)   {
            e.printStackTrace();
        }
    }

    public void update(float deltaTime)   {

        for(Sprite sprite : World.currentWorld.sprites) {
            if (sprite instanceof Player) {
                if (isCollidingWith(sprite) && !isPickedUp) {
                    isPickedUp = true;
                    ((Player) sprite).hasKey = true;
                    SoundPlayer.playKeySound();

                    World.currentWorld.removeSprite(this);
                }

            }



        }
    }

    public void render(Graphics g)   {
        g.drawImage(image, (int) (posX - width / 2), (int) (posY - height / 2), null);
    }

    private boolean isCollidingWith(Sprite other) {
        float myLeft = posX - width / 2;
        float myRight = posX + width / 2;
        float myUp = posY - height / 2;
        float myDown = posY + height / 2;

        float otherLeft = other.posX - other.width / 2;
        float otherRight = other.posX + other.width / 2;
        float otherUp = other.posY - other.height / 2;
        float otherDown = other.posY + other.height / 2;

        return myLeft < otherRight && myRight > otherLeft && myDown > otherUp && myUp < otherDown;
    }





}
