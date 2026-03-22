package object;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;
import tile.Tile;

public class OBJ_Chest extends SuperObject {
	
	public boolean isOpen = false;

	public OBJ_Chest(GamePanel gp) {
		super(gp);
		name = "chest";
		width = gp.tileSize;
		height = gp.tileSize * 16 / 9;
		collision = true;
		solidArea.x = 0;
		solidArea.y = gp.tileSize;
		solidArea.height = gp.tileSize * 2 / 3;
		solidArea.width = gp.tileSize;
		solidAreaDefaultX = 0;
		solidAreaDefaultY = gp.tileSize;
		objectWidthSize = 54;
		objectHeightSize = 96;
		spriteCounter = 0;
		row = 0;
		col = 6;
		
		try {
			sheet = ImageIO.read(getClass().getResourceAsStream("/objects/Chests.png"));
			image = sheet.getSubimage(0, col * objectHeightSize, objectWidthSize, objectHeightSize); 
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Chests errors");
		}
	}

	public void update() {
		spriteCounter++;
		
		if (spriteCounter > 4) {
			
			if (gp.player.isMoving == true) {
				interacting = false;
			}
			
			if (interacting) {
				if (row < 2) {
					row++;
					if (row == 1) gp.playSE(2);
				}				
			} else {
				if (row > 0) {
					row--;
					if (row == 1) gp.playSE(3);
				}
			}		
			if (sheet != null) {
				image = sheet.getSubimage(col * objectWidthSize, row * objectHeightSize, objectWidthSize, objectHeightSize);		
			}
			spriteCounter = 0;
		}
	}
}