package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;

import main.ChickenGame;

public class Enemy extends Thread {
	int second = 0;
	Image EnemyShoot;
	ChickenGame cGame;
	double x, y;
	int temp;
	public Rectangle _colRec;
	Random rnd = new Random();
	boolean goRight = true;

	int startX = 200, startY = 200;
	Image Enemyimg;
	Image t;
	boolean isAlive;

	Timer t3 = new Timer();

	public Enemy(ChickenGame c, double x, double y) {
		this.cGame = c;
		ImageIcon img2 = new ImageIcon("egg.jpg");
		EnemyShoot = img2.getImage();
		this.x = x;
		this.y = y;
		temp = (int) y;

		// ImageIcon img = new ImageIcon("Chicken.png");
		ImageIcon img = new ImageIcon("chick.png");
		Enemyimg = img.getImage();
		_colRec = new Rectangle((int) x, (int) y, 64, 64);

		isAlive = true;
		start();

	}

	public Enemy(ChickenGame cGame) {
		this.x = startX;
		_colRec = new Rectangle((int) x, (int) y, 64, 64);
		this.y = startY;
		this.cGame = cGame;

		isAlive = true;

		start();
	}

	public void run() {

		while (isAlive) {
			//
			_colRec.setLocation((int) this.x, (int) this.y);
//			int step;
//			step=(int)x;
////			
////			
//			if (x<=1500 && goRight) {
//			move(2);
//			_colRec.setLocation((int) x, (int) y);
//			System.out.println("x equal to:"+ x);
//			if(x==1000)
//		{
//				move(-2);
//			  goRight=false;
//			 break;
//			}
//			
//		}
			
			
		
//		if (x<1400 &&!goRight) {
//
//			
//			_colRec.setLocation((int) x, (int) y);
//			System.out.println("this is x:"+x);
//			if(x==0)
//				goRight=true;
//		}
			
		

			
			

			

			try {
				sleep(31);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cGame.repaint();
		}
			// collidedWithSpaceShip();

		}

	

	public void setIsAlive(boolean flag) {
		isAlive = flag;
	}

	public boolean collidedWith(Rectangle rec) {
		return _colRec.intersects(rec);
	}

	public void collidedWithSpaceShip() {

		// cGame.c.print();
		// if (cGame.c.getShotsList() != null && !cGame.c.getShotsList().isEmpty())
		// {
		// for (SpaceShoot shot : cGame.c.getShotsList())
		// {
		// if (shot != null)
		// {
		// if (x == shot.getX() && y == shot.getY())
		// {
		// isAlive = false;
		// System.out.println("col!");
		// }
		// }
		// }
		// }

	}

	TimerTask task = new TimerTask() {
		public void run() {

		}

	};
	//
	// public void start() {
	//
	// t3.scheduleAtFixedRate(task, 2000, 5000);
	// temp=temp+10;
	// }

	public void render(Graphics g) {
		if (isAlive) {

			g.drawImage(Enemyimg, (int) x, (int) temp, 64, 64, null);
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(Color.red);
			// g2.draw(_colRec);

		}

	}

	public void move(int dx) {
		x += dx;

	}

	public boolean checkifvalidstep(int dx) {
		if (x + dx >= 0 && x + dx <= 3100 && x<1400) {
			return true;

		}
		goRight=false;
		return false;

	}

	public double getY() {
		// TODO Auto-generated method stub
		return y;
	}

	public double getX() {
		// TODO Auto-generated method stub
		return x;
	}

	public void setX(int i) {
		x += i;

	}
	void maybeCall()
	{
		
		
		if(goRight)
			move(2);
			else
				move(-2);
			
			if(x<1500 && goRight)
			{
				goRight=true;
			}
			else
				if(x==1500 || !goRight)
				{
				  goRight=false;
				}
	}

}
