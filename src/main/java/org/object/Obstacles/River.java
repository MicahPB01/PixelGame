package org.object.Obstacles;

import org.object.Sprite;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class River extends Sprite {
    private Polygon polygon;
    private ArrayList<BufferedImage> waterFrames;
    private int currentFrameIndex;
    private long lastFrameTime;
    private long frameDuration = 200;  // Adjust the duration (in milliseconds) between frames

    public River(float posX, float posY, int[] xPoints, int[] yPoints, int numPoints) {
        super(posX, posY);
        polygon = new Polygon(xPoints, yPoints, numPoints);
        loadWaterFrames();
        currentFrameIndex = 0;
        lastFrameTime = System.currentTimeMillis();

    }

    private void loadWaterFrames() {
        waterFrames = new ArrayList<>();

        try {
            // Load your water images (replace "water_frame_1.png", etc., with your file paths)
            BufferedImage frame1 = ImageIO.read(getClass().getResource("/Environment/Water/Water_1.png"));
            BufferedImage frame2 = ImageIO.read(getClass().getResource("/Environment/Water/Water_2.png"));
            BufferedImage frame3 = ImageIO.read(getClass().getResource("/Environment/Water/Water_3.png"));
            BufferedImage frame4 = ImageIO.read(getClass().getResource("/Environment/Water/Water_4.png"));

            // Add frames to the list
            waterFrames.add(frame1);
            waterFrames.add(frame2);
            waterFrames.add(frame3);
            waterFrames.add(frame4);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateFrame() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastFrameTime >= frameDuration) {
            currentFrameIndex = (currentFrameIndex + 1) % waterFrames.size();
            lastFrameTime = currentTime;
        }
    }

    public void update(float deltaTime) {
        updateFrame();
        // Other update logic for the river, if needed
    }

    public void render(Graphics g) {
        BufferedImage currentFrame = waterFrames.get(currentFrameIndex);

        if (currentFrame != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            TexturePaint texturePaint = new TexturePaint(
                    currentFrame, new Rectangle(0, 0, currentFrame.getWidth(), currentFrame.getHeight())
            );
            g2d.setPaint(texturePaint);
            g2d.fill(polygon);
            g2d.dispose();
        } else {
            // If frame loading fails, draw a simple blue polygon
            g.setColor(Color.GREEN);
            g.drawPolygon(polygon);
        }
    }

    public Polygon getPolygon() {
        return polygon;
    }
}
