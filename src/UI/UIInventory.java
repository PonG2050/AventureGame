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
	public int slotCol = 0;
	public int slotRow = 0;
	public final int maxSlotCol = 4;
	public final int maxSlotRow = 5;
	public UIInventory(GamePanel gp) {
		super(gp, gp.screenWidth/2 - 256, gp.screenHeight/2 - 256, 256*gp.scale, 256*gp.scale);
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
	    if (gp.keyH.E == true) {
	        
	        int mouseX = gp.mouseL.mouseX;
	        int mouseY = gp.mouseL.mouseY;

	        final int slotStartX = x + 108 * gp.scale + 2 * gp.scale; 
	        final int slotStartY = y + 28 * gp.scale + 2 * gp.scale; 
	        final int slotSize = 23 * gp.scale; 
	        final int gap = 11 * gp.scale;
	        int invWidth = (maxSlotCol + 1) * slotSize;
	        int invHeight = (maxSlotRow * slotSize) + gap + slotSize; 

	        if (mouseX >= slotStartX && mouseX < slotStartX + invWidth &&
	            mouseY >= slotStartY && mouseY < slotStartY + invHeight) {
	            
	            slotCol = (mouseX - slotStartX) / slotSize;
	            
	            int relY = mouseY - slotStartY;
	            
	            if (relY < maxSlotRow * slotSize) {
	                slotRow = relY / slotSize;
	            } else if (relY >= (maxSlotRow * slotSize) + gap) {
	                slotRow = maxSlotRow;
	            }
	        }
	    }
	}
	@Override
	public void draw(Graphics2D g2) {
		// === HOTBAR ===
		int hotbarScreenX = gp.screenWidth/2 - (hotbarWidth * gp.scale)/2;
		int hotbarScreenY = gp.screenHeight - gp.tileSize * 2;
		
		int spacing = 23 * gp.scale;
		int hotbarCursorX = hotbarScreenX - gp.tileSize/2 - 2 * gp.scale + (gp.keyH.slot - 1) * spacing;
		int hotbarCursorY = hotbarScreenY - gp.tileSize/2 - 2 * gp.scale;
		
		g2.drawImage(hotbar, hotbarScreenX, hotbarScreenY, hotbarWidth * gp.scale, hotbarHeight * gp.scale, null);
		g2.drawImage(cursor, hotbarCursorX, hotbarCursorY, cursorWidth * gp.scale, cursorHeight * gp.scale, null);
		
		// === INVENTORY ===
		if (gp.keyH.E == true) {
			g2.drawImage(image, x, y, width, height, null);
			
			// g2.drawRect(x + 108 * gp.scale, y + 28 * gp.scale, 119 * gp.scale, 153 * gp.scale);
			
			final int slotStartX = x + 108 * gp.scale + 2*gp.scale; 
			final int slotStartY = y + 28 * gp.scale + 2*gp.scale; 
			final int slotSize = 23 * gp.scale; 
			int targetX = slotStartX + (slotCol * slotSize);
			int targetY = slotStartY + (slotRow * slotSize);
			if (slotRow == maxSlotRow) {
				targetY = slotStartY + (slotRow * slotSize) + 11*gp.scale;
			}
			
			int offset = 12 * gp.scale; 
			
			int invCursorX = targetX - offset;
			int invCursorY = targetY - offset;
			
			g2.drawImage(cursor, invCursorX, invCursorY, cursorWidth * gp.scale, cursorHeight * gp.scale, null);
		}
	}
}
