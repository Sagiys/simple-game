package main.states;

import java.awt.Graphics;

import main.Handler;
import main.entity.creature.Player;
import main.ui.Gui;
import main.worlds.World;

public class GameState extends State{
	
	private World world;
	
	public GameState(Handler handler)
	{
		super(handler);
		world = new World(handler, "/worlds/world1.map"); //loading map file
		handler.setWorld(world); //setting world
	}
	
	//updating game state
	public void update() 
	{
		if(handler.getKeyManager().pause) setState(handler.getGame().menuState);
		world.update();
	}

	//rednering game state
	public void render(Graphics g) 
	{
		world.render(g);
	}
	
}
