package UI;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class UIManager {
	GamePanel gp;
	UIComponent UIComponent;
	UIBackground Background;
	UIInventory Inventory;
	// PLAYER'S HEALTH
	UIHealthBar playerHealthBar;
    BufferedImage heartFull, heartHalf, heartBlank;
    // PLAYER'S ENERGY 
	UIEnergyBar playerEnergyBar;
    BufferedImage energyFull, energyHalf, energyBlank;
    // TITLE BUTTONS
	UIButton playButton, quitButton, optionButton;
	BufferedImage sheet, playImg, quitImg, optionImg, hotbarImg;
	public UIManager(GamePanel gp) {
		this.gp = gp;
		setDefaultValue();
	}

	public void setDefaultValue() {
		Background = new UIBackground(gp);
		Inventory = new UIInventory(gp);
		
		try {
			
			sheet = ImageIO.read(getClass().getResourceAsStream("/UI/UI_ALL.png"));
			
			// PLAY BUTTON SETTING
			playImg = sheet.getSubimage(102 * 16, 43 * 16, 16 * 3, 16);
			int playButtonHeight = gp.tileSize*3;
			int playButtonWidth = gp.tileSize*9;
			int playButtonX = gp.screenWidth/2 - playButtonWidth/2;
			int playButtonY = gp.screenHeight/2 - 3 * gp.tileSize;
			playButton = new UIButton(gp, "PLAY", playImg, playButtonX, playButtonY, playButtonWidth, playButtonHeight);
			// QUIT BUTTON SETTING
			quitImg = sheet.getSubimage(102 * 16, 44 * 16, 16 * 3, 16);
			int quitButtonHeight = gp.tileSize*3;
			int quitButtonWidth = gp.tileSize*9;
			int quitButtonX = gp.screenWidth/2 - quitButtonWidth/2;
			int quitButtonY = gp.screenHeight/2 + 3 * gp.tileSize;
			quitButton = new UIButton(gp, "QUIT", quitImg, quitButtonX, quitButtonY, quitButtonWidth, quitButtonHeight);
			// OPTION BUTTON SETTING
			optionImg = sheet.getSubimage(102 * 16, 42 * 16, 16 * 3, 16);
			int optionButtonHeight = gp.tileSize*3;
			int optionButtonWidth = gp.tileSize*9;
			int optionButtonX = gp.screenWidth/2 - optionButtonWidth/2;
			int optionButtonY = gp.screenHeight/2;
			optionButton = new UIButton(gp, "OPTION", optionImg, optionButtonX, optionButtonY, optionButtonWidth, optionButtonHeight);
			// PLAYER'S HEART IMAGE
			heartFull = sheet.getSubimage(0 * 16, 62 * 16, 16, 16);
			heartHalf = sheet.getSubimage(1 * 16, 62 * 16, 16, 16);
			heartBlank = sheet.getSubimage(2 * 16, 62 * 16, 16, 16);
	        int hbX = gp.tileSize / 2;
	        int hbY = gp.tileSize / 2;
	        int heartSize = gp.tileSize / 2;
	        playerHealthBar = new UIHealthBar(gp, hbX, hbY, heartSize, heartSize); 
	        playerHealthBar.heartFull = heartFull;
	        playerHealthBar.heartHalf = heartHalf;
	        playerHealthBar.heartBlank = heartBlank;
	        // PLAYER' ENERGY IMAGE
			energyFull = sheet.getSubimage(9 * 16, 62 * 16, 16, 16);
			energyHalf = sheet.getSubimage(10 * 16, 62 * 16, 16, 16);
	        energyBlank = sheet.getSubimage(11 * 16, 62 * 16, 16, 16);
	        int eX = gp.tileSize / 2;
	        int eY = gp.tileSize;
	        int energySize = gp.tileSize / 2;
	        playerEnergyBar = new UIEnergyBar(gp, eX, eY, energySize, energySize); 
	        playerEnergyBar.energyFull = energyFull;
	        playerEnergyBar.energyHalf = energyHalf;
	        playerEnergyBar.energyBlank = energyBlank;
	
			
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void titleStateUpdate() {
		Background.update();
		playButton.update();
		quitButton.update();
		optionButton.update();
		if (playButton.isClicked()) {
			gp.gameState = gp.playState;
		} else if (quitButton.isClicked()) {
			System.exit(0);
		}
	}
	public void playStateUpdate() {
		Inventory.update();
		playerHealthBar.update();
		playerEnergyBar.update();	
	}
	public void pauseStateUpdate() {
		
	}
	public void draw(Graphics2D g2) {
		if (gp.gameState == gp.titleState) {
			Background.draw(g2);
			playButton.draw(g2);
			quitButton.draw(g2);
			optionButton.draw(g2);
		}
		if (gp.gameState == gp.playState) {
			Inventory.draw(g2);
			playerHealthBar.draw(g2);
			playerEnergyBar.draw(g2);
		}
		if (gp.gameState == gp.pauseState) {
			
		}
	}
}
