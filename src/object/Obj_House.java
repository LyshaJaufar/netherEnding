package object;

public class Obj_House extends SuperObject {
	
	public Obj_House() {
		name = "House";
		collision = true;
		
		maxHealthValue = 8;
		healthValue = maxHealthValue;
		
		invincible = true;
		invincibleCounter = 0;
		destroyed = false;
	}
	
}
