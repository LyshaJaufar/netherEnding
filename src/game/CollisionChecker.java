package game;

import entity.Entity;
import entity.Player;

public class CollisionChecker {
	GamePanel gp;
	
	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}
	
	public void checkTile(Entity entity) {
		
		int entityLeftX = entity.x + entity.solidArea.x;
		int entityRightX = entity.x + entity.solidArea.x + entity.solidArea.width;
		int entityTopY = entity.y + entity.solidArea.y;
		int entityBottomY = entity.y + entity.solidArea.y + entity.solidArea.height;
		
		int entityLeftCol = entityLeftX/gp.tileSize;
		int entityRightCol = entityRightX/gp.tileSize;
		int entityTopRow = entityTopY/gp.tileSize;
		int entityBottomRow = entityBottomY/gp.tileSize;
		
		gp.tileM.loadMap();

		int tileNum1, tileNum2;
				
		if (entity.direction == "up") {
			entityTopRow = (entityTopY - entity.speed)/gp.tileSize;
			
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
 
			//System.out.println(gp.tileM.tile[tileNum1].collision);

			if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
				entity.collisionUpOn = true;
			}
		}
		
		else if (entity.direction == "down") {
						
			entityBottomRow = (entityBottomY + entity.speed)/gp.tileSize;
			
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			
			if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
				entity.collisionDownOn = true;
			}
		}
		
		else if (entity.direction == "left") {
			entityLeftCol = (entityLeftX - entity.speed)/gp.tileSize;
			
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			
			if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
				entity.collisionLeftOn = true;
			}
		}
		
		else if (entity.direction == "right") {
			entityRightCol = (entityRightX + entity.speed)/gp.tileSize;
			
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			
			if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
				entity.collisionRightOn = true;
			}
		}
	}
	
	public int checkObject(Entity entity, boolean player) {
		int index = 999;
		
		for (int i = 0; i < gp.obj.length; i++) {
			if (gp.obj[i] != null) {
				
				// Get entity's solid area pst
				entity.solidArea.x += entity.x;
				entity.solidArea.y += entity.y;
				
				// Get the object's solid area pst
				gp.obj[i].solidArea.x += gp.obj[i].x;
				gp.obj[i].solidArea.y += gp.obj[i].y;
				
				if (entity.direction == "up") {
					entity.solidArea.y -= entity.speed;
				}
				if (entity.direction == "down") {
					entity.solidArea.y += entity.speed;
				}
				if (entity.direction == "left") {
					entity.solidArea.x -= entity.speed;
				}
				if (entity.direction == "right") {
					entity.solidArea.x += entity.speed;
				}
				
				if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
					if (gp.obj[i].collision == true) {
						entity.collisionOn = true;
					}
					if (player == true) {
						index = i;
					}
				}
				
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
				gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
			}
		}
		
		return index;
	}
	
	public int checkEntity(Entity entity, Entity[] target) {
		int index = 999;
		
		for (int i = 0; i < target.length; i++) {
			if (target[i] != null) {
				
				// Get entity's solid area pst
				entity.solidArea.x += entity.x;
				entity.solidArea.y += entity.y;
				
				// Get the target's solid area pst
				target[i].solidArea.x += target[i].x;
				target[i].solidArea.y += target[i].y;
				
				if (entity.direction == "up") {
					entity.solidArea.y -= entity.speed;
				}
				if (entity.direction == "down") {
					entity.solidArea.y += entity.speed;
				}
				if (entity.direction == "left") {
					entity.solidArea.x -= entity.speed;
				}
				if (entity.direction == "right") {
					entity.solidArea.x += entity.speed;
				}
				
				if (entity.solidArea.intersects(target[i].solidArea)) {
					if (target[i] != entity) {
						entity.collisionOn = true;
						index = i;		
					}
				}
				
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				target[i].solidArea.x = target[i].solidAreaDefaultX;
				target[i].solidArea.y = target[i].solidAreaDefaultY;
			}
		}
		return index;		
	}
	
	public int getCol(GamePanel gp, Entity entity) {
		return entity.x/gp.tileSize;		
	}
	
	public int getRow(GamePanel gp, Entity entity) {
		return entity.y/gp.tileSize;
	}
}
