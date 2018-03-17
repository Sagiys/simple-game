package main.entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import main.Handler;

public abstract class Entity {

	protected float x, y; //position for entity
	protected int width, height; //dimension for entity
	protected Handler handler; // handler object
	protected Rectangle bounds; //renctangle that defines collision bounds	
	protected boolean active = true;
	protected int health;
	
	public Entity(Handler handler, float x, float y, int width, int height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.handler = handler;
		health = 10;
		
		bounds = new Rectangle(0, 0, width, height); //default collision bounds that covers whole entity
	}
	
	public abstract void update(); //updating entity
	
	public abstract void render(Graphics g); //rendering entity
	
	public abstract void die();
	
	public boolean checkEntityCollisions(float xOffset, float yOffset)
	{
		for(Entity e : handler.getWorld().getEntityManager().getEntities())
		{
			if(e.equals(this))
				continue;
			if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset)))
			return true;
		}
		return false;
	}
	
	public void hurt(int amt)
	{
		health -= amt;
		if(health <= 0)
		{
			active = false;
			die();
		}
	}
	
	public Rectangle getCollisionBounds(float xOffset, float yOffset)
	{
		return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
	}

	//getting x pos from entity
	public float getX() {
		return x;
	}

	//setting x pos for entity
	public void setX(float x) {
		this.x = x;
	}

	//getting y pos from entity
	public float getY() {
		return y;
	}

	//setting x pos for entity
	public void setY(float y) {
		this.y = y;
	}

	//next 4 methods
	//returns and sets dimension for entity
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	
}
