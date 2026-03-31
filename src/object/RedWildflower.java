package object;
import java.util.Random;

import Main.GamePanel;

public class RedWildflower extends SuperObject {
	
	public boolean isOpen = false;

	public RedWildflower(GamePanel gp) {
		super(gp);
		name = "RedWildflower";
		int[][] RedWildflowerList = {{3, 4, 5},
						    		 {1, 1, 1}};
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
		col = RedWildflowerList[0][i];
		row = RedWildflowerList[1][i];
		
		image = Outdoor_decor_sheet.getSubimage(col * 16, row * 16, 16, 16); 

	}

	public void update() {
		
	}
}