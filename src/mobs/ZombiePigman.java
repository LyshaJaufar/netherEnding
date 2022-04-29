package mobs;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import entity.Entity;
import game.GamePanel;


public class ZombiePigman extends Entity { 

	static Color zombieVeryLightGreen = new Color(159, 179, 136);
	static Color zombieLightGreen = new Color(115, 167, 56);
	static Color zombieGreen = new Color(102, 153, 102);
	static Color zombieMossGreen = new Color(43, 61, 21);
	static Color pigman = new Color(242, 201, 209);
	static Color pants = new Color(82, 41, 15);
	
	public static String name = "Zombie Pigman";
	
	int actionLockCounter = 0;
	
	int zombiePigmanXCoord[] = new int[] {14, 20};
	int zombiePigmanYCoord[] = new int[] {22, 6};
	int zombiePigmanCoordIndex = 0;


	public ZombiePigman(GamePanel gp, int index) {
		
		super(gp, name);
		
		speed = 1;
		
		solidArea = new Rectangle(1, 23, 48, 48);
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		zombiePigmanCoordIndex = index;
		
		setDefaultValues(gp);
	}
	
	public void setDefaultValues(GamePanel gp) {
		
		x = zombiePigmanXCoord[zombiePigmanCoordIndex] * gp.tileSize;
		y = zombiePigmanYCoord[zombiePigmanCoordIndex] * gp.tileSize;
		speed = 1;
		direction = "left";	
		
	}

	
	public void draw(Graphics2D graphics, GamePanel gp) {
		createZombiePigman(graphics, gp);
	}
	
	public void setAction() {

		actionLockCounter++;
		
		
		if (actionLockCounter == 120) {

			Random random = new Random();
			int randomDirection = random.nextInt(100) + 1;

			if (randomDirection <= 25) {
				direction = "up";
			}
			else if (randomDirection > 25 && randomDirection <= 50) {
				direction = "down";
			}
			else if (randomDirection > 50 && randomDirection <= 75) {
				direction = "left";
			}
			else if (randomDirection > 75 && randomDirection <= 100) {
				direction = "right";
			}
			
			actionLockCounter = 0;
		}		

		
	}
				
