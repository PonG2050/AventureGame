package UI;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Main.GamePanel;

public class UIEnergyBar extends UIComponent{
	public BufferedImage energyFull, energyHalf, energyBlank;
	private int full, half, blank, maxEnergy;

	public UIEnergyBar(GamePanel gp, int x, int y, int width, int height) {
		super(gp, x, y, width, height);
		
	}

	@Override
	public void update() {
		int energy = gp.player.energy;
		int maxEnergy = gp.player.maxEnergy;
		
		full = energy / 2;
		half = energy % 2;
		maxEnergy = maxEnergy / 2;
		blank = maxEnergy - full - half;
	}

	@Override
	public void draw(Graphics2D g2) {
		int drawX = x;
		int spacing = width + 4;
		for (int i = 0; i < full; i++) {
			if (energyFull != null) g2.drawImage(energyFull, drawX, y, width, height, null);
			drawX += spacing;
		}
		if (half == 1) {
			if (energyHalf != null) g2.drawImage(energyHalf, drawX, y, width, height, null);
			drawX += spacing;
		}
		for (int i = 0; i < blank; i++) {
			if (energyBlank != null) g2.drawImage(energyBlank, drawX, y, width, height, null);
			drawX += spacing;
		}
	}
	
}
