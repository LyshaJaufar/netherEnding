package game;

import mobs.ZombiePigman;
import object.Obj_House;

public class AssetSetter {
	GamePanel gp;
	
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
		gp.mob[0] = new ZombiePigman(gp, 0);
		gp.obj[0].x = 14 * gp.tileSize;
		gp.obj[0].y = 22 * gp.tileSize;
		gp.mob[1] = new ZombiePigman(gp, 1);
		gp.obj[1].x = 20 * gp.tileSize;
		gp.obj[1].y = 6 * gp.tileSize;

	}
}
