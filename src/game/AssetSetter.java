package game;

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
}
