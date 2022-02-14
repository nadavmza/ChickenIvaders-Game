package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import objects.Enemy;
import objects.EnemyShoot;
import objects.GiftEnemy;
import objects.SpaceShip;
import objects.SpaceShoot;

public class ChickenGame extends JPanel {
	public static LinkedList<SpaceShoot> _bulletList = new LinkedList<SpaceShoot>();
	public static LinkedList<Enemy> _enemyList = new LinkedList<Enemy>();
	public static LinkedList<EnemyShoot> _enemyShootList = new LinkedList<EnemyShoot>();
	public static GiftEnemy gift;
	public static SpaceShip _player;
	public static Enemy _enemy;
	public static timeGame timegame;
	SpaceShoot sh;
	// public Controller c;
	public static boolean gamepause=false;
	private boolean isShooting = false;
	private boolean dialog = false;
	public int[] num = { 212, 418 };
	public boolean l = false;
	public boolean r = false;
	public boolean u = false;
	public boolean d = false;
	public int counterPressu = 0;
	public int counterPressd = 0;
	public int counterPressr = 0;
	public int counterPressl = 0;
	int zz = 0;
	int i = 0;
	int count = 0;
	int CounterLevels = 0;
	private boolean isFinishL1 = false;
	private final int level1time = 30;
	public static int LIVES = 3;
	Random randomGenerator;
	public static Enemy _enemyS;
	ChickenMain m;
	ChickenGame c;
	ChickenGame dd;
	JFrame f1 = new JFrame();
	private boolean isGift = false;
	JLabel imageLabel = new JLabel();
	int x = 1400;
	int y = 700;

	public ChickenGame() {
		c = this;

		_player = new SpaceShip(this);
		_enemy = new Enemy(this);
		timegame = new timeGame(level1time);
		gift = new GiftEnemy(this, 640, 640);
		// sh = new SpaceShoot(this);
		randomGenerator = new Random();
		initLevel1();

		addKeyListener(new KLl());
		setFocusable(true);
		requestFocusInWindow();

	}

	void print() {

	}

	public void initGame() {
		isFinishL1 = false;
		CounterLevels = 0;
		dialog=false;
		LIVES = 3;
		_enemyList = new LinkedList<Enemy>();
		_bulletList = new LinkedList<SpaceShoot>();
		_player = new SpaceShip(this);
		_enemy = new Enemy(this);
		_enemyShootList = new LinkedList<EnemyShoot>();
		timegame = new timeGame(level1time);
		addKeyListener(new KLl());
		setFocusable(true);
		requestFocusInWindow();
		initLevel1();

	}

	public void initLevel1() {
		++CounterLevels;
		if (isFinishL1 == false) {
			for (int i = 0; i < 10; i++) {
				_enemyList.add(new Enemy(this, (int) (i * 22) * 5, (int) 20));
			}
		}
		if (isFinishL1 == true) {
			JOptionPane.showMessageDialog(null, "good work");
			for (int j = 0; j < 14; j++) {

				_enemyList.add(new Enemy(this, (int) (j * 15) * 5, (int) 20));
			}
		}
		if (CounterLevels == 3) {

			for (int j = 0; j < 16; j++) {
				for (int s = 0; s < 16; s++) {
					gift = new GiftEnemy(this, (int) (j * 14) * 5, (int) 20);
					_enemyList.add(new Enemy(this, (int) (j * 14) * 5, (int) 20));
					_enemyList.add(new Enemy(this, (int) (s * 14) * 5, (int) 80));

				}
			}

		}

	}

	public void drawEnemies(Graphics g) {
		for (int i = 0; i < _enemyList.size(); i++) {

			int index = randomGenerator.nextInt(_enemyList.size());
			Enemy item = _enemyList.get(index);
			_enemyS = item;
			_enemy = _enemyList.get(i);
			_enemy.render(g);

		}
	}

	public void drawShootE(Graphics g) {

		for (EnemyShoot sz : _enemyShootList) {

			sz.render(g);
		}

	}

	public void drawHeart(Graphics g) {
		ImageIcon img = new ImageIcon("spacee.jpg");
		Image ss = img.getImage();

		g.drawImage(ss, 0, 0, getWidth(), getHeight(), null);
		ImageIcon z = new ImageIcon("giphy.gif");
		Image ssz = z.getImage();

		for (int i = 0; i < 3; i++) {

			if (LIVES == 3) {
				g.drawImage(ssz, x, y, getWidth() / 12, getHeight() / 12, null);
				g.drawImage(ssz, x - 110, y, getWidth() / 12, getHeight() / 12, null);
				g.drawImage(ssz, x - 220, y, getWidth() / 12, getHeight() / 12, null);
			}
			if (LIVES == 2) {
				g.drawImage(ssz, x, y, getWidth() / 12, getHeight() / 12, null);
				g.drawImage(ssz, x - 110, y, getWidth() / 12, getHeight() / 12, null);

			}
			if (LIVES == 1) {
				g.drawImage(ssz, x, y, getWidth() / 12, getHeight() / 12, null);

			}

		}

	}

