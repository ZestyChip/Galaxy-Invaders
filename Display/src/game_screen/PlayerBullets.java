package game_screen;

import java.awt.Graphics2D;
import java.util.ArrayList;
import player_bullets.PlayerWeaponType;
import player_bullets.SpaceshipTurret;
import timer.Timer;

public class PlayerBullets {
    private Timer timer;
    public ArrayList<PlayerWeaponType> bullets = new ArrayList<PlayerWeaponType>(); // arraylist for bullets
    
    public PlayerBullets()
    {
        timer = new Timer();
    }
    
    public void draw(Graphics2D g) // create bullets on screen based on however many are defined in the arraylist
    {
        for(int i = 0; i< bullets.size(); i++)
        {
            bullets.get(i).draw(g);
        }
    }
    
    public void update(double delta) // update bullets, translate them up, remove them if necessary 
    {
        for(int i = 0; i < bullets.size(); i++)
        {
            bullets.get(i).update(delta);
            if(bullets.get(i).destroyBullet())
            {
                bullets.remove(i);
            }
        }
    }
    
    public void bulletFire(double xPos, double yPos, int width,int height)
    {
       if(timer.timerEvent(300)) // every 300 ms allow creation of bullet in the same position of spaceship
       {
            bullets.add(new SpaceshipTurret(xPos + 22, yPos + 15, width, height));
       }
    }
}
