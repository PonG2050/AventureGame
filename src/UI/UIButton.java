package UI;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Main.GamePanel;

public class UIButton extends UIComponent{

	private String name;
	public UIButton(GamePanel gp, String name, BufferedImage image, int x, int y, int w, int h) {
		super(gp, x, y, w, h);
		this.name = name;
		this.image = image;
	}
	public boolean isClicked() {
		return (gp.mouseL.mouseX > x && gp.mouseL.mouseX < x + width &&
				gp.mouseL.mouseY > y && gp.mouseL.mouseY < y + height &&
				gp.mouseL.leftClick);
	}
	@Override
	public void update() {}
	@Override
	public void draw(Graphics2D g2) {
		g2.drawImage(image, x, y, width, height, null);
	}
}
