package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;
import Main.KeyHandler;
import Main.MouseListener;
import object.SuperObject;

public class Player extends Entity {

	GamePanel gp;
	KeyHandler keyH;
	MouseListener mouseL;

	public final int screenX;
	public final int screenY;
	public int keyCount = 0;
	final int playerScale = 2;

	public Player(GamePanel gp, KeyHandler keyH, MouseListener mouseL) {

		super(gp);
		this.gp = gp;
		this.keyH = keyH;
		this.mouseL = mouseL;
		
		height = 32;
		width = 32;
		
		screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
		screenY = gp.screenHeight / 2 - (gp.tileSize / 2);
		
		int totalScale = gp.scale * playerScale;

		solidArea = new Rectangle();
		solidArea.x = 6 * totalScale;
		solidArea.y = 10 * totalScale;
		solidArea.width = 4 * totalScale;
		solidArea.height = 1 * totalScale;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;

		setDefaultValue();
		getPlayerImage();
	}

	public void setDefaultValue() {

		worldX = gp.maxWorldCol / 2 * gp.tileSize;
		worldY = gp.maxWorldCol / 2 * gp.tileSize;
		speed = 5;
		direction = "idle";
	}

	public void getPlayerImage() {
		try {
			sheet = ImageIO.read(getClass().getResourceAsStream("/player/Player_sheet.png"));
			image = sheet.getSubimage(0, 0, width, height);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public BufferedImage getPlayerSubImage(int row, int maxCol) {
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

	public void update() {
		isMoving = false;
	    
	    double currentSpeed = speed;

	    boolean isMovingX = keyH.Left || keyH.Right;
	    boolean isMovingY = keyH.Up || keyH.Down;

	    if (isMovingX && isMovingY) {
	        currentSpeed = speed / Math.sqrt(2); 
	    }

	    if (keyH.Up == true || keyH.Down == true) {
	        if (keyH.Up == true) direction = "up";
	        if (keyH.Down == true) direction = "down";

	        collisionOn = false;
	        gp.cChecker.checkTile(this);
	        int objectIndex = gp.cChecker.checkObject(this,  true);
	        pickupObject(objectIndex);
	        interactObject(objectIndex);
	        
	        if (collisionOn == false) {
	            if (keyH.Up == true) worldY -= (int)currentSpeed;
	            if (keyH.Down == true) worldY += (int)currentSpeed;
	        }
	        isMoving = true;
	    }
   
	    if (keyH.Left == true || keyH.Right == true) {
	        if (keyH.Left == true) direction = "left";
	        if (keyH.Right == true) direction = "right";

	        collisionOn = false;
	        gp.cChecker.checkTile(this);
	        int objectIndex = gp.cChecker.checkObject(this,  true);
	        pickupObject(objectIndex);
	        interactObject(objectIndex);

	        if (collisionOn == false) {
	            if (keyH.Left == true) worldX -= (int)currentSpeed;
	            if (keyH.Right == true) worldX += (int)currentSpeed;
	        }
	        isMoving = true;
	    }
		
		spriteCounter++;
		if (spriteCounter > 4) {
			spriteCounter = 0;

			// idle
			if (isMoving == false) {
				
		        if (direction.equals("up")) {
		        	
		        	if (mouseL.leftClick) {image = getPlayerSubImage(11, 3);} 
		        	else {image = getPlayerSubImage(7, 5);}
		        	
		        }else if (direction.equals("down")) {
		        	
		        	if (mouseL.leftClick) {image = getPlayerSubImage(8, 3);} 
		        	else {image = getPlayerSubImage(0, 5);}
		        	
		        }else if (direction.equals("left")) {
		        	
		        	if (mouseL.leftClick) {image = getPlayerSubImage(10, 3);} 
		        	else {image = getPlayerSubImage(4, 5);}
		        	
		        }else if (direction.equals("right")) {
		        	
		        	if (mouseL.leftClick) {image = getPlayerSubImage(9, 3);} 
		        	else {image = getPlayerSubImage(2, 5);}
		        	
		        }else if (direction.equals("idle")) {
		        	
		        	if (mouseL.leftClick) {image = getPlayerSubImage(8, 3);} 
		        	else {image = getPlayerSubImage(0, 5);}
		        	
		        }
			} else {
		        if (direction.equals("up")) {
		        	image = getPlayerSubImage(6, 5);
		        }
		        else if (direction.equals("down")) {
		        	image = getPlayerSubImage(1, 5);
		        }
		        else if (direction.equals("left")) {
		        	image = getPlayerSubImage(5, 5);
		        }
		        else if (direction.equals("right")) {
		        	image = getPlayerSubImage(3, 5);
		        }
			}
		}
	}

	public void pickupObject(int i) {
		if (i != 999) {
			if (gp.obj[i] != null) {
				String objectName = gp.obj[i].name;
				switch (objectName) {
				case "key":
					gp.playSE(1);
					keyCount++;
					gp.obj[i] = null;
					System.out.println("pick up a object");
					break;
				}
			}
		}
	}
	public void interactObject(int i) {
		if (i != 999) {
			if (gp.obj[i] != null) {
			String objectName = gp.obj[i].name;
			switch (objectName) {
			case "chest":
				if (keyCount > 0) {
					gp.obj[i].interacting = true;
					System.out.println("open chest");
				}

				break;
				}				
			}
		}
	}
	public void draw(Graphics2D g2) {
		
		g2.drawImage(image, screenX, screenY, gp.tileSize * playerScale, gp.tileSize * playerScale, null);
		if (keyH.HitBox == true) {
			g2.setColor(Color.red);
			g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
		}
	}

}