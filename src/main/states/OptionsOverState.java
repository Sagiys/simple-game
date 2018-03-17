package main.states;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import main.Handler;
import main.gfx.Assets;
import main.ui.ClickListener;
import main.ui.UIComboBox;
import main.ui.UIImageButton;
import main.ui.UIManager;
import main.utils.Utils;

public class OptionsOverState extends OverState{

	//dimenions of overstate
	private float x, y;
	private int width, height;
	
	private Font font;
	private int fontSize;
	
	private UIManager uiManager;
	public boolean init = false; // variable that tells if this overstate is opened or closed
	
	public OptionsOverState(Handler handler, float x, float y, int width, int height) 
	{
		super(handler);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		uiManager = new UIManager(handler);
		fontSize = (int)(handler.getWidth() * 0.0109375);
		font = Assets.menuFont.deriveFont(Font.PLAIN, fontSize);

	}

	//updates overState
	@Override
	public void update() 
	{
		//if users opened that state
		//initialize all of ui
		if(!init) 
		{
			initUI();
			init = true; //tells that its open
		}
		//updates ui
		uiManager.update();
	}

	@Override
	public void render(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		//setting small transparency
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
		
		g.drawImage(Assets.overStateBackground, (int)x, (int)y, width, height, null);
		//setting off transperency just for this object
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
		//renders ui
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString("Resolution: ",
				(int) (handler.getWidth() * 0.34375),		//x
				(int) (handler.getHeight() * 0.25694));		//y
		uiManager.render(g);
	}

	private void initUI()
	{
		//setting up uimanager
		handler.getMouseManager().setUIManager(uiManager);
		
		//checking if buttons are already created
		if(uiManager.getObjectByID(5) == null)
		{
			//creating x button
			uiManager.addObject(new UIImageButton(	(int) (handler.getWidth() * 0.8783),	//x
													(int) (handler.getHeight() * 0.1667),   //y
													(int) (width * 0.04),	//w
													(int) (height * 0.08), //h equal cuz its square
					5,
					Assets.denyButton, new ClickListener(){
				public void onClick() 
				{
					init = false; //telling program that overstate was closed 
					handler.getMouseManager().setUIManager(null); //setting down uimanager
					OverState.setOverState(null); //disabling overstate
				}}));
			//creating combobox
			uiManager.addObject(new UIComboBox((int)(handler.getWidth() * 0.41406),		//x
												(int)(handler.getHeight() * 0.23611),	//y
												(int)(handler.getWidth() * 0.109375),	//width
												(int)(handler.getHeight() * 0.13888),	//height
												10, Assets.options));
		}
		
	}
	
}
