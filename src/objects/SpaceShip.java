package objects;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import main.ChickenGame;

public class SpaceShip extends Thread {
	
	public ChickenGame cGame;
	public int x, y;
	public int dx=0;
	public int dy=0;
	public int dirx=1, diry=-1;
	public final  int INC=2;
	public int startX=212, startY=418;
	public int ballDelay=4;
	public int numOfLives=3;
	public int wastedLives=0;
	public Image SpaceShipImage;
	public boolean isPaused= false;
	public boolean isAlive;
	private int counter;

	
	public SpaceShip(ChickenGame cGame)
	{
		this.x=startX;;
		this.y=startY;
		this.cGame=cGame;
		ImageIcon img =new ImageIcon("SpaceShip.png");		
		SpaceShipImage=img.getImage();
		_colRec = new Rectangle((int) x, (int) y, 64, 64);
		
		isAlive=true;
		start();
	}

	
	
	public int getX() {
		return x;
	}



	public void setX(int x) {
		this.x = x;
	}



	public int getY() {
		return y;
	}



	public void setY(int y) {
		this.y = y;
	}



	public void run()
	{
		while(isAlive)
		{
			
			System.out.println("x="+ x + "y" + y);
			handleMovement();
				
			
			CheckIsAlive();
			_colRec.setLocation((int) x, (int) y);
			
			
			if(isPaused)
			{
				synchronized (this)
				{
					try {
						wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}	
			}
			
			try {
				sleep(7);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cGame.repaint();
			
		}
		
	}
		
			int [] places = new int[2];
			private Rectangle _colRec;
	
	int getBallImageWidth()
	{
		return SpaceShipImage.getWidth(null);
	}
	
	int getBallImageHeight()
	{
		return SpaceShipImage.getHeight(null);
	}
	
	public void moveShipRL(int dx)
	{
		x +=dx ;
	}
	public void moveShipUD(int dy)
	{
		y +=dy ;
	}
	public int [] getPlace()
	{
		
		places[0]=x;
		places[1]=y;
	return places;
	}
	
	
	public void drawBall(Graphics g)
	{
		g.drawImage(SpaceShipImage, x,y,96,96, null);
		g.setColor(Color.red);
		g.drawLine(x, y, x, y+15);
	}


	public void setPlace(int[] p) {
		// TODO Auto-generated method stub
		x=p[0];
		y=p[1];
	}

	
	public void setPlacep(int[] p) {
		
		x=p[0];
		y=p[1];
	
		
			x+=15;
			y-=15;
			
		
	}
	
	
	public boolean collidedWith(Rectangle rec)
	{
		
		return _colRec.intersects(rec);
	}
	
	public void setIsAlive(boolean flag)
	{
		isAlive=flag;
	}



	public void CheckIsAlive()
	{
		if(ChickenGame.LIVES==0)
		{
			isAlive=false;
		}
	}
	public void handleMovement()
	{
		
		if(x<0&& dx<0)
		{
			
			
			dx=0;
		}
		else
			if(1500<x && dx>0)
			{
				
				dx=0;
			}
		
		
		
		if(y<0&& dy<0)
		{
			
			
			dy=0;
		}
		else
			if(742<y && dy>0)
			{
				
				dy=0;
			}
				
		
		
		
		
		
		x+=dx;
		y+=dy;
	}



	
	
	

}
