package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {

	public final int originalTileSize = 2;
	final int scale = 10;
	
	public final int tileSize = originalTileSize * scale;
	public final int maxScreenCol = 36;
	public final int maxScreenRow = 32;
	public final int screenWidth = tileSize * maxScreenCol;	
	public final int screenHeight = tileSize * maxScreenRow;

	// System
	TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler(this);
	public CollisionChecker collisionChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	Thread gameThread;
	public UI ui = new UI(this);
	int FPS = 60;
	
	// Player, Objects & Enemies
	public Player player = new Player(this, keyH);
	public SuperObject obj[] = new SuperObject[20];
	public ArrayList<Entity> mob = new ArrayList<Entity>();
 	
	// Set player's default positions
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 4;
	
	// Game State
	public int gameState;
	public final int menuState = 0;
	public final int playState = 1;
	public final int upgradeState = 2;
	public final int infoState = 3;
	public final int gameOverState = 4;
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.BLUE);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void setUpGame() {
		aSetter.setObject();
		gameState = menuState;
	}
	   
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	// Game Loop
	public void run() {
		
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
			
		while (gameThread != null) {
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;
			
			if (delta >= 1) {
				update();
				repaint();
				delta--;
			}
		}
	}
	
	public void update() {

		if (gameState == playState) {
			player.update();

			for (int i = 0; i < mob.size(); i++) {
				if (mob.get(i) != null) {
					mob.get(i).update(mob.get(i).x, mob.get(i).y, obj[0].x, obj[0].y);
				}
			}
		}
	}	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		if (gameState == menuState) {
			ui.draw(g2);
		} else {
			tileM.draw(g2);
			obj[0].draw(g2, this, 14, 16);
			obj[1].draw(g2, this, 17, 14);
			obj[2].draw(g2, this, 20, 15);
			
			for (int i = 0; i < mob.size(); i++) {
				if (mob.get(i) != null) {
					mob.get(i).draw(g2, this);
				}
			}
			
			player.draw(g2);
			
			ui.draw(g2);
		}
		
		g2.dispose();
	}
}
