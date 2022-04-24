package entity;

import java.awt.Color;
import java.awt.Graphics2D;

import game.GamePanel;
import game.KeyHandler;

public class Player extends Entity {
	GamePanel gp;
	KeyHandler keyH;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		
		setDefaultValues();
	}
	
	public void setDefaultValues() {
		x = gp.screenWidth/2;
		y = gp.screenHeight/2;
		speed = 4;
		direction = "left";
	}

	public void update() {
		if (keyH.upPressed == true) {
			direction = "up";
			y -= speed;
		}
		else if (keyH.downPressed == true) {
			direction = "down";
			y += speed;
		}
		else if (keyH.leftPressed == true) {
			direction = "left";
			x -= speed;
		}
		else if (keyH.rightPressed == true) {
			direction = "right";
			x += speed;
		}
	}

	public void draw(Graphics2D g2) {
		
		Color darkBlue = new Color(33, 50, 94);
		Color yellow = new Color(248, 234, 140);
		Color darkYellow = new Color(214, 173, 96);
		Color pink = new Color(251, 107, 144);

		int pixelSize = 1;
				
		if (direction == "left" || direction == "up") {
			g2.setColor(darkBlue);
			g2.fillRect(x, y, (pixelSize * 2), pixelSize);		// Layer 1
			g2.fillRect(x-(pixelSize * 2), y+pixelSize, (pixelSize * 6), pixelSize);
			g2.fillRect(x-(pixelSize * 3), y+(pixelSize * 2), (pixelSize * 8), pixelSize);
			
			g2.fillRect(x-(pixelSize * 4), y+(pixelSize * 3), (pixelSize * 10), pixelSize);
			
			g2.setColor(yellow);
			g2.fillRect(x-(pixelSize * 9), y+(pixelSize * 3), (pixelSize * 3), pixelSize);
			g2.fillRect(x+(pixelSize * 8), y+(pixelSize * 3), (pixelSize * 3), pixelSize);
	
			g2.fillRect(x-(pixelSize * 10), y+(pixelSize * 4), (pixelSize * 5), pixelSize);
	
			g2.fillRect(x+(pixelSize * 7), y+(pixelSize * 4), (pixelSize * 5), pixelSize);		
			
			g2.setColor(darkBlue);
			g2.fillRect(x-(pixelSize * 5), y+(pixelSize * 4), (pixelSize * 12), pixelSize);
		
			g2.setColor(pink);
			g2.fillRect(x-(pixelSize * 11), y+(pixelSize * 5), (pixelSize * 4), pixelSize);
			g2.fillRect(x+(pixelSize * 9), y+(pixelSize *5), (pixelSize * 4), pixelSize);
		
			g2.setColor(yellow);
			g2.fillRect(x-(pixelSize * 7), y + (pixelSize * 5), pixelSize * 3, pixelSize);
			g2.fillRect(x+(pixelSize * 6), y + (pixelSize * 5), pixelSize * 3, pixelSize);
			
			g2.setColor(darkYellow);
			g2.fillRect(x-(pixelSize * 5), y + (pixelSize * 5), pixelSize, pixelSize);
			g2.fillRect(x+(pixelSize * 6), y + (pixelSize * 5), pixelSize, pixelSize);
			
			g2.setColor(darkBlue);
			g2.fillRect(x-(pixelSize * 4), y + (pixelSize * 5), pixelSize * 2, pixelSize);		
			g2.fillRect(x+(pixelSize * 4), y + (pixelSize * 5), pixelSize * 2, pixelSize);
			
			g2.setColor(yellow);
			g2.fillRect(x-(pixelSize * 2), y + (pixelSize * 5), pixelSize * 6, pixelSize);
			
			g2.setColor(pink);
			g2.fillRect(x-(pixelSize * 11), y+(pixelSize * 6), (pixelSize * 5), pixelSize);
			g2.fillRect(x+(pixelSize * 8), y+(pixelSize * 6), (pixelSize * 5), pixelSize);
			
			g2.setColor(yellow);
			g2.fillRect(x-(pixelSize * 6), y+(pixelSize * 6), (pixelSize * 2), pixelSize);
			g2.fillRect(x+(pixelSize * 6), y+(pixelSize * 6), (pixelSize * 2), pixelSize);
			
			g2.fillRect(x-(pixelSize * 3), y+(pixelSize * 6), (pixelSize * 8), pixelSize);
			
			g2.setColor(darkYellow);
			g2.fillRect(x-(pixelSize * 4), y+(pixelSize * 6), (pixelSize), pixelSize);
			g2.fillRect(x+(pixelSize * 5), y+(pixelSize * 6), (pixelSize), pixelSize);
			
			g2.setColor(pink);
			g2.fillRect(x-(pixelSize * 11), y+(pixelSize * 7), (pixelSize * 5), pixelSize);
			g2.fillRect(x+(pixelSize * 8), y+(pixelSize * 7), (pixelSize * 5), pixelSize);
			
			g2.setColor(yellow);
			g2.fillRect(x-(pixelSize * 6), y+(pixelSize * 7), pixelSize, pixelSize);
			g2.fillRect(x+(pixelSize * 7), y+(pixelSize * 7), pixelSize, pixelSize);
			
			g2.fillRect(x-(pixelSize * 5), y+(pixelSize * 7), pixelSize * 11, pixelSize);
			
			g2.setColor(darkYellow);
			g2.fillRect(x-(pixelSize * 5), y+(pixelSize * 7), pixelSize, pixelSize);
			g2.fillRect(x+(pixelSize * 6), y+(pixelSize * 7), pixelSize, pixelSize);
			
			g2.setColor(pink);
			g2.fillRect(x-(pixelSize * 10), y+(pixelSize * 8), (pixelSize * 5), pixelSize);
			g2.fillRect(x+(pixelSize * 7), y+(pixelSize * 8), (pixelSize * 5), pixelSize);
			
			g2.setColor(yellow);
			g2.fillRect(x-(pixelSize * 5), y+(pixelSize * 8), (pixelSize * 12), pixelSize);
			
			g2.setColor(pink);
			g2.fillRect(x-(pixelSize * 9), y+(pixelSize * 9), (pixelSize * 3), pixelSize);
			g2.fillRect(x+(pixelSize * 8), y+(pixelSize * 9), (pixelSize * 3), pixelSize);
			
			g2.setColor(yellow);
			g2.fillRect(x-(pixelSize * 6), y+(pixelSize * 9), (pixelSize * 14), pixelSize);
			
			g2.fillRect(x-(pixelSize * 8), y+(pixelSize * 10), (pixelSize * 18), pixelSize * 4);
			
			g2.fillRect(x-(pixelSize * 8), y+(pixelSize * 14), (pixelSize * 2), pixelSize * 2);
			
			g2.fillRect(x+(pixelSize * 6), y+(pixelSize * 14), (pixelSize * 4), pixelSize * 2);
		
			// Eyes
			g2.setColor(Color.BLACK);
			g2.fillRect(x-(pixelSize * 6), y+(pixelSize * 14), (pixelSize * 2), pixelSize * 2);
			g2.fillRect(x+(pixelSize * 4), y+(pixelSize * 14), (pixelSize * 2), pixelSize * 2);
			
			
			g2.setColor(yellow);
			g2.fillRect(x-(pixelSize * 4), y+(pixelSize * 14), (pixelSize), pixelSize * 5);
		
			g2.fillRect(x-(pixelSize * 3), y+(pixelSize * 14), (pixelSize), pixelSize);
			
			g2.fillRect(x-(pixelSize * 3), y+(pixelSize * 18), (pixelSize), pixelSize * 2);
		
			g2.fillRect(x+(pixelSize*2), y+(pixelSize * 14), pixelSize * 2, pixelSize);
			
			g2.setColor(Color.WHITE);
			g2.fillRect(x-(pixelSize * 2), y+(pixelSize * 14), (pixelSize * 4), pixelSize);
		
			g2.fillRect(x-(pixelSize * 3), y+(pixelSize * 15), (pixelSize * 2), pixelSize * 2);
			
			g2.fillRect(x+(pixelSize * 1), y+(pixelSize * 15), (pixelSize * 2), pixelSize * 2);
		
			g2.fillRect(x-(pixelSize * 1), y+(pixelSize * 17), (pixelSize * 2), pixelSize * 2);
	
			g2.fillRect(x-(pixelSize * 3), y+(pixelSize * 17), (pixelSize), pixelSize);
			
			g2.fillRect(x+(pixelSize * 2), y+(pixelSize * 17), (pixelSize), pixelSize);
		
			g2.fillRect(x-(pixelSize * 2), y+(pixelSize * 18), (pixelSize), pixelSize);
		
			g2.fillRect(x+(pixelSize * 1), y+(pixelSize * 18), (pixelSize), pixelSize);
		
			// Mouth
			g2.setColor(Color.BLACK);
			g2.fillRect(x-(pixelSize * 1), y+(pixelSize * 15), (pixelSize * 2), pixelSize * 2);
			
			g2.fillRect(x-(pixelSize * 2), y+(pixelSize * 17), (pixelSize), pixelSize);
			g2.fillRect(x+(pixelSize * 1), y+(pixelSize * 17), (pixelSize), pixelSize);
			
			
			g2.setColor(yellow);
			g2.fillRect(x+(pixelSize * 3), y+(pixelSize * 15), (pixelSize), pixelSize * 5);
			g2.fillRect(x+(pixelSize * 2), y+(pixelSize * 18), (pixelSize), pixelSize * 2);
			
			g2.fillRect(x-(pixelSize * 3), y+(pixelSize * 19), (pixelSize * 6), pixelSize);
		
			g2.fillRect(x-(pixelSize * 7), y+(pixelSize * 16), (pixelSize * 4), pixelSize);
		
			g2.fillRect(x+(pixelSize * 3), y+(pixelSize * 16), (pixelSize * 6), pixelSize);
	
			g2.fillRect(x-(pixelSize * 6), y+(pixelSize * 17), (pixelSize * 3), pixelSize);
		
			g2.fillRect(x+(pixelSize * 3), y+(pixelSize * 17), (pixelSize * 5), pixelSize);
		
			g2.fillRect(x-(pixelSize * 5), y+(pixelSize * 18), (pixelSize), pixelSize);
			
			g2.fillRect(x+(pixelSize * 4), y+(pixelSize * 18), (pixelSize * 3), pixelSize);
		
			g2.fillRect(x+(pixelSize * 4), y+(pixelSize * 19), (pixelSize), pixelSize);
			
			// Robe
			g2.setColor(darkBlue);
			g2.fillRect(x-(pixelSize * 9), y+(pixelSize * 10), pixelSize, pixelSize * 10);
			g2.fillRect(x-(pixelSize * 8), y+(pixelSize * 16), pixelSize, pixelSize * 4);
			g2.fillRect(x-(pixelSize * 7), y+(pixelSize * 17), pixelSize, pixelSize * 4);
			g2.fillRect(x-(pixelSize * 6), y+(pixelSize * 18), pixelSize, pixelSize * 3);
			g2.fillRect(x-(pixelSize * 5), y+(pixelSize * 19), pixelSize, pixelSize * 2);
			g2.fillRect(x-(pixelSize * 4), y+(pixelSize * 19), pixelSize, pixelSize);
			
			g2.fillRect(x+(pixelSize * 5), y+(pixelSize * 19), pixelSize, pixelSize);
			g2.fillRect(x+(pixelSize * 6), y+(pixelSize * 19), pixelSize, pixelSize*2);
			g2.fillRect(x+(pixelSize * 7), y+(pixelSize * 18), pixelSize, pixelSize*3);
			g2.fillRect(x+(pixelSize * 8), y+(pixelSize * 17), pixelSize, pixelSize*4);
			g2.fillRect(x+(pixelSize * 9), y+(pixelSize * 16), pixelSize, pixelSize*4);
			g2.fillRect(x+(pixelSize * 10), y+(pixelSize * 10), pixelSize, pixelSize*10);
	
			g2.fillRect(x-(pixelSize * 4), y+(pixelSize * 20), pixelSize * 10, pixelSize * 13);
			
			g2.fillRect(x-(pixelSize * 6), y+(pixelSize * 24), pixelSize * 2, pixelSize * 4);
	
			g2.fillRect(x+(pixelSize * 6), y+(pixelSize * 25), pixelSize, pixelSize * 8);
			g2.fillRect(x+(pixelSize * 7), y+(pixelSize * 26), pixelSize, pixelSize * 7);
			g2.fillRect(x+(pixelSize * 8), y+(pixelSize * 27), pixelSize, pixelSize * 6);
			g2.fillRect(x+(pixelSize * 9), y+(pixelSize * 29), pixelSize * 2, pixelSize * 4);
			g2.fillRect(x+(pixelSize * 11), y+(pixelSize * 30), pixelSize * 2, pixelSize * 3);
			g2.fillRect(x+(pixelSize * 13), y+(pixelSize * 31), pixelSize, pixelSize * 2);
		}
		else if (direction == "right" || direction == "down") {
			g2.setColor(darkBlue);
			g2.fillRect(x, y, (pixelSize * 2), pixelSize);		// Layer 1
			g2.fillRect(x-(pixelSize * 2), y+pixelSize, (pixelSize * 6), pixelSize);
			g2.fillRect(x-(pixelSize * 3), y+(pixelSize * 2), (pixelSize * 8), pixelSize);
			
			g2.fillRect(x-(pixelSize * 4), y+(pixelSize * 3), (pixelSize * 10), pixelSize);
			
			g2.setColor(yellow);
			g2.fillRect(x-(pixelSize * 9), y+(pixelSize * 3), (pixelSize * 3), pixelSize);
			g2.fillRect(x+(pixelSize * 8), y+(pixelSize * 3), (pixelSize * 3), pixelSize);
	
			g2.fillRect(x-(pixelSize * 10), y+(pixelSize * 4), (pixelSize * 5), pixelSize);
	
			g2.fillRect(x+(pixelSize * 7), y+(pixelSize * 4), (pixelSize * 5), pixelSize);		
			
			g2.setColor(darkBlue);
			g2.fillRect(x-(pixelSize * 5), y+(pixelSize * 4), (pixelSize * 12), pixelSize);
		
			g2.setColor(pink);
			g2.fillRect(x-(pixelSize * 11), y+(pixelSize * 5), (pixelSize * 4), pixelSize);
			g2.fillRect(x+(pixelSize * 9), y+(pixelSize *5), (pixelSize * 4), pixelSize);
		
			g2.setColor(yellow);
			g2.fillRect(x-(pixelSize * 7), y + (pixelSize * 5), pixelSize * 3, pixelSize);
			g2.fillRect(x+(pixelSize * 6), y + (pixelSize * 5), pixelSize * 3, pixelSize);
			
			g2.setColor(darkYellow);
			g2.fillRect(x-(pixelSize * 5), y + (pixelSize * 5), pixelSize, pixelSize);
			g2.fillRect(x+(pixelSize * 6), y + (pixelSize * 5), pixelSize, pixelSize);
			
			g2.setColor(darkBlue);
			g2.fillRect(x-(pixelSize * 4), y + (pixelSize * 5), pixelSize * 2, pixelSize);		
			g2.fillRect(x+(pixelSize * 4), y + (pixelSize * 5), pixelSize * 2, pixelSize);
			
			g2.setColor(yellow);
			g2.fillRect(x-(pixelSize * 2), y + (pixelSize * 5), pixelSize * 6, pixelSize);
			
			g2.setColor(pink);
			g2.fillRect(x-(pixelSize * 11), y+(pixelSize * 6), (pixelSize * 5), pixelSize);
			g2.fillRect(x+(pixelSize * 8), y+(pixelSize * 6), (pixelSize * 5), pixelSize);
			
			g2.setColor(yellow);
			g2.fillRect(x-(pixelSize * 6), y+(pixelSize * 6), (pixelSize * 2), pixelSize);
			g2.fillRect(x+(pixelSize * 6), y+(pixelSize * 6), (pixelSize * 2), pixelSize);
			
			g2.fillRect(x-(pixelSize * 3), y+(pixelSize * 6), (pixelSize * 8), pixelSize);
			
			g2.setColor(darkYellow);
			g2.fillRect(x-(pixelSize * 4), y+(pixelSize * 6), (pixelSize), pixelSize);
			g2.fillRect(x+(pixelSize * 5), y+(pixelSize * 6), (pixelSize), pixelSize);
			
			g2.setColor(pink);
			g2.fillRect(x-(pixelSize * 11), y+(pixelSize * 7), (pixelSize * 5), pixelSize);
			g2.fillRect(x+(pixelSize * 8), y+(pixelSize * 7), (pixelSize * 5), pixelSize);
			
			g2.setColor(yellow);
			g2.fillRect(x-(pixelSize * 6), y+(pixelSize * 7), pixelSize, pixelSize);
			g2.fillRect(x+(pixelSize * 7), y+(pixelSize * 7), pixelSize, pixelSize);
			
			g2.fillRect(x-(pixelSize * 5), y+(pixelSize * 7), pixelSize * 11, pixelSize);
			
			g2.setColor(darkYellow);
			g2.fillRect(x-(pixelSize * 5), y+(pixelSize * 7), pixelSize, pixelSize);
			g2.fillRect(x+(pixelSize * 6), y+(pixelSize * 7), pixelSize, pixelSize);
			
			g2.setColor(pink);
			g2.fillRect(x-(pixelSize * 10), y+(pixelSize * 8), (pixelSize * 5), pixelSize);
			g2.fillRect(x+(pixelSize * 7), y+(pixelSize * 8), (pixelSize * 5), pixelSize);
			
			g2.setColor(yellow);
			g2.fillRect(x-(pixelSize * 5), y+(pixelSize * 8), (pixelSize * 12), pixelSize);
			
			g2.setColor(pink);
			g2.fillRect(x-(pixelSize * 9), y+(pixelSize * 9), (pixelSize * 3), pixelSize);
			g2.fillRect(x+(pixelSize * 8), y+(pixelSize * 9), (pixelSize * 3), pixelSize);
			
			g2.setColor(yellow);
			g2.fillRect(x-(pixelSize * 6), y+(pixelSize * 9), (pixelSize * 14), pixelSize);
			
			g2.fillRect(x-(pixelSize * 8), y+(pixelSize * 10), (pixelSize * 18), pixelSize * 4);
			
			g2.fillRect(x-(pixelSize * 8), y+(pixelSize * 14), (pixelSize * 2), pixelSize * 2);
			
			g2.fillRect(x+(pixelSize * 6), y+(pixelSize * 14), (pixelSize * 4), pixelSize * 2);
		
			// Eyes
			g2.setColor(Color.BLACK);
			g2.fillRect(x-(pixelSize * 4), y+(pixelSize * 14), (pixelSize * 2), pixelSize * 2);
			g2.fillRect(x+(pixelSize * 6), y+(pixelSize * 14), (pixelSize * 2), pixelSize * 2);
			
			
			g2.setColor(yellow);
			g2.fillRect(x-(pixelSize * 2), y+(pixelSize * 14), (pixelSize), pixelSize * 5);
		
			g2.fillRect(x-(pixelSize * 1), y+(pixelSize * 14), (pixelSize), pixelSize);
			
			g2.fillRect(x-(pixelSize * 1), y+(pixelSize * 18), (pixelSize), pixelSize * 2);
		
			g2.fillRect(x+(pixelSize*4), y+(pixelSize * 14), pixelSize * 2, pixelSize);
			
			g2.setColor(Color.WHITE);
			g2.fillRect(x, y+(pixelSize * 14), (pixelSize * 4), pixelSize);
		
			g2.fillRect(x-(pixelSize * 1), y+(pixelSize * 15), (pixelSize * 2), pixelSize * 2);
			
			g2.fillRect(x+(pixelSize * 3), y+(pixelSize * 15), (pixelSize * 2), pixelSize * 2);
		
			g2.fillRect(x+(pixelSize * 1), y+(pixelSize * 17), (pixelSize * 2), pixelSize * 2);
	
			g2.fillRect(x-(pixelSize * 1), y+(pixelSize * 17), (pixelSize), pixelSize);
			
			g2.fillRect(x+(pixelSize * 4), y+(pixelSize * 17), (pixelSize), pixelSize);
		
			g2.fillRect(x, y+(pixelSize * 18), (pixelSize), pixelSize);
		
			g2.fillRect(x+(pixelSize * 3), y+(pixelSize * 18), (pixelSize), pixelSize);
		
			
			// Mouth
			g2.setColor(Color.BLACK);
			g2.fillRect(x+(pixelSize * 1), y+(pixelSize * 15), (pixelSize * 2), pixelSize * 2);
			
			g2.fillRect(x, y+(pixelSize * 17), (pixelSize), pixelSize);
			g2.fillRect(x+(pixelSize * 3), y+(pixelSize * 17), (pixelSize), pixelSize);
			
			g2.setColor(yellow);
			g2.fillRect(x-(pixelSize * 6), y+(pixelSize * 14), (pixelSize * 2), pixelSize * 4);
			g2.fillRect(x-(pixelSize * 7), y+(pixelSize * 16), (pixelSize), pixelSize);
			
			g2.fillRect(x-(pixelSize * 4), y+(pixelSize * 16), (pixelSize * 2), pixelSize * 2);
			g2.fillRect(x+(pixelSize * 6), y+(pixelSize * 16), (pixelSize * 2), pixelSize * 2);			

			g2.fillRect(x+(pixelSize * 5), y+(pixelSize * 15), (pixelSize), pixelSize * 4);			

			g2.fillRect(x+(pixelSize * 8), y+(pixelSize * 16), (pixelSize), pixelSize);			

			g2.fillRect(x+(pixelSize * 6), y+(pixelSize * 18), (pixelSize), pixelSize);			

			g2.fillRect(x-(pixelSize * 5), y+(pixelSize * 18), (pixelSize * 3), pixelSize);		
			
			g2.fillRect(x-(pixelSize * 3), y+(pixelSize * 19), (pixelSize * 2), pixelSize);	
			
			g2.fillRect(x, y+(pixelSize * 19), (pixelSize * 5), pixelSize);	
			
			g2.fillRect(x+(pixelSize * 4), y+(pixelSize * 18), (pixelSize), pixelSize);	
			
			
			// Robe
			g2.setColor(darkBlue);
			g2.fillRect(x-(pixelSize * 9), y+(pixelSize * 10), pixelSize, pixelSize * 10);
			g2.fillRect(x-(pixelSize * 8), y+(pixelSize * 16), pixelSize, pixelSize * 4);
			g2.fillRect(x-(pixelSize * 7), y+(pixelSize * 17), pixelSize, pixelSize * 4);
			g2.fillRect(x-(pixelSize * 6), y+(pixelSize * 18), pixelSize, pixelSize * 3);
			g2.fillRect(x-(pixelSize * 5), y+(pixelSize * 19), pixelSize, pixelSize * 2);
			g2.fillRect(x-(pixelSize * 4), y+(pixelSize * 19), pixelSize, pixelSize);
			
			g2.fillRect(x+(pixelSize * 5), y+(pixelSize * 19), pixelSize, pixelSize);
			g2.fillRect(x+(pixelSize * 6), y+(pixelSize * 19), pixelSize, pixelSize*2);
			g2.fillRect(x+(pixelSize * 7), y+(pixelSize * 18), pixelSize, pixelSize*3);
			g2.fillRect(x+(pixelSize * 8), y+(pixelSize * 17), pixelSize, pixelSize*4);
			g2.fillRect(x+(pixelSize * 9), y+(pixelSize * 16), pixelSize, pixelSize*4);
			g2.fillRect(x+(pixelSize * 10), y+(pixelSize * 10), pixelSize, pixelSize*10);
	
			g2.fillRect(x-(pixelSize * 4), y+(pixelSize * 20), pixelSize * 10, pixelSize * 13);
			
			g2.fillRect(x+(pixelSize * 6), y+(pixelSize * 24), pixelSize * 2, pixelSize * 4);
	
			g2.fillRect(x-(pixelSize * 5), y+(pixelSize * 25), pixelSize, pixelSize * 8);
			g2.fillRect(x-(pixelSize * 6), y+(pixelSize * 26), pixelSize, pixelSize * 7);
			g2.fillRect(x-(pixelSize * 7), y+(pixelSize * 27), pixelSize, pixelSize * 6);
			g2.fillRect(x-(pixelSize * 8), y+(pixelSize * 29), pixelSize * 2, pixelSize * 4);
			g2.fillRect(x-(pixelSize * 10), y+(pixelSize * 30), pixelSize * 2, pixelSize * 3);
			g2.fillRect(x-(pixelSize * 11), y+(pixelSize * 31), pixelSize, pixelSize * 2);
		}
	}
}