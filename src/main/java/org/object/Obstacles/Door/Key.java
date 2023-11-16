package org.object.Obstacles.Door;

import org.graphics.Renderer;
import org.input.Input;
import org.object.Sprite;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class Key extends Sprite {

    private boolean isPickedUp;

    public Key(float posX, float posY) {
        super(posX, posY);
        isPickedUp = false;
        width = 20;
        height = 20;

        try {
            image = Renderer.loadImage("/Objects/Key.png");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(float deltaTime) {
        if (!isPickedUp && isPlayerNear()) {
            // Player is near the key and can pick it up
            if (Input.getKeyDown(KeyEvent.VK_SPACE)) {
                isPickedUp = true;
                // Additional logic when the key is picked up (e.g., play a sound)
            }
        }
    }

    private boolean isPlayerNear() {
        float distanceToPlayer = (float) Math.sqrt(
                Math.pow(posX - Player.getPlayer().getPosX(), 2) +
                        Math.pow(posY - Player.getPlayer().getPosY(), 2)
        );
        return distanceToPlayer < 30; // Adjust this value based on your game's requirements
    }

    public boolean isPickedUp() {
        return isPickedUp;
    }

    public void render(Graphics g) {
        if (!isPickedUp) {
            g.drawImage(image, (int) (posX - width / 2), (int) (posY - height / 2), null);
        }
    }
}
