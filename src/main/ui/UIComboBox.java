// eL IMPORTANTO
// I know its not universal combobox but i wont use it anywhere else so whatever :)
package main.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import main.gfx.Assets;
import main.utils.PropertiesManager;
import main.utils.Utils;

public class UIComboBox extends UIObject{
	
	private PropertiesManager props = new PropertiesManager();			// instance of propertiesmanager
	private StringBuilder stringBuilder = new StringBuilder();			// instance of stringbuilder
	private Font font;	// creating font
	private int fontSize;
	
	private int located = -1;	// if so mouse is not on that combobox
	private int sheight;
	private int amount;			// amount of options in list
	private boolean open = false; //is combobox opened;
	private boolean clicked = false;
	private String selected, selectedWidth, selectedHeight;		//string displayed, divided on 2 parts that goes to propertymanager
	
	private String[] options;
	
	public UIComboBox(float x, float y, int width, int height, int ID, String[] options)
	{
		super(x, y, width, height, ID);
		this.options = options;
		amount = options.length;
		props.openProperties();
		//creating dimenions to display
		stringBuilder.append(props.getProperty("width"));
		stringBuilder.append("x");
		stringBuilder.append(props.getProperty("height"));
	    selected = stringBuilder.toString();
//	    sheight = (int) (Utils.parseInt(props.getProperty("width")) * 0.0196);
	    sheight = height/(amount + 1);
	    fontSize = (int)(Utils.parseInt(props.getProperty("width")) * 0.0109375);
	    font = Assets.menuFont.deriveFont(Font.PLAIN, fontSize); 
	}

	@Override
	public void update() 
	{
		if(located == -1) open = false; //checking if combobox is opened or not
		if(open) clicked = true; // making update optionso nly once after finishing fun with combobox
	}

	@Override
	public void render(Graphics g) 
	{
		FontMetrics metrics = g.getFontMetrics(font);
		int th = metrics.getHeight();
		
		g.setFont(font);
		g.setColor(Color.BLACK);
		
		g.drawImage(Assets.comboBoxActivatingButton[0], (int) x, (int) y, width, sheight, null);						//drawing general button not hovered
		if(located == -2) g.drawImage(Assets.comboBoxActivatingButton[1], (int) x, (int) y, width, sheight, null);		//drawing general button hovered
		g.drawString(selected, (int)x+ width/10, (int)y + (th/3) * 2); 	//drawing selected resolution
		//ifcombobox is open renders list
		if(open)
		{
			//renders empty list 
			for(int i = 0; i < amount; i++)
			{
				g.drawImage(Assets.comboBoxList[0], (int) x, (int) y + sheight + (sheight * i), width, sheight, null);
			}
			//renders selected option
			if(located >= 0)
			{
				g.drawImage(Assets.comboBoxList[1], (int) x, (int) y + sheight + (sheight * located), width, sheight, null);
			}
			//renders strings of options
			for(int i = 0; i < amount; i++)
			{
				g.drawString(options[i],(int) x + width/10, (int) (y + sheight + (sheight * i)) + (th/3) * 2);
			}
		}
	}
	
	@Override
	public void onMouseMove(MouseEvent e)
	{
		//checking if mouse is on combobox
		if(bounds.contains(e.getX(), e.getY()))
			{
				hovering = true;
				//if mouse is on general button
				if(e.getY() > bounds.y && e.getY() < bounds.y + sheight)
				{
					located = -2;
				}else
				{
					located = -1;
				}
				//checking which object on list is selected
				for(int i = 0; i < amount; i++)
				{
					if(e.getY() >= bounds.y  + sheight + (sheight * i) && e.getY() < bounds.y + (sheight * 2) + (sheight * i)&& open == true) located = i;
				}
				//checking if mouse is outside combobox
				if(e.getX() < bounds.x && e.getX() > bounds.x + bounds.width && e.getY() < bounds.y && e.getY() > bounds.y + bounds.height) located = -1;
			}
		//mouse is not on combobox
		else
		{
			//mouse was on combobox but left
			if(clicked)
				{
					//saving options
					getOptions();
					props.setProperty("height", selectedHeight);
					props.setProperty("width", selectedWidth);
					clicked = false;
				}
			hovering = false;
			located = -1;
		}
	}
	
	@Override
	public void onClick() {
		//that means that general button was pressed
		if(located == -2)
			{
				open = !open;
		}
		//getting action from list object
		for(int i = 0; i < amount; i++)
		{
			if(located == i) 
			{
				selected = options[i];
			}

		}
	}
	
	//spliting 2 properties from 1 displayed string
	private void getOptions()
	{
		String[] tokens = selected.split("x");
		selectedWidth = tokens[0];
		selectedHeight = tokens[1];
	}
}
