package org.object;

import org.graphics.Renderer;
import org.input.Input;
import org.world.World;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class Player extends Mob {

    public Player(float posX, float posY) {
        super(posX, posY);

        width = 22;
        height = 30;
        horizontalSpeed = 100;
        verticalSpeed = 100;


        try {
            image = Renderer.loadImage("/Characters/Jeff/Jeff_Default.png");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void update (float deltaTime)   {
        float moveX = 0;
        float moveY = 0;



        if(Input.getKey(KeyEvent.VK_A))   {

            try {
                image = Renderer.loadImage("/Characters/Jeff/Jeff_Left.png");
            }
            catch (IOException e)   {
                e.printStackTrace();
            }

            moveX -= horizontalSpeed;
        }

        if(Input.getKey(KeyEvent.VK_D))   {

            try {
                image = Renderer.loadImage("/Characters/Jeff/Jeff_Right.png");
            }
            catch (IOException e)   {
                e.printStackTrace();
            }

            moveX += horizontalSpeed;
        }

        if(Input.getKey(KeyEvent.VK_W))   {

            try {
                image = Renderer.loadImage("/Characters/Jeff/Jeff_Up.png");
            }
            catch (IOException e)   {
                e.printStackTrace();
            }

            moveY -= verticalSpeed;
        }

        if(Input.getKey(KeyEvent.VK_S))   {

            try {
                image = Renderer.loadImage("/Characters/Jeff/Jeff_Default.png");
            }
            catch (IOException e)   {
                e.printStackTrace();
            }

            moveY += verticalSpeed;
        }



        //do collisions
        if(doesCollide(posX + moveX * deltaTime, posY))   {
            moveX -= moveX;
        }

        if(doesCollide(posX, posY + moveY * deltaTime))   {
            moveY -= moveY;
        }
        //end collisions

    Bucket bucket = new Bucket(posX, posY, 0);

    if(Input.getKeyDown(KeyEvent.VK_M))   {
        World.currentWorld.addSprite(bucket);
    }

    if(Input.getKeyUp(KeyEvent.VK_M))   {
        World.currentWorld.removeSprite(bucket);
    }


        posX += moveX * deltaTime;
        posY += moveY * deltaTime;
    }

    private boolean doesCollide (float x, float y)   {
        float myLeft = x - width / 2;
        float myRight = x + width / 2;
        float myUp = y - height / 2;
        float myDown = y + height / 2;


        for(Sprite sprite : World.currentWorld.sprites)   {

            if(sprite == this)   {
                continue;
            }

            float otherLeft = sprite.posX - width / 2;
            float otherRight = sprite.posX + width / 2;
            float otherUp = sprite.posY - sprite.height / 2;
            float otherDown = sprite.posY + sprite.height / 2;

            if(myLeft < otherRight && myRight > otherLeft && myDown > otherUp && myUp < otherDown)   {
                return true;
            }
        }
        return false;
    }



    public void render (Graphics g)   {

        g.drawImage(image, (int) (posX - width / 2), (int) (posY - height / 2), null);
    }
}
