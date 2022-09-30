package player_bullets;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public abstract class PlayerWeaponType // type of abstract class used to easily create more spaceship turrets if needed
{
    protected double xPos, yPos;
    protected int width, height;
    
    public abstract void draw(Graphics2D g);
    public abstract void update(double delta);
    public abstract boolean collisionRect(Rectangle rect);
    public abstract boolean destroyBullet();
    
    protected abstract void isBulletOut();
    
    public double getxPos() {
        return xPos;
    }

    public void setxPos(double xPos) {
        this.xPos = xPos;
    }

    public double getyPos() {
        return yPos;
    }

    public void setyPos(double yPos) {
        this.yPos = yPos;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
