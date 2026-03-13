package object;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Main.GamePanel;

public class SuperObject {
	BufferedImage image;
	BufferedImage sheet; 
	public String name;
	public boolean collision = false;
	public boolean interacting = false;
	public int worldX, worldY;
	public int width, height;
	public Rectangle solidArea = new Rectangle();
	
	public int solidAreaDefaultX = 0, solidAreaDefaultY = 0;
	public void update() {
		
	}
	public void draw(Graphics2D g2, GamePanel gp) {
		
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