	public void createZombiePigman(Graphics2D graphics, GamePanel gp) {
		
		int pixel = 2;
		
		// Main body of the Zombie Pigman
		graphics.setColor(zombieVeryLightGreen);
		graphics.fillRect(x,y, pixel ,pixel);
		graphics.fillRect(x + pixel, y + pixel, pixel, pixel);
		graphics.fillRect(x, y + pixel, pixel * 2, pixel);
		graphics.fillRect(x + pixel, y + (pixel * 5), pixel * 2, pixel);
		graphics.fillRect(x - (pixel), y + (pixel * 9), (pixel), pixel * 3);
		graphics.fillRect(x - (pixel * 2), y + (pixel * 11), pixel, pixel);
		graphics.fillRect(x - (pixel * 2), y + (pixel * 15), pixel, pixel * 2);
		
		graphics.setColor(zombieLightGreen);
		graphics.fillRect(x + pixel, y, pixel, pixel);
		graphics.fillRect(x + pixel * 6, y + pixel * 15, pixel * 2, pixel);
		
		graphics.setColor(zombieGreen);
		graphics.fillRect(x + (pixel * 2), y + pixel, pixel, pixel);
		graphics.fillRect(x, y + (pixel * 3), pixel, pixel * 3);
		graphics.fillRect(x, y + pixel * 7, pixel * 3, pixel);
		graphics.fillRect(x + pixel * 2, y + pixel * 12, pixel, pixel);
		graphics.fillRect(x + pixel * 5, y + pixel * 10, pixel, pixel * 3);
		graphics.fillRect(x + (pixel * 6), y + (pixel * 14), pixel * 2, pixel);
		graphics.fillRect(x, y + pixel * 18, pixel * 4, pixel);
		graphics.fillRect(x + pixel * 2, y + pixel * 19, pixel, pixel);
		
		graphics.setColor(zombieMossGreen);
		graphics.fillRect(x + (pixel* 2), y, pixel, pixel);
		graphics.fillRect(x + (pixel * 3), y, pixel, (pixel * 2));
		graphics.fillRect(x + pixel, y + (pixel * 2), pixel, pixel * 3);
		graphics.fillRect(x + (pixel * 5), y + (pixel * 3), pixel, pixel);
		graphics.fillRect(x + (pixel * 5), y + (pixel * 5), pixel, pixel * 2);
		graphics.fillRect(x, y + (pixel * 6), pixel * 5, pixel);
		graphics.fillRect(x + pixel * 2, y + pixel * 4, pixel * 2, pixel);
		graphics.fillRect(x + pixel * 3, y + pixel * 5, pixel, pixel);
		graphics.fillRect(x + pixel, y + (pixel * 9), pixel * 3, pixel);
		graphics.fillRect(x + pixel * 2, y + (pixel * 10), pixel * 2, pixel);
		graphics.fillRect(x + pixel, y + (pixel * 11), pixel * 2, pixel);
		graphics.fillRect(x + pixel * 3, y + (pixel * 7), pixel, pixel * 2);
		graphics.fillRect(x, y + pixel * 13, pixel * 3, pixel);
		graphics.fillRect(x + pixel, y + pixel * 14, pixel * 2, pixel);
		graphics.fillRect(x + pixel * 4, y + pixel * 13, pixel * 2, pixel * 2);
		graphics.fillRect(x - (pixel), y + (pixel * 12), pixel, pixel * 2);
		graphics.fillRect(x - (pixel * 2), y + (pixel * 14), pixel * 2, pixel);
		graphics.fillRect(x + (pixel * 7), y + (pixel * 12), pixel, pixel * 3);
		graphics.fillRect(x - (pixel), y + (pixel * 15), pixel, pixel * 2);
		graphics.fillRect(x + pixel * 6, y + pixel * 16, pixel * 2, pixel);
		
		graphics.setColor(pigman);
		graphics.fillRect(x + (pixel * 4), y, pixel * 2, pixel * 2);
		graphics.fillRect(x + (pixel * 2), y + (pixel * 2), pixel * 2, pixel * 2);
		graphics.fillRect(x + (pixel * 5), y + (pixel * 4), pixel, pixel);
		graphics.fillRect(x + (pixel * 4), y + (pixel * 3), pixel, pixel * 3);
		graphics.fillRect(x - (pixel * 2), y + (pixel * 7), (pixel * 2), pixel * 2);
		graphics.fillRect(x, y + (pixel * 9), pixel, pixel);
		graphics.fillRect(x, y + (pixel * 11), pixel, pixel);
		graphics.fillRect(x - (pixel * 2), y + (pixel * 9), pixel, pixel * 2);
		graphics.fillRect(x, y + pixel * 15, pixel * 6, pixel);
		graphics.fillRect(x + pixel * 3, y + pixel * 11, pixel, pixel * 4);
		graphics.fillRect(x + pixel * 4, y + pixel * 7, pixel * 4, pixel * 3);
		graphics.fillRect(x + pixel * 4, y + pixel * 10, pixel, pixel * 3);
		graphics.fillRect(x - (pixel * 2), y + (pixel * 12), pixel, pixel * 2);
		graphics.fillRect(x + (pixel * 6), y + (pixel * 12), pixel, pixel * 2);
		graphics.fillRect(x + (pixel * 6), y + pixel * 10, pixel * 2, pixel * 2);
		graphics.fillRect(x, y + pixel * 19, pixel * 2, pixel * 2);
		graphics.fillRect(x + pixel * 4, y + pixel * 18, pixel * 2, pixel * 3);
		graphics.fillRect(x + pixel * 3, y + pixel * 19, pixel, pixel * 2);
		graphics.fillRect(x + pixel * 2, y + pixel * 20, pixel, pixel);
		
		graphics.setColor(Color.BLACK);
		graphics.fillRect(x, y + (pixel * 2), pixel, pixel);
		graphics.fillRect(x + (pixel * 5), y + (pixel * 2), pixel, pixel);
		
		graphics.setColor(Color.WHITE);
		graphics.fillRect(x + (pixel * 2), y + (pixel * 2), pixel, pixel);
		graphics.fillRect(x + (pixel * 4), y + (pixel * 2), pixel, pixel);
		graphics.fillRect(x, y + pixel * 8, pixel * 3, pixel);
		graphics.fillRect(x, y + pixel * 10, pixel * 2, pixel);
		graphics.fillRect(x, y + pixel * 12, pixel * 2, pixel);
		graphics.fillRect(x, y + pixel * 14, pixel, pixel);
		
		graphics.setColor(pants);
		graphics.fillRect(x, y + pixel * 16, pixel * 6, pixel * 2);

	}	

}																																						
