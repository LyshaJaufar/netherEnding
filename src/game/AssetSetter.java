package game;

import mobs.ZombiePigman;
import object.Obj_House;

public class AssetSetter {
	GamePanel gp;
	public int zombiePigmanXCoord[] = new int[] {14, 25, 12, 29, 5, 19, 15, 10, 6, 20};
	public int zombiePigmanYCoord[] = new int[] {22, 10, 4, 14, 15, 21, 9, 18, 13, 7};
	public int zombiePigmanCoordIndex = 0;
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
		if (zombiePigmanCoordIndex == zombiePigmanXCoord.length) {
			zombiePigmanCoordIndex = 0;
		}	
		
		gp.mob.add(new ZombiePigman(gp, zombiePigmanCoordIndex));
		gp.mob.get(zombiePigmanCount).x = zombiePigmanXCoord[zombiePigmanCoordIndex] * gp.tileSize;
		gp.mob.get(zombiePigmanCount).y = zombiePigmanYCoord[zombiePigmanCoordIndex] * gp.tileSize;

		zombiePigmanCount++;
		zombiePigmanCoordIndex++;
	}
}
