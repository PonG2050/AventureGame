package object;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;
import entity.Entity;

public class SuperObject extends Entity{

	public String name;
	public boolean interacting = false;
	int objectWidthSize;
	int objectHeightSize;
	BufferedImage Outdoor_decor_sheet;
	public SuperObject(GamePanel gp) {
		super(gp);
		try {
			Outdoor_decor_sheet = ImageIO.read(getClass().getResourceAsStream("/objects/Outdoor_Decor.png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}	
	public void update() {
		
	}
	
	@Override
	public void draw(Graphics2D g2) {
		
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;

		if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
			worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
			worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
			worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

			g2.drawImage(image, screenX, screenY, width, height, null);
			if (gp.keyH.HitBox == true) {
				g2.setColor(Color.white);
				g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
			}

		}
	}
}
