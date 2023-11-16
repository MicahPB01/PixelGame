// AngryNPC.java
package org.object;

import org.game.Game;
import org.gameSounds.SoundPlayer;
import org.graphics.Renderer;
import org.world.World;

import java.awt.*;
import java.io.IOException;
import java.util.Random;

public class Grift extends Mob implements Damageable{

    private Cow targetCow;
    private int damage = 10;
    private long lastNoiseTime = System.currentTimeMillis();
    private long nextNoiseTime = generateRandomNoiseInterval(); // Initialize with a random interval
    private long lastDamageTime = 0;
    private long damageCooldown = 2000;
    int griftHealth = 50;
    private HealthBar healthBar;
    float maxHealth = griftHealth;
    float collisionProximityThreshold = 10;


    public Grift(float posX, float posY, Cow targetCow) {
        super(posX, posY);
        this.targetCow = targetCow;

        width = 32;
        height = 32;
        horizontalSpeed = 40; // Adjust the speed as needed
        isSolid = false;

        try {
            image = Renderer.loadImage("/Characters/Grift/Grift.png");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        healthBar = new HealthBar(this, 20, 5, Color.RED, Color.GREEN);
    }

    private void checkNonPlayerCollisions(float deltaTime) {
        // Iterate over all sprites in the world
        for (Sprite sprite : World.currentWorld.sprites) {
            if (sprite instanceof Player || sprite instanceof Grift) {
                // Skip collision checks with other players or Grifts
                continue;
            }

            // Check for collisions with other objects
            if (doesCollide(posX + horizontalSpeed * deltaTime, posY, sprite)) {
                // Handle collision with non-player object if needed
                handleNonPlayerCollision(sprite);
            }
        }
    }

    private void handleNonPlayerCollision(Sprite sprite) {
        // Calculate the direction vector between the Grift and the collision object
        float directionX = sprite.posX - posX;
        float directionY = sprite.posY - posY;
        float distance = (float) Math.sqrt(directionX * directionX + directionY * directionY);

        // Adjust this threshold based on the proximity at which you want to push back
        float collisionProximityThreshold = 50;

        // Check if the Grift is in the proximity of the collision
        if (distance < collisionProximityThreshold) {
            // Calculate the normalized direction
            directionX /= distance;
            directionY /= distance;

            // Push back by one pixel in the opposite direction
            posX -= directionX;
            posY -= directionY;
        }
    }


    @Override
    public void update(float deltaTime) {
        // Implement NPC behavior here

        checkNonPlayerCollisions(deltaTime);
        // Check for collision with other Grifts
        checkGriftCollisions(deltaTime);




        if (targetCow != null) {
            // Move towards the cow
            float directionX = targetCow.getPosX() - posX;
            float directionY = targetCow.getPosY() - posY;
            float distance = (float) Math.sqrt(directionX * directionX + directionY * directionY);

            if (distance > 0) {
                directionX /= distance;
                directionY /= distance;
            }

            posX += directionX * horizontalSpeed * deltaTime;
            posY += directionY * horizontalSpeed * deltaTime;

            // Check for collision with the cow
            if (isCollidingWithCow() && canDamage()) {
                targetCow.takeDamage(damage);
                lastDamageTime = System.currentTimeMillis();
            }

            // Check for making noise
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastNoiseTime >= nextNoiseTime) {
                makeNoise();
                lastNoiseTime = currentTime;
                nextNoiseTime = generateRandomNoiseInterval();
            }
        }



    }

    private boolean doesCollide(float x, float y, Sprite other) {
        float myLeft = x - width / 2;
        float myRight = x + width / 2;
        float myUp = y - height / 2;
        float myDown = y + height / 2;


        float otherLeft = other.posX- other.width / 2;
        float otherRight = other.posX + other.width / 2;
        float otherUp = other.posY - other.height / 2;
        float otherDown = other.posY + other.height / 2;

        return myLeft < otherRight && myRight > otherLeft && myDown > otherUp && myUp < otherDown;
    }


    private boolean canDamage() {
        // Check if enough time has passed since the last damage
        return System.currentTimeMillis() - lastDamageTime >= damageCooldown;
    }


    private void makeNoise() {
        SoundPlayer.playGriftGrumble();

    }

    private long generateRandomNoiseInterval() {
        // Generate a random interval between 1 and 5 seconds (in milliseconds)
        Random random = new Random();
        return (long) (1000 * (1 + random.nextInt(5)));
    }
    private boolean isCollidingWithCow() {
        // Define a radius for the attack
        float attackRadius = 45; // Adjust the radius as needed

        // Calculate the distance between the Grift and the cow
        float distance = (float) Math.sqrt(
                Math.pow(posX - targetCow.getPosX(), 2) +
                        Math.pow(posY - targetCow.getPosY(), 2)
        );

        // Check if the cow is within the attack radius
        return distance < attackRadius;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, (int) (posX - width / 2), (int) (posY - height / 2), null);
        healthBar.render(g, 20);
    }

    @Override
    public float getHealth() {
        return griftHealth;
    }

    public void takeDamage(int damage)   {
        griftHealth -= damage;
        if(griftHealth == 0)   {
            World.currentWorld.removeSprite(this);
        }

    }

    @Override
    public float getMaxHealth() {
        return maxHealth;
    }

    @Override
    public float getPosX() {
        return posX;
    }

    @Override
    public float getPosY() {
        return posY;
    }

    private void checkGriftCollisions(float deltaTime) {
        // Iterate over all sprites in the world
        for (Sprite sprite : World.currentWorld.sprites) {
            if (sprite instanceof Grift && sprite != this) {
                // Check for collisions with other Grifts
                if (doesCollide(posX + horizontalSpeed * deltaTime, posY, sprite)) {
                    // Prevent movement towards each other
                    float directionX = posX - 10;
                    float directionY = posY - 10;
                    float distance = (float) Math.sqrt(directionX * directionX + directionY * directionY);

                    if (distance < collisionProximityThreshold) {
                        // Adjust the current Grift's position to avoid overlapping
                        posX -= 20;
                        posY -= 20;
                    }
                }
            }
        }
    }


}
