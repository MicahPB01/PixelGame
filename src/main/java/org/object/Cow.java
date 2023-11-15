package org.object;

import org.gameSounds.SoundPlayer;
import org.graphics.Renderer;
import org.object.Sprite;
import org.object.Mob;
import org.world.World;

import java.awt.*;
import java.io.IOException;

public class Cow extends Mob{

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


    public Cow(float posX, float posY) {
        super(posX, posY);

        width = 38;
        height = 25;
        horizontalSpeed = 0.1f;
        verticalSpeed = 1;


        try {
            image = Renderer.loadImage("/Characters/Cow/Cow_Left.png");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void update(float deltaTime) {
        getMilked();

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

        // Reset the milking flag after 5 seconds
        if (isBeingMilked && System.currentTimeMillis() - milkingStartTime >= MILKING_DURATION) {
            isBeingMilked = false;

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

        // Other update logic for the cow
        // ...

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






    public void render (Graphics g)   {

        g.drawImage(image, (int) (posX - width / 2), (int) (posY - height / 2), null);

    }


    private void getMilked() {
        for (Sprite sprite : World.currentWorld.sprites) {
            if (sprite instanceof Bucket) {
                Bucket bucket = (Bucket) sprite;

                // Check if the bucket is close to the cow
                if (Math.abs(bucket.posX - posX) < 30 && Math.abs(bucket.posY - posY) < 30) {
                    // Set the milking start time only if not already being milked
                    if (!isBeingMilked) {
                        milkingStartTime = System.currentTimeMillis();

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

}
