package main.entity.statics;

import java.awt.Graphics;

import main.Handler;

public class House extends StaticEntity{
	
	public House(Handler handler, float x, float y) {
		super(handler, x, y, 0, 0);
		
		// collision bounds
		bounds.x = 0;
		bounds.y = 0;
		bounds.width = 500;
		bounds.height = 200;
		
		health = 22220;
	}
	
	public void die(){}

	@Override
	public void update() {
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(java.awt.Color.red);
		g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
				(int) (y + bounds.y - handler.getGameCamera().getyOffset()),
				bounds.width, bounds.height);
	}

}
