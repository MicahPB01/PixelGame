// HealthBar.java
package org.object;

import java.awt.*;

public class HealthBar {

    private Damageable damageable;
    private int width;
    private int height;
    private Color backgroundColor;
    private Color foregroundColor;

    public HealthBar(Damageable damageable, int width, int height, Color backgroundColor, Color foregroundColor) {
        this.damageable = damageable;
        this.width = width;
        this.height = height;
        this.backgroundColor = backgroundColor;
        this.foregroundColor = foregroundColor;
    }

    public void render(Graphics g, int yOffset) {
        float healthPercentage = (float) damageable.getHealth() / damageable.getMaxHealth();
        int barWidth = (int) (width * healthPercentage);

        // Draw background
        g.setColor(backgroundColor);
        g.fillRect((int) damageable.getPosX() - width / 2, (int) damageable.getPosY() - height / 2 - yOffset, width, height);

        // Draw foreground
        g.setColor(foregroundColor);
        g.fillRect((int) damageable.getPosX() - width / 2, (int) damageable.getPosY() - height / 2 - yOffset, barWidth, height);
    }
}
