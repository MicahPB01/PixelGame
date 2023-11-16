package org.object;

import org.graphics.Renderer;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Sprite {

    public float posX = 0;
    public float posY = 0;
    public BufferedImage image = null;

    public float width = 0;
    public float height = 0;

    public boolean isSolid = true;

    public Sprite (float posX, float posY)   {
        this.posX = posX;
        this.posY = posY;
    }
    public void update (float deltaTime)   {


    }
    public void render(Graphics g)   {
        if(image == null)   {
            return;
        }
        //real x/y set position at center

        int realX = (int) posX - image.getWidth() / 2;
        int realY = (int) posY - image.getHeight() / 2;

        realX = realX - (int) Renderer.camX + Renderer.gameWidth / 2;
        realY = realY - (int) Renderer.camY + Renderer.gameHeight / 2;


        g.drawImage(image, realX, realY, image.getWidth(), image.getHeight(), null);
    }

    public float getPosX() {
        return posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosX(int x)   {
        posX = x;
    }

    public void setPosY(int y)   {
        posY = y;
    }


}
