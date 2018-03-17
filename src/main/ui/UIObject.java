package main.ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

public abstract class UIObject {

	protected float x, y;
	protected int width, height;
	protected Rectangle bounds;
	protected boolean hovering = false;
	protected boolean focused = false;
	protected int ID;
	protected UIManager uiManager;
	
	public UIObject(float x, float y, int width, int height, int ID)
	{
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		this.ID = ID;
		bounds = new Rectangle ((int) x, (int) y, width, height);
	}

	public abstract void update();
	public abstract void render(Graphics g);
	public abstract void onClick();
	
	//checking if mouse is on button
	public void onMouseMove(MouseEvent e)
	{
		if(bounds.contains(e.getX(), e.getY()))
			{
				hovering = true;
			}
		else
		{
			hovering = false;
		}
	}
	
	public void onMouseRelease(MouseEvent e)
	{
		if(hovering && e.getButton() == e.BUTTON1) onClick();
	}
	
	//getters and setter
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

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

	public boolean isHovering() {
		return hovering;
	}

	public void setHovering(boolean hovering) {
		this.hovering = hovering;
	}
	
	public int getID()
	{
		return ID;
	}
}
