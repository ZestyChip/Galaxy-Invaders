package timer;

public class Timer // class used to calculate elapsed time
{

    private long prevTime;

    public Timer() 
    {
        prevTime = System.currentTimeMillis();

    }
    
    public long getPrevTime() 
    {
        return prevTime;
    }

    public void setCurrentTime(long currentTime) 
    {
        this.prevTime = currentTime;
    }
    
    public void resetTimer()
    {
        prevTime = System.currentTimeMillis();
    }
    
    public boolean timerEvent(int timer)
    {
        if(System.currentTimeMillis() - getPrevTime() > timer)
        {
            resetTimer();
            return true;
        }
        
        return false;
    }
    
    public boolean isTimerReady(int timer)
    {
        if(System.currentTimeMillis() - getPrevTime() > timer)
        {
            return true;
        }
        
        return false;
    }
}