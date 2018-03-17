package main.worlds;

import java.awt.Graphics;

import main.Handler;
import main.entity.EntityManager;
import main.entity.creature.Player;
import main.entity.statics.Tree;
import main.items.ItemManager;
import main.tiles.Tile;
import main.utils.Utils;

public class World {

	private EntityManager entityManager;
	private ItemManager itemManager;
	
	private Handler handler;
	private int width, height;
	private int spawnX, spawnY;
	private int[][] tiles;
	
	public World(Handler handler, String path)
	{
		this.handler = handler;
		itemManager = new ItemManager(handler);
		entityManager = new EntityManager(handler, new Player(handler, 100, 100)); // 0
		entityManager.addEntity(new Tree(handler, 100, 300)); // 1
		
		loadWorld(path);
		
//		entityManager.getPlayer().setX(spawnX);
//		entityManager.getPlayer().setY(spawnY);
	}
	

	public void update()
	{
		itemManager.update();
		entityManager.update();
	}
	
	//renders whole world
	public void render(Graphics g)
	{
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
		int xEnd = (int) Math.min(width, (int) (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1.55);
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
		int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);
		
		for(int y = yStart; y < yEnd; y++)
		{
			for(int x = xStart; x < xEnd; x++)
			{
				getTile(x, y).render(g, (int) ((x * Tile.TILEWIDTH) - handler.getGameCamera().getxOffset()),
										(int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()) );
			}
		}
		itemManager.render(g);
		entityManager.render(g);
		
	}
	
	//returns tile from specific coordinates from 2d array - tiles
	public Tile getTile(int x, int y)
	{
		if(x < 0 || y < 0 || x >= width || y >= height) return Tile.grassTile; //every tile thats should be not renderer but somehow it is, renders as grassTile
		
		Tile t = Tile.tiles[tiles[x][y]];
		if(t == null) return Tile.dirtTile;
		return t;
	}
	
	//loads world from .map file (simple text file)
	private void loadWorld(String path)
	{
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+"); //splits txt file every white character
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		
		tiles = new int[width][height];
		for(int y = 0; y < height; y++)
		{
			for(int x = 0; x < width; x++)
			{
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
			}
		}
	}
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}


	public ItemManager getItemManager() {
		return itemManager;
	}


	public void setItemManager(ItemManager itemManager) {
		this.itemManager = itemManager;
	}


	public Handler getHandler() {
		return handler;
	}


	public void setHandler(Handler handler) {
		this.handler = handler;
	}
	
}
