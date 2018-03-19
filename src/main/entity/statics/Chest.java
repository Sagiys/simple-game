package main.entity.statics;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import main.Handler;
import main.gfx.Assets;
import main.items.Item;

public class Chest extends StaticEntity{
	
	private boolean isClose = false;
	
	public Chest(Handler handler, float x, float y) {
		super(handler, x, y, 160, 80);
		
		// collision bounds
		bounds.x = 25;
		bounds.y = 18;
		bounds.width = 97;
		bounds.height = 62;
		
		health = 11110;
	}
	
	public void die()
	{
		handler.getWorld().getItemManager().addItem(Item.swordItem.createNew((int)x + width / 2, (int)y + height / 2));
		handler.getWorld().getEntityManager().getPlayer().addXP(3000);
	}

	@Override
	public void update() {
		int a = (int) Math.pow((handler.getWorld().getEntityManager().getPlayer().getX() +
				handler.getWorld().getEntityManager().getPlayer().getWidth() / 2) - (x + 160 / 2), 2);
		int b = (int) Math.pow(((handler.getWorld().getEntityManager().getPlayer().getY() +
						handler.getWorld().getEntityManager().getPlayer().getHeight() / 2) - (y + 80 / 2) ), 2);
		if(Math.sqrt(a + b) < 200) 
		{
			isClose = true;
		}else
		isClose = false;
		if(isClose && handler.getKeyManager().keyJustPressed(KeyEvent.VK_F)) 
			{
				active = false;
				die();
			}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.chest, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		if(isClose)g.drawImage(Assets.interract,(int) (x + 50 - handler.getGameCamera().getxOffset()), (int)(y - 20 - handler.getGameCamera().getyOffset()), 100, 66, null);
	}

}
