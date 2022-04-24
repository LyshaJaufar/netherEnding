package tile;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.color.ColorSpace;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import game.GamePanel;

public class TileManager {
	GamePanel gp;
	Tile[] tile;
	int mapTileNum[][];
	
	static Color darkerGray1 = new Color(110, 110, 110);
	static Color darkerGray2 = new Color(120, 120, 120);
	static Color stoneBlock = new Color(140, 140, 140);
	static Color lighterGray1 = new Color(150, 150, 150);
	static Color lighterGray2 = new Color(165, 165, 165);
	static Color diamondBlock = new Color(74, 255, 233);
	
	static Color lavaBlock = new Color(236, 60, 0);
	static Color darkOrange = new Color(253, 95, 0);
	static Color orange = new Color(251, 132, 0);
	static Color fadedYellow = new Color(255, 200, 9);
	static Color brightYellow = new Color(255, 242, 85);
	
	static Color darkPrismarineBlock = new Color(13, 120, 115);
	static Color lighterGreen1 = new Color(0, 86, 63);
	static Color ligherGreen2 = new Color(21, 79, 76);
	static Color fungusGreen = new Color(0, 66, 36);
	static Color darkerGreen1 = new Color(18, 53, 36);
	static Color darkerGreen2 = new Color(1, 50, 32);
	
	static Color magmaBlock = new Color(62, 6, 6);
	static Color darkerBrown1 = new Color(92, 14, 14);
	
	static Color netherBrick = new Color(114,50,50);
	static Color netherDarkerBrown = new Color(81,21,21);
	static Color netherLigherBrown = new Color(150, 69, 69);
	
	static Color netherrackBlock = new Color(138, 84, 81);
	static Color reddishOrange = new Color(122, 17, 17);
	static Color netherrackPeach = new Color(214, 137, 133);
	
	static Color bedrockBlock = Color.GRAY;
	
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		tile = new Tile[10];
		mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
		
