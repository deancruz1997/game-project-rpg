package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;

public class GamePanel extends JPanel implements Runnable {

	// Screen Settings
	final int originalTileSize = 16; // 16 x 16 tile size
	final int scale = 3;
	
	public final int tileSize = originalTileSize * scale; // 48 x 48 tile size
	
	// Maximum tiles on screen = 16 x 9 tiles (16:9 ratio)
	final int maxScreenCol = 16;
	final int maxScreenRow = 9;
	final int screenWidth = tileSize * maxScreenCol; // 1536 pixels
	final int screenHeight = tileSize * maxScreenRow; // 864 pixels
	
	// FPS
	int FPS = 60;
	
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	Player player = new Player(this, keyH);
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.white);
		this.setDoubleBuffered(true); // Enabling this to true can improve game's rendering performance
	
		getImage();
		loadAnimations();
		
		this.addKeyListener(keyH);
		this.setFocusable(true); // Game panel can be "focusable" to receive key input
		
	}
	
	public void updateAnimationTick() {
		player.updateAnimationTick();
	}
	
	public void getImage() {
		player.getPlayerImage();

	}
	
	public void loadAnimations() {
		player.loadAnimations();

	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
		
	}

	@Override
	public void run() {
		
		double drawInterval = 1000000000/FPS; // 1 second (in nanoseconds) divided by FPS. Interval of game loop
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
		
		
		// Game Loop
		while (gameThread != null) {
			
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			
			if (delta >= 1) {
				// 1. Update: update information such as character position
				update();
				
				// 2. Draw: draw the screen with the updated information
				repaint();
				delta--;
				drawCount++;
				
			}
			
			if (timer >= 1000000000) {
				System.out.println("FPS: " + drawCount);
				drawCount = 0;
				timer = 0;
			}

		}
		
	}
	
	// Update in game loop
	public void update() {
		player.update();
	}
	
	// Draw in game loop
	public void paintComponent(Graphics g) {
		// Required when using paintComponent method
		super.paintComponent(g);
		
		// Change Graphics g to Graphics2D class for more functionality
		Graphics2D g2 = (Graphics2D)g;
		updateAnimationTick();
		player.draw(g2);
		
		// Dispose of this graphics context and release any system resources that it is using
		g2.dispose();
		
	}
	
}
