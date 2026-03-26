package UI;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class UIInventory extends UIComponent{
	public UIInventory(GamePanel gp) {
		super(gp, gp.screenWidth/2 - 256, gp.screenHeight/2 - 256, 256*2, 256*2);
		setDefaultValue();
	}
	private void setDefaultValue() {
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/UI/UI_Inventory.png"));
			
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
		g2.drawImage(image, x, y, width, height, null);
	}
}
