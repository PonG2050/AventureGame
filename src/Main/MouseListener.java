package Main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import entity.Player;

public class MouseListener extends MouseAdapter{
	public boolean rightClick, leftClick;
	@Override
	public void mousePressed(MouseEvent e) {
		int code = e.getButton();
		if (code == MouseEvent.BUTTON1) {
			leftClick = true;
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		int code = e.getButton();
		if (code == MouseEvent.BUTTON1) {
			leftClick = false;
		}
	}
}
