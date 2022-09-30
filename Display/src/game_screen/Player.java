package game_screen;

import display.Display;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class Player implements KeyListener
{
    private final int speed = 5;
    
    private BufferedImage pSprite;
    private Rectangle Prect;
    private double xPos, yPos;
    private int width, height;
    protected int x, y, width2, height2;
        
    private boolean left = false, right = false, shoot = false; // right, left, shoot booleans
    
    public PlayerBullets playerBullets;
    
    public Player(double xPos, double yPos, int width, int height)
    {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        
        Prect = new Rectangle((int) xPos,(int) yPos, width, height); // create rectangle for player based on specific dimensions and position
        try{
            URL url = this.getClass().getResource("/Images/Laser_Cannon.png"); // import player image
            pSprite = ImageIO.read(url);
        }catch(IOException e){};
        
        playerBullets = new PlayerBullets();
    }
    
    public void draw(Graphics2D g)
    {
        g.drawImage(pSprite,(int) xPos,(int) yPos, width, height, null);
        playerBullets.draw(g);
    }
    
    public void update(double delta) // update the player rectangle based on key input and player speed
    {
        if(right && !left && xPos < Display.WIDTH-width)
        {
            xPos+= speed * delta;
            Prect.x = (int) xPos;
        }
        if(!right && left && xPos > 10)
        {
            xPos-= speed * delta;
            Prect.x = (int) xPos;
        }
        playerBullets.update(delta); // update bullet spawn location due to player being in different location
        
        if(shoot) // check for shoot boolean and create bullet where the player is standing 
        {
            playerBullets.bulletFire(xPos, yPos, 5, 5);
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) 
    {
        
    }

    @Override
    public void keyPressed(KeyEvent e) // method used to check for key press
    {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) // move right
        {
            right = true;
        }
        else if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) // move left
        {
            left = true;
        }
        
        if(key == KeyEvent.VK_SPACE) // enable shooting
        {
            shoot = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e)  // method used to check for key release
    {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) // stop moving right
        {
            right = false;
        }
        else if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) // stop moving left
        {
            left = false;
        }
        
        if(key == KeyEvent.VK_SPACE) // disable shooting
        {
            shoot = false;
        }
    }
    
    public Rectangle getRect()
    {
        return Prect;
    }
}
