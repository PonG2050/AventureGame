package object;
import java.util.Random;

import Main.GamePanel;

public class BigRock extends SuperObject {
	
	public boolean isOpen = false;

	public BigRock(GamePanel gp) {
		super(gp);
		name = "BigRock";
		int[][] RockList = {{4, 6, 4, 6},
						    {11, 11, 13, 13}};
		Random random = new Random();
		width = gp.tileSize * 2;
		height = gp.tileSize * 2;
		collision = true;
		
		solidArea.height = gp.tileSize/2;
		solidArea.width = gp.tileSize * 2 - gp.tileSize/2;
		solidArea.x = gp.tileSize - solidArea.width/2;
		solidArea.y = gp.tileSize*2 - solidArea.height*2;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		objectWidthSize = 32;
		objectHeightSize = 32;
		spriteCounter = 0;
		int i = random.nextInt(4);
		col = RockList[0][i];
		row = RockList[1][i];
		
		image = Outdoor_decor_sheet.getSubimage(col * 16, row * 16, objectWidthSize, objectHeightSize); 

	}

	public void update() {
		
	}
}