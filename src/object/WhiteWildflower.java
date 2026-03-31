package object;
import java.util.Random;

import Main.GamePanel;

public class WhiteWildflower extends SuperObject {
	
	public boolean isOpen = false;

	public WhiteWildflower(GamePanel gp) {
		super(gp);
		name = "WhiteWildflower";
		int[][] WhiteWildflowerList = {{3, 4, 5},
						    		 {0, 0, 0}};
		Random random = new Random();
		width = gp.tileSize;
		height = gp.tileSize;
		collision = false;
		
		solidArea.height = 0;
		solidArea.width = 0;
		solidArea.x = 0;
		solidArea.y = 0;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	
		objectWidthSize = 16*gp.tileSize;
		objectHeightSize = 16*gp.tileSize;
		spriteCounter = 0;
		int i = random.nextInt(3);
		col = WhiteWildflowerList[0][i];
		row = WhiteWildflowerList[1][i];
		
		image = Outdoor_decor_sheet.getSubimage(col * 16, row * 16, 16, 16); 

	}

	public void update() {
		
	}
}