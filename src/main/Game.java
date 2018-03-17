package main;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import main.display.Display;
import main.gfx.Assets;
import main.gfx.GameCamera;
import main.input.KeyManager;
import main.input.MouseManager;
import main.states.GameState;
import main.states.MenuState;
import main.states.State;
import main.utils.PropertiesManager;

public class Game implements Runnable{

	private Display display;
	
	public PropertiesManager propsManager = new PropertiesManager();
	
	private int width, height; //dimension for actual window
	
	public String title; //title of game
	
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs; //declaration for buffers
	private Graphics g; //declaration for graphcis object 
	
	//states
	public State gameState;
	public State menuState;

	//input
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	//camera
	private GameCamera gameCamera;
	
	//handler
	private Handler handler;
	
	//constructor for game, used by launcher
	public Game(String title, int width, int height)
	{
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
		propsManager.openProperties(); // opens props file for whole game
	}

	// initializing all stuff that must by initialized only once
	private void init()
	{
		display = new Display(title, width, height); //creating instance of Display
		display.getFrame().addKeyListener(keyManager); //adding keyManager to JFrame
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		Assets.init();	
		
		handler = new Handler(this); //instance for handler and adding to this main class
		gameCamera = new GameCamera(handler, 0, 0);	// creating camera object
		
		//states stuff
		gameState = new GameState(handler);
		menuState = new MenuState(handler);
		
//		optionsOverState = new OptionsOverState(handler, 300, 300, 600, 200);
		State.setState(menuState);
	
	}
	
	//main update method
	private void update()
	{
		keyManager.update(); //updates key manager to get fluent movement
		
		if(State.getState() != null) State.getState().update(); //updates current state
	}
		
	//main render method
	private void render()
	{
		
		//creates 3 image buffers if they re not exist 
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null)
		{
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		//passing graphics from bs to graphical object - g
		g = bs.getDrawGraphics();
		// clear screen
		g.clearRect(0, 0, width, height);
		// actually drawing
		if(State.getState() != null) State.getState().render(g); //drawing current state
		// end of drawing
		bs.show(); // makes next buffer visible
		g.dispose(); // disposes all graphics -- releasing memory
	}
	
	//main game loop
	public void run() 
	{
		//initialization stuff for display, managers, states, assets etc.
		init();
		
		//variables for game's clock
		int fps = 60;  //variable that set up game update frequency
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;

		//game loop
		while(running)
		{
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			//game runs in 60 updates per second
			if(delta >= 1)
			{
				update();
				render();
				ticks++;
				delta--;
			}
			//runs every 1 second
			//made for printing stability of game
			if(timer >= 1000000000)
			{
				System.out.println("FPS: " + ticks);
				ticks = 0;
				timer = 0;
			}
			//optimlaization code
			try{
				Thread.sleep(5);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		stop();
	
	}
	
	//getting keyManager
	public KeyManager getKeyManager()
	{
		return keyManager;
	}
	
	public MouseManager getMouseManager()
	{
		return mouseManager;
	}
	
	//getting camera
	public GameCamera getGameCamera()
	{
		return gameCamera;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	//creating main thread
	public synchronized void start()
	{
		if(running) return;
		running = true;
		
		thread = new Thread(this);
		thread.start();
	}
	
	//waits for thread's end
	//stops whole program
	public synchronized void stop()
	{
		if(!running) return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
