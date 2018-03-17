//class that helps organizing whole game
//all of stuff here will be simple getters
//for easier possible future access to important variables

package main;

import main.display.Display;
import main.entity.EntityManager;
import main.gfx.GameCamera;
import main.input.KeyManager;
import main.input.MouseManager;
import main.utils.PropertiesManager;
import main.worlds.World;

public class Handler {

	private Game game; //instance for game
	private World world; //instance for world
	private Display display;
	private EntityManager entityManager;
	
	public Handler(Game game)
	{
		this.game = game;
	}
	
	//returns width of window
	public int getWidth()
	{
		return game.getWidth();
	}
	
	//returns height of window
	public int getHeight()
	{
		return game.getHeight();
	}
	
	//method that gives acces to private KeyManager object
	public KeyManager getKeyManager()
	{
		return game.getKeyManager();
	}
	
	public MouseManager getMouseManager()
	{
		return game.getMouseManager();
	}
	
	// returns properties manager
	public PropertiesManager getPropsManager()
	{
		return game.propsManager;
	}
	
	//method that gives acces to private Camera object
	public GameCamera getGameCamera()
	{
		return game.getGameCamera();
	}
	
	public EntityManager getEntityManager()
	{
		return entityManager;
	}

	public Game getGame() {
		return game;
	}
	
	public Display getDisplay()
	{
		return display;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	//returns actual world
	public World getWorld() {
		return world;
	}

	//set actual world
	public void setWorld(World world) {
		this.world = world;
	}
	
}
