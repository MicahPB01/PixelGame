package org.object.Items;

import org.graphics.Renderer;
import org.object.Mobs.Player;
import org.object.Mobs.Grift;
import org.object.Sprite;
import org.world.World;

import java.awt.*;
import java.io.IOException;

public class Sword extends Sprite {

    private long lastDamageTime = 0;
    private long damageCooldown = 2000; // Increase cooldown to 2000 milliseconds (2 seconds)
    public boolean isVisible = true; // Initial visibility state
    public boolean hasBeenUsed = false;

    public Sword(float posX, float posY) {
        super(posX, posY);

        width = 10;
        height = 10;

        try {
            image = Renderer.loadImage("/Weapons/Sword.png");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        isSolid = false;
    }

    @Override
    public void update(float deltaTime) {
        // Check if the 'E' key is being held down
        checkForPickup();

        // Check if the sword should be visible based on cooldown
        if (System.currentTimeMillis() - lastDamageTime >= damageCooldown) {
            isVisible = true;
            hasBeenUsed = false; // Reset the flag when cooldown is over
        } else {
            isVisible = false;
        }
    }

    public void render(Graphics g) {
        // Render the sword only if it is still in the list of sprites and is visible
        if (isVisible) {
            g.drawImage(image, (int) (posX - width / 2), (int) (posY - height / 2), null);
        }
    }

    public void swingSword(Player player) {
        // Check for collision with Grifts
        if (isVisible) {
            for (Sprite sprite : World.currentWorld.sprites) {
                if (sprite instanceof Grift && isCollidingWith(sprite) && !hasBeenUsed) {
                    Grift grift = (Grift) sprite;
                    grift.takeDamage(1);
                    lastDamageTime = System.currentTimeMillis();
                    hasBeenUsed = true;
                }
            }
        }
    }

    private boolean isCollidingWith(Sprite other) {
        float myLeft = posX - width / 2;
        float myRight = posX + width / 2;
        float myUp = posY - height / 2;
        float myDown = posY + height / 2;

        float otherLeft = other.posX - other.width / 2;
        float otherRight = other.posX + other.width / 2;
        float otherUp = other.posY - other.height / 2;
        float otherDown = other.posY + other.height / 2;

        return myLeft < otherRight && myRight > otherLeft && myDown > otherUp && myUp < otherDown;
    }

    public void setPosX(float x) {
        posX = x;
    }

    public void setPosY(float y) {
        posY = y;
    }

    public void checkForPickup() {
        for (Sprite sprite : World.currentWorld.sprites) {
            if (sprite instanceof Player && isCollidingWith(sprite)) {
                ((Player) sprite).hasSword = true;
                World.currentWorld.removeSprite(this);
            }
        }
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }
}
