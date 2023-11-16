package org.object;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Bridge extends Sprite {
    private BufferedImage bridgeTexture;

    public Bridge(float posX, float posY, int width, int height) {
        super(posX, posY);
        this.width = width;
        this.height = height;
        isSolid = false;

        // Load the bridge texture image
        try {
            bridgeTexture = ImageIO.read(getClass().getResource("/Environment/Bridge/Bridge.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(float deltaTime) {
        // Update logic for the bridge, if needed
    }

    public void render(Graphics g) {
        if (bridgeTexture != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            TexturePaint texturePaint = new TexturePaint(
                    bridgeTexture, new Rectangle(0, 0, bridgeTexture.getWidth(), bridgeTexture.getHeight())
            );
            g2d.setPaint(texturePaint);
            g2d.fillRect((int) (posX - width / 2), (int) (posY - height / 2), (int) width, (int) height);
            g2d.dispose();
        } else {
            // If texture loading fails, draw a simple brown rectangle
            g.setColor(new Color(139, 69, 19)); // RGB values for brown
            g.fillRect((int) (posX - width / 2), (int) (posY - height / 2), (int) width, (int) height);
        }
    }
}
