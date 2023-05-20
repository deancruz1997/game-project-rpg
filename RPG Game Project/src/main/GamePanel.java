package main;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {

	// Screen Settings
	final int originalTileSize = 16; // 16 x 16 tile size
	final int scale = 3;
	
	final int tileSize = originalTileSize * scale; // 48 x 48 tile size
	
	// Maximum tiles on screen = 16 x 12 tiles (4:3 ratio)
	final int maxScreenCol = 16;
	final int maxScreenRow = 12;
	final int screenWidth = tileSize * maxScreenCol; // 768 pixels
	final int screenHeight = tileSize * maxScreenRow; // 576 pixels
	
	Thread gameThread;
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true); // Enabling this to true can improve game's rendering performance
		
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
		
	}

	@Override
	public void run() {
		
		
	}
	
}
