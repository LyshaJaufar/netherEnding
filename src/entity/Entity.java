package entity;

import java.awt.Rectangle;

public class Entity {
	public int x, y;
	public int speed;
	
	public String direction;
	public Rectangle solidArea;
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collisionOn = false;
	
}
