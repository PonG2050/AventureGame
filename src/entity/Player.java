package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;
import Main.KeyHandler;

public class Player extends Entity{
	
	GamePanel gp;
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		
		this.gp = gp;
		this.keyH = keyH;
		
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		
		setDefaultValue();
		getPlayerImage();
	}

	public void setDefaultValue() {
		
		worldX = 45 * gp.tileSize;
		worldY = 45 * gp.tileSize;
		speed = 4;
		direction = "stand";
	}
	
	public void getPlayerImage() {
		
		try {

			up1 = ImageIO.read(getClass().getResourceAsStream("/player/up1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/up2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/down1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/down2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/right1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/right2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/left1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/left2.png"));
	        for (int i = 0; i < 10; i++) {
	            stand[i] = ImageIO.read(getClass().getResourceAsStream("/player/stand" + (i+1) + ".png"));
	        }

		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	public void update() {		
		if(keyH.Up == true) {
			worldY -= speed;
			direction = "up";
		} else if (keyH.Down == true) {
			worldY += speed;
			direction = "down";
		} else if (keyH.Left == true) {
			worldX -= speed;
			direction = "left";
		} else if (keyH.Right == true) {
			worldX += speed;
			direction = "right";
		}
		else {
			direction = "stand";
		}

		spriteCounter++;
		if (spriteCounter > 10) {
			spriteCounter = 0;
			StandCounter++;
			if (spriteNum == 1) {
				spriteNum = 2;
			}
			else {
				spriteNum = 1;
			}
			if (StandCounter >= 10) {
				StandCounter = 0;
			}
		}
	}
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			if (spriteNum == 1) {
				image = up1;
				}
			else {
				image = up2;
			}
			break;
		case "down":
			if (spriteNum == 1) {
				image = down1;
				}
			else {
				image = down2;
			}
			break;
		case "left":
			if (spriteNum == 1) {
				image = left1;
				}
			else {
				image = left2;
			}
			break;
		case "right":
			if (spriteNum == 1) {
				image = right1;
				}
			else {
				image = right2;
			}
			break;
		case "stand":
			image = stand[StandCounter];
			break;
			
		}
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		
			}
}
