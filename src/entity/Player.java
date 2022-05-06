package entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import game.GamePanel;
import game.KeyHandler;

public class Player extends Entity {

	public static String name = "Player";
	KeyHandler keyH;
	int playerMovementCount = 70;

	public Player(GamePanel gp, KeyHandler keyH) {
		
		super(gp, name);

		this.keyH = keyH;
		
		solidArea = new Rectangle(1, 23, 4, 8);
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		attackArea.width = 36;
		attackArea.height = 36;
		
		setDefaultValues();
	}
	
	public void setDefaultValues() {
		x = gp.screenWidth/2;
		y = gp.screenHeight/2;
		speed = 4;
		direction = "left";
	}

	public void update() {
		if (attacking) {
			attacking();
		}
		if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || 
				keyH.rightPressed == true) {
			playerMovementCount++;
		}
		if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || 
				keyH.rightPressed == true || keyH.enterPressed == true) {
			if (keyH.upPressed == true) {
				direction = "up";
			}
			else if (keyH.downPressed == true) {
				direction = "down";
			}
			else if (keyH.leftPressed == true) {
				direction = "left";
			}
			else if (keyH.rightPressed == true) {
				direction = "right";
			}
			if (keyH.enterPressed == true) {
				attacking = true;
			}
			
			// Check Tile Collision
			collisionOn = false;
			gp.collisionChecker.checkTile(this);
			
			// Check Object Collision
			int objIndex = gp.collisionChecker.checkObject(this, true);
			
			// Check Mob Collision
			int monsterIndex = gp.collisionChecker.checkEntity(this, gp.mob);
			
			// If collision is false, player can move
			if (collisionOn == false && keyH.enterPressed == false) {
				if (direction == "up") {
					y -= speed;
				}
				else if (direction == "down") {
					y += speed;
				}
				else if (direction == "left") {
					x -= speed;
				}
				else if (direction == "right") {
					x += speed;
				}
			}		
		}
		
		// Spawning system
		if (playerMovementCount % 115 == 0) {
			playerMovementCount = 1;
			gp.aSetter.setMob();
		}
	}
	

	public void attacking() {
		attackAnimationCounter++;
		
		if (attackAnimationCounter <= 5) {
			attackAnimationNum = 1;
		}

		else if (attackAnimationCounter > 5 && attackAnimationCounter <= 25) {
			attackAnimationNum = 2;
			
			// Save x, y coords & solid area
			int currentXCoord = x;
			int currentYCoord = y;
			int solidAreaWidth = solidArea.width;
			int solidAreaHeight = solidArea.height;
			
			// Updated x, y & solid area
			if (direction == "up") {
				y -= attackArea.height;
			} 
			else if (direction == "down") {
				y += attackArea.height;
			}
			else if (direction == "left") {
				x -= attackArea.width;
			}
			else if (direction == "right") {
				x += attackArea.width;
			}
			
			// Attack area becomes solid area
			solidArea.width = attackArea.width;
			solidArea.height = attackArea.height;
			
			// Check mob collision with the updated x, y & solid area
			int mobIndex = gp.collisionChecker.checkEntity(this, gp.mob);
			damageMob(mobIndex);
			
			// After checking collision, restore the original data
			x = currentXCoord;
			y = currentYCoord;
			solidArea.width = solidAreaWidth;
			solidArea.height = solidAreaHeight;
			
		}
		 
		else if (attackAnimationCounter > 25) {
			attackAnimationNum = 1;
			attackAnimationCounter = 0;
			attacking = false;
		}
	}
	
	public void damageMob(int mobIndex) {

		if (mobIndex != 999 && gp.mob.get(mobIndex).life > 0) {
			if (gp.mob.get(mobIndex).invincible == true) {
				gp.mob.get(mobIndex).invincibleCounter++;
				if (gp.mob.get(mobIndex).invincibleCounter > 15) {
					gp.mob.get(mobIndex).invincible = false;
					gp.mob.get(mobIndex).invincibleCounter = 0;
				}
			}
			else if (gp.mob.get(mobIndex).invincible == false) {
				gp.mob.get(mobIndex).life--;	
				gp.mob.get(mobIndex).invincible = true;
			}
		}
		else if (mobIndex != 999 && gp.mob.get(mobIndex).life <= 0) {
			gp.mob.get(mobIndex).dead = true;
		}
	}
		
	public void draw(Graphics2D g2) {
		
		Color darkBlue = new Color(33, 50, 94);
		Color yellow = new Color(248, 234, 140);
		Color darkYellow = new Color(214, 173, 96);
		Color pink = new Color(251, 107, 144);
		Color diamondSword = new Color(69,172,165);
		Color swordHilt = new Color(122, 53, 13);

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
		
		if (attacking == true && attackAnimationNum == 1 && direction == "left") {
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
			
			// Sword
			g2.setColor(diamondSword);
			g2.fillRect(x-(pixelSize * 8), y+(pixelSize * 24), pixelSize * 2, pixelSize * 4);
			g2.fillRect(x-(pixelSize * 10), y+(pixelSize * 25), pixelSize * 2, pixelSize * 2);
		}
		else if (attacking == true && direction == "right" && attackAnimationNum == 1) {
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
			
			// Sword
			g2.setColor(diamondSword);
			g2.fillRect(x+(pixelSize * 8), y+(pixelSize * 24), pixelSize * 2, pixelSize * 4);
			g2.fillRect(x+(pixelSize * 10), y+(pixelSize * 25), pixelSize * 2, pixelSize * 2);
		}
		if (attacking == true && attackAnimationNum == 2 && direction == "left") {
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
			
			// Sword
			g2.setColor(diamondSword);
			g2.fillRect(x-(pixelSize * 21), y+(pixelSize * 24), pixelSize * 14, pixelSize * 4);
			g2.fillRect(x-(pixelSize * 23), y+(pixelSize * 25), pixelSize * 2, pixelSize * 2);
			
			g2.setColor(swordHilt);
			g2.fillRect(x-(pixelSize * 8), y+(pixelSize * 24), pixelSize*2, pixelSize * 4);
		}
		else if (attacking == true && direction == "right" && attackAnimationNum == 2) {
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
			
			// Sword
			g2.setColor(swordHilt);
			g2.fillRect(x+(pixelSize * 8), y+(pixelSize * 24), pixelSize*2, pixelSize * 4);

			g2.setColor(diamondSword);
			g2.fillRect(x+(pixelSize * 10), y+(pixelSize * 24), pixelSize * 14, pixelSize * 4);
			g2.fillRect(x+(pixelSize * 24), y+(pixelSize * 25), pixelSize * 2, pixelSize * 2);
		}
	}
}