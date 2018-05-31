package main.entity.statics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import main.Handler;
import main.gfx.Assets;
import main.items.Item;

public class Trap extends StaticEntity{
	
	private Rectangle r;
	
	public Trap(Handler handler, float x, float y) {
		super(handler, x, y, 100, 100);
		
		bounds.x = 0;
		bounds.y = 0;
		bounds.width = 0;
		bounds.height = 0;
		
		r = new Rectangle((int)x , (int)y , 100 ,100);
		
		
		health = 20000;
	}
	
	public void die(){}

	@Override
	public void update() {
		if(handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0, 0).intersects(r))
		handler.getWorld().getEntityManager().getPlayer().hurt(1);
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.tree, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		//debbuging code
		g.setColor(java.awt.Color.red);
		g.fillRect((int) (r.x - handler.getGameCamera().getxOffset()),
				(int) (r.y - handler.getGameCamera().getyOffset()),
				r.width, r.height);
		g.setColor(Color.BLACK);
		g.setFont(Assets.guiFont);
		g.drawString("It's a trap", (int)(x - handler.getGameCamera().getxOffset()), (int)(y + 20 - handler.getGameCamera().getyOffset()));
		g.drawString("It hurts", (int)(x - handler.getGameCamera().getxOffset()), (int)(y + 50 - handler.getGameCamera().getyOffset()));

	}

}
