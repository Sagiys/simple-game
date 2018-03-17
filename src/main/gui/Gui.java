package main.gui;

import java.awt.Color;
import java.awt.Graphics;

import main.entity.creature.Player;
import main.gfx.Assets;

public class Gui {
	
	private Player player;
	private int height;
	private float percent;
	
	public Gui(Player player)
	{
		this.player = player;
	}
	
	public void update()
	{
		percent = (float) ((player.getHealth() * 1.00) / player.getMaxHP());
		height = (int) (106 * percent);
	}
	
	public void render(Graphics g)
	{
		g.setColor(Color.red);
		g.drawImage(Assets.rect, 507, 713, 106, -height, null);
		g.drawImage(Assets.gui, 500, 570, null);
	}
	
}
