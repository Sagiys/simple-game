package main.states;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import main.Handler;
import main.gfx.Animation;
import main.gfx.Assets;
import main.ui.ClickListener;
import main.ui.UIButton;
import main.ui.UIManager;

public class MenuState extends State{

	private UIManager uiManager;
	
	//guardian stuff
	private float x = (float)(handler.getWidth() * 0.2938); // x pos of guard
	private float y = (float)(handler.getHeight() * 0.6083); // y pos of guard
	private float xSpeed; // speed of guard
	private boolean goingLeft = false; // left or right
	private Animation movingRight;
	private Animation movingLeft;
	private float xLight; //x pos for center of light
	private float yLight; //ypos for center of light
	private int guardianHeight = (int) (handler.getHeight() * 0.1333);
	private int guardianWidth = (int) (handler.getWidth() * 0.05);
	
	//positions and heights
	private float xPositionMenu = (float) (handler.getWidth() * 0.047);
	private float yPositionMenu = (float) (handler.getHeight() * 0.34);
	private float yPositionMenuHelper = (float) (handler.getHeight() * 0.07);
	private int xPositionLogo = (int) (handler.getWidth() * 0.0547);
	private int yPositionLogo = (int) (handler.getHeight() * 0.0972);
	private int widthLogo = (int) (handler.getWidth() * 0.1398);
	private int heightLogo = (int) (handler.getHeight() * 0.1611);
	private int fontSize = (int) (handler.getWidth() * 0.02);
	private int widthButton = (int) (handler.getWidth() * 0.156);
	private int heightButton = (int) (handler.getHeight() * 0.0555);
	private int leftGuardianLimit = (int) (handler.getWidth() * 0.0469);
	private int rightGuardianLimit = (int) (handler.getWidth() * 0.8984);
	
	//lighting stuff
	private float[] dist = {0.0f, 1.0f};
	private Color[] colors = {new Color(0.0f, 0.0f, 0.0f, 0.0f), Color.BLACK}; //going from white transparent to black non transparent
	private Point2D center; //center of light
	private float lightRadius = (float) (handler.getWidth() * 0.3125);
	private RadialGradientPaint p;
	
	public OverState optionsOverState;
	
	public MenuState(Handler handler)
	{
		super(handler);
		uiManager = new UIManager(handler);
	
		optionsOverState = new OptionsOverState(handler,
				(int)(handler.getWidth() * 0.2969),	//x
				(int)(handler.getHeight() * 0.1389),	//y
				(int)(handler.getWidth() * 0.625),	//width
				(int)(handler.getHeight() * 0.5556));	//height
		
		
		//initializing ui
		uiInit();
		
		//initaliziing animations for guardian
		movingRight = new Animation(170, Assets.guard_right);
		movingLeft = new Animation(170, Assets.guard_left);
		
		if(handler.getWidth() == 1280) xSpeed = 1.5f;
		else if(handler.getWidth() == 1920) xSpeed = 2.25f;
		else if(handler.getWidth() == 640) xSpeed = 0.75f;
		else if(handler.getWidth() == 1336) xSpeed = 1.6f;
		else xSpeed = 1.5f;
		
	}
	
	//updates menu state
	@Override
	public void update() 
	{	
		//setting up ui manager for menustate
		if(handler.getMouseManager().getUIManager() == null) handler.getMouseManager().setUIManager(uiManager);
		
		//ticking guardian's animations
		movingRight.tick();
		movingLeft.tick();
		
		uiManager.update();
		//calculating position for guardian
		moveGuard();
		
		if(!goingLeft) xLight = x + (int)(guardianWidth * 0.75);  //calculating x pos for light while walking right
		if(goingLeft) xLight = x + (int)(guardianWidth * 0.25); //calculating x pos for light while walking left
		yLight = y + (int)(guardianHeight * 0.646); // calculating y pos for light, same for both sides;
		if(OverState.getOverState() != null) OverState.getOverState().update(); //updates overstate if exists
		
	}

	//renders menu state
	@Override
	public void render(Graphics g) 
	{
		g.drawImage(Assets.menuBackground, 0, 0, handler.getWidth(), handler.getHeight(), null); //drawing background of menu
		g.drawImage(getCurrentAnimation(), (int) x, (int) y, guardianWidth, guardianHeight, null); //drawing guardian
		
		//drawing lighting
		drawLighting(g, 0.80f);
		
		uiManager.render(g);
		g.drawImage(Assets.logo, xPositionLogo, yPositionLogo, widthLogo, heightLogo, null);
		if(OverState.getOverState() != null) OverState.getOverState().render(g);
	}

	public void moveGuard()
	{
		//calculating x pos
		x += xSpeed;
		//turning left
		if(x > rightGuardianLimit)
		{
			xSpeed *= -1;
			goingLeft = true;
		}
		//turning right
		else if(x < leftGuardianLimit)
		{
			xSpeed *= -1;
			goingLeft = false;
		}
	}
	
	
	//drawing lighting method
	private void drawLighting(Graphics g, float value)
	{
		Graphics2D g2d = (Graphics2D) g;
		center  = new Point2D.Float(xLight, yLight);
		p = new RadialGradientPaint(center, lightRadius, dist, colors);
		g2d.setPaint(p);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, value));
		g2d.fillRect(0, 0, handler.getWidth(), handler.getWidth());
	}
	
	//setting ui method
	private void uiInit()
	{
		uiManager.addObject(new UIButton("New Game", xPositionMenu , yPositionMenu, widthButton, heightButton, 1,  true, Color.WHITE, Assets.menuFont.deriveFont(Font.PLAIN, fontSize ), new ClickListener(){
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				State.setState(handler.getGame().gameState);
				
			}}));
		uiManager.addObject(new UIButton("Load Game", xPositionMenu , yPositionMenu + yPositionMenuHelper, widthButton, heightButton, 2,  true, Color.WHITE, Assets.menuFont.deriveFont(Font.PLAIN, fontSize ),  new ClickListener(){
			public void onClick() {
				System.out.println(System.getenv("APPDATA"));
			}}));
		uiManager.addObject(new UIButton("Options", xPositionMenu , yPositionMenu + 2 * yPositionMenuHelper, widthButton, heightButton, 3, true, Color.WHITE, Assets.menuFont.deriveFont(Font.PLAIN, fontSize ), new ClickListener(){
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				OverState.setOverState(optionsOverState);
				System.out.println("wcisnalem sie");
			}}));
		uiManager.addObject(new UIButton("Quit", xPositionMenu , yPositionMenu + 3 * yPositionMenuHelper, widthButton, heightButton, 4,  true, Color.WHITE, Assets.menuFont.deriveFont(Font.PLAIN, fontSize ), new ClickListener(){
			public void onClick() {
				System.exit(0);
			}}));
	}
	
	//returning current animation frame for guardian
	private BufferedImage getCurrentAnimation()
	{
		if(!goingLeft) return movingRight.getCurrentFrame();
		else return movingLeft.getCurrentFrame();
	}
}
