package main.gfx;

import java.awt.image.BufferedImage;

public class SpriteSheet {

	private BufferedImage sheet;
	
	public SpriteSheet(BufferedImage sheet)
	{
		this.sheet = sheet;
	}
	
	//parting SpriteSheet by getting subimages
	public BufferedImage part(int x, int y, int width, int height)
	{
		return sheet.getSubimage(x, y, width, height);
	}
	
}
