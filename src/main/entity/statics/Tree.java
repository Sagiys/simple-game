package main.entity.statics;

import java.awt.Graphics;

import main.Handler;
import main.gfx.Assets;
import main.items.Item;
import main.tiles.Tile;

public class Tree extends StaticEntity{
	
	public Tree(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH * 2, Tile.TILEHEIGHT * 3);
		
		// collision bounds
		bounds.x = width / 2 - 10;
		bounds.y = (int) (height / 1.5F) - 10;
		bounds.width = width / 7;
		bounds.height = (int) (height - height / 1.5F + 10);
		
		health = 20;
	}
	
	public void die()
	{
		handler.getWorld().getItemManager().addItem(Item.woodItem.createNew((int)x + width / 2, (int)y + height / 2));
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.tree, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		//debbuging code
		g.setColor(java.awt.Color.red);
		g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
				(int) (y + bounds.y - handler.getGameCamera().getyOffset()),
				bounds.width, bounds.height);
	}

}
