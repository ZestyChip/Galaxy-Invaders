package state;

import game_screen.GameScreen;
import java.awt.Canvas;
import java.awt.Graphics2D;
import java.util.ArrayList;
import menu.MainMenu;

public class StateMachine 
{
    
    private ArrayList<SuperStateMachine> states = new ArrayList<SuperStateMachine>(); // araylist for the different states at which the program may be in
    private Canvas canvas; // canvas used to draw on
    private byte selectState = 0;
    
    public StateMachine(Canvas canvas)
    {
        SuperStateMachine game = new GameScreen(this);
        SuperStateMachine menu = new MainMenu(this);
        states.add(menu); // adds menu as the first state
        states.add(game); // game is second state
        
        this.canvas = canvas;
    }
    
    public void draw(Graphics2D g)
    {
        states.get(selectState).draw(g); // draws the states
    }
    
    public void update(double delta) throws InterruptedException
    {
        states.get(selectState).update(delta); // updates the current state
    }
    
    public void setState(byte i)
    {
        for(int r = 0; r < canvas.getKeyListeners().length; r++)
        {
            canvas.removeKeyListener(canvas.getKeyListeners()[r]);
        }
        selectState = i;
        states.get(selectState).init(canvas);
    }
    
    public byte getStates()
    {
        return selectState;
    }
}
