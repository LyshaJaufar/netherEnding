package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import game.GamePanel;
import mobs.ZombiePigman;
import object.SuperObject;

public class Entity {
	
	GamePanel gp;
	
	public int x, y;
	public int speed;
	
	public String direction;
	public String name;
	public Rectangle solidArea = new Rectangle(0,0,30,35);
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collisionOn = false;
	public boolean collisionUpOn = false;
	public boolean collisionDownOn = false;
	public boolean collisionLeftOn = false;
	public boolean collisionRightOn = false;
	
	boolean findingGate = false;
	boolean insideVillage = false;
	boolean upperVillageBorder = false;
	boolean lowerVillageBorder = false;
	boolean leftVillageBorder = false;
	boolean rightVillageBorder = false;
	boolean fromSide = false;
		
	
	public ZombiePigman zombiePigman;
	
	public Entity(GamePanel gp, String name) {
		this.gp = gp;
		this.name = name;
	}

	public void draw(Graphics2D g2, GamePanel gamePanel) {
		zombiePigman.createZombiePigman(g2, gp);
		
	}
	
	public void damageHouse(int objIndex) {
		if (objIndex != 999 && gp.obj[objIndex].healthValue > 0) {
			if (gp.obj[objIndex].invincible == true) {
				gp.obj[objIndex].invincibleCounter++;
				if (gp.obj[objIndex].invincibleCounter > 90) {
					gp.obj[objIndex].invincible = false;
					gp.obj[objIndex].invincibleCounter = 0;
				}
			}

			if (gp.obj[objIndex].invincible == false) {
				gp.obj[objIndex].healthValue--;
				gp.obj[objIndex].invincible = true;
			}
		} else if (objIndex != 999 && gp.obj[objIndex].healthValue == 0) {
			gp.obj[objIndex].destroyed = true;
		}
	}
	
