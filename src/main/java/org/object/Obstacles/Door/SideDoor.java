package org.object.Obstacles.Door;

import org.graphics.Renderer;
import org.object.Mobs.Player;
import org.object.Sprite;
import org.world.World;

import java.awt.*;
import java.io.IOException;

public class SideDoor extends Sprite {

    private boolean isLocked;

    public SideDoor(float posX, float posY) {
        super(posX, posY);
        isLocked = true;
        width = 4;
        height = 30;

        try {
            image = Renderer.loadImage("/Obstacles/Door/Door_Side.png");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(float deltaTime) {


        for(Sprite sprite : World.currentWorld.sprites)   {
            if(sprite instanceof Player && ((Player) sprite).hasKey && isCollidingWith(sprite, 5))   {
                World.currentWorld.removeSprite(this);
                unlock();
            }
        }






    }

    private boolean isCollidingWith(Sprite other, float offset) {
        float myLeft = posX - (width / 2 + offset);
        float myRight = posX + (width / 2 + offset);
        float myUp = posY - (height / 2 + offset);
        float myDown = posY + (height / 2 + offset);

        float otherLeft = other.posX - other.width / 2;
        float otherRight = other.posX + other.width / 2;
        float otherUp = other.posY - other.height / 2;
        float otherDown = other.posY + other.height / 2;

        return myLeft < otherRight && myRight > otherLeft && myDown > otherUp && myUp < otherDown;
    }


    public void unlock() {
        isLocked = false;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void render(Graphics g) {
        if (isLocked) {
            g.drawImage(image, (int) (posX - width / 2), (int) (posY - height / 2), null);
        }
    }
}
