package levels;

import java.awt.Graphics2D;

public interface SuperLevel // interface used to easily create more levels in the future if needed
{

	void draw(Graphics2D g);
	void update(double delta);
	void hasDirectionChange(double delta);
	void changeDirectionAllEnemys(double delta);
	
	boolean isGameOver();
	boolean isComplete();
}