package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import game.GamePanel;
import mobs.ZombiePigman;

public class Entity {
	
	GamePanel gp;
	
	public int x, y;
	public int speed;
	
	public String direction;
	public String name;
	public Rectangle solidArea = new Rectangle(0,0,30,35);
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collisionOn = false;
	
	public ZombiePigman zombiePigman;
	
	public Entity(GamePanel gp, String name) {
		this.gp = gp;
		this.name = name;
	}

	public void draw(Graphics2D g2, GamePanel gamePanel) {
		zombiePigman.createZombiePigman(g2, gp);
		
	}
	
	public void setAction() {}
	public void update() {

		setAction();
		
		collisionOn = false;
		gp.collisionChecker.checkTile(this);
		gp.collisionChecker.checkEntity(this, gp.mob);
		
		System.out.println(direction);
		System.out.println(collisionOn);
		if (collisionOn == false) {
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

}
