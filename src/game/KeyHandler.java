package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	public boolean upPressed;
	public boolean downPressed;
	public boolean leftPressed;
	public boolean rightPressed;
	public boolean enterPressed;
	
	GamePanel gp;
	Main main;
	
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		
		// MENU STATE
		if (gp.gameState == gp.menuState) {
			if (code == KeyEvent.VK_W) {
				if (gp.ui.commandNum > 0) {
					gp.ui.commandNum--;
				}				
			}
			if (code == KeyEvent.VK_A) {
				if (gp.ui.commandNum < 2) {
					gp.ui.commandNum++;
				}	
			}
			if (code == KeyEvent.VK_ENTER) {
				if (gp.ui.commandNum == 0) {
					gp.gameState = gp.playState;
				}	
			}
			if (code == KeyEvent.VK_ENTER) {
				if (gp.ui.commandNum == 1) {
					gp.gameState = gp.upgradeState;
				}	
			}
			if (code == KeyEvent.VK_ENTER) {
				if (gp.ui.commandNum == 2) {
					gp.gameState = gp.infoState;
				}	
			}
		}
		
		// PLAY STATE
		if (gp.gameState == gp.playState) {
			if (code == KeyEvent.VK_W) {
				upPressed = true;
			}
			if (code == KeyEvent.VK_A) {
				downPressed = true;
			}
			if (code == KeyEvent.VK_S) {
				leftPressed = true;
			}
			if (code == KeyEvent.VK_D) {
				rightPressed = true;
			}
			if (code == KeyEvent.VK_ENTER) {
				enterPressed = true;
			}
		}
		
		
		// GAME OVER STATE
		if (gp.gameState == gp.gameOverState) {
			if (code == KeyEvent.VK_W) {
				if (gp.ui.commandNum > 1) {
					gp.ui.commandNum--;
				}				
			}
			if (code == KeyEvent.VK_A) {
				if (gp.ui.commandNum < 1) {
					gp.ui.commandNum++;
				}	
			}
			if (code == KeyEvent.VK_ENTER) {
				if (gp.ui.commandNum == 1) {
					gp.gameState = gp.menuState;
					Main.main(null);
				}	
			}
		}
		
		// UPGRADE SCREEN
		if (gp.gameState == gp.upgradeState) {
			if (code == KeyEvent.VK_B) {
				gp.gameState = gp.menuState;
			}
		}
		
		// INFO SCREEN
		if (gp.gameState == gp.infoState) {
			if (code == KeyEvent.VK_B) {
				gp.gameState = gp.menuState;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_W) {
			upPressed = false;
		}
		if (code == KeyEvent.VK_A) {
			downPressed = false;
		}
		if (code == KeyEvent.VK_S) {
			leftPressed = false;
		}
		if (code == KeyEvent.VK_D) {
			rightPressed = false;
		}	
		if (code == KeyEvent.VK_ENTER) {
			enterPressed = false;
		}
	}
	
}
