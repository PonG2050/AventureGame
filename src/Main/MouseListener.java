package Main;

import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Player;

public class MouseListener extends MouseAdapter{
	GamePanel gp;
	public boolean rightClick, leftClick;
	public int mouseX;
	public int mouseY;
	int spriteCounter = 0;
	int col = 0;
	boolean isClicking = false;
	BufferedImage image, sheet;
	
	public MouseListener(GamePanel gp) {
		this.gp = gp;
		try {
			sheet = ImageIO.read(getClass().getResourceAsStream("/UI/Pointer_Click_Anim.png"));
			image = sheet.getSubimage(0, 0, 16, 16);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		int code = e.getButton();
		if (code == MouseEvent.BUTTON1) {
			leftClick = true;
			isClicking = true;
			mouseX = e.getX();
			mouseY = e.getY();
			System.out.println(mouseX + " " + mouseY);
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		int code = e.getButton();
		if (code == MouseEvent.BUTTON1) {
			leftClick = false;
		}
	}
	public void update() {
		if (isClicking == true) {
			spriteCounter++;
			if (spriteCounter > 5) {
				col++;
				if (col > 3) {
					col = 0;
					isClicking = false;
				}
				image = sheet.getSubimage(16 * col, 0, 16, 16);
				spriteCounter = 0;
			}
		}
	}
	public void draw(Graphics2D g2) {
		if (isClicking) {
			g2.drawImage(image, mouseX - (16 * gp.scale) / 2, mouseY - (16 * gp.scale) / 2, 16 * gp.scale, 16 * gp.scale, null);
		}
	}

	public void action() {
		
	}
}
