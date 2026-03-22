package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class OBJ_Key extends SuperObject{
	//27
	//21 * 47
	public OBJ_Key(GamePanel gp) {

		super(gp);
		name = "key";
		width = gp.tileSize * 2 / 3;
		height = gp.tileSize * 4 / 3;
		collision = false;
		solidArea.x = width/2 - solidArea.width/2;
		solidArea.y = height/2 - solidArea.height/2;
		solidArea.height = gp.tileSize;
		solidArea.width = gp.tileSize / 2;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		objectWidthSize = 21;
		objectHeightSize = 47;
		spriteCounter = 0;
		row = 0;
		col = 0;
		try {
			
			sheet = ImageIO.read(getClass().getResourceAsStream("/objects/Key 8 - GOLD - Spritesheet.png"));
			image = sheet.getSubimage(0, 0, objectWidthSize, objectHeightSize);
			
		} catch (IOException e) {
			System.out.println("key");
		}
	}
	public void update() {
		spriteCounter++;
		if (spriteCounter > 3) {
			if (col < 26) {
				col++;
			} else col = 0;
			
			if (sheet != null) {
				image = sheet.getSubimage(col * objectWidthSize, row * objectHeightSize, objectWidthSize, objectHeightSize);
			}
			spriteCounter = 0;
		}
	}
}
