package object;
import java.util.Random;

import Main.GamePanel;

public class SmallRock extends SuperObject {
	
	public boolean isOpen = false;

	public SmallRock(GamePanel gp) {
		super(gp);
		name = "SmallRock";
		Random random = new Random();
		width = gp.tileSize;
		height = gp.tileSize;
		collision = true;
		
		solidArea.height = gp.tileSize/2;
		solidArea.width = gp.tileSize/2;
		solidArea.x = gp.tileSize/2 - solidArea.width/2;
		solidArea.y = gp.tileSize/2 - solidArea.height/2;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		objectWidthSize = 16;
		objectHeightSize = 16;
		spriteCounter = 0;
		row = 5;
		col = random.nextInt(2);
		
		image = Outdoor_decor_sheet.getSubimage(col * objectWidthSize, row * objectHeightSize, objectWidthSize, objectHeightSize); 

	}

	public void update() {
		
	}
}