package org.object;

import org.graphics.Renderer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Fence extends Sprite {


    public Fence(float posX, float posY) {
        super(posX, posY);

        width = 50;
        height = 18;


        try {
            image = Renderer.loadImage("/Obstacles/Basic_Fence.png");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public void render (Graphics g)   {

        g.drawImage(image, (int) posX, (int) posY, null);



    }
}
