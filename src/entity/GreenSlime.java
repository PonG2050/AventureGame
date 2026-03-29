package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class GreenSlime extends Entity{
	
	public GreenSlime(GamePanel gp) {
		super(gp);
		height = 64;
		width = 64;
		direction = "idle";
		speed = 1;
		scale = 2;
		
		solidArea = new Rectangle();
		solidArea.height = gp.tileSize * 2 / 3;
		solidArea.width = gp.tileSize * 2 / 3;
		solidArea.x = width/2 - solidArea.width/2;
		solidArea.y = height/2 - solidArea.height/2;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		getGreenSlimeImage();
	}
	public void getGreenSlimeImage() {
		try {
			sheet = ImageIO.read(getClass().getResourceAsStream("/monsters/Slime_Green.png"));
			image = sheet.getSubimage(0, 0, width, height);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void update() {
        super.update();

        spriteCounter++;
        if (spriteCounter > 20) {
            if (isMoving) {
                row = 1;
                col++;
                if (col >= 8) col = 0;
                //JUMP SOUND
                if (col == 1) {
                    if (isOnScreen()) {
                        gp.playSE(4);
                    }
                }
                //FALL SOUND
                if (col == 5) {
                    if (isOnScreen()) {
                        gp.playSE(5);
                    }
                }
           } else {
                row = 0;
                col++;
                if (col >= 4) col = 0;
           }
            
            if (sheet != null) {
                image = sheet.getSubimage(width * col, height * row, width, height);
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
	    int randomInterval = 60 + new Random().nextInt(60);
	    
	    if (actionLockCounter >= randomInterval) {
	        Random random = new Random();
	        int i = random.nextInt(100) + 1;

	        if (i <= 15) { direction = "up"; isMoving = true; }
	        else if (i <= 30) { direction = "down"; isMoving = true; }
	        else if (i <= 45) { direction = "left"; isMoving = true; }
	        else if (i <= 60) { direction = "right"; isMoving = true; }
	        else { isMoving = false; direction = "idle"; }

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
