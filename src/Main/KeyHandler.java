package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

public class KeyHandler implements KeyListener {

	public boolean Up, Down, Left, Right, Hit,  HitBox = false;

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
		if (code == KeyEvent.VK_F3) {
			if (HitBox == false) {
				HitBox = true;
			} else HitBox = false;
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
