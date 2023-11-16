package org.object;

import java.awt.*;

public class WinArea extends Sprite{
    int width;
    int height;
    int gameWon = 0;


    public WinArea(float posX, float posY, int width, int height) {
        super(posX, posY);


        this.width = width;
        this.height = height;

        isSolid = false;

    }


    public void render(Graphics g) {
        // Render the WinningArea sprite
        g.setColor(Color.BLUE);
        g.drawRect((int) (posX - width / 2), (int) (posY - height / 2), (int) width, (int) height);


    }



}
