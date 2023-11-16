package org.object.Mobs;

import org.graphics.Renderer;
import org.input.Input;
import org.object.*;
import org.object.Items.Bucket;
import org.object.Items.Sword;
import org.object.Obstacles.Bridge;
import org.object.Obstacles.River;
import org.world.World;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class Player extends Mob implements Damageable {
    public static boolean isBucketHeld = false;
    private float prevPosX;
    private float prevPosY;
    float playerHealth = 200;
    float maxHealth = playerHealth;
    private HealthBar healthBar;
    private Sword sword;
    public boolean hasSword;
    public boolean hasKey;
    public boolean isGrabbingCow = false;


    public Player(float posX, float posY, boolean hasSword, boolean hasKey) {
        super(posX, posY);
        this.hasSword = hasSword;
        this.hasKey = hasKey;

        width = 22;
        height = 30;
        horizontalSpeed = 100;
        verticalSpeed = 100;

        try {
            image = Renderer.loadImage("/Characters/Jeff/Jeff_Default.png");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        healthBar = new HealthBar(this, 50, 5, Color.RED, Color.GREEN);
    }

    public void update(float deltaTime) {

        float moveX = 0;
        float moveY = 0;

        prevPosX = posX;
        prevPosY = posY;

        handleInput(deltaTime);





        if (Input.getKey(KeyEvent.VK_A)) {
            try {
                image = Renderer.loadImage("/Characters/Jeff/Jeff_Left.png");
            } catch (IOException e) {
                e.printStackTrace();
            }
            moveX -= horizontalSpeed;
        }

        if (Input.getKey(KeyEvent.VK_D)) {
            try {
                image = Renderer.loadImage("/Characters/Jeff/Jeff_Right.png");
            } catch (IOException e) {
                e.printStackTrace();
            }
            moveX += horizontalSpeed;
        }

        if (Input.getKey(KeyEvent.VK_W)) {
            try {
                image = Renderer.loadImage("/Characters/Jeff/Jeff_Up.png");
            } catch (IOException e) {
                e.printStackTrace();
            }
            moveY -= verticalSpeed;
        }

        if (Input.getKey(KeyEvent.VK_S)) {
            try {
                image = Renderer.loadImage("/Characters/Jeff/Jeff_Default.png");
            } catch (IOException e) {
                e.printStackTrace();
            }
            moveY += verticalSpeed;
        }

        // do collisions
        if (doesCollide(posX + moveX * deltaTime, posY + moveY * deltaTime)) {
            moveX = 0;
            moveY = 0;
        }

        // end collisions

        Bucket bucket = new Bucket(posX, posY, 0);

        if (Input.getKeyDown(KeyEvent.VK_M)) {
            World.currentWorld.addSprite(bucket);
            isBucketHeld = true;

        }

        if (Input.getKeyUp(KeyEvent.VK_M)) {
            isBucketHeld = false;
        }

        posX += moveX * deltaTime;
        posY += moveY * deltaTime;
    }

    private boolean isOnBridge(float x, float y) {
        for (Sprite sprite : World.currentWorld.sprites) {
            if (sprite instanceof Bridge) {
                // Assuming the bridge is a horizontal line, check if the player is within its bounds
                float bridgeY = sprite.posY;  // Change this based on your bridge implementation
                float bridgeLeft = sprite.posX - (sprite.width / 2);
                float bridgeRight = sprite.posX + (sprite.width / 2);
                return y > bridgeY - height / 2 && y < bridgeY + height / 2 && x > bridgeLeft && x < bridgeRight;
            }
        }
        return false;
    }


    private boolean doesCollide(float x, float y) {
        float myLeft = x - width / 2;
        float myRight = x + width / 2;
        float myUp = y - height / 2;
        float myDown = y + height / 2;

        for (Sprite sprite : World.currentWorld.sprites) {
            if (sprite == this || !sprite.isSolid) {
                continue;
            }

            if (isOnBridge(x, y))   {
                continue;
            }

            if (sprite instanceof River) {
                // Handle river collision with its polygon
                River river = (River) sprite;
                if (river.getPolygon().intersects(myLeft, myUp, width, height)) {
                    return true;
                }
            } else {
                // Handle collisions with other solid sprites
                float otherLeft = sprite.posX - (sprite.width / 2);
                float otherRight = sprite.posX + (sprite.width / 2);
                float otherUp = sprite.posY - (sprite.height / 2);
                float otherDown = sprite.posY + (sprite.height / 2);

                if (myLeft < otherRight && myRight > otherLeft && myDown > otherUp && myUp < otherDown) {
                    return true;
                }
            }
        }
        return false;
    }

    public void render(Graphics g) {
        g.drawImage(image, (int) (posX - width / 2), (int) (posY - height / 2), null);
        g.setColor(Color.GREEN);
        g.drawRect((int) (posX - width / 2), (int) (posY - height / 2), (int) width, (int) height);

        healthBar.render(g, 20);

        if(sword != null)   {
            sword.render(g);
        }
    }



    public float getPrevPosX() {
        return prevPosX;
    }

    public float getPrevPosY() {
        return prevPosY;
    }





    @Override
    public float getHealth() {
        return playerHealth;
    }

    @Override
    public void takeDamage(int damage) {

    }

    @Override
    public float getMaxHealth() {
        return maxHealth;
    }

    private void spawnSword() {
        // Check if the player already has a sword
        if (sword == null) {
            // Spawn a new sword near the player
            sword = new Sword(posX, posY - 20); // Adjust the position as needed
            World.currentWorld.addSprite(sword);
        }
    }



    private void handleInput(float deltaTime) {


        // Spawn the sword when 'E' is pressed
        if (Input.getKeyDown(KeyEvent.VK_E) && hasSword) {
            if (sword == null) {
                sword = new Sword(posX, posY);
                World.currentWorld.addSprite(sword);
            }

            sword.swingSword(this);

        }

        World.currentWorld.removeSprite(sword);

        // Move the sword with the player
        if (sword != null) {
            sword.setPosX(posX + 11);
            sword.setPosY(posY - 10 );
        }

    }
}
