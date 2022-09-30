package display;

import java.awt.BorderLayout;
import javax.swing.JApplet;
/* a simple class(extends JApplet) used to create the 
** border layout of the screen which
** works hand in hand with the jframe
*/
public class Display_Applet extends JApplet 
{                                             
    private static final long serialVersionUID = 1L;
    private Display display = new Display();
    
    public void initialize()
    {
        setLayout(new BorderLayout());
        add(display);
    }
    
    public void start()
    {
        display.start();
    }

    public void stop()
    {
        display.stop();
    }
}