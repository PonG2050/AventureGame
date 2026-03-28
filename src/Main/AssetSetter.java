package Main;

import entity.Entity;
import entity.Fox;
import entity.GreenSlime;
import entity.Skeleton;
import object.OBJ_Chest;
import object.OBJ_Key;
import object.OBJ_Oak_tree;
import object.SuperObject;

public class AssetSetter {
	
	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		spawnObject(new OBJ_Chest(gp), 176, 180);
		spawnObject(new OBJ_Key(gp), 178, 180);
		spawnObject(new OBJ_Chest(gp), 172, 170);
		
		spawnObject(new OBJ_Oak_tree(gp), 132, 121);
		spawnObject(new OBJ_Oak_tree(gp), 129, 133);
		spawnObject(new OBJ_Oak_tree(gp), 136, 131);
		spawnObject(new OBJ_Oak_tree(gp), 126, 132);
		spawnObject(new OBJ_Oak_tree(gp), 138, 123);
		spawnObject(new OBJ_Oak_tree(gp), 118, 129);
		spawnObject(new OBJ_Oak_tree(gp), 113, 125);
		spawnObject(new OBJ_Oak_tree(gp), 123, 125); 
		spawnObject(new OBJ_Oak_tree(gp), 162, 123); 
		spawnObject(new OBJ_Oak_tree(gp), 152, 123); 
		spawnObject(new OBJ_Oak_tree(gp), 145, 124); 
		spawnObject(new OBJ_Oak_tree(gp), 144, 129); 
		spawnObject(new OBJ_Oak_tree(gp), 140, 134); 
		spawnObject(new OBJ_Oak_tree(gp), 142, 138); 
		spawnObject(new OBJ_Oak_tree(gp), 146, 114);
		spawnObject(new OBJ_Oak_tree(gp), 157, 112); 
		spawnObject(new OBJ_Oak_tree(gp), 171, 116); 
		spawnObject(new OBJ_Oak_tree(gp), 171, 125); 
		spawnObject(new OBJ_Oak_tree(gp), 177, 136); 
		spawnObject(new OBJ_Oak_tree(gp), 178, 120); 
		spawnObject(new OBJ_Oak_tree(gp), 183, 123); 
		spawnObject(new OBJ_Oak_tree(gp), 190, 114); 

	}
	
	public void setMobs() {
		spawnMobs(new GreenSlime(gp), 131, 126);
		spawnMobs(new GreenSlime(gp), 133, 128);
		spawnMobs(new Fox(gp), 125, 132);
		spawnMobs(new Skeleton(gp), 135, 129);
		spawnMobs(new Skeleton(gp), 132, 132);
		spawnMobs(new Skeleton(gp), 127, 131); 
	}
	public void spawnObject(SuperObject new_obj, int col, int row) {
		for (int i = 0; i < gp.obj.length; i++) {
			if (gp.obj[i] == null) {
				gp.obj[i] = new_obj;
				gp.obj[i].worldX = gp.tileSize * col;
				gp.obj[i].worldY = gp.tileSize * row;
				break;
			}
		}
	}
	public void spawnMobs(Entity new_entity, int col, int row) {
		for (int i = 0; i < gp.mob.length; i++) {
			if (gp.mob[i] == null) {
				gp.mob[i] = new_entity;
				gp.mob[i].worldX = gp.tileSize * col;
				gp.mob[i].worldY = gp.tileSize * row;
				break;
			}
		}
	}
}
