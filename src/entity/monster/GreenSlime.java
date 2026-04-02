package entity.monster;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import Main.GamePanel;
import entity.Entity;

public class GreenSlime extends Entity{
	
	public GreenSlime(GamePanel gp) {
		super(gp);
		monster = true;
		damage = 1;
		height = 64;
		width = 64;
		direction = "idle";
		speed = 1;
		scale = 2;
		maxLife = 5;
		life = 5;
		
		solidArea = new Rectangle();
		solidArea.height = gp.tileSize * 2 / 3;
		solidArea.width = gp.tileSize * 2 / 3;
		solidArea.x = (width * gp.scale/scale)/2 - solidArea.width/2;
		solidArea.y = (height * gp.scale/scale)/2 - solidArea.height/2;
		
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidAreaDefaultWidth = solidArea.width;
		solidAreaDefaultHeight = solidArea.height;
		
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
        // GET SOURCES	
        spriteCounter++;
        if (spriteCounter > 10) {
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
	@Override
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
	    if (isOnScreen()) {
	        
	        g2.drawImage(image, screenX, screenY, gp.tileSize * scale, gp.tileSize * scale, null);
			if (gp.keyH.HitBox == true) {
				g2.setColor(Color.red);
				g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
			}
			// DRAW HEALTH BAR
			double oneScale = (double) solidArea.width / maxLife;
			int hpBarValue = (int) (oneScale * life);
			
			if (hpBarValue < 0) hpBarValue = 0;
			
			int barHeight = gp.tileSize / 12;
			
			g2.setColor(new Color(35, 35, 35));
			g2.fillRect(screenX + solidArea.x, screenY + gp.tileSize/4, solidArea.width + 2, barHeight + 2);
			
			g2.setColor(new Color(255, 0, 30));
			g2.fillRect(screenX + solidArea.x, screenY + gp.tileSize/4, hpBarValue, barHeight);

	    }	    
	}
}
