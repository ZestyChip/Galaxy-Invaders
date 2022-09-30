package game_screen;

import display.Display;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import levels.Level1;
import state.StateMachine;
import state.SuperStateMachine;
import timer.TickTimer;

public class GameScreen extends SuperStateMachine
{
    private Player player;
    private Level1 level;
    
    // score set to 0
    public static int score = 0;
    
    //set different fonts
    private Font gameScreen = new Font("Arial", Font.PLAIN, 60);
    private Font pointScreen = new Font("Arial", Font.BOLD, 20);
    private TickTimer gameOverTimer = new TickTimer(180);
    
    public GameScreen(StateMachine stateMachine) // game is defined as a state in the program
    {
        super(stateMachine);
        player = new Player(Display.WIDTH/2 - 50, Display.HEIGHT-75, 50, 50); // player is placed in the game screen
        level = new Level1(player); // import the level and all its contents on the game screen
    }

    @Override
    public void update(double delta) throws InterruptedException
    {
        player.update(delta);
        level.update(delta);
        if(level.isComplete()) // end game if complete
        {
            gameOverTimer.tick(delta);
            if(gameOverTimer.isEventReady())
            {
                Thread.sleep(5000);
                System.exit(0);
            }
        }
        if(level.isGameOver()) // end game if gameover
        {
            gameOverTimer.tick(delta);
            if(gameOverTimer.isEventReady())
            {
                Thread.sleep(5000);
                System.exit(0);
            }
        }
    }

    @Override
    public void draw(Graphics2D g)
    {
        g.setColor(Color.YELLOW);
        g.setFont(pointScreen);
        g.drawString("Points: " + score, 15, 20);
        
        player.draw(g);
        level.draw(g);
        
        if(level.isComplete()) // Check for gameover and display if necessary
        {
            g.setColor(Color.RED);
            g.setFont(gameScreen);
            String gameComplete = "YOU WIN";
            int gameCompleteWidth = g.getFontMetrics().stringWidth(gameComplete);
            g.drawString(gameComplete, (Display.WIDTH/2)-(gameCompleteWidth/2), Display.HEIGHT/2);
        }
        if(level.isGameOver()) // Check for gamecomplete and display if necessary 
        {
            g.setColor(Color.RED);
            g.setFont(gameScreen);
            String gameOver = "GAME OVER!";
            int gameOverWidth = g.getFontMetrics().stringWidth(gameOver);
            g.drawString(gameOver, (Display.WIDTH/2)-(gameOverWidth/2), Display.HEIGHT/2);
        }
    }

    @Override
    public void init(Canvas canvas) // allow key inputs to influence the canvas
    {
        canvas.addKeyListener(player);
    }
}
