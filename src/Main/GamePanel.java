package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.JPanel;

import UI.UIManager;
import entity.Entity;
import entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {

	public final int originalTileSize = 16;
	public final int scale = 2;
	// screen settings
	public final int tileSize = originalTileSize * scale;
	public final int maxScreenCol = 32;
	public final int maxScreenRow = 20;
	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenHeight = tileSize * maxScreenRow;
	// world settings
	public final int maxWorldCol = 250;
	public final int maxWorldRow = 250;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;

	private int FPS = 60;
	
	//GAME STATE
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int pauseState = 2;

	TileManager tileM = new TileManager(this);
	public KeyHandler keyH = new KeyHandler(this);
	public MouseListener mouseL = new MouseListener(this);
	Sound music = new Sound();
	Sound soundEffect = new Sound();
	
	public LoadAsset lAsset = new LoadAsset();
	public Player player = new Player(this, keyH, mouseL);
	public CollisionCheck cChecker = new CollisionCheck(this);
	public SuperObject obj[] = new SuperObject[10];
	public Entity monster[] = new Entity[10];
	public UIManager ui = new UIManager(this);
	
	ArrayList<Entity> entityList = new ArrayList<>();
	
	public AssetSetter aSetter = new AssetSetter(this);
	
	Thread gameThread;

	public GamePanel() {

		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.addMouseListener(mouseL);
		this.setFocusable(true);
	}

	public void setupGame() {
		
		gameState = titleState;
		
		aSetter.setObject();
		aSetter.setMonster();
	}
	public void startGameThread() {

		gameThread = new Thread(this);
		gameThread.start();

	}

	@Override
	public void run() {

		double drawInterval = 1000000000.0 / FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;

		while (gameThread != null) {

			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;

			if (delta >= 1) {

				update();
				repaint();
				delta--;

			}

		}
	}

	public void update() {
		// MOUSE UPDATE	
		mouseL.update();
		
		//TITLE STATE
		if (gameState == titleState) {
			ui.update();
		}
		//PLAY STATE 
		if (gameState == playState) {
			player.update();
			for (int i = 0; i < obj.length; i++) {
				if (obj[i] != null) {
					(obj[i]).update();
				}
			}
			for (int i = 0; i < monster.length; i++) {
				if (monster[i] != null) {
					monster[i].update();
				}
			}
		}
		if (gameState == pauseState) {}
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		tileM.draw(g2);

		//ENTITY AND OBJECT PAINT SETTING
		entityList.add(player);
		for (int i = 0; i < monster.length; i++) {
			if (monster[i] != null) {
				entityList.add(monster[i]);
			}
		}
		for( int i = 0; i < obj.length; i++) {
			if (obj[i] != null) {
				entityList.add(obj[i]);
			}
		}
		entityList.sort((entity1, entity2) -> {
			int value1 = entity1.worldY + entity1.solidArea.y + entity1.solidArea.height;
			int value2 = entity2.worldY + entity2.solidArea.y + entity2.solidArea.height;
			return Integer.compare(value1, value2);
		});
		for (int i = 0; i < entityList.size(); i++) {
			entityList.get(i).draw(g2);
		}
		entityList.clear();
		
		ui.draw(g2);
		mouseL.draw(g2);
		
		g2.dispose();
	}
	public void playMusic(int i) {
		music.play(i);
		music.loop(i);
	}
	public void stopMusic(int i) {
		music.stop(i);
	}
	public void playSE(int i) {
		// ADD NEW THREAD TO FIX THE LAG WHEN PLAY SOUND EFFECTS
		new Thread(new Runnable() {
			@Override
			public void run() {
				soundEffect.play(i);
			}
		}).start();
	}
}