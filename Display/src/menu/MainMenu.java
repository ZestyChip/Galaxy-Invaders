package menu;

import display.Display;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import state.StateMachine;
import state.SuperStateMachine;

public class MainMenu extends SuperStateMachine implements KeyListener // main menu class
{

    private Font titleFont = new Font("Arial", Font.PLAIN, 70); // Title font
    private Font gameStartFont = new Font("Arial", Font.PLAIN, 30); // Press enter font
    private String title = "Galaxy Invaders";
    private String gameStart = "Press ENTER to play";
    
    public MainMenu(StateMachine stateMachine) // main menu is created into one of the states of the program
    {
        super(stateMachine);
    }
    
    @Override
    public void update(double delta) throws InterruptedException
    {
        
    }

    @Override
    public void draw(Graphics2D g) // Draw the main menu title screen
    {
        g.setFont(titleFont);
        int titleWidth = g.getFontMetrics().stringWidth(title);
        g.setColor(Color.ORANGE);
        g.drawString(title, (Display.WIDTH/2) - (titleWidth/2), (Display.HEIGHT/2) - 150);
        
        g.setFont(gameStartFont);
        g.setColor(Color.WHITE);
        int gameStartWidth = g.getFontMetrics().stringWidth(gameStart);
        g.drawString(gameStart, (Display.WIDTH/2) - (gameStartWidth/2), (Display.HEIGHT/2) + 25);
    }

    @Override
    public void init(Canvas canvas) // Allow key input to influence the canvas
    {
        canvas.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) 
    {
        
    }

    @Override
    public void keyPressed(KeyEvent e) // Check for Enter key
    {
        if(e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            getStateMachine().setState((byte) 1);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) 
    {
        
    }
    
}
