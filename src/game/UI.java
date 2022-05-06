package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import tile.Tile;
import tile.TileManager;


public class UI {
	
	GamePanel gp;
	Font arial_40;
	public Graphics2D g2;
	public int commandNum = 0;
	
	public static Color darkerGray1 = new Color(110, 110, 110);
	public static Color darkerGray2 = new Color(120, 120, 120);
	public static Color stoneBlock = new Color(140, 140, 140);
	public static Color lighterGray1 = new Color(150, 150, 150);
	public static Color lighterGray2 = new Color(165, 165, 165);
	
	public UI(GamePanel gp) {
		this.gp = gp;
		
		arial_40 = new Font("Arial", Font.PLAIN, 40);
	}
	
	public void draw(Graphics2D g2) {
		this.g2 = g2;
		
		g2.setFont(arial_40);
		g2.setColor(new Color(50, 50, 50));
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30f));
		g2.drawString("KILL COUNT", 30, 60);
		
		g2.setColor(new Color(220, 220, 220));
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40f));
		g2.drawString(String.valueOf(gp.player.killCount), 30, 110);
		
		
		if (gp.gameState == gp.gameOverState) {
			drawGameOverScreen();
		}
		if (gp.gameState == gp.menuState) {
			drawMenuScreen();
		}
	}
		
	public void drawMenuScreen() {

		g2.setColor(new Color(50, 50, 50));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		int x;
		int y;
		String text;
		
		// TITLE
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 70f));
		text = "NetherEnding";

		// Shadow
		g2.setColor(Color.BLACK);
		x = getXforCenteredText(text);
		y = gp.tileSize * 8;
		g2.drawString(text, x, y);
		
		// Main
		g2.setColor(new Color(170, 170, 170));
		g2.drawString(text, x-4, y-4);
		
		
		// START GAME
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 34f));
		text = "START GAME";
		x = getXforCenteredText(text);
		int boxCoordX = x;
		y = gp.tileSize * 12;
		
		// Box
		for (int i = 0; i < 250; i+=gp.tileSize) {
			for (int j = 0; j < 60; j+= gp.tileSize) {
				createStoneBlock(g2, boxCoordX-gp.tileSize + i, y-gp.tileSize* 2-5 + j, gp);	
			}
		}
			
		// Main
		if (commandNum == 0) {
			g2.setColor(new Color(74, 255, 233));
			g2.drawString(text, x-4, y-4);
			
			g2.setColor(Color.RED);
			g2.fillRect(boxCoordX-gp.tileSize-5, y-gp.tileSize * 2 - 5, gp.tileSize/2, gp.tileSize * 3);
		} else {
			g2.setColor(new Color(50, 50, 50));
			g2.drawString(text, x-4, y-4);
		}

		
		// UPDGRADES
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 34f));
		
		// Box
		for (int i = 0; i < 250; i+=gp.tileSize) {
			for (int j = 0; j < 60; j+= gp.tileSize) {
				createStoneBlock(g2, boxCoordX-gp.tileSize + i, y+gp.tileSize+15 + j, gp);	
			}
		}
																										
		text = "UPGRADES";
		x = getXforCenteredText(text);
		y = gp.tileSize * 16;
		
		// Main
		if (commandNum == 1) {
			g2.setColor(new Color(74, 255, 233));
			g2.drawString(text, x-4, y-4);
			
			g2.setColor(Color.RED);
			g2.fillRect(boxCoordX-gp.tileSize-5, y-gp.tileSize * 2 - 5, gp.tileSize/2, gp.tileSize * 3);
		}	else {
			g2.setColor(new Color(50, 50, 50));
			g2.drawString(text, x-4, y-4);
		}
																													
		// INFO
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 34f));
		text = "INFO";
		x = getXforCenteredText(text);
		y = gp.tileSize * 20;
		
		// Box
		for (int i = 0; i < 250; i+=gp.tileSize) {
			for (int j = 0; j < 60; j+= gp.tileSize) {
				createStoneBlock(g2, boxCoordX-gp.tileSize + i,y-gp.tileSize*2-5 + j, gp);	
			}
		}
			
		// Main
		if (commandNum == 2) {
			g2.setColor(new Color(74, 255, 233));
			g2.drawString(text, x-4, y-4);
			
			g2.setColor(Color.RED);
			g2.fillRect(boxCoordX-gp.tileSize-5, y-gp.tileSize * 2 - 5, gp.tileSize/2, gp.tileSize * 3);
		} else {
			g2.setColor(new Color(50, 50, 50));
			g2.drawString(text, x-4, y-4);
		}
	}
	
	public void drawGameOverScreen() {

		g2.setColor(new Color(0,0,0,150));
		g2.fillRect(0,0, gp.screenWidth, gp.screenHeight);
		
		int x;
		int y;
		String text;
		
		// GAME OVER
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));
		text = "Game Over";

		// Shadow
		g2.setColor(Color.BLACK);
		x = getXforCenteredText(text);
		y = gp.tileSize * 15;
		g2.drawString(text, x, y);
		
		// Main
		g2.setColor(Color.WHITE);
		g2.drawString(text, x-4, y-4);
		
		
		// RETRY
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 34f));
		text = "RETRY";
 
		// Shadow
		g2.setColor(Color.BLACK);
		x = getXforCenteredText(text);
		y = gp.tileSize * 18;
		g2.drawString(text, x, y);
		
		// Main
		g2.setColor(new Color(170, 170, 170));
		g2.drawString(text, x-4, y-4);
		if (commandNum == 1) {
			g2.drawString(">", x-14-gp.tileSize, y);
		}
	}
	
	public int getXforCenteredText(String text) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		return gp.screenWidth/2 - length/2;
	}
	
	public void createStoneBlock(Graphics2D graphics, int x, int y, GamePanel gp) {
		
		// Main Diamond Block
		graphics.setColor(stoneBlock);
		graphics.fillRect(x, y, gp.tileSize, gp.tileSize);
		   
		// Smaller pixels inside of Diamond Block
		graphics.setColor(darkerGray1);
		graphics.fillRect(x + 5, y + 5, 2, 2);
		graphics.fillRect(x + 1, y + 5, 2, 2);
		graphics.fillRect(x + 7, y + 7, 2, 2);
		graphics.fillRect(x + 18, y + 2, 2, 2);
		graphics.fillRect(x + 18, y + 10, 2, 2);
		graphics.fillRect(x + 16, y + 12, 2, 2);
		graphics.fillRect(x + 18, y + 14, 2, 2);
		   
		graphics.setColor(lighterGray1);
		graphics.fillRect(x + 3, y + 7, 4, 2);
		graphics.fillRect(x + 9, y + 7, 2, 2);
		graphics.fillRect(x + 10, y + 13, 4, 2);
		   
		graphics.setColor(lighterGray2);
		graphics.fillRect(x + 3, y + 3, 6, 2);
		graphics.fillRect(x + 1, y + 7, 2, 4);
		graphics.fillRect(x + 4, y + 5, 2, 2);
		graphics.fillRect(x + 2, y + 6, 4, 2);
		graphics.fillRect(x + 8, y + 6, 2, 2);
		graphics.fillRect(x + 12, y + 10, 6, 2);
		graphics.fillRect(x + 14, y + 14, 4, 2);
		graphics.fillRect(x + 15, y + 17, 2, 2);
		graphics.fillRect(x + 9, y, 4, 2);	
	}
}
