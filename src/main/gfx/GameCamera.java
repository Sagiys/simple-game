package main.gfx;

import main.Handler;
import main.entity.Entity;
import main.tiles.Tile;

public class GameCamera {

	private float xOffset, yOffset;
	private Handler handler;
	public boolean checkin = true;
	
	public GameCamera(Handler handler,float xOffset, float yOffset)
	{
		this.handler = handler;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	//method that fixes blankSpaces on map
	public void checkBlankSpace()
	{
		if(xOffset < 0) xOffset = 0;
		if(xOffset > handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth()) xOffset = handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth();
		if(yOffset < 0) yOffset = 0;
		if(yOffset > handler.getWorld().getHeight() *Tile.TILEHEIGHT - handler.getHeight()) yOffset = handler.getWorld().getHeight() *Tile.TILEHEIGHT - handler.getHeight();
	}
	
	//centering on entity
	public void centerOnEntity(Entity e)
	{
		if(checkin) {
			xOffset = e.getX() - (handler.getWidth() / 2 + e.getWidth() / 2) + 150;
			yOffset = e.getY() - handler.getHeight() / 2 + e.getHeight() / 2;
			checkBlankSpace();
		}
	}
	
	public void move(float xAmt, float yAmt)
	{
		xOffset += xAmt;
		yOffset += yAmt;
		checkBlankSpace();
	}
	
	public float getxOffset() {
		return xOffset;
	}

	public void setxOffset(float xOffset) {
		this.xOffset = xOffset;
	}

	public float getyOffset() {
		return yOffset;
	}

	public void setyOffset(float yOffset) {
		this.yOffset = yOffset;
	}
	
}
