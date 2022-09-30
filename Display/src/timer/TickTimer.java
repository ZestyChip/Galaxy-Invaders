package timer;

public class TickTimer 
{
    private float tick, tickTarget;
    
    public TickTimer(float tickTarget)
    {
        this.tickTarget = tickTarget;
        this.tick = 0;
    }
    
    public void tick(double delta)
    {
        if (tick <= tickTarget) // increment tick by 1 times the delta(the last frame)
        {
            tick += 1 * delta;
        }
    }
    
    public boolean isEventReady()
    {
        if(tick >= tickTarget)
        {
            return true;
        }
        return false;
    }
    
    public void resetTimer()
    {
        tick = 0;
    }
}
