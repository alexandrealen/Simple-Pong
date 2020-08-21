import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball 
{
	public int width, height, pontuacao, pontuacaomari;
	public boolean perdeu, ganhou;
	
	
	public double dx, dy, x, y, speed=1.8, speeddy = 3;
	
	public Ball(int x, int y)
	{
		this.x = x;
		this.y = y;
		this.width = 3;
		this.height = 3;
		
		int angle = new Random().nextInt(320) + 45;
        dx = Math.cos(Math.toRadians(angle));
        dy = 0.7;
	}
	
	public void tick()
	{
		if(x+(dx*speed)+width >= Game.LARGURA)
		{
			dx *= -1;
		}
		else if (x + (dx*speed) < 0)
		{
			dx *= -1;
		}
		
		Rectangle bounds = new Rectangle((int)(x + (dx*speed)), (int)( y + (dy*speed)), width, height);
		Rectangle boundsPlayer = new Rectangle(Game.player.x, Game.player.y, Game.player.width, Game.player.height);
		Rectangle boundsEnemy = new Rectangle((int)Game.enemy.x, (int)Game.enemy.y, Game.enemy.width, Game.enemy.height);
		
		if(bounds.intersects(boundsPlayer))
		{
			dy *= -1;
			if(dx < 0.3 && dx > 0)
			{
				dx++;
			}
			else if(dx > -0.3 && dx < 0)
			{
				dx--;
			}
		}
		else if(bounds.intersects(boundsEnemy))
		{
			dy *= -1;
		}
		
		
		x += dx * speed;
		y += dy * speeddy;
		
		if(y > Game.ALTURA) 
		{
			perdeu = true;
		}
		if(y < 0)
		{
			ganhou = true;
		}
		
	}
	
	public void render(Graphics g)
	{
		g.setColor(Color.white);
		g.fillRect((int)x, (int)y, width, height);
	}

	public void restart() 
	{
		if(ganhou)
		{
			pontuacao += 1;
		}
		else if (perdeu)
		{
			pontuacaomari += 1;
		}
		ganhou = false;
		perdeu = false;
		int angle = new Random().nextInt(320) + 45;
		dx = Math.cos(Math.toRadians(angle));
        dy = 0.7;
        x = 120;
        y= 60;
        
	}
}
