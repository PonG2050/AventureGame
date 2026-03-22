package Main;

import entity.Fox;
import entity.GreenSlime;
import entity.Skeleton;
import object.OBJ_Chest;
import object.OBJ_Key;
import object.OBJ_Oak_tree;

public class AssetSetter {
	
	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		gp.obj[0] = new OBJ_Chest(gp);
		gp.obj[0].worldX = 176 * gp.tileSize;
		gp.obj[0].worldY = 180 * gp.tileSize;
		
		gp.obj[1] = new OBJ_Key(gp);
		gp.obj[1].worldX = 178 * gp.tileSize;
		gp.obj[1].worldY = 180 * gp.tileSize;
		
		gp.obj[2] = new OBJ_Chest(gp);
		gp.obj[2].worldX = 172 * gp.tileSize;
		gp.obj[2].worldY = 170 * gp.tileSize;
		
		gp.obj[3] = new OBJ_Oak_tree(gp);
		gp.obj[3].worldX = 168 * gp.tileSize;
		gp.obj[3].worldY = 170 * gp.tileSize;
		
		gp.obj[4] = new OBJ_Oak_tree(gp);
		gp.obj[4].worldX = 174 * gp.tileSize;
		gp.obj[4].worldY = 170 * gp.tileSize;
		
		gp.obj[5] = new OBJ_Oak_tree(gp);
		gp.obj[5].worldX = 172 * gp.tileSize;
		gp.obj[5].worldY = 177 * gp.tileSize;
		
		gp.obj[6] = new OBJ_Oak_tree(gp);
		gp.obj[6].worldX = 166 * gp.tileSize;
		gp.obj[6].worldY = 166 * gp.tileSize;
	}
	public void setMonster() {
		gp.monster[0] = new GreenSlime(gp);
		gp.monster[0].worldX = 172 * gp.tileSize;
		gp.monster[0].worldY = 169 * gp.tileSize;
		
		gp.monster[1] = new GreenSlime(gp);
		gp.monster[1].worldX = 172 * gp.tileSize;
		gp.monster[1].worldY = 180 * gp.tileSize;
		
		gp.monster[2] = new Fox(gp);
		gp.monster[2].worldX = 177 * gp.tileSize;
		gp.monster[2].worldY = 180 * gp.tileSize;
		
		gp.monster[3] = new Skeleton(gp);
		gp.monster[3].worldX = 178 * gp.tileSize;
		gp.monster[3].worldY = 180 * gp.tileSize;
		
		gp.monster[4] = new Skeleton(gp);
		gp.monster[4].worldX = 178 * gp.tileSize;
		gp.monster[4].worldY = 180 * gp.tileSize;
		
		gp.monster[5] = new Skeleton(gp);
		gp.monster[5].worldX = 178 * gp.tileSize;
		gp.monster[5].worldY = 180 * gp.tileSize;
	}
}
