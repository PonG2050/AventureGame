package UI;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import Main.GamePanel;
public class UIHealthBar extends UIComponent {
	
	public BufferedImage heartFull, heartHalf, heartBlank;
	private int full, half, blank, maxHearts;

	public UIHealthBar(GamePanel gp, int x, int y, int width, int height) {
		super(gp, x, y, width, height);
	}

	@Override
	public void update() {
		int life = gp.player.life;
		int maxLife = gp.player.maxLife;
		

		full = life / 2;
		half = life % 2;
		maxHearts = maxLife / 2;
		blank = maxHearts - full - half;
	}

	@Override
	public void draw(Graphics2D g2) {
		int drawX = x;
		int spacing = width + 4;
		for (int i = 0; i < full; i++) {
			if (heartFull != null) g2.drawImage(heartFull, drawX, y, width, height, null);
			drawX += spacing;
		}
		if (half == 1) {
			if (heartHalf != null) g2.drawImage(heartHalf, drawX, y, width, height, null);
			drawX += spacing;
		}
		for (int i = 0; i < blank; i++) {
			if (heartBlank != null) g2.drawImage(heartBlank, drawX, y, width, height, null);
			drawX += spacing;
		}
	}
}