	public void drawBullets(Graphics g) {
		for (SpaceShoot s : _bulletList) {
			s.render(g);
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		drawHeart(g);

		if (_player.isPaused == true) {
			g.setColor(new Color(255, 255, 255, 40));
			g.fillRect(0, 0, getWidth(), getHeight());
			Font myFont = new Font("Lucida Calligraphy", Font.BOLD, 20);
			g.setFont(myFont);
			g.setColor(Color.yellow);
			g.drawString("PAUSE", 200, 230);
		}

		if (isFinishL1 == false) {
			drawShootE(g);
			_player.drawBall(g);
			drawEnemies(g);
			drawBullets(g);
		}
		if (isFinishL1 == true) {
			drawShootE(g);

			_player.drawBall(g);
			drawEnemies(g);
			drawBullets(g);
			gift.render(g);
		}

		timegame.drawTimer(g);

		if (timegame.getTime() == 0) {
			g.setColor(new Color(255, 255, 255, 40));
			g.fillRect(0, 0, getWidth(), getHeight());
			Font myFontt = new Font("Lucida Calligraphy", Font.BOLD, 20);
			g.setFont(myFontt);
			g.setColor(Color.yellow);
			g.drawString("Game Over", 200, 230);
			_player.stop();

		}

		for (int i = 0; i < _enemyList.size(); i++) {
			Enemy e = _enemyList.get(i);

			for (SpaceShoot bullet : _bulletList) {
				if (e.collidedWith(bullet.getColRec())) {
					e.setIsAlive(false);
					_enemyList.remove(i);
					bullet.setOnScreen(false);

				}
			}
			if (_enemyList.size() == 0) {
				_enemyList = new LinkedList<Enemy>();
				_bulletList = new LinkedList<SpaceShoot>();
				_player = new SpaceShip(this);
				_enemy = new Enemy(this);
				_enemyShootList = new LinkedList<EnemyShoot>();
				timegame = new timeGame(level1time);
				this.isFinishL1 = true;

				initLevel1();
			}

		}

		for (EnemyShoot bullet : _enemyShootList)

		{

			if (_player.collidedWith(bullet.getColRec())) {

				LIVES--;
				_enemyShootList.remove(bullet);
				bullet = null;
				// bullet.setOnScreen(false);
				// _player=null;
				// _player.setIsAlive(false);

				// bullet.setOnScreen(false);

			}
			if (LIVES == 0 && dialog == false) {
				Object[] options = { "New Game", "Exit" };
				int res = JOptionPane.showOptionDialog(null, "Start New Game Or Exit", "You Lost!!",
						JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
				LIVES--;
				System.out.println("this is lives" + LIVES);
				switch (res) {

				case 0: {

					initGame();
					dialog = true;
					break;
				}
				case 1: {

					System.exit(0);
					break;
				}
				}
			}

		}

		if (_player.collidedWith(gift.getColRec())) {

			gift.setOnScreen(false);
			isGift = true;
			gift.stop();

		}

	}

	class KLl extends KeyAdapter {

		public void keyPressed(KeyEvent e) {

			if (e.getKeyCode() == KeyEvent.VK_UP) {
				_player.dy = -_player.INC;
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				_player.dy = _player.INC;
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				_player.dx = -_player.INC;
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				_player.dx = _player.INC;
			}
			if (e.getKeyCode() == KeyEvent.VK_SPACE && !isShooting) {
				isShooting = true;
				count++;
				_bulletList.add(new SpaceShoot(ChickenGame.this, (int) _player.getX() + 32, (int) _player.getY()));
				if (isGift)
					_bulletList.add(new SpaceShoot(ChickenGame.this, (int) _player.getX() + 16, (int) _player.getY()));
				if (count % 5 == 0)
					_enemyShootList.add(new EnemyShoot(ChickenGame.this, (int) _enemyS.getX(), (int) _enemyS.getY()));
				if (isFinishL1 == true) {

					if (count % 4 == 0) {
						_enemyShootList
								.add(new EnemyShoot(ChickenGame.this, (int) _enemyS.getX(), (int) _enemyS.getY()));
					}
				}

			}

			if (e.getKeyCode() == KeyEvent.VK_P ) {
				_player.isPaused = true;
				gamepause=true;
			}

		}

		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub

		}

		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				_player.dy = 0;
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				_player.dy = 0;
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				_player.dx = 0;
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				_player.dx = 0;
			}
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				isShooting = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_P) {
				_player.isPaused = false;
				gamepause=false;
			}

		}

	}

}
