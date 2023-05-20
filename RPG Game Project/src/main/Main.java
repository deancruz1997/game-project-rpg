package main;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {

		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		
		window.setTitle("Heavensbound");
		
		// Add GamePanel class to our "frame"
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		
		// Causes window to be sized to fit the preferred size and layout of its subcomponents (GamePanel)
		window.pack();
		gamePanel.startGameThread();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
				
	}

}
