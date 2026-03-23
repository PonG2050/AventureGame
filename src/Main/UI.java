package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class UI {
	GamePanel gp;
	Graphics2D g2;
	Font arial_20, arial_40B;
	int width = 16;
	int height = 16;
	int spriteCounter = 0;
	
	// BACKGROUND 
	int backgroundWidth, backgroundHeight;
	int backgroundCol = 0, backgroundRow = 0;
	
	// PLAY BUTTON
	int playButtonX, playButtonY, playButtonHeight, playButtonWidth;
	// QUIT BUTTON
	int quitButtonX, quitButtonY, quitButtonHeight, quitButtonWidth;
	BufferedImage backGround, sheet, backgroundSheet, backgroundImage, playImage, settingImage, quitImage;
	
	public UI(GamePanel gp) {
		this.gp = gp;
		arial_20 = new Font("Arial", Font.PLAIN, 20);
		arial_40B = new Font("Arial", Font.BOLD, 40);
		// BACKGROUND SETTING 
		backgroundWidth = 630;
		backgroundHeight = 500;
		// PLAY BUTTON SETTING
		playButtonHeight = gp.tileSize*3;
		playButtonWidth = gp.tileSize*9;
		playButtonX = gp.screenWidth/2 - playButtonWidth/2;
		playButtonY = gp.screenHeight/2 - 3 * gp.tileSize;
		// QUIT BUTTON SETTING
		quitButtonHeight = gp.tileSize*3;
		quitButtonWidth = gp.tileSize*9;
		quitButtonX = gp.screenWidth/2 - playButtonWidth/2;
		quitButtonY = gp.screenHeight/2;

		try {
			// GET SHEET
			backgroundSheet = ImageIO.read(getClass().getResourceAsStream("/UI/BackGround.png"));
			sheet = ImageIO.read(getClass().getResourceAsStream("/UI/UI_Buttons.png"));
			// GET IMAGE 
			backgroundImage = backgroundSheet.getSubimage(0, 0, backgroundWidth, backgroundHeight);
			playImage = sheet.getSubimage(102 * width, 1 * height, width * 3, height);
			quitImage = sheet.getSubimage(102 * width, 2 * height, width * 3, height);
			
		}catch (IOException e) {
			e.printStackTrace();
		}

	}
	public void checkClicked() {
		
	}
	// TITLE STATE METHODS
	public void updateTitleState() {
		
		if (gp.mouseL.mouseX > playButtonX &&
			gp.mouseL.mouseX < playButtonX + playButtonWidth &&
			gp.mouseL.mouseY > playButtonY &&
			gp.mouseL.mouseY < playButtonY + playButtonHeight) {
			
			gp.gameState = gp.playState;
			
		}
		
		// BACKGROUND UPDATE
		if (spriteCounter > 5) {
			if (backgroundRow > 119) {
				backgroundRow = 0;
			} else {
				backgroundImage = backgroundSheet.getSubimage(0, backgroundHeight * backgroundRow, backgroundWidth, backgroundHeight);
				backgroundRow++;
			}
			spriteCounter = 0;
		} else spriteCounter++;
		
		
	}
	public void drawTitleState(Graphics2D g2) {
		g2.drawImage(backgroundImage, 0, 0, backgroundWidth + (14 * gp.tileSize), backgroundHeight + (14 * gp.tileSize), null);
		g2.drawImage(playImage, playButtonX, playButtonY, playButtonWidth, playButtonHeight, null);
		g2.drawImage(quitImage, quitButtonX, quitButtonY, quitButtonWidth, quitButtonHeight, null);
	}
	
	//PLAY STATE METHODS
	public void updatePlayState() {}
	public void drawPlayState(Graphics2D g2) {
		g2.setFont(arial_20);
		g2.setColor(Color.white);
		g2.drawString("x: " + gp.player.worldX/ gp.tileSize + ", y: " + gp.player.worldY/gp.tileSize, gp.tileSize, gp.tileSize);
	}
	//PAUSE STATE METHODS
	public void updatePauseState() {}
	public void drawPauseState(Graphics2D g2) {
		String text = "PAUSE";
		g2.setFont(arial_40B);
		g2.setColor(Color.white);
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth/2 - length/2;
		int y = gp.screenHeight/2;
		g2.drawString(text, x, y);
	}
}
