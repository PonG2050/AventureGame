package object;
import java.util.Random;

import Main.GamePanel;

public class NormalRock extends SuperObject {
	
	public boolean isOpen = false;

	public NormalRock(GamePanel gp) {
		super(gp);
		name = "NormalRock";
		int[][] RockList = {{3, 4, 6, 7, 8, 6, 7},
						    {6, 6, 6, 6, 6, 7 ,7}};
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
		
		objectWidthSize = 16*gp.tileSize;
		objectHeightSize = 16*gp.tileSize;
		spriteCounter = 0;
		int i = random.nextInt(7);
		col = RockList[0][i];
		row = RockList[1][i];
		
		image = Outdoor_decor_sheet.getSubimage(col * 16, row * 16, 16, 16); 

	}

	public void update() {
		
	}
}