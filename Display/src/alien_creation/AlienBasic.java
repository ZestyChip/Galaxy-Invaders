package alien_creation;

import display.Display;
import game_screen.GameScreen;
import game_screen.Player;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import sprite.SpriteAnimate;

public class AlienBasic extends Alien // class for alien creation
{
    private double speed = 1.5d; // alien speed
    private Rectangle rect; // rectangle used for alien hitbox
    private SpriteAnimate alienSprite; // sprite used to define aliens
    public static boolean hitBottom = false; // boolean condition to check for alien collision with bottom
    
    public AlienBasic(double xPos, double yPos, int rows, int columns)
    {
	alienSprite = new SpriteAnimate(xPos, yPos, rows, columns, 300, "/Images/Aliens.png"); // import alien image
        alienSprite.setWidth(25);
        alienSprite.setHeight(25);
        alienSprite.setLimit(2);
        
        this.setRect(new Rectangle((int) alienSprite.getxPos(), (int) alienSprite.getyPos(), alienSprite.getWidth(), alienSprite.getHeight())); // create alien hitbox with dimensions and position
        alienSprite.setLoop(true);
    }
	
    @Override
    public void draw(Graphics2D g) // draw the aliens
    {
	alienSprite.draw(g);
    }

    @Override
    public void update(double delta, Player player) // update the aliens
    {
	alienSprite.update(delta);
		
	alienSprite.setxPos(alienSprite.getxPos() - (delta * speed));
	this.getRect().x = (int) alienSprite.getxPos();
    }

    @Override
    public void changeDirection(double delta) 
    {
	speed *= -1.05d;
	alienSprite.setxPos(alienSprite.getxPos() - (delta * speed)); // change alien speed to negative to make them go side to side
	this.getRect().x = (int) alienSprite.getxPos();
		
	alienSprite.setyPos(alienSprite.getyPos() + (delta * 30)); // bring aliens down
	this.getRect().y = (int) alienSprite.getyPos();
    }

    @Override
    public boolean deathScene() // death of aliens method
    {
        if(!alienSprite.isPlay())
            return false;
        if(alienSprite.isSpriteAnimDestroyed())  // check for sprite destruction
        {
            return true;
	}
        return false;
    }

    @Override
    public boolean collide(int i, Player player, ArrayList<Alien> enemies)
    {
        if(alienSprite.isPlay()) 
        {
            if(enemies.get(i).deathScene()) // check if deathscene is true
            {
		enemies.remove(i); // remove that alien
            }
            return false;
	}
        for(int w = 0; w < player.playerBullets.bullets.size(); w++)
        {
            if(enemies != null && player.playerBullets.bullets.get(w).collisionRect(((AlienBasic) enemies.get(i)).getRect())) // check for collision between bullet and alien
            {
                alienSprite.resetLimit();
                alienSprite.setAnimateSpeed(120);
                alienSprite.setPlay(true, true); // 
                GameScreen.score += 10; // give 10 score per alien killed
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isOutOfBounds() 
    {
	if(rect.x > 0 && rect.x < Display.WIDTH - rect.width)
        {
   
            if(alienSprite.getyPos() > 520)
            {
                //System.out.println(alienSprite.getyPos());
                //System.out.println("out");
                hitBottom = true;
            }
            return false;
        }
        return true;
    }

    public Rectangle getRect() 
    {
	return rect;
    }

    public void setRect(Rectangle rect) 
    {
        this.rect = rect;
    }
}
