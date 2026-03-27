package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import Main.GamePanel;
import Main.KeyHandler;

public class Fox extends Entity{
	int currentIdleRow = 1;
	public Fox(GamePanel gp) {
		super(gp);
		height = 32;
		width = 32;
		direction = "idle";
		speed = 1;
		scale = 2;
		
		solidArea = new Rectangle();
		solidArea.height = gp.tileSize * 2 / 3;
		solidArea.width = gp.tileSize * 7 / 8;
		solidArea.x = width / 2 * scale - solidArea.width / 2;
		solidArea.y = height * scale - solidArea.height;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		getFoxImage();
	}
	public void getFoxImage() {
		sheet = gp.lAsset.FoxImage;
		image = sheet.getSubimage(0, 0, width, height);
	}
	public BufferedImage getFoxSubImage(int row, int maxCol) {
	    if (col > maxCol) {
	        col = 0;
	    }
	    BufferedImage subImage = sheet.getSubimage(col * width, row * height, width, height);
	    if (col >= maxCol) {
	        col = 0;
	    } else {
	        col++;
	    }
	    return subImage;
	}
	public void getFoxIdle() {
        Random random = new Random();
        int i = random.nextInt(100) + 1;
        if (i <= 50) {image = getFoxSubImage(currentIdleRow, 13);}
        else if (i <= 100) {image = getFoxSubImage(currentIdleRow, 13);}
	}
	public void getFoxMovement() {
		switch (direction) {
		case "up":image = getFoxSubImage(2, 7);break;
		case "down":image = getFoxSubImage(9, 7);break;
		case "right":image = getFoxSubImage(9, 7);break;
		case "left":image = getFoxSubImage(2, 7);break;
		}
	}

	@Override
	public void update() {
	    super.update();

	    if (spriteCounter > 4) { 
	        if (isMoving) {
	            getFoxMovement();
	        } else {
	            getFoxIdle();
	        }
	        spriteCounter = 0;
	    }
	}


    public boolean isOnScreen() {
        return worldX + gp.tileSize * scale > gp.player.worldX - gp.player.screenX &&
               worldX - gp.tileSize * scale < gp.player.worldX + gp.player.screenX &&
               worldY + gp.tileSize * scale > gp.player.worldY - gp.player.screenY &&
               worldY - gp.tileSize * scale < gp.player.worldY + gp.player.screenY;
    }

	public void action() {
	    actionLockCounter++;
	    int randomInterval = 80 + new Random().nextInt(60);
	    
	    if (actionLockCounter >= randomInterval) {
	        Random random = new Random();
	        int i = random.nextInt(100) + 1;

	        if (i <= 5) { direction = "up"; isMoving = true; }
	        else if (i <= 10) { direction = "down"; isMoving = true; }
	        else if (i <= 15) { direction = "left"; isMoving = true; }
	        else if (i <= 20) { direction = "right"; isMoving = true; }
	        else { 
	        	direction = "idle";
	        	isMoving = false;
	        	currentIdleRow = (random.nextInt(2) == 0) ? 1 : 8;
	        	}

	        actionLockCounter = 0;
	        col = 0;
	    }
	}
	public void draw(Graphics2D g2) {
	    
	    int screenX = worldX - gp.player.worldX + gp.player.screenX;
	    int screenY = worldY - gp.player.worldY + gp.player.screenY;

	    if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
	        worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
	        worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
	        worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
	        
	        g2.drawImage(image, screenX, screenY, gp.tileSize * scale, gp.tileSize * scale, null);
			if (gp.keyH.HitBox == true) {
				g2.setColor(Color.red);
				g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
			}
	    }
	}
}
