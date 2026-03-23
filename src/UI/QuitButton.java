package UI;


import Main.GamePanel;

public class QuitButton extends UIManager{

	public QuitButton(GamePanel gp) {
		super(gp);
		height = gp.tileSize*3;
		width = gp.tileSize*9;
		screenX = gp.screenWidth/2 - width/2;
		screenY = gp.screenHeight/2;
		image = sheet.getSubimage(102 * width, 2 * height, width * 3, height);
	}
	public void update() {
		
	}
}