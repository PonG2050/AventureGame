package UI;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import Main.GamePanel;

public class UIBackground extends UIComponent {
	BufferedImage[] frames;
	int spriteCounter = 0;
	int currentFrames = 0;

	public UIBackground(GamePanel gp) {
		super(gp, 0, 0, 630, 500);
		frames = new BufferedImage[120];
		loadFrames();
	}
	private void loadFrames() {
		try {
			
			BufferedImage sheet = ImageIO.read(getClass().getResourceAsStream("/UI/BackGround.png"));
			for (int i = 0; i < 120; i++) {
				frames[i] = sheet.getSubimage(0, 500 * i, 630, 500);
			}
			image = frames[0];
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void update() {
		spriteCounter++;
		if (spriteCounter > 10) {
			currentFrames = (currentFrames >= 119) ? 0 : ++currentFrames;
			image = frames[currentFrames];
			spriteCounter = 0;
		}
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.drawImage(image, x, y, width * gp.scale, height * gp.scale, null);
	}
}