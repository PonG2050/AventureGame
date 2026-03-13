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
		tile = new Tile[100];
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
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));

			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResource("/tiles/Water_Middle.png"));
			tile[1].collision = true;

			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResource("/tiles/Path_Middle.png"));

			// CLIFF TILES
			BufferedImage sheet1 = ImageIO.read(getClass().getResourceAsStream("/tiles/Cliff_Tile.png"));
			getSheetComponent(3, 21, 3, tile, sheet1, true);

			
			// FARM LAND TILES
			BufferedImage sheet2 = ImageIO.read(getClass().getResourceAsStream("/tiles/FarmLand_Tile.png"));
			getSheetComponent(21, 30, 3, tile, sheet2, false);

			
			//BEACH TILES
			BufferedImage sheet3 = ImageIO.read(getClass().getResourceAsStream("/tiles/Beach_Tile.png"));
			getSheetComponent(30, 45, 5, tile, sheet3, true);

			// WATER TILES
			BufferedImage sheet4 = ImageIO.read(getClass().getResourceAsStream("/tiles/Water_Tile.png"));
			getSheetComponent(45, 63, 3, tile, sheet4, true);

			// PATH TILES
			BufferedImage sheet5 = ImageIO.read(getClass().getResourceAsStream("/tiles/Path_Tile.png"));
			getSheetComponent(63, 81, 3, tile, sheet5, false);
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
