package main.entity.creature;

import java.awt.Graphics;
import java.awt.Rectangle;

import main.Handler;

public class Enemy extends Creature{

	private Rectangle n;
	private Rectangle s;
	private Rectangle w;
	private Rectangle e;
	private Rectangle nw;
	private Rectangle ne;
	private Rectangle se;
	private Rectangle sw;
		
	public Enemy(Handler handler, float x, float y) {
		super(handler, x, y, 64, 64);
		
		bounds.x = (int)x;
		bounds.y = (int)y;
		bounds.width = 64;
		bounds.height = 64;
		
		speed = 3.0f;
		health = 50;

		n = new Rectangle((int) ((x + bounds.x + bounds.width / 2 - 15) - handler.getGameCamera().getxOffset()), (int) (y + bounds.y - 30 - handler.getGameCamera().getyOffset()), 30, 30);
		
	}

	@Override
	public void update() {
//		System.out.println(n.x);
//		System.out.println(n.y);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(java.awt.Color.pink);
		g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
				(int) (y + bounds.y - handler.getGameCamera().getyOffset()),
				bounds.width, bounds.height);
		g.setColor(java.awt.Color.blue);
//		g.fillRect((int) ((x + bounds.x + bounds.width / 2 - 15) - handler.getGameCamera().getxOffset()), (int) (y + bounds.y - 30 - handler.getGameCamera().getyOffset()), 30, 30);
		g.fillRect(n.x, n.y, n.width, n.height);

	}

	@Override
	public void die() {
		
	}

}
