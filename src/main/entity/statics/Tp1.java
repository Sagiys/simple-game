package main.entity.statics;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import main.Handler;
import main.gfx.Assets;
import main.items.Item;

public class Tp1 extends StaticEntity{
	
	private boolean isClose = false;
	
	public Tp1(Handler handler, float x, float y) {
		super(handler, x, y, 50, 50);
		
		bounds.x = 0;
		bounds.y = 0;
		bounds.width = 50;
		bounds.height = 50;
		
		health = 20000;
	}
	
	public void die(){}

	@Override
	public void update() {
		int a = (int) Math.pow((handler.getWorld().getEntityManager().getPlayer().getX() +
				handler.getWorld().getEntityManager().getPlayer().getWidth() / 2) - (x + 160 / 2), 2);
		int b = (int) Math.pow(((handler.getWorld().getEntityManager().getPlayer().getY() +
						handler.getWorld().getEntityManager().getPlayer().getHeight() / 2) - (y + 80 / 2) ), 2);
		if(Math.sqrt(a + b) < 100) 
		{
			isClose = true;
		}else
		isClose = false;
		if(isClose && handler.getKeyManager().keyJustPressed(KeyEvent.VK_F)) 
			{
				handler.getGameCamera().checkin = !handler.getGameCamera().checkin;
				handler.getWorld().getEntityManager().getPlayer().setX(10370); 
				handler.getWorld().getEntityManager().getPlayer().setY(670); 
				handler.getGameCamera().setxOffset(9750);
				handler.getGameCamera().setyOffset(150);
			}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(java.awt.Color.black);
		g.fillRect((int) (x - handler.getGameCamera().getxOffset()),
				(int) (y - handler.getGameCamera().getyOffset()),
				bounds.width, bounds.height);
		if(isClose)g.drawImage(Assets.interract,(int) (x + 50 - handler.getGameCamera().getxOffset()), (int)(y - 20 - handler.getGameCamera().getyOffset()), 100, 66, null);
	}

}
