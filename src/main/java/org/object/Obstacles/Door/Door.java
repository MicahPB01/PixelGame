package org.object.Obstacles.Door;

import org.graphics.Renderer;
import org.object.Sprite;

import java.awt.*;
import java.io.IOException;

public class Door extends Sprite {

    private boolean isLocked;

    public Door(float posX, float posY) {
        super(posX, posY);
        isLocked = true;
        width = 22;
        height = 30;

        try {
            image = Renderer.loadImage("/Obstacles/Door/Door_Front.png");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(float deltaTime) {
        // Additional logic for the door can be added here
        // For example, check if the player has the key and unlock the door accordingly
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
