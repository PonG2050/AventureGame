package entity;

import java.awt.Color;
import java.awt.Font;
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
	int slowDown;
	public int energy;
	public int maxEnergy;
	public boolean invincible = false;
	private int invincibleCounter = 0;
	private int recoveryCounter = 0;
	public Player(GamePanel gp, KeyHandler keyH, MouseListener mouseL) {

		super(gp);
		this.gp = gp;
		this.keyH = keyH;
		this.mouseL = mouseL;
		
		height = 32;
		width = 32;
		name = "player";
		
		screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
		screenY = gp.screenHeight / 2 - (gp.tileSize / 2);
		
		int totalScale = gp.scale * playerScale;
		Font Arial40 = new Font("Arial", Font.BOLD, 40);;

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
		maxLife = 20;
		life = 18;
		maxEnergy = 20;
		energy = 20;
		slowDown = 3;
		direction = "idle";
	}
	// GET IMAGE METHODS
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
	public void getPlayerIdleImage() {
		switch (direction) {
		case "up":image = getPlayerSubImage(7, 5);break;
		case "down":image = getPlayerSubImage(0, 5);break;
		case "right":image = getPlayerSubImage(2, 5);break;
		case "left":image = getPlayerSubImage(4, 5);break;
		case "idle":image = getPlayerSubImage(0, 5);break;
		}
	}
	public void getPlayerMovementImage() {
		switch (direction) {
		case "up":image = getPlayerSubImage(6, 5);break;
		case "down":image = getPlayerSubImage(1, 5);break;
		case "right":image = getPlayerSubImage(3, 5);break;
		case "left":image = getPlayerSubImage(5, 5);break;
		}
	}
	public void getPlayerActionImage() {
		if (mouseL.leftClick == true) {
			switch (direction) {
			case "up":image = getPlayerSubImage(11, 3);break;
			case "down":image = getPlayerSubImage(8, 3);break;
			case "right":image = getPlayerSubImage(9, 3);break;
			case "left":image = getPlayerSubImage(10, 3);break;
			}
		}
	}
	// UPDATE METHOD - DONT USE SUPER UPDATE
	public void update() {
		
		// PLAYER MOVEMENT SETTINGs
		isMoving = false;
	    double currentSpeed = speed;
	    // SLOWDOWN WHEN PLAYER ATTACKS
        if (mouseL.leftClick == true) {
        	currentSpeed = speed - slowDown;
        }
        // SLOWDOWN WHEN PLAYER HAS LOW ENERGY
        if (energy < 5) {
        	currentSpeed = speed - slowDown;
        }
	    boolean isMovingX = (keyH.Left || keyH.Right);
	    boolean isMovingY = (keyH.Up || keyH.Down);
	    // CHECK DIAGONAL MOVING 
	    if (isMovingX && isMovingY) {
	        currentSpeed = speed / Math.sqrt(2);
	        if (mouseL.leftClick == true) {
	        	currentSpeed /= Math.sqrt(2);
	        }
	        if (energy < 5) {
	        	currentSpeed /= Math.sqrt(2);
	        }
	    }
	    
	    if (keyH.Up == true || keyH.Down == true) {
	        if (keyH.Up == true) direction = "up";
	        if (keyH.Down == true) direction = "down";

	        collisionOn = false;
	        gp.cChecker.checkTile(this);
	        // OBJECT COLLISION CHECK
	        int objectIndex = gp.cChecker.checkObject(this,  true);
	        // ENTITY COLLISION CHECK
	        int entityIndex = gp.cChecker.checkEntity(this, gp.monster);
	        if (entityIndex != 999) {
	        	if (invincible == false) {
		        	life -= gp.monster[entityIndex].damage;
	        		invincible = true;
	        	}
	        }
	        // ACTION CHECK
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
	        // OBJECT COLLISION CHECK
	        int objectIndex = gp.cChecker.checkObject(this,  true);
	        // ENTITY COLLISION CHECK
	        int entityIndex = gp.cChecker.checkEntity(this, gp.monster);
	        if (entityIndex != 999) {
	        	if (invincible == false) {
		        	life -= gp.monster[entityIndex].damage;
	        		invincible = true;
	        	}
	        }
	        // ACTION CHECK
	        pickupObject(objectIndex);
	        interactObject(objectIndex);

	        if (collisionOn == false) {
	            if (keyH.Left == true) worldX -= (int)currentSpeed;
	            if (keyH.Right == true) worldX += (int)currentSpeed;
	        }
	        isMoving = true;
	    }
		
		// RECOVERY UPDATE
		recoveryCounter++;
		if (recoveryCounter > 300) {
			if (life < maxLife) {
				if (energy > 0) {
					energy--;
					life++;
				}
			}
			recoveryCounter = 0;
		}
		// INVINCIBLE UPDATE
        if (invincible == true) {
        	invincibleCounter++;
        	if (invincibleCounter > 60) {
        		invincible = false;
        		invincibleCounter = 0;
        	}
        }
		// HEALTH UPDATE
		if (life <= 0) {
			System.exit(0);
		}
	    // GET PLAYER IMAGE
		spriteCounter++;
		if (spriteCounter > 4) {
			spriteCounter = 0;
			if (mouseL.leftClick == true) {
				getPlayerActionImage();
			} else if (isMoving){
				getPlayerMovementImage();
			} else if (isMoving == false) {
				getPlayerIdleImage();
			}
		}
	}
	public void pickupObject(int i) {
		if (i != 999) {
			if (gp.obj[i] != null) {
				String objectName = gp.obj[i].name;
				switch (objectName) {
				case "Key":
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
			case "Chest":
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
			g2.setColor(Color.white);
			// DRAW HITBOX
			g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
			// DRAW THE PLAYER'S POSITION
			g2.drawString("Position: " + worldX/gp.tileSize + ", " + worldY/gp.tileSize, gp.tileSize, gp.tileSize * 3);
		}
	}

}