package game;

import java.util.Random;

import mobs.ZombiePigman;
import object.Obj_House;

public class AssetSetter {
	GamePanel gp;
	public int zombiePigmanXCoord;
	public int zombiePigmanYCoord;
	public int zombiePigmanCount = 0;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		gp.obj[0] = new Obj_House();
		gp.obj[0].x = 14 * gp.tileSize;
		gp.obj[0].y = 16 * gp.tileSize;
		
		gp.obj[1] = new Obj_House();
		gp.obj[1].x = 17 * gp.tileSize;
		gp.obj[1].y = 14 * gp.tileSize;
		
		gp.obj[2] = new Obj_House();
		gp.obj[2].x = 20* gp.tileSize;
		gp.obj[2].y = 15 * gp.tileSize;
	}
	
	
	public void setMob() {

		int xCoordMax = 29;
		int xCoordMin = 10;
		int yCoordMax = 25;
		int yCoordMin = 5;
			
		zombiePigmanYCoord = (int)Math.floor(Math.random()*(yCoordMax-yCoordMin+1)+yCoordMin);
		zombiePigmanXCoord = (int)Math.floor(Math.random()*(xCoordMax-xCoordMin+1)+xCoordMin);
				
		System.out.println("x first: " + zombiePigmanXCoord);
		System.out.println("y first: " + zombiePigmanYCoord);
		
		// if mob spawns inside village or on the gate, randomly add/subtract to ensure it is outside the village
		if ((zombiePigmanXCoord > 11 && zombiePigmanXCoord < 24) && (zombiePigmanYCoord > 9 && zombiePigmanYCoord < 17)) {
			Random rand = new Random(); 

		    int intRandom = rand.nextInt(3); 
		    
		    if (intRandom == 0) {
		    	if (zombiePigmanXCoord >= 18) {
		    		zombiePigmanXCoord += 6;
		    	} else {
		    		zombiePigmanXCoord -= 6;
		    	}
		    }
		    else if (intRandom == 1) {
		    	zombiePigmanYCoord += 8;
		    }
		    else if (intRandom == 2) {
		    	zombiePigmanYCoord -= 8;
		    }
		}
		
		gp.mob.add(new ZombiePigman(gp));
		gp.mob.get(zombiePigmanCount).x = zombiePigmanXCoord * gp.tileSize;
		gp.mob.get(zombiePigmanCount).y = zombiePigmanYCoord * gp.tileSize;

		System.out.println("x: " + zombiePigmanXCoord);
		System.out.println("y: " + zombiePigmanYCoord);
		zombiePigmanCount++;
	}
}
