package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
	GamePanel gp;
	public boolean Up, Down, Left, Right, Hit,  HitBox = false, E;
	public int slot = 1;
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();

		if (code == KeyEvent.VK_W) {
			Up = true;
		}
		if (code == KeyEvent.VK_S) {
			Down = true;
		}

		if (code == KeyEvent.VK_A) {
			Left = true;
		}

		if (code == KeyEvent.VK_D) {
			Right = true;
		}
		// HITBOX KEY
		if (code == KeyEvent.VK_F3) HitBox = !HitBox;
		// INVENTORT KEY
		if (code == KeyEvent.VK_E) E = !E;
		if (code == KeyEvent.VK_1) slot = 1;
		if (code == KeyEvent.VK_2) slot = 2;
		if (code == KeyEvent.VK_3) slot = 3;
		if (code == KeyEvent.VK_4) slot = 4;
		if (code == KeyEvent.VK_5) slot = 5;
		if (code == KeyEvent.VK_ESCAPE) {
			gp.gameState = (gp.gameState == gp.playState) ? gp.pauseState : gp.playState;
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();

		if (code == KeyEvent.VK_W) {
			Up = false;
		}
		if (code == KeyEvent.VK_S) {
			Down = false;
		}

		if (code == KeyEvent.VK_A) {
			Left = false;
		}

		if (code == KeyEvent.VK_D) {
			Right = false;
		}
	}
	
}
