package org.object.Obstacles.Fence;

import org.graphics.Renderer;
import org.object.Sprite;

import java.awt.*;
import java.io.IOException;

public class VerticalFence extends Sprite {



    public VerticalFence(float posX, float posY, float width, float height) {
        super(posX, posY);

        this.width = width;
        this.height = height;



        try {
            image = Renderer.loadImage("/Obstacles/Fence/VerticalFence.png");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public void render(Graphics g) {
        g.drawImage(image, (int) (posX - width / 2), (int) (posY - height / 2), null);
    }

}
