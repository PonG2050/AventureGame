package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import Main.GamePanel;

public class Entity {
	
	GamePanel gp;
	
	public int worldX, worldY;
	public int speed, scale;
	public boolean isMoving = false;
	
	public Rectangle solidArea;
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collisionOn = false;
	public String direction;
	public int spriteCounter = 0;
	public int actionLockCounter = 0;
	
	
	//IMAGE SETTINGS
	BufferedImage image, sheet;
	int row = 0, col = 0, height, width;
	
	//ONLY PLAYER CLASS
	// idle elements
	public BufferedImage[] idlef = new BufferedImage[6];
	public BufferedImage[] idleu = new BufferedImage[6];
	public BufferedImage[] idler = new BufferedImage[6];
	public BufferedImage[] idlel = new BufferedImage[6];
	// walk elements
	public BufferedImage[] down = new BufferedImage[6];
	public BufferedImage[] up = new BufferedImage[6];
	public BufferedImage[] right = new BufferedImage[6];
	public BufferedImage[] left = new BufferedImage[6];
	// idle counter
	public int idlefCounter = 1;
	public int idleuCounter = 1;
	public int idlelCounter = 1;
	public int idlerCounter = 1;
	// walk counter
	public int downCounter = 1;
	public int upCounter = 1;
	public int rightCounter = 1;
	public int leftCounter = 1;
	public int WalkCounter = 1;
	public int IdleCounter = 1;
	public int spriteNum = 0;
	
	//ONLY MONSTER CLASS
	public BufferedImage[] idle = new BufferedImage[8];
	public BufferedImage[] move = new BufferedImage[8];

	
	public Entity(GamePanel gp) {
		this.gp = gp;
	}
	public void action() {
		
	}
	public void update() {
		
		action();
		collisionOn = false;
		gp.cChecker.checkTile(this);
		gp.cChecker.checkObject(this, false);
	}
	public void draw(Graphics2D g2) {
		
	}
}
