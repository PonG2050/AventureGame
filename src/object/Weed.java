package object;
import java.util.Random;

import Main.GamePanel;

public class Weed extends SuperObject {
	
	public boolean isOpen = false;

	public Weed(GamePanel gp) {
		super(gp);
		name = "Weed";
		int[][] WeedList = {{0, 1, 2},
						    {2, 2, 2}};
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
		col = WeedList[0][i];
		row = WeedList[1][i];
		
		image = Outdoor_decor_sheet.getSubimage(col * 16, row * 16, 16, 16); 

	}

	public void update() {
		
	}
}