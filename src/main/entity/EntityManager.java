package main.entity;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import main.Handler;
import main.entity.creature.Player;
import main.entity.statics.Tree;

public class EntityManager {

	private Handler handler;
	private Player player;
	private ArrayList<Entity> entities;
	// rendering order
	// calculate down y axis bounds to define which entity should be rendered 1st
	private Comparator<Entity> renderSorter = new Comparator<Entity>()
			{
				@Override
				public int compare(Entity a, Entity b) {
					if(a.getY() + a.getHeight() < b.getY() + b.getHeight()) return -1;
					return 1;
				}
				
			};
	
	public EntityManager(Handler handler, Player player)
	{
		this.handler = handler;
		this.player = player;
		entities = new ArrayList<Entity>();
		addEntity(player);
	}
	//updates all entities
	public void update()
	{
		Iterator<Entity> it = entities.iterator();
		while(it.hasNext())
		{
			Entity e = it.next();
			e.update();
			if(!e.isActive()) it.remove();
		}
		entities.sort(renderSorter);
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_K))
		{
			handler.getWorld().getEntityManager().addEntity(new Tree(handler, handler.getMouseManager().getMouseX() + handler.getGameCamera().getxOffset(), handler.getMouseManager().getMouseY() + handler.getGameCamera().getyOffset()));
		}
	}
	
	//renders all entities
	public void render(Graphics g)
	{
		for(Entity e : entities)
		{
			e.render(g);
		}
		player.postRender(g);
	}
	
	public void addEntity(Entity e)
	{
		entities.add(e);
	}

	// gettera and setters
	
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}
	
}
