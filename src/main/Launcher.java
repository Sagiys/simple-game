package main;

import main.utils.PropertiesManager;
import main.utils.Utils;

public class Launcher {
	
	// instance of propmanager for launcher to get dimensions of window
	private static PropertiesManager propsManager = new PropertiesManager();
	//main method
	public static void main(String[] args)
	{	
		// opens properties file
		propsManager.openProperties();
		
		Game game = new Game("Rogue-like", Utils.parseInt(propsManager.getProperty("width")), Utils.parseInt(propsManager.getProperty("height"))); //game object that starts whole game
		game.start(); // starts main thread
	}
}
