package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

import Main.GamePanel;

public class TileManager {

	GamePanel gp;
	public Tile[] tile;
	public Tile[][] worldMap;
	HashMap<String, Tile[]> tileRegistry = new HashMap<>();

	public TileManager(GamePanel gp) {

		this.gp = gp;
		tile = new Tile[100];
		getTileImage();
		loadMap("/maps/World01.txt");
	}
	//CREAT THE METHOD TO SUBIMAGE, ADD IN THE ARRAY TO SUPPORT THE HASHMAP
	public Tile[] cutSheet(int maxCol, BufferedImage sheet, int numTiles, boolean collision) {
		Tile[] subTiles = new Tile[numTiles];
		int row = 0;
		int col = 0;
		for (int i = 0; i < numTiles; i++) {
			subTiles[i] = new Tile();
			subTiles[i].image = sheet.getSubimage(col * gp.originalTileSize, row * gp.originalTileSize, gp.originalTileSize, gp.originalTileSize);
			subTiles[i].collision = collision;
			col++;
			if (col == maxCol) {
				col = 0;
				row++;
			}
		}
		
		return subTiles;
	}
	//GET SPRITES AND ADD IN HASHMAP
	public void getTileImage() {
	    try {
	        tileRegistry.put("grass", cutSheet(1, ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png")), 1, false));
	        tileRegistry.put("water_mid", cutSheet(1, ImageIO.read(getClass().getResourceAsStream("/tiles/Water_Middle.png")), 1, true));
	        tileRegistry.put("path_mid", cutSheet(1, ImageIO.read(getClass().getResourceAsStream("/tiles/Path_Middle.png")), 1, false));

	        
	        BufferedImage cliffSheet = ImageIO.read(getClass().getResourceAsStream("/tiles/Cliff_Tile.png"));
	        tileRegistry.put("cliff", cutSheet(3, cliffSheet, 18, true));

	        BufferedImage farmSheet = ImageIO.read(getClass().getResourceAsStream("/tiles/FarmLand_Tile.png"));
	        tileRegistry.put("farm", cutSheet(3, farmSheet, 9, false));

	        BufferedImage beachSheet = ImageIO.read(getClass().getResourceAsStream("/tiles/Beach_Tile.png"));
	        tileRegistry.put("beach", cutSheet(5, beachSheet, 15, true));

	        BufferedImage waterSheet = ImageIO.read(getClass().getResourceAsStream("/tiles/Water_Tile.png"));
	        tileRegistry.put("water", cutSheet(3, waterSheet, 18, true));

	        BufferedImage pathSheet = ImageIO.read(getClass().getResourceAsStream("/tiles/Path_Tile.png"));
	        tileRegistry.put("path", cutSheet(3, pathSheet, 18, false));

	    } catch (IOException e) {
	        System.out.println("get image errors " + e.getMessage());
	    }
	}	
	public void loadMap(String filePath) {
	    worldMap = new Tile[gp.maxWorldCol][gp.maxWorldRow];
	    try {
	        InputStream is = getClass().getResourceAsStream(filePath);
	        BufferedReader br = new BufferedReader(new InputStreamReader(is));

	        int row = 0;
	        while (row < gp.maxWorldRow) {
	            String line = br.readLine();
	            if (line == null) break;

	            String tokens[] = line.split(" ");

	            for (int col = 0; col < gp.maxWorldCol; col++) {
	                String token = tokens[col]; 
	                
	                String[] parts = token.split(":");
	                String category = parts[0];
	                int index = Integer.parseInt(parts[1]);

	                worldMap[col][row] = tileRegistry.get(category)[index];
	            }
	            row++;
	        }
	        br.close();
	    } catch (Exception e) {
	        System.out.println("LoadMap erors: " + e.getMessage());
	    }
	}

	public void update() {

	}

	public void draw(Graphics2D g2) {
	    int worldCol = 0;
	    int worldRow = 0;

	    while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
	        
	        Tile currentTile = worldMap[worldCol][worldRow];

	        int worldX = worldCol * gp.tileSize;
	        int worldY = worldRow * gp.tileSize;
	        int screenX = worldX - gp.player.worldX + gp.player.screenX;
	        int screenY = worldY - gp.player.worldY + gp.player.screenY;

	        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
	            worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
	            worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
	            worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

	            if (currentTile != null && currentTile.image != null) {
	                g2.drawImage(currentTile.image, screenX, screenY, gp.tileSize, gp.tileSize, null);
	            }
	        }
	        
	        worldCol++;
	        if (worldCol == gp.maxWorldCol) {
	            worldCol = 0;
	            worldRow++;
	        }
	    }
	}

}
