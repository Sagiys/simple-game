package main.states;

import java.awt.Graphics;

import main.Handler;

public abstract class OverState {

	private static OverState currentOverState = null;
	protected Handler handler;
	
	public OverState(Handler handler)
	{
		this.handler = handler;
	}
	
	public static void setOverState(OverState overState)
	{
		currentOverState = overState;
	}
	
	public static OverState getOverState()
	{
		return currentOverState;
	}
	
	public abstract void update();
	public abstract void render(Graphics g);
	
}
