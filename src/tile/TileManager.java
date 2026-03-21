package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

import Main.GamePanel;

public class TileManager {

	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][];

	public TileManager(GamePanel gp) {

		this.gp = gp;
		tile = new Tile[1000];
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		getTileImage();
		loadMap("/maps/World01.txt");
	}	
	public void getSheetComponent (int x, int y, int maxCol, Tile tile[],BufferedImage sheet,boolean collision) {
		// start, end, colNum, tile, sheet, collision
		int index = x;
		int col = 0;
		int row = 0;
		while (index < y) {
			tile[index] = new Tile();
			int positionX = col * gp.originalTileSize;
			int positionY = row * gp.originalTileSize;
			tile[index].image = sheet.getSubimage(positionX, positionY, gp.originalTileSize, gp.originalTileSize);
			tile[index].collision = collision;
			index++;
			col++;
			if (col == maxCol) {
				col = 0;
				row++;
			}
		}
	}
	public void getTileImage() {

		try {	
			// CLIFF TILES
			BufferedImage sheet = ImageIO.read(getClass().getResourceAsStream("/tiles/TileSheet.png"));
			getSheetComponent(0, 999, 20, tile, sheet, false);
			
			int[] collisionList = {128, 129, 130, 131, 132, 133, 148, 149, 150, 151, 152, 153, 168, 169, 170, 171, 172, 173, 188, 189, 190, 191, 192, 193, 198, 199, 200, 201, 202, 203, 208, 209, 210, 211, 212, 213, 660, 661, 662, 663, 664, 665, 666, 667};
			for (int i = 0; i < collisionList.length; i++) {
				tile[collisionList[i]].collision = true;
			}
			
		} catch (IOException e) {
			System.out.println("tile errors");
		}
	}

	public void loadMap(String filePath) {

		try {

			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			int col = 0;
			int row = 0;

			while (col < gp.maxWorldCol && row < gp.maxWorldRow) {

				String line = br.readLine();

				while (col < gp.maxWorldCol) {

					String numbers[] = line.split(" ");

					int num = Integer.parseInt(numbers[col]);
					mapTileNum[col][row] = num;
					col++;
				}
				if (col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			br.close();
		} catch (Exception e) {
			System.out.println("map errors");
		}
	}

	public void update() {

	}

	public void draw(Graphics2D g2) {

		int worldCol = 0;
		int worldRow = 0;

		while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

			int tileNum = mapTileNum[worldCol][worldRow];

			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;

			if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
				worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
				worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
				worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

				g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);

			}
			worldCol++;
			if (worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}
		}

	}

}