	public void setAction(int currentPstX, int currentPstY, int targetX, int targetY, int col, int row) {}
	public void update(int currentPstX, int currentPstY, int targetX, int targetY) {

		int upperGatePst = 20*gp.tileSize;
		int lowerGatePst = 16*gp.tileSize;
		int leftGatePst = 14*gp.tileSize;
		int rightGatePst = 15*gp.tileSize;
		
		int firstTargetX = gp.obj[0].x;
		int firstTargetY = gp.obj[0].y;
		
		// Choose which house to attack
		if (gp.obj[0].destroyed == true && gp.obj[1].destroyed == false) {
			targetX = gp.obj[1].x;
			targetY = gp.obj[1].y;
		}
		else if (gp.obj[1].destroyed == true) {
			targetX = gp.obj[2].x;
			targetY = gp.obj[2].y;
		}

		
		int col = gp.collisionChecker.getCol(gp, this);
		int row = gp.collisionChecker.getRow(gp, this);
		setAction(currentPstX, currentPstY, firstTargetX, firstTargetY, col, row);
		
		collisionOn = false;
		gp.collisionChecker.checkTile(this);
		gp.collisionChecker.checkEntity(this, gp.mob);

		int objIndex = gp.collisionChecker.checkObject(this, true);
		damageHouse(objIndex);

		// Path finding
		// if mob is above or below the village, go up/down to the village at the point where the house is
		if (collisionOn == false && currentPstX-1 != firstTargetX && findingGate == false && !(row > 9 && row < 16)) {
			if (direction == "left") {
				x -= speed;
			}
			else if (direction == "right") {
				x += speed;
			}
		}	
		else if (collisionOn == false && currentPstX-1 == firstTargetX && findingGate == false && !(row > 9 && row < 16)) {
			if (direction == "up") {
				y -= speed;
			}
			else if (direction == "down") {
				y += speed;
			}
		}	
		
		// if mob is to the left/right of the village, go up/down at the point where the house is
		else if (row > 9 && row < 16) {
			if (collisionOn == false && currentPstY != firstTargetY && findingGate == false) {
				
				if (direction == "up") {
					y -= speed;
				}
				else if (direction == "down") {
					y += speed;
				}
			}
		}
		// mob has reached upper village border. move to the gate
		if (collisionDownOn == true && insideVillage == false) {
			if (currentPstX-1 != upperGatePst) {
				findingGate = true;
				upperVillageBorder = true;	
				
				if (currentPstX-1 < upperGatePst) {
					direction = "right";
					x += speed;
				} else if (currentPstX-1 > upperGatePst) {
					direction = "left";
					x -= speed;
				}
			}
		}
		// mob has reached lower village border. move to the gate
		if (collisionUpOn == true && insideVillage == false) {
			if (currentPstX-1 != lowerGatePst) {
				findingGate = true;
				lowerVillageBorder = true;
				
				if (currentPstX-1 < lowerGatePst) {
					direction = "right";
					x += speed;
				} else if (currentPstX-1 > lowerGatePst) {
					direction = "left";
					x -= speed;
				}				
			}
		}
		// mob has reached left village border. move to the gate
		if (collisionRightOn == true && insideVillage == false) {
			if (currentPstY != leftGatePst) {
				findingGate = true;
				leftVillageBorder = true;	
				
				if (currentPstY > leftGatePst) {
					direction = "up";
					y -= speed;
				} else if (currentPstY < leftGatePst) {
					direction = "down";
					y += speed;
				}	
			}
		}
		// mob has reached right village border. move to the gate
		if (collisionLeftOn == true && insideVillage == false) {
			if (currentPstY != rightGatePst) {
				findingGate = true;
				rightVillageBorder = true;	
				
				if (currentPstY > rightGatePst) {
					direction = "up";
					y -= speed;
				} else if (currentPstY < rightGatePst) {
					direction = "down";
					y += speed;
				}
			}
		}
		// found gate from upper border. move into the village
		if (findingGate == true && upperVillageBorder == true && currentPstX-1 == upperGatePst && collisionOn == false) {
			if (currentPstY+10 < targetY) {
				direction = "down";
				y += speed;
			}
			else {
				insideVillage = true;
				if (currentPstX != targetX) {
					if (currentPstX > targetX) {
						direction = "left";
						x -= speed;
					} else if (currentPstX < targetX) {
						direction = "right";
						x += speed;
					}
				}
			}
		}
		// found gate from lower border. move into the village
		if (findingGate == true && lowerVillageBorder == true && currentPstX-1 == lowerGatePst && collisionOn == false) {
			if (currentPstY > targetY-15) {
				direction = "up";
				y -= speed;
			}
			else {
				insideVillage = true;
				if (currentPstX != targetX) {
					if (currentPstX > targetX) {
						direction = "left";
						x -= speed;
					} else if (currentPstX < targetX) {
						direction = "right";
						x += speed;
					}
				}
			}
		}
		// found gate from left border. move into the village
		if (findingGate == true && leftVillageBorder == true && currentPstY == leftGatePst && collisionOn == false) {
			fromSide = true;
			if (currentPstX < targetX) {
				direction = "right";
				x += speed;
			}
			else {
				insideVillage = true;
				if (currentPstY != targetY) {
					if (currentPstY < targetY) {
						direction = "down";
						y += speed;
					} else if (currentPstY > targetY) {
						direction = "up";
						y -= speed;
					}
				}
			}
		}
		// found gate from right border. move into the village
		if (findingGate == true && rightVillageBorder == true && currentPstY == rightGatePst) {
			fromSide = true;
			if (currentPstX-25 > targetX) {
				direction = "left";
				x -= speed;
			}
			else {
				insideVillage = true;
				if (currentPstY != targetY) {
					if (currentPstY < targetY) {
						direction = "down";
						y += speed;
					} else if (currentPstY > targetY) {
						direction = "up";
						y -= speed;
					}
				}
			}
		}

		// mob moves to house
		if (insideVillage && !fromSide) {
			if (currentPstX != targetX+gp.tileSize) {
				if (currentPstX > targetX) {
					direction = "left";
					x -= speed;
				} else if (currentPstX < targetX) {
					direction = "right";
					x += speed;
				}
			}
		}
		else if (insideVillage && fromSide) {
			;
			if (currentPstY != targetY-20) {

				if (currentPstY < targetY-20) {
					direction = "down";
					y += speed;
				}
				else if (currentPstY > targetY-20) {
					direction = "up";
					y -= speed;
				}
			}
		}

	}

	
}
