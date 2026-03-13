package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;
import Main.KeyHandler;
import object.SuperObject;

public class Player extends Entity {

	GamePanel gp;
	KeyHandler keyH;

	public final int screenX;
	public final int screenY;
	int keyCount = 0;
	final int playerScale = 2;

	public Player(GamePanel gp, KeyHandler keyH) {

		super(gp);
		this.gp = gp;
		this.keyH = keyH;

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
		direction = "idlef";
	}

	public void getPlayerImage() {

		try {
			// idle frames
			for (int i = 0; i < 6; i++) {
				idlef[i] = ImageIO.read(getClass().getResourceAsStream("/player/idle" + (i + 1) + "f.png"));
			}
			for (int i = 0; i < 6; i++) {
				idleu[i] = ImageIO.read(getClass().getResourceAsStream("/player/idle" + (i + 1) + "u.png"));
			}
			for (int i = 0; i < 6; i++) {
				idler[i] = ImageIO.read(getClass().getResourceAsStream("/player/idle" + (i + 1) + "r.png"));
			}
			for (int i = 0; i < 6; i++) {
				idlel[i] = ImageIO.read(getClass().getResourceAsStream("/player/idle" + (i + 1) + "l.png"));
			}
			// walk frames
			for (int i = 0; i < 6; i++) {
				down[i] = ImageIO.read(getClass().getResourceAsStream("/player/down" + (i + 1) + ".png"));
			}
			for (int i = 0; i < 6; i++) {
				up[i] = ImageIO.read(getClass().getResourceAsStream("/player/up" + (i + 1) + ".png"));
			}
			for (int i = 0; i < 6; i++) {
				right[i] = ImageIO.read(getClass().getResourceAsStream("/player/right" + (i + 1) + ".png"));
			}
			for (int i = 0; i < 6; i++) {
				left[i] = ImageIO.read(getClass().getResourceAsStream("/player/left" + (i + 1) + ".png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void update() {
		isMoving = false;
	    
	    double currentSpeed = speed;

	    boolean isMovingX = keyH.Left || keyH.Right;
	    boolean isMovingY = keyH.Up || keyH.Down;

	    if (isMovingX && isMovingY) {
	        currentSpeed = speed / Math.sqrt(2); 
	    }

	    // ============================================================
	    // PHẦN 1: DI CHUYỂN TRỤC Y (LÊN / XUỐNG)
	    // ============================================================
	    
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

	    // ============================================================
	    // PHẦN 2: DI CHUYỂN TRỤC X (TRÁI / PHẢI)
	    // ============================================================
	    
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

	    // ============================================================
	    // PHẦN 3: TRẠNG THÁI & ANIMATION
	    // ============================================================
	    
	    if (isMoving == false) {
	        if (direction.equals("up")) direction = "idleu";
	        else if (direction.equals("down")) direction = "idlef";
	        else if (direction.equals("left")) direction = "idlel";
	        else if (direction.equals("right")) direction = "idler";
	    }
		
		spriteCounter++;
		if (spriteCounter > 8) {
			spriteCounter = 0;

			// idle
			idlefCounter++;
			if (idlefCounter >= 6) {
				idlefCounter = 0;
			}
			idleuCounter++;
			if (idleuCounter >= 6) {
				idleuCounter = 0;
			}
			idlerCounter++;
			if (idlerCounter >= 6) {
				idlerCounter = 0;
			}
			idlelCounter++;
			if (idlelCounter >= 6) {
				idlelCounter = 0;
			}

			// walk
			downCounter++;
			if (downCounter >= 6) {
				downCounter = 0;
			}
			upCounter++;
			if (upCounter >= 6) {
				upCounter = 0;
			}
			rightCounter++;
			if (rightCounter >= 6) {
				rightCounter = 0;
			}
			leftCounter++;
			if (leftCounter >= 6) {
				leftCounter = 0;
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

		BufferedImage image = null;

		switch (direction) {
		case "up":
			image = up[upCounter];
			break;
		case "down":
			image = down[downCounter];
			break;
		case "left":
			image = left[leftCounter];
			break;
		case "right":
			image = right[rightCounter];
			break;
		case "idlef":
			image = idlef[idlefCounter];
			break;
		case "idleu":
			image = idleu[idleuCounter];
			break;
		case "idler":
			image = idler[idlerCounter];
			break;
		case "idlel":
			image = idlel[idlelCounter];
			break;
		}

		g2.drawImage(image, screenX, screenY, gp.tileSize * playerScale, gp.tileSize * playerScale, null);
		if (keyH.HitBox == true) {
			g2.setColor(Color.red);
			g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
		}
	}

}