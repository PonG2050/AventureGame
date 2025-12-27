package entity;

import java.awt.image.BufferedImage;

public class Entity {
	
	public int worldX, worldY;
	public int speed;
	
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	BufferedImage[] stand = new BufferedImage[10];
	BufferedImage[] idle = new BufferedImage[4];
	BufferedImage[] walk = new BufferedImage[5];
	
	public String direction;

	public int spriteCounter = 0;
	public int spriteNum = 1;
	public int StandCounter = 1;
	public int WalkCounter = 1;
	public int IdleCounter = 1;
}
