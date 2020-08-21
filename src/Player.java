import java.awt.Color;
import java.awt.Graphics;

public class Player 
{
	public boolean right,left;
	public int x, y;
	public int width, height;
	public double velocity = 2;
	
	public Player (int x, int y)
	{
		this.x = x;
		this.y = y;
		this.width = 40;
		this.height = 4;
	}
	
	public void tick()
	{
		if(right)
		{
			x += velocity;
		}
		else if (left)
		{
			x -= velocity;
		}
		if(x + width > Game.LARGURA)
		{
			x = Game.LARGURA - width;
		}
		else if(x < 0)
		{
			x = 0;
		}
	}
	
	public void render(Graphics g)
	{
		g.setColor(Color.green);
		g.fillRect(x, y, (int)width, (int)height);
	}

	public void restart() 
	{
		x = 100;
		
	}

}
