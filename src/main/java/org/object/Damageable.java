// Damageable.java
package org.object;

public interface Damageable {
    float getHealth();

    void takeDamage(int damage);

    float getMaxHealth();
    float getPosX();
    float getPosY();
}
