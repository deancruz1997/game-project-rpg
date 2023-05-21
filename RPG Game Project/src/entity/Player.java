package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

	GamePanel gp;
	KeyHandler keyH;
	public int directionValue = 0;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		
		setDefaultValues();
	}
	
	public void setDefaultValues() {
		x = 100;
		y = 100;
		speed = 4;
		
	}
	
	public void updateAnimationTick() {
		aniTick++;
		
		if (aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			
			if (aniIndex >= 8)
				aniIndex = 0;
		}
			
	}
	
	public void getPlayerImage() {
		try {
			img = ImageIO.read(getClass().getResourceAsStream("/player/playerMoving.png"));
			
		} catch(IOException e) {
			e.printStackTrace();
		
		}
	}
	
	public void update() {
		if (keyH.upPressed) {
			direction = "up";
			y -= speed;
		}
		
		else if (keyH.downPressed) {
			direction = "down";
			y += speed;
		}
		
		else if (keyH.leftPressed) {
			direction = "left";
			x -= speed;
		}
		
		else if (keyH.rightPressed) {
			direction = "right";
			x += speed;
		}
		
	}
	
	public void loadAnimations() {
		animations = new BufferedImage[4][8];
		
		for (int j = 0; j < animations.length; j++)
			for (int i = 0; i < animations[j].length; i++)
				animations[j][i] = img.getSubimage(i * 16, j * 16, 16, 16);
	}
	
	public void draw(Graphics2D g2) {
		switch(direction) {

		case "down":
			directionValue = 0;
			break;
			
		case "up":
			directionValue = 1;
			break;
		
		case "right":
			directionValue = 2;
			break;
			
		case "left":
			directionValue = 3;
			break;
		}
		
		if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed)
			g2.drawImage(animations[directionValue][aniIndex], x, y, gp.tileSize, gp.tileSize, null);
		
		else
			g2.drawImage(animations[directionValue][0], x, y, gp.tileSize, gp.tileSize, null);
		
	}
	
}
