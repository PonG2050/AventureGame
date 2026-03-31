package object;
import java.util.Random;

import Main.GamePanel;

public class Lotus_leaf extends SuperObject {
	
	public boolean isOpen = false;

	public Lotus_leaf(GamePanel gp) {
		super(gp);
		name = "Lotus_leaf";
		int[][] Lotus_leafList = {{3, 4, 5, 4},
						    {3, 3, 3, 3}};
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
		int i = random.nextInt(4);
		col = Lotus_leafList[0][i];
		row = Lotus_leafList[1][i];
		
		image = Outdoor_decor_sheet.getSubimage(col * 16, row * 16, 16, 16); 

	}

	public void update() {
		
	}
}