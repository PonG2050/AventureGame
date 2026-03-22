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
	Font arial_40;
	int width = 16;
	int height = 16;
	BufferedImage backGround, sheet, play, setting, quit;
	
	public UI(GamePanel gp) {
		this.gp = gp;
		arial_40 = new Font("Arial", Font.PLAIN, 20);
		try {
			sheet = ImageIO.read(getClass().getResourceAsStream("/UI/UI_Buttons.png"));
		}catch (IOException e) {
			e.printStackTrace();
		}
		play = sheet.getSubimage(102 * width, 1 * height, width * 3, height);
	}
	public void drawTitle(Graphics2D g2) {
		g2.drawImage(play, gp.screenWidth/2 - gp.tileSize*2, gp.screenHeight/2, gp.tileSize*6, gp.tileSize*2, null);
	}
	public void draw(Graphics2D g2) {
		drawTitle(g2);
		g2.setFont(arial_40);
		g2.setColor(Color.white);
		g2.drawString("x: " + gp.player.worldX/ gp.tileSize + ", y: " + gp.player.worldY/gp.tileSize, gp.tileSize, gp.tileSize);
	}
}
