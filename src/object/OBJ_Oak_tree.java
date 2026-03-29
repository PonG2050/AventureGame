package object;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class OBJ_Oak_tree extends SuperObject{
	
	public OBJ_Oak_tree(GamePanel gp) {
		super(gp);
		objectWidthSize = 21;
		objectHeightSize = 47;
		name = "Oak_tree";
		width = gp.tileSize * 4;
		height = gp.tileSize * 5;
		collision = true;
		solidArea.height = gp.tileSize - 10;
		solidArea.width = gp.tileSize / 2;
		solidArea.x = width / 2 - solidArea.width / 2;
		solidArea.y = height / 2 + solidArea.height / 2 + 20;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		spriteCounter = 0;
		
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/objects/Oak_tree.png"));
			
		} catch (IOException e) {
			System.out.println("Oak_tree");
		}
	}
	@Override
	public void draw(Graphics2D g2) {
	    
	    int screenX = worldX - gp.player.worldX + gp.player.screenX;
	    int screenY = worldY - gp.player.worldY + gp.player.screenY;

	    boolean playerIsBehind = false;
	    int playerBottomY = gp.player.worldY + gp.player.solidArea.y + gp.player.solidArea.height;
	    int objectBottomY = worldY + solidArea.y + solidArea.height;
	    if (playerBottomY < objectBottomY &&
	    	playerBottomY - (objectBottomY) > - gp.tileSize * 4 &&
	        gp.player.worldX + gp.player.solidArea.x + gp.player.solidArea.width > worldX &&
	        gp.player.worldX + gp.player.solidArea.x < worldX + width) {
	        
	        playerIsBehind = true;
	    }

	    if (playerIsBehind) {
	        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
	    }

	    if (worldX + width > gp.player.worldX - gp.player.screenX &&
	        worldX - width < gp.player.worldX + gp.player.screenX &&
	        worldY + height > gp.player.worldY - gp.player.screenY &&
	        worldY - height < gp.player.worldY + gp.player.screenY) {
	        
	        g2.drawImage(image, screenX, screenY, width, height, null);
	    }

	    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));

	    if (gp.keyH.HitBox) {
	        g2.setColor(java.awt.Color.white);
	        g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
	    }
	}
}
