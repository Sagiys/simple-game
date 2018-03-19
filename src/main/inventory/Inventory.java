package main.inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import main.Handler;
import main.gfx.Assets;
import main.gfx.Text;
import main.items.Item;

public class Inventory {

	private Handler handler;
	private boolean active = false;
	private ArrayList<Item> invItems;
	private int invX;
	private int invY;
	private int invListCenterX;
	private int invListCenterY;
	private int invListSpacing = 34;
	
	private int selectedItem = 0;
	private boolean x = true;
	
	public Inventory(Handler handler)
	{
		this.handler = handler;
		invItems = new ArrayList<Item>();
		invX = handler.getWidth() / 2 - Assets.inventory.getWidth() / 2;
		invY = handler.getHeight() / 2 - Assets.inventory.getHeight() / 2;
		invListCenterX = invX + Assets.inventory.getWidth() / 2;
		invListCenterY = invY + Assets.inventory.getHeight() / 2;
		
	}
	
	public void update()
	{
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_E))
			active = !active;
		if(!active) return;
		
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_W))
			selectedItem--;
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_S))
			selectedItem++;
		if(selectedItem < 0)
			selectedItem = invItems.size() - 1;
		else if(selectedItem == invItems.size())
			selectedItem = 0;
		for(int i = 0; i < invItems.size(); i++)
		{
			if(invItems.get(i).getId() == 3)
			{
			
				if(x)handler.getWorld().getEntityManager().getPlayer().dmgUp();
				x = false;
			}
		}
		
	}
	
	public void render(Graphics g)
	{
		if(!active) return;
		g.drawImage(Assets.inventory, invX, invY, null);
		
		int len = invItems.size();
		if(len == 0) return;
		
		for(int i = -5; i < 6; i++)
		{
			if(selectedItem + i < 0 || selectedItem + i >= len) continue;
			if(i == 0)
			{
				Text.drawString(g, "> " + invItems.get(selectedItem + i).getName() + ": " + invItems.get(selectedItem + i).getCount() + " <", invListCenterX, invListCenterY + i * invListSpacing, true, Color.YELLOW, Assets.invFont);
			}else
			Text.drawString(g, invItems.get(selectedItem + i).getName() + ": " + invItems.get(selectedItem + i).getCount(), invListCenterX, invListCenterY + i * invListSpacing, true, Color.WHITE, Assets.invFont);
		}
	}
	
	public void addItem(Item item)
	{
		for(Item i : invItems)
		{
			if(i.getId() == item.getId())
			{
				i.setCount(i.getCount() + item.getCount());
				return;
			}
		}
		invItems.add(item);
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
