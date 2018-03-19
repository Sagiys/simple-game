package main.ui;

import java.awt.Color;
import java.awt.Graphics;

import main.entity.creature.Player;
import main.gfx.Assets;
import main.gfx.Text;

public class Gui {
	
	private Player player;
	private int height, width;
	private float percent, percent2;
	
	public Gui(Player player)
	{
		this.player = player;
	}
	
	public void update()
	{
		percent = (float) ((player.getHealth() * 1.00) / player.getMaxHP());
		height = (int) (106 * percent);
		percent2 = (float) ((player.getCurrentXP() * 1.00) / player.getMaxXP());
		width = (int) (1068 * percent2);
	}
	
	public void render(Graphics g)
	{
		g.drawImage(Assets.rect, 507+660, 713, 106, -height, null);
		g.drawImage(Assets.gui, 500+660, 570, null);
		g.setColor(Color.CYAN);
		g.fillRect(0, 690, 1160, 30);
		g.fillRect(0, 660, 120, 30);
		Text.drawString(g, "LVL: " + player.getLvl(), 10, 715, false, Color.BLACK, Assets.guiFont);
		g.setColor(Color.red);
		g.fillRect(80, 695, 1070, 20);
		g.setColor(Color.YELLOW);
		g.fillRect(81, 696, width, 18);
		Text.drawString(g, player.getCurrentXP() + "/" + player.getMaxXP(), 620, 705, true, Color.BLACK, Assets.guiFont);
		Text.drawString(g, "DMG: " + player.getDmg(), 10, 685, false, Color.BLACK, Assets.guiFont);
	}
	
}
