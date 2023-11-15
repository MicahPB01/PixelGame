package org.test;

import org.graphics.Renderer;
import org.input.Input;
import org.object.Sprite;
import org.world.World;

import java.awt.event.KeyEvent;
import java.io.IOException;

public class TestSprite extends Sprite   {

    public TestSprite(float posX, float posY)   {
        super(posX, posY);

        try   {
            image = Renderer.loadImage("/Characters/Jeff/Jeff_Default.png");
        }
        catch (IOException e)   {
            e.printStackTrace();
        }
    }

    public void update(float deltaTime) {
        if(Input.getKey(KeyEvent.VK_W))   {
            posY -= 50 * (deltaTime);

            try {
                image = Renderer.loadImage("/Characters/Jeff/Jeff_Up.png");
            }
            catch (IOException e)   {
                e.printStackTrace();
            }
        }

        if(Input.getKey(KeyEvent.VK_S))   {
            posY += 50 * (deltaTime);

            try {
                image = Renderer.loadImage("/Characters/Jeff/Jeff_Default.png");
            }
            catch (IOException e)   {
                e.printStackTrace();
            }
        }

        if(Input.getKey(KeyEvent.VK_A))   {
            posX -= 50 * (deltaTime);

            try {
                image = Renderer.loadImage("/Characters/Jeff/Jeff_Left.png");
            }
            catch (IOException e)   {
                e.printStackTrace();
            }
        }

        if(Input.getKey(KeyEvent.VK_D))   {
            posX += 50 * (deltaTime);

            try {
                image = Renderer.loadImage("/Characters/Jeff/Jeff_Right.png");
            }
            catch (IOException e)   {
                e.printStackTrace();
            }
        }


    }


}
