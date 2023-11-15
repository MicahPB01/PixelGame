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
@Override
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


        for(Sprite sprite : World.currentWorld.sprites)   {
            if(sprite == this)   {
                continue;
            }

            Rectangle myRect = new Rectangle((int) (posX + moveX * deltaTime - width / 2), (int) (posY - height / 2), (int) width, (int) height);



            Rectangle otherRect = new Rectangle((int) (sprite.posX), (int) (sprite.posY),
                    (int) sprite.width, (int) sprite.height);

            if(myRect.intersects(otherRect))   {
                moveX -= moveX;
            }

            myRect = new Rectangle((int) (posX - width / 2), (int) (posY + moveY * deltaTime - height / 2), (int) width, (int) height);

            if(myRect.intersects(otherRect))   {
                moveY -= moveY;
            }
        }
        //end collisions


        posX += moveX * deltaTime;
        posY += moveY * deltaTime;
    }
@Override
    public void render (Graphics g)   {

        g.drawImage(image, (int) (posX - width / 2), (int) (posY - height / 2), null);
    }
}
