package Main;

import entity.Entity;
import entity.Fox;
import entity.GreenSlime;
import entity.Skeleton;
import object.BigRock;
import object.BlueWildflower;
import object.Islet;
import object.Lotus_leaf;
import object.NormalRock;
import object.OBJ_Chest;
import object.OBJ_Key;
import object.OBJ_Oak_tree;
import object.SmallRock;
import object.SuperObject;
import object.Weed;
import object.WhiteWildflower;

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
		spawnObject(new OBJ_Oak_tree(gp), 131, 140); 
		spawnObject(new OBJ_Oak_tree(gp), 123, 145); 
		spawnObject(new OBJ_Oak_tree(gp), 115, 143); 
		spawnObject(new OBJ_Oak_tree(gp), 122, 136); 
	
		spawnObject(new SmallRock(gp), 125, 125); 
		spawnObject(new SmallRock(gp), 137, 129); 
		spawnObject(new SmallRock(gp), 112, 130); 
		spawnObject(new SmallRock(gp), 134, 125); 
		spawnObject(new SmallRock(gp), 156, 122); 
		
		spawnObject(new NormalRock(gp), 128, 129); 
		spawnObject(new NormalRock(gp), 142, 133); 
		spawnObject(new NormalRock(gp), 134, 137); 
		spawnObject(new NormalRock(gp), 124, 133); 
		spawnObject(new NormalRock(gp), 140, 138); 
		spawnObject(new NormalRock(gp), 147, 118); 
		spawnObject(new NormalRock(gp), 161, 115); 
		spawnObject(new NormalRock(gp), 119, 138); 
		spawnObject(new NormalRock(gp), 127, 145); 
		spawnObject(new NormalRock(gp), 117, 148); 
		
		spawnObject(new BigRock(gp), 128, 126); 
		spawnObject(new BigRock(gp), 144, 139); 
		spawnObject(new BigRock(gp), 143, 134); 
		spawnObject(new BigRock(gp), 132, 129); 
		
		spawnObject(new Islet(gp), 139, 152); 
		spawnObject(new Islet(gp), 138, 152); 
		spawnObject(new Islet(gp), 142, 154); 
		spawnObject(new Islet(gp), 135, 120); 
		spawnObject(new Islet(gp), 139, 155); 
		spawnObject(new Islet(gp), 126, 122);
		
		spawnObject(new Lotus_leaf(gp), 127, 122); 
		spawnObject(new Lotus_leaf(gp), 128, 122); 
		spawnObject(new Lotus_leaf(gp), 128, 121); 
		spawnObject(new Lotus_leaf(gp), 129, 122); 
		spawnObject(new Lotus_leaf(gp), 140, 152); 
		spawnObject(new Lotus_leaf(gp), 135, 155); 
		spawnObject(new Lotus_leaf(gp), 142, 155); 
		spawnObject(new Lotus_leaf(gp), 137, 154); 
		spawnObject(new Lotus_leaf(gp), 142, 155); 
		spawnObject(new Lotus_leaf(gp), 110, 128); 
		spawnObject(new Lotus_leaf(gp), 111, 120); 
		spawnObject(new Lotus_leaf(gp), 121, 117); 
		spawnObject(new Lotus_leaf(gp), 140, 120); 
		spawnObject(new Lotus_leaf(gp), 141, 120); 
		spawnObject(new Lotus_leaf(gp), 136, 116); 

		spawnObject(new BlueWildflower(gp), 122, 129); 
		spawnObject(new BlueWildflower(gp), 121, 129); 
		spawnObject(new BlueWildflower(gp), 122, 128); 
		spawnObject(new BlueWildflower(gp), 122, 130); 
		spawnObject(new BlueWildflower(gp), 123, 129); 
		spawnObject(new BlueWildflower(gp), 123, 130); 

		spawnObject(new WhiteWildflower(gp), 142, 129); 
		spawnObject(new WhiteWildflower(gp), 141, 130); 
		spawnObject(new WhiteWildflower(gp), 141, 129); 
		spawnObject(new WhiteWildflower(gp), 142, 129); 
		spawnObject(new WhiteWildflower(gp), 141, 129); 
		spawnObject(new WhiteWildflower(gp), 140, 129); 
		spawnObject(new WhiteWildflower(gp), 142, 128); 

		spawnObject(new Weed(gp), 135, 125); 
		spawnObject(new Weed(gp), 130, 125); 
		spawnObject(new Weed(gp), 130, 131); 
		spawnObject(new Weed(gp), 138, 131); 

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
