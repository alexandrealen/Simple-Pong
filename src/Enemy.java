import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Enemy 
{
	public double x,y;
	public int width, height;
	public double gerador;
	
	public Enemy(int x, int y)
	{
		this.x = x;
		this.y = y;
		this.width = 40;
		this.height = 4;
				
	}
	
	
	public void tick()
	{
		gerador = new Random().nextInt(50) + 1/ 1  + new Random().nextInt(10) ;
		x += (Game.ball.x - x - 20) * (1/gerador);
		
		if(x > 200)
		{
			x = 200;
		}
		else if(x < 0)
		{
			x = 0;
		}
	}
	
	public void render(Graphics g)
	{
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, width, height);
	}


	public void restart() 
	{
		x = 100;
	}
	
	
	
	
}
