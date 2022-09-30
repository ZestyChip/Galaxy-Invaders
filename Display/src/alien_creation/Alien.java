package alien_creation;

import java.awt.Graphics2D;
import java.util.ArrayList;
import game_screen.Player;

public abstract class Alien // abstract class for future aliens(makes creating their classes easier)
{
    public abstract void draw(Graphics2D g);
    public abstract void update(double delta, Player player);
    public abstract void changeDirection(double delta);

    public abstract boolean deathScene();
    public abstract boolean collide(int i, Player player, ArrayList<Alien> enemies);
    public abstract boolean isOutOfBounds();
}

