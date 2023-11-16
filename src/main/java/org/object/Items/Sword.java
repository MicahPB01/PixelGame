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
    private long damageCooldown = 500;

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

/*
        if (!Input.getKey(KeyEvent.VK_E)) {
            // If 'E' key is released, remove the sword from the world
            this.posY -= 1000;
        }


 */

    }

    public void render(Graphics g) {
        // Render the sword only if it is still in the list of sprites
/*

        if (World.currentWorld.sprites.contains(this)) {
            g.drawImage(image, (int) (posX - width / 2), (int) (posY - height / 2), null);
        }


 */

        g.drawImage(image, (int) (posX - width / 2), (int) (posY - height / 2), null);
    }

    public void swingSword(Player player) {
        // Check for collision with Grifts
        for (Sprite sprite : World.currentWorld.sprites) {
            if (sprite instanceof Grift && isCollidingWith(sprite) && canDamage()) {
                Grift grift = (Grift) sprite;
                grift.takeDamage(25);
                lastDamageTime = System.currentTimeMillis();
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

    private boolean canDamage() {
        // Check if enough time has passed since the last damage
        return System.currentTimeMillis() - lastDamageTime >= damageCooldown;
    }

    public void checkForPickup()   {
        for (Sprite sprite : World.currentWorld.sprites) {
            if (sprite instanceof Player && isCollidingWith(sprite) ) {
                ((Player) sprite).hasSword = true;
                World.currentWorld.removeSprite(this);
            }
        }
    }
}


