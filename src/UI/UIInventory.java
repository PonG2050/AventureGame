package UI;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class UIInventory extends UIComponent{
	BufferedImage hotbar;
	// ORGINAL SIZE OF HOTBAR
	int hotbarWidth = 120, hotbarHeight = 28;
	public UIInventory(GamePanel gp) {
		super(gp, gp.screenWidth/2 - 256, gp.screenHeight/2 - 256, 256*2, 256*2);
		setDefaultValue();
	}
	private void setDefaultValue() {
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/UI/UI_Inventory.png"));
			hotbar = image.getSubimage(108, 154, hotbarWidth, hotbarHeight);
			
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
		g2.drawImage(hotbar, gp.screenWidth/2 - (hotbarWidth * gp.scale)/2, gp.screenHeight - gp.tileSize * 2, hotbarWidth * gp.scale, hotbarHeight * gp.scale, null);
		if (gp.keyH.E == true) {
			g2.drawImage(image, x, y, width, height, null);
		}
	}
}
