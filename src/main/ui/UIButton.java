package main.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;

public class UIButton extends UIObject{

	private String text;
	private boolean centered;
	private ClickListener clicker;
	private Color color;
	private Font font;
	
	public UIButton(String text, float x, float y, int width, int height,int ID, boolean centered, Color color, Font font, ClickListener clicker) {
		super(x, y, width, height, ID);
		this.text = text;
		this.centered = centered;
		this.clicker = clicker;
		this.color = color;
		this.font = font;
		bounds = new Rectangle((int) x, (int) y, width, height);
		
	}

	@Override
	public void update() {
		
	}
	
	@Override
	public void render(Graphics g) 
	{
			//getting dimensions of text
			FontMetrics metrics = g.getFontMetrics(font);
			int th = metrics.getHeight();
			int tw = metrics.stringWidth(text);
			
			//if centered calculates how to draw string in middle of button
			//uses textures from above
			if(hovering) 
			{
				g.setColor(new Color(200, 200, 200, 100));
				g.fillRect((int) x, (int) y, width, height);
				g.setFont(font);
				g.setColor(color);
				if(centered)
				{
					g.drawString(text, (int) ((x + width / 2) - (tw / 2)), (int) (y + height / 2) + th / 4);
				}else
				{
					g.drawString(text, (int) x, (int) y + height);
				}
			}else
			{
				g.setFont(font);
				g.setColor(color);
				if(centered)
				{
					g.drawString(text, (int) ((x + width / 2) - (tw / 2)), (int) (y + height / 2) + th / 4);
				}else
				{
					g.drawString(text, (int) x, (int) y + height);
				}
			}
			
	}

	@Override
	public void onClick() {
		clicker.onClick();
		hovering = false;
	}

}
