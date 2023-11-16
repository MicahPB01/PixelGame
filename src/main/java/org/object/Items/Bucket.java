package org.object.Items;

import org.graphics.Renderer;
import org.object.Sprite;
import org.world.World;

import java.awt.*;
import java.io.IOException;

public class Bucket extends Sprite {
    //0 is left, 1 is right
    public int direction = 0;
    public float speed = 0.0f;
    public float damage = 1.0f;
    private final long spawnTime;
    private static final long BUCKET_DURATION = 1_000;
    public Bucket(float posX, float posY, int direction) {
        super(posX, posY);
        this.direction = direction;
        isSolid = false;

        width = 10;
        height = 14;

        try {
            image = Renderer.loadImage("/Objects/Bucket.png");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        spawnTime = System.currentTimeMillis();
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

        if(System.currentTimeMillis() - spawnTime >= BUCKET_DURATION)   {
            World.currentWorld.removeSprite(this);
        }

    }



    public void render (Graphics g)   {

        g.drawImage(image, (int) (posX - width / 2 + 20), (int) (posY - height / 2), null);



    }
}
