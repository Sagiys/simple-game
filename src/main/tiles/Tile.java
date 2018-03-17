//todo
//make dynamic list of tiles isntead of static array

package main.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.utils.PropertiesManager;
import main.utils.Utils;

public class Tile {
	
	private static PropertiesManager propsManager = new PropertiesManager();
	
	public static Tile[] tiles = new Tile[256];
	public static Tile grassTile = new GrassTile(0);
	public static Tile rockTile = new RockTile(1);
	public static Tile dirtTile = new DirtTile(2);
	
	public static int TILEWIDTH;
	public static int TILEHEIGHT;
	
	
	protected BufferedImage texture;
	protected final int id;
	
	public Tile(BufferedImage texture, int id)
	{
		this.texture = texture;
		this.id = id;
		
		propsManager.openProperties();
		TILEWIDTH = (int) (Utils.parseInt(propsManager.getProperty("width")) * 0.05);			//64
		TILEHEIGHT = (int) (Utils.parseInt(propsManager.getProperty("height")) * 0.088888);		//64
		
		tiles[id] = this;
	}
	
	//update tile for future tile animations
	public void update()
	{
		
	}
	
	//rendering tile
	public void render(Graphics g, int x, int y)
	{
		g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
	}
	
	public boolean isSolid()
	{
		return false;
	}
	
	public int getId()
	{
		return id;
	}
	
}
