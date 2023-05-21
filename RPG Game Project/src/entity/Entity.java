package entity;

import java.awt.image.BufferedImage;

public class Entity {

	public int x, y;
	public int speed;
	
	public BufferedImage img; 
	public BufferedImage[][] animations;
	public String direction = "down";
	public int aniTick, aniIndex, aniSpeed = 10;
	
}
