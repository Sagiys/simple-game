package main.entity.creature;

import main.Handler;
import main.entity.Entity;
import main.tiles.Tile;

public abstract class Creature extends Entity{

	//default variables for creatures
	public static final int DEFAULT_HEALTH = 10;
	public static final float DEFAULT_SPEED = 3.0f;
	public static final int DEFAULT_CREATURE_WIDTH = 64;
	public static final int DEFAULT_CREATURE_HEIGHT = 64;
	
	//variables that every creature must have
	protected float speed;
	protected float xMove, yMove;
	
	public Creature(Handler handler, float x, float y, int width, int height)
	{
		super(handler, x, y, width, height);
		speed = DEFAULT_SPEED;
		xMove = 0; 
		yMove = 0;
	}

	public void move()
	{
		if(!checkEntityCollisions(xMove, 0f)) moveX();
		if(!checkEntityCollisions(0f, yMove)) moveY();
	}
	
	//moving in x axis including collision
	public void moveX()
	{
		if(xMove > 0) //moving right
		{
			int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH; //temp x value, converting x pos in float to x pos in tiles including collision bounds
			if(!collisionWithTile(tx, (int) (y+ bounds.y) / Tile.TILEHEIGHT) && !collisionWithTile(tx, (int)(y + bounds.y + bounds.height) / Tile.TILEHEIGHT))
			{
				x+= xMove;
			}else{
				x = tx * Tile.TILEWIDTH - bounds.x - bounds.width - 1;
			}
			
		}else if(xMove < 0) //moving left
		{
			int tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;
			if(!collisionWithTile(tx, (int) (y+ bounds.y) / Tile.TILEHEIGHT) && !collisionWithTile(tx, (int)(y + bounds.y + bounds.height) / Tile.TILEHEIGHT))
			{
				x+= xMove;
			}else{
				x = tx * Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x;
			}
		}
		
	}
	
	//moving in y axis including collision
	public void moveY()
	{
		if(yMove < 0)//up
		{
			int ty = (int) (y+yMove + bounds.y) / Tile.TILEHEIGHT; //temp y value, converting y pos in float to y pos in tiles including collision bounds
			
			if(!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) && !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty))
			{
				y += yMove;
			}else{
				y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y;
			}
		}else if(yMove > 0)//down
		{
			int ty = (int) (y+yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;
			
			if(!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) && !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty))
			{
				y += yMove;
			}else{
				y = ty * Tile.TILEHEIGHT - bounds.y - bounds.height - 1;
			}
		}
	}
	
	//returns that colliding tile is solid or not
	protected boolean collisionWithTile(int x, int y)
	{
		return handler.getWorld().getTile(x, y).isSolid();
	}
	
	//getters and setters
	public float getxMove() {
		return xMove;
	}

	public void setxMove(float xMove) {
		this.xMove = xMove;
	}

	public float getyMove() {
		return yMove;
	}

	public void setyMove(float yMove) {
		this.yMove = yMove;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	
	
}
