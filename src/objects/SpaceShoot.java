package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import main.ChickenGame;

public class SpaceShoot extends Thread
{

	ChickenGame cGame;
	double x, y;
	SpaceShip t;
	Rectangle _colRec;

	int startX = 0, startY = 0;
	Image ShootImage;
	boolean onScreen;

	public SpaceShoot(ChickenGame c, double x, double y)
	{
		
		this.cGame = c;
		this.x = x;
		this.y = y;
		// SetShoot(x,y);
		ImageIcon img = new ImageIcon("greenBullet.png");
		ShootImage = img.getImage();
		_colRec = new Rectangle((int) x, (int) y, 32, 32);
		onScreen = true;
		start();

	}

	public void run()
	{

		while (onScreen)
		{
		
			_colRec.setLocation((int) x, (int) y);
			if (y > -5)
				y -= 10;
			else

				onScreen = false;

			//checkForCol();
			System.out.println("y=" + y);
			try
			{
				sleep(14);
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public synchronized void checkForCol()
	{
		/*
		 * for (Enemy e : ChickenGame._enemyList) { if (e.getX() - x <= 32 &&
		 * e.getY() - y <= 32) { onScreen = false;
		 * ChickenGame._enemyList.remove(e); } }
		 */

		for (int i = 0; i < ChickenGame._enemyList.size(); i++)
		{

			Enemy temp = ChickenGame._enemyList.get(i);
			if (temp.getX() - x <= 32 && temp.getY() - y <= 2)
			{
				onScreen = false;
				ChickenGame._enemyList.remove(i);
			}

		}
	}

	public void SetShoot(int x2, int y2)
	{

		this.x = x2 + 2;
		this.y = y2 + 2;

	}

	public void render(Graphics g)
	{
		if (onScreen){
			g.drawImage(ShootImage, (int) x, (int) y, 32, 32, null);

			 Graphics2D g2 = (Graphics2D) g;
			 g2.setColor(Color.BLUE);
			   //g2.draw(_colRec); 
		}
	}

	public void setOnScreen(boolean f)
	{
		onScreen=f;
	}
	public Rectangle getColRec()
	{
		return _colRec;
	}
	public void UpdateShoot()
	{
		y -= 10;
	} 

	public double getY()
	{
		return y;
	}

	public double getX()
	{
		// TODO Auto-generated method stub
		return x;
	}

}
