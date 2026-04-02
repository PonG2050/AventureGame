package entity.monster;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import Main.GamePanel;
import entity.Entity;

public class Skeleton extends Entity{
	int currentIdleRow = 1;
	public Skeleton(GamePanel gp) {
		super(gp);
		height = 32;
		width = 32;
		direction = "idle";
		speed = 2;
		scale = 2;
		maxLife = 15;
		life = 15;
		
		int totalScale = gp.scale * scale;

		solidArea = new Rectangle();
		solidArea.x = 6 * totalScale;
		solidArea.y = 10 * totalScale;
		solidArea.width = 4 * totalScale;
		solidArea.height = 1 * totalScale;
		
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidAreaDefaultWidth = solidArea.width;
		solidAreaDefaultHeight = solidArea.height;
		
		getSkeletonImage();
	}
	public void getSkeletonImage() {
		try {
			 
		sheet = ImageIO.read(getClass().getResourceAsStream("/monsters/Skeleton.png"));
		image = sheet.getSubimage(0, 0, width, height);
		
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	public BufferedImage getSkeletonSubImage(int row, int maxCol) {
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
	public void getSkeletonIdle() {
		switch (direction) {
		case "up":image = getSkeletonSubImage(2, 5);break;
		case "down":image = getSkeletonSubImage(0, 5);break;
		case "right":image = getSkeletonSubImage(1, 5);break;
		case "left":image = getSkeletonSubImage(7, 5);break;
		case "idle":image = getSkeletonSubImage(0, 5);break;
		}
	}
	public void getSkeletonMovement() {
		switch (direction) {
		case "up":image = getSkeletonSubImage(5, 5);break;
		case "down":image = getSkeletonSubImage(3, 5);break;
		case "right":image = getSkeletonSubImage(4, 5);break;
		case "left":image = getSkeletonSubImage(8, 5);break;
		}
	}

	@Override
	public void update() {
	    super.update();
	    // GET SOURCES
	    spriteCounter++;
	    if (spriteCounter > 4) { 
	        if (isMoving) {
	            getSkeletonMovement();
	        } else {
	            getSkeletonIdle();
	        }
	        spriteCounter = 0;
	    }
	}
	@Override
	public void action() {
	    actionLockCounter++;
	    int randomInterval = 24 + new Random().nextInt(64);
	    
	    if (actionLockCounter >= randomInterval) {
	        Random random = new Random();
	        int i = random.nextInt(100) + 1;

	        if (i <= 5) { direction = "up"; isMoving = true; }
	        else if (i <= 10) { direction = "down"; isMoving = true; }
	        else if (i <= 15) { direction = "left"; isMoving = true; }
	        else if (i <= 20) { direction = "right"; isMoving = true; }
	        else { isMoving = false; }

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
			
			//DRAW HEALTH BAR
			double oneScale = (double) solidArea.width / maxLife;
			int hpBarValue = (int) (oneScale * life);
			
			if(hpBarValue < 0) hpBarValue = 0;
			int barHeight = gp.tileSize / 12;
			
			g2.setColor(new Color(35, 35, 35));
			g2.fillRect(screenX + solidArea.x, screenY + gp.tileSize/4, solidArea.width + 2, barHeight + 2);
			
			g2.setColor(new Color(255, 0, 30));
			g2.fillRect(screenX + solidArea.x, screenY + gp.tileSize/4, hpBarValue, barHeight);
	    }
	}
}
