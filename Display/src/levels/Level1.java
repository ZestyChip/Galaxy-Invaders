package levels;

import alien_creation.Alien;
import alien_creation.AlienBasic;
import game_screen.Player;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Level1 implements SuperLevel{

    private Player player;
    
    public static ArrayList<Alien> enemies = new ArrayList<Alien>(); // alien arraylist
	
    public Level1(Player player)
    {
        this.player = player;
        for(int y = 0; y < 5; y++) // 5 rows
        {
            for(int x = 0 ; x < 10; x++) // 10 columns
            {
                Alien e = new AlienBasic(150 + (x * 50), 25 + (y * 40), 1, 3);
                enemies.add(e); // add all enemies to arraylist
            }
        }
    }
	
    @Override
    public void draw(Graphics2D g) 
    {
	for(int i = 0; i < enemies.size(); i++)
        {
            enemies.get(i).draw(g); // draw all enemies on screen
        }
    }

    @Override
    public void update(double delta) 
    {
	if(enemies == null)
        {
            return;
        }
	for(int i = 0; i < enemies.size(); i++)
        {
            enemies.get(i).update(delta, player); // constantly update enemies
	}
	for(int i = 0; i < enemies.size(); i++)
        {
            enemies.get(i).collide(i, player, enemies); // constantly check for collision
	}
	hasDirectionChange(delta); // constantly check to change direction
    }

    @Override
    public void hasDirectionChange(double delta) // method to check if alien is out of bounds in order to change direction
    {
        for(int i = 0; i < enemies.size(); i++)
        {
            if(enemies.get(i).isOutOfBounds())
            {
                changeDirectionAllEnemys(delta);
            }
	}
    }

    @Override
    public void changeDirectionAllEnemys(double delta) // method used to change direction of every alien remaining arraylist
    {
	for(int i = 0; i < enemies.size(); i++)
        {
            enemies.get(i).changeDirection(delta);
	}
    }
    
    @Override
    public boolean isGameOver() // check for level loss
    {
        if(AlienBasic.hitBottom) // check for alien hitting bottom
        {
            return true;
        }
        return false;
    }
    
    @Override
    public boolean isComplete()  // check for level completion
    {
        if(enemies.isEmpty()) // check for empty enemy arraylist
        {
            return true;
        }
        return false;
    }
}