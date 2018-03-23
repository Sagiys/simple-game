package main.entity.statics;

import java.awt.Graphics;

import main.Handler;
import main.tiles.Tile;

public class Barrier extends StaticEntity{
	
	public Barrier(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		
		// collision bounds
		bounds.x = (int)x;
		bounds.y = (int)y;
		bounds.width = width;
		bounds.height = height;
		
		health = 99999;
	}
	
	public void die(){}

	@Override
	public void update() {}

	@Override
	public void render(Graphics g) {
		g.setColor(java.awt.Color.red);
		g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
				(int) (y + bounds.y - handler.getGameCamera().getyOffset()),
				bounds.width, bounds.height);
	}

}
