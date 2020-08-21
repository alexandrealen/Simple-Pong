import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener
{
	private static final long serialVersionUID = 1L;
	public static int SCALE = 3;
	public static int LARGURA = 240;
	public static int ALTURA = 120;
	public static Player player;
	public static Enemy enemy;
	public static Ball ball;
	public boolean restart;
	public BufferedImage layer = new BufferedImage(LARGURA, ALTURA, BufferedImage.TYPE_INT_RGB);
	
	public Game()
	{
		this.setPreferredSize(new Dimension(LARGURA*SCALE,ALTURA*SCALE));
		this.addKeyListener(this);
		player = new Player(100, ALTURA-4);
		enemy = new Enemy(100, 0);
		ball = new Ball(120, 60);
		JFrame  frame = new JFrame("Pong");
		frame.add(this);
		frame.addKeyListener(this);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.pack();	
		frame.setLocationRelativeTo(null);
	}
	public static void main(String[] args)
	{
		Game game = new Game();
		new Thread(game).start();
	}
	
	public void tick()
	{
		player.tick();
		enemy.tick();
		ball.tick();
		if(restart)
		{
			restart();
		}
	}
	
	public void render()
	{
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null)
		{
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = layer.getGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, LARGURA, ALTURA);
		player.render(g);
		enemy.render(g);
		ball.render(g);
		
		g.setFont(new Font("Arial",Font.BOLD,10));
        g.setColor(new Color(255,255,255));
        g.drawString("CPU score: " + ball.pontuacao,0,10);
        
        g.setFont(new Font("Arial",Font.BOLD,10));
        g.setColor(new Color(255,255,255));
        g.drawString("Your score " + ball.pontuacaomari,170,115);
		
		if(ball.ganhou && restart == false) 
        {
            g.setFont(new Font("Arial",Font.BOLD,10));
            g.setColor(new Color(0,0,250));
            g.drawString("Win, press space to try again",50,60);
        }
		
		
		else if(ball.perdeu && restart == false) 
        {
            g.setFont(new Font("Arial",Font.BOLD,10));
            g.setColor(new Color(250,120, 120));
            g.drawString("Lose, Press space to try again",50,60);
        }
		
		g = bs.getDrawGraphics();
		g.drawImage(layer, 0, 0, LARGURA*SCALE, ALTURA*SCALE, null);
		
		bs.show();
	}
	@Override
	public void run() 
	{
		while(true)
		{
			tick();
			render();
			try 
			{
				Thread.sleep(1000/60);
			}
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}

	}
	@Override
	public void keyTyped(KeyEvent e) 
	{
		
	}
	@Override
	public void keyPressed(KeyEvent e) 
    {
            if(e.getKeyCode() == KeyEvent.VK_D) 
            {
                player.right = true;
            }
            if(e.getKeyCode()  == KeyEvent.VK_A) 
            {
                player.left = true;
            }
            if(e.getKeyCode()  == KeyEvent.VK_SPACE && ball.ganhou || ball.perdeu)
 	       {
 	    	   restart = true;
 	       }

    }
	@Override
	public void keyReleased(KeyEvent e) 
	{
	       if(e.getKeyCode() == KeyEvent.VK_D) 
	       {
	            player.right = false;
	       }
	       if(e.getKeyCode()  == KeyEvent.VK_A) 
	       {
	            player.left = false;
	       }
	       if(e.getKeyCode()  == KeyEvent.VK_SPACE)
	       {
	    	   restart = false;
	       }
	}
	
	public void restart()
	{
		player.restart(); 
		enemy.restart();
		ball.restart();
		
	}
}
