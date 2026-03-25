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
	UIButton playButton, quitButton, optionButton;
	BufferedImage sheet, playImg, quitImg, optionImg;
	public UIManager(GamePanel gp) {
		this.gp = gp;
		try {
			BufferedImage playImage = ImageIO.read(getClass().getResourceAsStream("/UI/UI_Buttons.png"));
		}catch (IOException e) {
			e.printStackTrace();
		}
		setDefaultValue();
	}

	public void setDefaultValue() {
		Background = new UIBackground(gp);
		
		try {
			sheet = ImageIO.read(getClass().getResourceAsStream("/UI/UI_Buttons.png"));
			playImg = sheet.getSubimage(102 * 16, 22 * 16, 16 * 3, 16);
			quitImg = sheet.getSubimage(102 * 16, 23 * 16, 16 * 3, 16);
			optionImg = sheet.getSubimage(102 * 16, 21 * 16, 16 * 3, 16);
			
			// PLAY BUTTON SETTING
			int playButtonHeight = gp.tileSize*3;
			int playButtonWidth = gp.tileSize*9;
			int playButtonX = gp.screenWidth/2 - playButtonWidth/2;
			int playButtonY = gp.screenHeight/2 - 3 * gp.tileSize;
			playButton = new UIButton(gp, "PLAY", playImg, playButtonX, playButtonY, playButtonWidth, playButtonHeight);
			// QUIT BUTTON SETTING
			int quitButtonHeight = gp.tileSize*3;
			int quitButtonWidth = gp.tileSize*9;
			int quitButtonX = gp.screenWidth/2 - quitButtonWidth/2;
			int quitButtonY = gp.screenHeight/2 + 3 * gp.tileSize;
			quitButton = new UIButton(gp, "QUIT", quitImg, quitButtonX, quitButtonY, quitButtonWidth, quitButtonHeight);
			// OPTION BUTTON SETTING
			int optionButtonHeight = gp.tileSize*3;
			int optionButtonWidth = gp.tileSize*9;
			int optionButtonX = gp.screenWidth/2 - optionButtonWidth/2;
			int optionButtonY = gp.screenHeight/2;
			optionButton = new UIButton(gp, "OPTION", optionImg, optionButtonX, optionButtonY, optionButtonWidth, optionButtonHeight);
			
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void update() {
		if (gp.gameState == gp.titleState) {
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
		if (gp.gameState == gp.playState) {
			
		}
		if (gp.gameState == gp.pauseState) {
			
		}
	}
	public void draw(Graphics2D g2) {
		if (gp.gameState == gp.titleState) {
			Background.draw(g2);
			playButton.draw(g2);
			quitButton.draw(g2);
			optionButton.draw(g2);
		}
		if (gp.gameState == gp.playState) {
			
		}
		if (gp.gameState == gp.pauseState) {
			
		}
	}
}
