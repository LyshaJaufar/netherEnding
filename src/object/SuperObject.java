package object;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import game.GamePanel;
import tile.Tile;
import tile.TileManager;

public class SuperObject {
	public String name;
	public boolean collision = false;
	public int x, y;
	public Rectangle solidArea = new Rectangle(0,0,25,25);
	public int solidAreaDefaultX = 0;
	public int solidAreaDefaultY = 0;
	
	public void draw(Graphics2D g2, GamePanel gp, int x, int y) {
		createVillageHouse(g2, x * gp.tileSize, y * gp.tileSize, gp);
	}
	
	public void createVillageHouse(Graphics2D graphics, int x, int y, GamePanel gp) {

		x += 4;
		
		// Main part of the house
		// Main Bedrock Block
		graphics.setColor(TileManager.bedrockBlock);
		graphics.fillRect(x, y, gp.tileSize-4, gp.tileSize);
		
		// Smaller pixels inside of Bedrock Block
		graphics.setColor(Color.darkGray);
		graphics.fillRect(x + 2, y, 6, 2);
		graphics.fillRect(x + 16, y, 4, 2);
		graphics.fillRect(x + 2, y + 4, 4, 8);
		graphics.fillRect(x + 4, y + 4, 10, 2);
		graphics.fillRect(x + 4, y + 8, 4, 4);
		graphics.fillRect(x + 16, y + 8, 2, 6);
		graphics.fillRect(x + 10, y + 8, 6, 4);
		graphics.fillRect(x, y + 14, 18, 3);
		graphics.fillRect(x+14, y + 17, 6,3);
		graphics.fillRect(x + 2, y + 17, 6, 3);
		graphics.fillRect(x + 8, y + 18, 6, 2);
		
		graphics.setColor(Color.lightGray);
		graphics.fillRect(x + 8, y, 4, 4);
		graphics.fillRect(x + 14, y, 2, 4);
		graphics.fillRect(x + 4, y + 2, 4, 2);
		graphics.fillRect(x + 16, y + 4, 4, 4);
		graphics.fillRect(x + 10, y + 6, 6, 2);
		graphics.fillRect(x, y + 12, 8, 2);
		graphics.fillRect(x, y + 17, 2,3);
		graphics.fillRect(x+8, y + 17, 6,2);
		
		graphics.setColor(Color.BLACK);
		graphics.fillRect(x + 12, y, 2, 2);
		graphics.fillRect(x, y + 4, 2, 2);
		graphics.fillRect(x + 14, y + 4, 2, 2);
		graphics.fillRect(x + 8, y + 8, 2, 2);
		graphics.fillRect(x + 8, y + 12, 2, 2);
		graphics.fillRect(x+18, y + 8, 2, 4);

		graphics.setColor(Color.GRAY);
		graphics.fillRect(x+1, y, 3, gp.tileSize);
		x += gp.tileSize-8;
		
		// Main Bedrock Block
		graphics.setColor(TileManager.bedrockBlock);
		graphics.fillRect(x, y, gp.tileSize, gp.tileSize);
		
		// Smaller pixels inside of Bedrock Block
		graphics.setColor(Color.darkGray);
		graphics.fillRect(x + 2, y, 6, 2);
		graphics.fillRect(x + 16, y, 4, 2);
		graphics.fillRect(x + 2, y + 4, 4, 8);
		graphics.fillRect(x + 4, y + 4, 10, 2);
		graphics.fillRect(x + 4, y + 8, 4, 4);
		graphics.fillRect(x + 16, y + 8, 2, 6);
		graphics.fillRect(x + 10, y + 8, 6, 4);
		graphics.fillRect(x, y + 14, 18, 3);
		graphics.fillRect(x+14, y + 17, 6,3);
		graphics.fillRect(x + 2, y + 17, 6, 3);
		graphics.fillRect(x + 8, y + 18, 6, 2);
		
		graphics.setColor(Color.lightGray);
		graphics.fillRect(x + 8, y, 4, 4);
		graphics.fillRect(x + 14, y, 2, 4);
		graphics.fillRect(x + 4, y + 2, 4, 2);
		graphics.fillRect(x + 16, y + 4, 4, 4);
		graphics.fillRect(x + 10, y + 6, 6, 2);
		graphics.fillRect(x, y + 12, 8, 2);
		graphics.fillRect(x, y + 17, 2,3);
		graphics.fillRect(x+8, y + 17, 6,2);
		
		graphics.setColor(Color.BLACK);
		graphics.fillRect(x + 12, y, 2, 2);
		graphics.fillRect(x, y + 4, 2, 2);
		graphics.fillRect(x + 14, y + 4, 2, 2);
		graphics.fillRect(x + 8, y + 8, 2, 2);
		graphics.fillRect(x + 8, y + 12, 2, 2);
		graphics.fillRect(x+18, y + 8, 2, 4);
		
		graphics.setColor(Color.GRAY);
		graphics.fillRect(x+(gp.tileSize - 4), y, 3, gp.tileSize);
		
		// Roof
		graphics.setColor(TileManager.bedrockBlock);
		graphics.fillRect(x-12, y - 4, gp.tileSize+11, 4);
		graphics.fillRect(x-8, y - 8, gp.tileSize+4, 4);
		graphics.fillRect(x-4, y - 12, gp.tileSize-3, 4);
		graphics.fillRect(x, y - 16, gp.tileSize-11, 4);

		
	}
}
