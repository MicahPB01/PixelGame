package org.object;

import org.graphics.Renderer;
import org.world.World;

import java.awt.*;
import java.io.IOException;

public class Bucket extends Sprite   {
    //0 is left, 1 is right
    public int direction = 0;
    public float speed = 1000000.0f;
    public float damage = 10.0f;
    public Bucket(float posX, float posY, int direction) {
        super(posX, posY);
        this.direction = direction;

        width = 10;
        height = 14;

        try {
            image = Renderer.loadImage("/Objects/Bucket.png");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void update (float deltaTime)   {
        float moveX = 0;

        if (direction == 0)   {
            moveX -= speed;
        }
        else   {
            moveX += speed;
        }

        posX += moveX * deltaTime;
    }



    public void render (Graphics g)   {

        g.drawImage(image, (int) (posX - width / 2 + 18), (int) (posY - height / 2), null);



    }
}
