package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Random;

import Main.GamePanel;

public class Entity {
	
	public GamePanel gp;
	
	public int worldX, worldY;
	public int speed, scale;
	public String name;
	public boolean isMoving = false;
	public int maxLife;
	public int life;
	public int recovery = 60;
	
	public Rectangle solidArea;
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collisionOn = false, collision = false;
	public String direction;
	public int spriteCounter = 0;
	public int actionLockCounter = new Random().nextInt(80);
	public int random = new Random().nextInt(40);

	//IMAGE SETTINGs
	public BufferedImage image, sheet;
	public int row = 0, col = 0, height, width;
	
	//ONLY MONSTER CLASS
	public BufferedImage[] idle = new BufferedImage[8];
	public BufferedImage[] move = new BufferedImage[8];

	
	public Entity(GamePanel gp) {
		this.gp = gp;
		solidArea = new Rectangle(0, 0, 48, 48);
	}
	public void action() {
		
	}
	public void update() {
	    action();

	    collisionOn = false;
	    gp.cChecker.checkTile(this);
	    gp.cChecker.checkObject(this, false);

	    if (isMoving && !collisionOn) {
	        switch (direction) {
	            case "up":    worldY -= speed; break;
	            case "down":  worldY += speed; break;
	            case "left":  worldX -= speed; break;
	            case "right": worldX += speed; break;
	        }
	    }

	    spriteCounter++;
	}
	public void draw(Graphics2D g2) {
		
	}
}
