package object;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class ObjectManager {

	GamePanel gp;
	Object[] object;
	public int mapObjectNum[][];
	public ObjectManager(GamePanel gp) {
		
		this.gp = gp;
		object = new Object[100];
		mapObjectNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		getObjectImage();
		loadObject("/objectsMap/ObjectsMap01.txt");
	}
	public void getObjectImage() {
	
		try {

			object[0] = new Object();
			object[0].image = ImageIO.read(getClass().getResourceAsStream("/objects/air.png"));
			object[1] = new Object();
			object[1].image = ImageIO.read(getClass().getResourceAsStream("/objects/tree00-1.png"));
			object[2] = new Object();
			object[2].image = ImageIO.read(getClass().getResourceAsStream("/objects/canopy.png"));

		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void loadObject(String filePath) {
		
		try {
			
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader (new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
				
				String line = br.readLine();
				
				while(col < gp.maxWorldCol) {
					
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					mapObjectNum[col][row] = num;
					col++;
				}
				if (col == gp.maxWorldCol ) {
					col = 0;
					row++;
				}
			}
			br.close();
		}catch(Exception e) {
		}
	}
	public int OscreenX;
	public int OscreenY;
	public void draw(Graphics2D g2) {
		
		int worldCol = 0;
		int worldRow = 0;
		
		
		while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
			
			int objectNum = mapObjectNum[worldCol][worldRow];
			
			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int OscreenX = worldX - gp.player.worldX + gp.player.screenX;
			int OscreenY = worldY - gp.player.worldY + gp.player.screenY;
			
			if (worldX + gp.tileSize> gp.player.worldX - gp.player.screenX &&
				worldX - gp.tileSize< gp.player.worldX + gp.player.screenX &&
				worldY + gp.tileSize> gp.player.worldY - gp.player.screenY &&
				worldY - gp.tileSize< gp.player.worldY + gp.player.screenY) {
				
				if ( objectNum == 1 ) {
					g2.drawImage( object[objectNum].image, OscreenX, OscreenY, gp.tileSize, gp.tileSize*2, null);
				}else {
					g2.drawImage( object[objectNum].image, OscreenX, OscreenY, gp.tileSize, gp.tileSize, null);

				}
			}
			worldCol++;
			if (worldCol == gp.maxWorldCol) {
				worldCol =0;
				worldRow++;
			}
		}
	
	
	}
	
}
