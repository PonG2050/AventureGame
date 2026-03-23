package Main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import entity.Player;

public class MouseListener extends MouseAdapter{
	public boolean rightClick, leftClick;
	public int mouseX;
	public int mouseY;
	@Override
	public void mousePressed(MouseEvent e) {
		int code = e.getButton();
		if (code == MouseEvent.BUTTON1) {
			leftClick = true;
			mouseX = e.getX();
			mouseY = e.getY();
			System.out.println(mouseX + " " + mouseY);
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		int code = e.getButton();
		if (code == MouseEvent.BUTTON1) {
			leftClick = false;
		}
	}
	public void update() {
		
	}
	public void action() {
		
	}
}
