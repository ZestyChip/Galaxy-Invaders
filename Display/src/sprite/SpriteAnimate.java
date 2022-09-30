package sprite;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import timer.Timer;

public class SpriteAnimate // class used for animation of most sprites
{
    private ArrayList<BufferedImage> sprites = new ArrayList <BufferedImage>(); // sprite arraylist
    private byte currentSprite;
    
    private boolean loop = false;
    private boolean play = false;
    private boolean destroyAfterAnim = false;
    
    private Timer timer;
    private int animateSpeed;
    private double xPos, yPos;
    private int width, height;
    private int limit;
    
    public SpriteAnimate(double xPos, double yPos, int rows, int columns, int animateSpeed, String imgPath)
    {
        this.animateSpeed = animateSpeed;
        this.xPos = xPos;
        this.yPos = yPos;
        
        try{
            URL url = this.getClass().getResource(imgPath);
            BufferedImage pSprite = ImageIO.read(url);
            int spriteWidth = pSprite.getWidth() / columns;
            int spriteHeight = pSprite.getHeight() / rows;
            for(int y = 0; y < rows; y++)
            {
                for(int x = 0; x < columns; x++)
                {
                    addSprite(pSprite, 0 + (x * spriteWidth), 0 + (y * spriteHeight), spriteWidth, spriteHeight);
                }
            }
        }catch(IOException e){};
        
        timer = new Timer();
        limit = sprites.size() - 1; // minus 1 because size doesn't inclulde 0 as an index
    }
    
    public void draw(Graphics2D g)
    {
        if(isSpriteAnimDestroyed())
        {
            return;
        }
        g.drawImage(sprites.get(currentSprite), (int) getxPos(), (int) getyPos(), width, height, null); // draw any sprite with specific dimensions and location
    }
    
    public void update(double delta) // update all sprites and either play them or loop them
    {
        if(isSpriteAnimDestroyed())
        {
            return;
        }
        if(loop && !play)
        {
            loopAnimation();
        }
        if (play && !loop)
        {
            playAnimation();
        }
    }
    
    public void stopAnimate()
    {
        loop = false;
        play = false;
    }
    
    public void resetSprite()
    {
        loop = false;
        play = false;
        currentSprite = 0;
    }
    
    private void loopAnimation()
    {
        if(timer.isTimerReady(animateSpeed) && currentSprite == limit)
        {
            currentSprite = 0;
            timer.resetTimer();
        }
        else if(timer.timerEvent(animateSpeed) && currentSprite != limit)
        {
            currentSprite++;
        }
    }
    
    private void playAnimation() // method used to animate the sprites based on an animation speed linked to the fps and timer of the game to not flicker
    {
        if (timer.isTimerReady(animateSpeed) && currentSprite != limit && !isDestroyAfterAnim()) 
        {
            play = false;
            currentSprite = 0;
	} 
        else if (timer.isTimerReady(animateSpeed) && currentSprite == limit && isDestroyAfterAnim()) 
        {
            sprites = null;
	}
        else if (timer.timerEvent(animateSpeed) && currentSprite != limit) 
        {
            currentSprite++;
	}
    }

    public byte getCurrentSprite() {
        return currentSprite;
    }

    public void setCurrentSprite(byte currentSprite) {
        this.currentSprite = currentSprite;
    }

    public boolean isLoop() {
        return loop;
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }
    
    public boolean isSpriteAnimDestroyed()
    {
        if (sprites == null)
        {
            return true;
        }
        return false;
    }
    
    public void addSprite(BufferedImage spriteMap, int xPos, int yPos, int width, int height) // method to add any sprite specified
    {
        sprites.add(spriteMap.getSubimage(xPos, yPos, width, height));
    }
    
    public void setPlay(boolean play, boolean destroyAfterAnim)
    {
        if(loop) // start and stop the play boolean
        {
            loop = false;
        }
        
        this.play = play;
        this.setDestroyAfterAnim(destroyAfterAnim);
    }
    
    // anything past here is just methods to get and set different variables throughout the program based on where the sprite is
    public double getxPos()
    {
        return xPos;
    }
    
    public void setxPos(double xPos)
    {
        this.xPos = xPos;
    }
    
    public double getyPos()
    {
        return yPos;
    }
    
    public void setyPos(double yPos)
    {
        this.yPos = yPos;
    }
    
    public boolean isDestroyAfterAnim()
    {
        return destroyAfterAnim;
    }
    
    public void setDestroyAfterAnim(boolean destroyAfterAnim)
    {
        this.destroyAfterAnim = destroyAfterAnim;
    }

    public int getWidth() 
    {
        return width;
    }

    public void setWidth(int width) 
    {
        this.width = width;
    }

    public int getHeight() 
    {
        return height;
    }

    public void setHeight(int height) 
    {
        this.height = height;
    }

    public int getAnimateSpeed() 
    {
        return animateSpeed;
    }

    public void setAnimateSpeed(int animateSpeed) 
    {
        this.animateSpeed = animateSpeed;
    }

    public int getLimit() 
    {
        return limit;
    }

    public void setLimit(int limit) 
    {
        if(limit > 0) // Get our zero index again
        {
            this.limit = limit - 1;
        }
        else
        {
            this.limit = limit;
        }
    }
    
    public void resetLimit()
    {
        limit = sprites.size() - 1;
    }
    
    public boolean isPlay() 
    {
        return play;
    }
}