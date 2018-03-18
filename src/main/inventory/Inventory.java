package main.inventory;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import main.Handler;
import main.items.Item;

public class Inventory {

	private Handler handler;
	private boolean active = false;
	private ArrayList<Item> invItems;
	
	public Inventory(Handler handler)
	{
		this.handler = handler;
		invItems = new ArrayList<Item>();
	}
	
	public void update()
	{
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_E))
			active = !active;
		if(!active) return;
		
		System.out.println("INVENTORY: ");
		for(Item i : invItems)
		{
			System.out.println(i.getName() + "  " + i.getCount() + "  ID:  " + i.getId());
		}
	}
	
	public void render(Graphics g)
	{
		if(!active) return;
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
}