		loadMap();
	}
	
	public void loadMap() {
		
		try {
			InputStream inputStream = getClass().getResourceAsStream("/maps/map.txt");
			BufferedReader bufferedR = new BufferedReader(new InputStreamReader(inputStream));
						
			int col = 0;
			int row = 0;
			while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
				 String line = bufferedR.readLine();
				 
				 while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
					 String numbers[] = line.split(",");
					 
					 int num = Integer.parseInt(numbers[col]);
					 
					 mapTileNum[col][row] = num;
					 col++;
				 }
				 if (col == gp.maxScreenCol) {
					 col = 0;
					 row++;
				 }
			}
			bufferedR.close();
		} catch (Exception e){
	
		}
	}
	
	public void draw(Graphics2D g2) {		
		for (int i = 0, y = 0; i < gp.maxScreenRow; i++, y+= gp.tileSize) {
			for (int j = 0, x = 0; j < gp.maxScreenCol; j++, x+=gp.tileSize) {
				int tileNum = mapTileNum[j][i];

				if (tileNum == 0) {
					createLavaBlock(g2, x, y, gp);
				}
				else if (tileNum == 1) {
					createNetherrackBlock(g2, x, y, gp);
				}
				else if (tileNum == 2) {
					createNetherrackBlock(g2, x, y, gp);
				}
				else if (tileNum == 3) {
					createNetherBrick(g2, x, y, gp);
				}
				else if (tileNum == 4) {
					createMagmaBlock(g2, x, y, gp);
				}
				else if (tileNum == 5) {
					createNetherBrick(g2, x, y, gp);
				}
			}
		}
	}
	
	public static void createLavaBlock(Graphics2D graphics, int x, int y, GamePanel gp) {

		// Main Lava Block
		graphics.setColor(lavaBlock);
		graphics.fillRect(x, y, gp.tileSize, gp.tileSize);
				   
		// Smaller pixels inside of Lava Block
		graphics.setColor(brightYellow);
		graphics.fillRect(x + 9, y + 9, 2, 2);
		graphics.fillRect(x + 15, y + 15, 2, 2);
		graphics.fillRect(x, y + 15, 2, 2);
				   
		graphics.setColor(fadedYellow);
		graphics.fillRect(x + 11, y + 9, 2, 2);
		graphics.fillRect(x + 8, y + 8, 2, 2);
		graphics.fillRect(x + 8, y + 11, 2, 2);
		graphics.fillRect(x + 18, y + 9, 2, 2);
		graphics.fillRect(x + 9, y + 4, 2, 2);
		graphics.fillRect(x + 5, y + 5, 2, 2);
		graphics.fillRect(x + 6, y + 17, 2, 2);
		graphics.fillRect(x + 17, y + 10, 1, 2);
		graphics.fillRect(x + 15, y + 17, 2, 2);
				   
		graphics.setColor(orange);
		graphics.fillRect(x + 18, y + 6, 2, 3);
		graphics.fillRect(x + 17, y + 6, 2, 2);
		graphics.fillRect(x + 2, y + 6, 2, 4);
		graphics.fillRect(x + 5, y + 3, 2, 2);
		graphics.fillRect(x + 3, y + 4, 2, 3);
		graphics.fillRect(x + 4, y + 7, 4, 2);
		graphics.fillRect(x + 8, y + 6, 4, 2);
		graphics.fillRect(x + 13, y + 15, 2, 3);
		graphics.fillRect(x, y + 13, 3, 2);
		graphics.fillRect(x + 14,  y, 4, 2);
		graphics.fillRect(x + 8, y + 13, 4, 2);
		graphics.fillRect(x + 7, y + 9, 1, 4);
		graphics.fillRect(x + 5, y + 15, 4, 2);
		graphics.fillRect(x + 18, y + 12, 2, 2);
		
	}
	
	public static void createMagmaBlock(Graphics2D graphics, int x, int y, GamePanel gp) {
		// Main Magma Block
		graphics.setColor(magmaBlock);
		graphics.fillRect(x, y, gp.tileSize, gp.tileSize);
		
		// Smaller pixels inside of Magma Block
		graphics.setColor(darkerBrown1);
		graphics.fillRect(x + 9, y + 9, 2, 2);
		graphics.fillRect(x + 15, y + 15, 2, 2);
		graphics.fillRect(x, y + 15, 2, 2);
				   
		graphics.setColor(orange);
		graphics.fillRect(x + 11, y + 9, 2, 2);
		graphics.fillRect(x + 8, y + 8, 2, 2);
		graphics.fillRect(x + 8, y + 11, 2, 2);
		graphics.fillRect(x + 18, y + 9, 2, 2);
		graphics.fillRect(x + 9, y + 4, 2, 2);
		graphics.fillRect(x + 5, y + 5, 2, 2);
		graphics.fillRect(x + 6, y + 17, 2, 2);
		graphics.fillRect(x + 17, y + 10, 1, 2);
		graphics.fillRect(x + 15, y + 17, 2, 2);
				   
		graphics.setColor(darkOrange);
		graphics.fillRect(x + 18, y + 6, 2, 3);
		graphics.fillRect(x + 17, y + 6, 2, 2);
		graphics.fillRect(x + 2, y + 6, 2, 4);
		graphics.fillRect(x + 5, y + 3, 2, 2);
		graphics.fillRect(x + 3, y + 4, 2, 3);
		graphics.fillRect(x + 4, y + 7, 4, 2);
		graphics.fillRect(x + 8, y + 6, 4, 2);
		graphics.fillRect(x + 13, y + 15, 2, 3);
		graphics.fillRect(x, y + 13, 3, 2);
		graphics.fillRect(x + 14,  y, 4, 2);
		graphics.fillRect(x + 8, y + 13, 4, 2);
		graphics.fillRect(x + 7, y + 9, 1, 4);
		graphics.fillRect(x + 5, y + 15, 4, 2);
		graphics.fillRect(x + 18, y + 12, 2, 2);
		
	}
	
	public static void createNetherBrick(Graphics2D graphics, int x, int y, GamePanel gp) {
		
		// Main Nether Brick Block
		graphics.setColor(netherBrick);
		graphics.fillRect(x, y, gp.tileSize, gp.tileSize);
		
		// Smaller pixels inside of Nether Brick
		graphics.setColor(netherDarkerBrown);
		graphics.fillRect(x, y, gp.tileSize, 2);
		graphics.fillRect(x, y + 6, gp.tileSize, 2);
		graphics.fillRect(x, y + 12, gp.tileSize, 2);
		graphics.fillRect(x, y + 18, gp.tileSize, 2);
		
		graphics.fillRect(x + 8, y + 1, 2, 5);
		graphics.fillRect(x + 16, y + 1, 2, 5);
		
		graphics.fillRect(x + 6, y + 7, 2, 5);
		graphics.fillRect(x + 14, y + 7, 2, 5);
		
		graphics.fillRect(x + 4, y + 13, 2, 5);
		graphics.fillRect(x + 12, y + 13, 2, 5);
		
		graphics.setColor(netherLigherBrown);
		graphics.fillRect(x, y + 2, 2, 2);
		graphics.fillRect(x + 4, y + 2, 2, 2);
		graphics.fillRect(x + 14, y + 2, 2, 2);
		graphics.fillRect(x + 2, y + 4, 2, 2);
		graphics.fillRect(x + 18, y + 4, 2, 2);
		graphics.fillRect(x + 6, y + 4, 2, 2);
		
		graphics.fillRect(x + 12, y + 8, 2, 2);
		graphics.fillRect(x + 2, y + 8, 2, 2);
		graphics.fillRect(x + 16, y + 10, 2, 2);
		graphics.fillRect(x + 8, y + 10, 2, 2);
		graphics.fillRect(x + 10, y + 10, 2, 2);
		
		graphics.fillRect(x + 2, y + 14, 2, 2);
		graphics.fillRect(x + 14, y + 14, 2, 2);
		graphics.fillRect(x + 18, y + 14, 2, 2);
		graphics.fillRect(x, y + 16, 2, 2);
		graphics.fillRect(x + 6, y + 16, 2, 2);
		graphics.fillRect(x + 10, y + 16, 2, 2);

	}
	
	public static void createNetherrackBlock(Graphics2D graphics, int x, int y, GamePanel gp) {
		
		// Main Netherrack Block
		graphics.setColor(netherrackBlock);
		graphics.fillRect(x, y, gp.tileSize, gp.tileSize);
		
		// Smaller pixels inside of Netherrack Block
		graphics.setColor(netherLigherBrown);
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
		
		graphics.setColor(netherrackPeach);
		graphics.fillRect(x + 8, y, 4, 4);
		graphics.fillRect(x + 14, y, 2, 4);
		graphics.fillRect(x + 4, y + 2, 4, 2);
		graphics.fillRect(x + 16, y + 4, 4, 4);
		graphics.fillRect(x + 10, y + 6, 6, 2);
		graphics.fillRect(x, y + 12, 8, 2);
		graphics.fillRect(x, y + 17, 2,3);
		graphics.fillRect(x+8, y + 17, 6,2);
		
		graphics.setColor(reddishOrange);
		graphics.fillRect(x + 12, y, 2, 2);
		graphics.fillRect(x, y + 4, 2, 2);
		graphics.fillRect(x + 14, y + 4, 2, 2);
		graphics.fillRect(x + 8, y + 8, 2, 2);
		graphics.fillRect(x + 8, y + 12, 2, 2);
		graphics.fillRect(x+18, y + 8, 2, 4);
		graphics.fillRect(x + 12, y + 10, 4, 3);
		graphics.fillRect(x + 2, y + 14, 2, 4);
		graphics.fillRect(x + 6, y + 18, 2, 2);
		graphics.fillRect(x + 10, y + 10, 2, 2);
		graphics.fillRect(x + 16, y, 4, 2);
		graphics.fillRect(x + 8, y + 8, 2, 2);
		graphics.fillRect(x + 18, y + 16, 2, 4);
		
		graphics.setColor(netherBrick);
		graphics.fillRect(x + 2, y + 2, 4, 2);
		graphics.fillRect(x + 4, y +12, 4, 2);
		graphics.fillRect(x + 8, y + 18, 2, 2);
		graphics.fillRect(x + 18, y + 6, 2, 2);
		
		graphics.setColor(fadedYellow);
		graphics.fillRect(x + 4, y, 2, 2);
		graphics.fillRect(x + 2, y + 6, 2, 2);
		graphics.fillRect(x + 16, y + 14, 2, 2);
		
	}
	   
	public static void createBedrock(Graphics2D graphics, int x, int y, GamePanel gp) {
		
		// Main Bedrock Block
		graphics.setColor(bedrockBlock);
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

		
	}
	
	public static void createDiamondBlock(Graphics2D graphics, int x, int y, GamePanel gp) {
			   
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
		 
		graphics.setColor(diamondBlock);
		graphics.fillRect(x + 2, y + 1, 2, 2);
		graphics.fillRect(x + 12, y + 3, 2, 2);
		graphics.fillRect(x + 10, y + 11, 4, 2);
		graphics.fillRect(x + 6, y + 16, 2, 2);
		graphics.fillRect(x + 12, y  + 16, 2, 2);
		graphics.fillRect(x + 12, y + 7, 5, 2);
		graphics.fillRect(x + 3, y + 9, 2, 2);
		graphics.fillRect(x + 10, y + 12, 2, 2);
		graphics.fillRect(x + 4, y + 12, 4, 2);
			   
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
	
	public static void createStoneBlock(Graphics2D graphics, int x, int y, GamePanel gp) {
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
	
	public static void createDarkPrismarineBlock(Graphics2D graphics, int x, int y, GamePanel gp) {
		// Main Dark Prismarine Block
		graphics.setColor(darkPrismarineBlock);
		graphics.fillRect(x, y, gp.tileSize, gp.tileSize);

		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				// Smaller pixels inside of Diamond Block
				graphics.setColor(lighterGreen1);
				graphics.fillRect(x + 2 + (i * 6), y + (j * 6), 2, 2);
				graphics.fillRect(x + (i * 6), y + 2 + (j * 6), 2, 2);

				graphics.setColor(ligherGreen2);
				graphics.fillRect(x + 4 + (i * 6), y + (j * 6), 2, 2);
				graphics.fillRect(x + 2 + (i * 6), y + 2 + (j * 6), 2, 2);
				graphics.fillRect(x + (i * 6), y + 4 + (j * 6), 2, 2);
				
				graphics.setColor(darkerGreen2);
				graphics.fillRect(x + 6 + (i * 6), y + 2 + (j * 6), 2, 2);
				graphics.fillRect(x + 6 + (i * 6), y + 4 + (j * 6), 2, 2);
				graphics.fillRect(x + 2 + (i * 6), y + 6 + (j * 6), 2, 2);
				graphics.fillRect(x + 4 + (i * 6), y + 6 + (j * 6), 2, 2);
				
				graphics.setColor(darkerGreen1);
				graphics.fillRect(x + (i * 6), y + 6 + (j * 6), 2, 2);
				graphics.fillRect(x + 6 + (i * 6), y + (j * 6), 2, 2);
				graphics.fillRect(x + 6 + (i * 6), y + 6 + (j * 6), 2, 2);
				
				graphics.setColor(fungusGreen);
				graphics.fillRect(x + 4 + (i * 6), y + 2 + (j * 6), 2, 2);
				graphics.fillRect(x + 4 + (i * 6), y + 4 + (j * 6), 2, 2);
				graphics.fillRect(x + 2 + (i * 6), y + 6 + (j * 6), 2, 2);
			}
		}

	}

}
