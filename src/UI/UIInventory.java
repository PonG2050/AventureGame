package UI;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class UIInventory extends UIComponent{
	BufferedImage hotbar, cursor, cursorSheet;
	// ORGINAL SIZE OF HOTBAR
	int hotbarWidth = 120, hotbarHeight = 28;
	int cursorWidth = 16 * 3, cursorHeight = 16 * 3;
	int[] slotList;
	public UIInventory(GamePanel gp) {
		super(gp, gp.screenWidth/2 - 256, gp.screenHeight/2 - 256, 256*2, 256*2);
		setDefaultValue();
	}
	private void setDefaultValue() {
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/UI/UI_Inventory.png"));
			hotbar = image.getSubimage(108, 154, hotbarWidth, hotbarHeight);
			cursorSheet = ImageIO.read(getClass().getResourceAsStream("/UI/UI_Selectors.png"));
			cursor = cursorSheet.getSubimage(0, 39 * 16, 3 * 16, 3 * 16);
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void draw(Graphics2D g2) {
		int hotbarScreenX = gp.screenWidth/2 - (hotbarWidth * gp.scale)/2;
		int hotbarScreenY = gp.screenHeight - gp.tileSize * 2;
		int spacing = 23 * gp.scale;
		int cursorScreenX = hotbarScreenX - gp.tileSize/2 - 2 * gp.scale + (gp.keyH.slot - 1) * spacing;
		int cursorScreenY = hotbarScreenY - gp.tileSize/2 - 2 * gp.scale;
		g2.drawImage(hotbar, hotbarScreenX, hotbarScreenY, hotbarWidth * gp.scale, hotbarHeight * gp.scale, null);
		if (gp.keyH.E == true) {
			g2.drawImage(image, x, y, width, height, null);
		}
		g2.drawImage(cursor, cursorScreenX, cursorScreenY, cursorWidth * gp.scale, cursorHeight * gp.scale, null);
	}
}
