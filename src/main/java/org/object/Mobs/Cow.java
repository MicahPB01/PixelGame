package org.object.Mobs;

import org.game.Game;
import org.gameSounds.SoundPlayer;
import org.graphics.Renderer;
import org.input.Input;
import org.object.*;
import org.object.Items.Bucket;
import org.world.World;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class Cow extends Mob implements Damageable {

    int timeToFlip = 1000;
    int timeElapsed;
    int spaceMoveMax = 400;
    int spaceMoveMin = -400;
    int currentSpace = 400;
    int oldSpace;
    int currentFlip;
    //0 is left
    int currentDirection = 0;
    float velocity = 20f;
    private long lastPlayTime = 0;
    private static final long PLAY_INTERVAL = 5_000; // 10 seconds in milliseconds
    private boolean isBeingMilked = false;
    private long milkingStartTime = 0;
    private final long MILKING_DURATION = 5_000;
    public int isIdle = 1;
    private Player player;
    private boolean insideWinArea = false;
    private long timeInsideWinArea = 0;
    private long winMessageDisplayStartTime = 0;
    private boolean displayingWinMessage = false;
    private long winMessageDisplayDuration = 5000;
    int cowHealth = 100;
    private HealthBar healthBar;
    float maxHealth = cowHealth;



    public Cow(float posX, float posY, Player player) {
        super(posX, posY);
        this.player = player;


        width = 38;
        height = 25;
        horizontalSpeed = 0.1f;
        verticalSpeed = 1;


        try {
            image = Renderer.loadImage("/Characters/Cow/Cow_Left.png");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        healthBar = new HealthBar(this, 50, 5, Color.RED, Color.GREEN);

    }

    public void takeDamage(int damage)   {
        cowHealth -= damage;
        if(cowHealth == 0)   {
            Game.resetLevel();
        }

    }

    public void update(float deltaTime) {
        getMilked();
        pushCow();

        if(isIdle == 1)   {
            idle(deltaTime);
        }


        checkWinArea(deltaTime);



        // Other update logic for the cow
        // ...

    }

    private void checkWinArea(float deltaTime) {
        for (Sprite sprite : World.currentWorld.sprites) {
            if (sprite instanceof WinArea) {
                WinArea winArea = (WinArea) sprite;

                if (isInside(winArea)) {
                    timeInsideWinArea += (long) (deltaTime * 1000); // Convert deltaTime to milliseconds

                    if (timeInsideWinArea >= 1000 && !insideWinArea) {
                        // Player wins
                        System.out.println("You Win!");
                        SoundPlayer.stopBackgroundMusic();
                        SoundPlayer.playWinSound();

                        insideWinArea = true; // Make sure this only triggers once


                    }
                } else {
                    timeInsideWinArea = 0; // Reset the timer if the cow is not inside the win area
                    insideWinArea = false;
                }
            }
        }
    }

    private boolean isInside(WinArea winArea) {
        // Implement logic to check if the cow is inside the win area
        // You may need to adjust this based on your sprite dimensions and positions
        return (posX >= winArea.posX - width / 2 && posX <= winArea.posX + winArea.width / 2 &&
                posY >= winArea.posY - height / 2 && posY <= winArea.posY + winArea.height / 2);
    }


    private boolean doesCollide (float x, float y)   {
        float myLeft = x - width / 2;
        float myRight = x + width / 2;
        float myUp = y - height / 2;
        float myDown = y + height / 2;


        for(Sprite sprite : World.currentWorld.sprites)   {

            if(sprite == this)   {
                continue;
            }

            float otherLeft = sprite.posX - width / 2;
            float otherRight = sprite.posX + width / 2;
            float otherUp = sprite.posY - sprite.height / 2;
            float otherDown = sprite.posY + sprite.height / 2;

            if(myLeft < otherRight && myRight > otherLeft && myDown > otherUp && myUp < otherDown)   {
                return true;
            }
        }
        return false;
    }



    private void pushCow() {
        float proximityThreshold = 50;  // Adjust this value based on your requirements

        float distanceToPlayer = (float) Math.sqrt(
                Math.pow(posX - player.getPosX(), 2) + Math.pow(posY - player.getPosY(), 2)
        );

        if (Input.getKeyDown(KeyEvent.VK_P) && distanceToPlayer < proximityThreshold) {
            isSolid = false;
            player.isGrabbingCow = true;

            float playerMoveX = player.getPosX() - player.getPrevPosX();
            float playerMoveY = player.getPosY() - player.getPrevPosY();

            // Adjust cow's position based on player's movement
            this.posX += playerMoveX;
            this.posY += playerMoveY;

            // Handle cow going off the left side
            if (this.posX < 0) {
                this.posX += Renderer.gameWidth;
            }
            // Handle cow going off the right side
            else if (this.posX > Renderer.gameWidth) {
                this.posX -= Renderer.gameWidth;
            }
        } else {
            isSolid = true;
            player.isGrabbingCow = false;
        }
    }




    public void render(Graphics g) {
        g.drawImage(image, (int) (posX - width / 2), (int) (posY - height / 2), null);

        if(displayingWinMessage)   {
            g.setColor(Color.YELLOW);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            String message = "Level Completed!";
            g.drawString(message, 250, 100);
        }

        if (insideWinArea) {
            if (!displayingWinMessage) {
                // Display win message
                g.setColor(Color.YELLOW);
                g.setFont(new Font("Arial", Font.BOLD, 30));
                String message = "Level Completed!";
                g.drawString(message, 250, 100);

                // Start tracking display time
                winMessageDisplayStartTime = System.currentTimeMillis();
                displayingWinMessage = true;
            } else {
                // Check if 5 seconds have passed
                long currentTime = System.currentTimeMillis();
                if (currentTime - winMessageDisplayStartTime >= winMessageDisplayDuration) {
                    // Create a new world after the specified duration
                    Game.loadNextWorld();
                    displayingWinMessage = false; // Reset the flag
                }
            }
        }

        healthBar.render(g, 20);
    }



    private void getMilked() {
        for (Sprite sprite : World.currentWorld.sprites) {
            if (sprite instanceof Bucket) {
                Bucket bucket = (Bucket) sprite;

                // Check if the bucket is close to the cow
                if (Math.abs((bucket.posX ) - posX - sprite.width / 2) < 50 && Math.abs((bucket.posY) - posY - sprite.height / 2) < 50) {
                    // Set the milking start time only if not already being milked
                    if (!isBeingMilked) {
                        milkingStartTime = System.currentTimeMillis();

                        isIdle = 0;

                        // Change the image during milking
                        try {
                            if (currentDirection == 0) {
                                image = Renderer.loadImage("/Characters/Cow/Cow_Left_Moo.png");
                            } else {
                                image = Renderer.loadImage("/Characters/Cow/Cow_Right_Moo.png");
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        isBeingMilked = true;
                    }

                    // Play the milk sound if enough time has passed
                    if (System.currentTimeMillis() - lastPlayTime >= PLAY_INTERVAL) {
                        SoundPlayer.playMilkSound();
                        lastPlayTime = System.currentTimeMillis();
                    }

                    // Optionally, remove the bucket after the interaction
                    World.currentWorld.removeSprite(bucket);
                }
            }
        }

        // Reset the milking flag after 5 seconds
        if (isBeingMilked && System.currentTimeMillis() - milkingStartTime >= MILKING_DURATION) {
            isBeingMilked = false;
            isIdle = 1;

            // Revert to the default cow image after milking
            try {
                if (currentDirection == 0) {
                    image = Renderer.loadImage("/Characters/Cow/Cow_Left.png");
                } else {
                    image = Renderer.loadImage("/Characters/Cow/Cow_Right.png");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void idle(float deltaTime)   {
        oldSpace = currentSpace;

        // do collisions
        // ...

        // end collisions

        if (!isBeingMilked) {
            if (currentDirection == 0 && currentSpace > spaceMoveMin + 1) {
                velocity = 10;
                currentSpace -= 1;

                try {
                    image = Renderer.loadImage("/Characters/Cow/Cow_Left.png");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                currentDirection = 1;
                velocity = -10;
                currentSpace += 1;
                try {
                    image = Renderer.loadImage("/Characters/Cow/Cow_Right.png");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (currentDirection == 1 && currentSpace < spaceMoveMax - 1) {
                velocity = -10;
                currentSpace += 1;
            } else {
                currentDirection = 0;
                velocity = 10;
                currentSpace -= 1;
            }

            if (doesCollide(posX + velocity * deltaTime, posY)) {
                velocity -= velocity;
                timeElapsed -= 1;
                currentSpace = oldSpace;
            }

            posX -= velocity * deltaTime;
        }

    }

    public float getPosX()   {
        return posX;
    }

    public float getPosY()   {
        return posY;
    }

    public float getWidth()   {
        return width;
    }

    public float getHeight()   {
        return height;
    }

    public float getHealth()   {
        return cowHealth;
    }

    public float getMaxHealth()   {
        return maxHealth;
    }





}
