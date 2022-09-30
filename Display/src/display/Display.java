package display;

import java.awt.*;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
import state.StateMachine;

public class Display extends Canvas implements Runnable
{

    public static void main(String[] args) 
    {
        Display display = new Display(); // create JFrame, name it and set its properties
        JFrame frame = new JFrame();
        frame.add(display);
        frame.pack();
        frame.setTitle("Galaga Invaders");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        display.start();
    }
    
    private boolean running = false;
    private Thread thread;
    
    public synchronized void start() // method used to identify that the game is running and initiate the jframe screen
    {
        if(running)
            return;
        
        running = true;
        
        thread = new Thread(this);
        thread.start();
    }
    
    public synchronized void stop() // method used to identify that the game is not running and close the jframe screen
    {
        if(!running)
            return;
        
        running = false;
        
        try
        {
            thread.join();
        } catch (InterruptedException e) {e.printStackTrace();}
    }
    
    public static int WIDTH = 800, HEIGHT = 600; // set width and height of screen
    public int fps;
    
    public static StateMachine state;
    
    public Display() // 
    {
        this.setSize(WIDTH, HEIGHT);
        this.setFocusable(true);
        
        state = new StateMachine(this);
        state.setState((byte) 0);
    }
    
    @Override
    public void run() // calculations to set game fps to 60 to eliminate flickering 
    {
        long timer = System.currentTimeMillis();
        long lastLoopTime = System.nanoTime();
        final int TARGET_FPS = 60;
        final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
        int frames = 0;
        
        this.createBufferStrategy(3);
        BufferStrategy buffstrat = this.getBufferStrategy();
        while(running)
        {
            long now = System.nanoTime();
            long updateLength = now - lastLoopTime;
            lastLoopTime = now;
            double delta = updateLength / ((double) OPTIMAL_TIME);
            
            frames++;
            
            if(System.currentTimeMillis() - timer > 1000)
            {
                timer += 1000;
                fps = frames;
                frames = 0;
                System.out.println(fps);
            }
            
            draw(buffstrat);
            try {
                update(delta);
            } catch (InterruptedException ex) {}
            
            try
            {
                Thread.sleep(((lastLoopTime - System.nanoTime() + OPTIMAL_TIME) / 1000000));
            }catch(Exception e){};
        }
    }
    
    public void draw(BufferStrategy buffstrat) // draw the black background
    {
        do{
            do{
                Graphics2D g = (Graphics2D) buffstrat.getDrawGraphics();
                g.setColor(Color.BLACK);
                g.fillRect(0,0, WIDTH + 50, HEIGHT + 50);
                
                state.draw(g);
                
                g.dispose();
            }while (buffstrat.contentsRestored());
            buffstrat.show();
        }while (buffstrat.contentsLost());
    }
    
    public void update(double delta) throws InterruptedException
    {
        state.update(delta);
    }
}