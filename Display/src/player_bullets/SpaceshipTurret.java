package player_bullets;

import display.Display;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class SpaceshipTurret extends PlayerWeaponType
{
    private Rectangle bullet; // rectangle for bullet
    private final double speed = 2.5d; // bullet speed
    
    public SpaceshipTurret(double xPos, double yPos, int width, int height)
    {
        this.setxPos(xPos);
        this.setyPos(yPos);
        this.setWidth(width);
        this.setHeight(height);
        
        this.bullet = new Rectangle((int)getxPos(),(int) getyPos(), getWidth(), getHeight()); // create bullet with specific dimensions and position to be shown on screen
    }
    
    @Override
    public void draw(Graphics2D g) // draw bullet, small green square
    {
        if(bullet == null)
        {
            return;
        }
        g.setColor(Color.GREEN);
        g.fill(bullet);
    }

    @Override
    public void update(double delta) // update bullets so they move up
    {
        if(bullet == null)
        {
            return;
        }
        this.setyPos(getyPos() - (delta * speed));
        bullet.y = (int) this.getyPos();
        isBulletOut();
    }
   
    @Override
    public boolean collisionRect(Rectangle rect) // check collision between bullet and rectangle entities
    {
        if(this.bullet == null)
        {
            return false;
        }
        if(bullet.intersects(rect))
        {
            this.bullet = null;
            return true;
        }
        
        return false;
    }

    @Override
    public boolean destroyBullet() // destroy bullet 
    {
        if(bullet == null)
        {
            return true;
        }
        return false;
    }

    @Override
    protected void isBulletOut() // check if bullet has gone off the screen
    {
        if (this.bullet == null)
        {
            return;
        }
        if(bullet.y < 0 || bullet.y > Display.HEIGHT || bullet.x < 0 || bullet.x > Display.WIDTH)
        {
            bullet = null;
        }
    }
    
}
