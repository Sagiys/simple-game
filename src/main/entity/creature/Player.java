package main.entity.creature;

import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import main.Handler;
import main.entity.Entity;
import main.entity.statics.Tree;
import main.gfx.Animation;
import main.gfx.Assets;
import main.inventory.Inventory;
import main.tiles.Tile;
import main.ui.Gui;


//jezeli tak to jakas akcja
//bedzie atak hehe
public class Player extends Creature{
	
	//declarations for movement animation
	private Animation downAnimation;
	private Animation leftAnimation;
	private Animation rightAnimation;
	private Animation upAnimation;
	private Animation idleAnimation;
	private Rectangle ar;
	private Gui gui;
	private Inventory inv;
	private int lvl, currentXP, maxXP; 
	private boolean lvlUp = false;
	private int dmg;
	
	//temp attack variables
	private long lastAttackTimer, attackCooldown = 100, attackTimer = attackCooldown;
	
	
// mouse detection stuff
	private Polygon[] pol = new Polygon[4]; // polygons made for checking mouse posistion
	private int centerX; 					// center of player in X axis
	private int centerY;					// center of player in Y axis
	private int ltX;	private int ltY;	// some variables for calculating positions for polygons
	private int rtX;	private int rtY;
	private int ldX;	private int ldY;
	private int rdX;	private int rdY;
	private final int MAX_HP = 100;
	
	private String facing = "down";
	
	public Player(Handler handler, float x, float y)
	{
		super(handler, x, y, Tile.TILEWIDTH, (Tile.TILEHEIGHT * 3)/2);		// 64 x 96
		
		bounds.x = width/2 - (int) (width * 0.203125); //32 - 13
		bounds.y = (int)(height * 0.39583);	//38
		bounds.width = (int)(2* width * 0.203125); //26
		bounds.height = (int)(4* width * 0.203125); //52
		
		if(handler.getWidth() == 1280) speed = 5.0f;
		else if(handler.getWidth() == 1920) speed = 7.5f;
		else if(handler.getWidth() == 640) speed = 2.5f;
		else if(handler.getWidth() == 1336) speed = 5.335f;
		else speed = 5.0f;
		
		//initialization for movement animation
		downAnimation = new Animation(150, Assets.player_down);
		leftAnimation = new Animation(170, Assets.player_left);
		rightAnimation = new Animation(170, Assets.player_right);
		upAnimation = new Animation(150, Assets.player_up);
		idleAnimation = new Animation(1000, Assets.player_idle);
		
		gui = new Gui(this);
		
		inv = new Inventory(handler);
		
		lvl = 1;
		currentXP = 0;
		maxXP = 100;
		dmg = 2;
		
		health = MAX_HP;
	}
	
	public void die()
	{
		System.out.println("u died");
	}

	@Override
	public void update() 
	{
		calculatePolygons();
		
		//updating movement animation
		downAnimation.tick();
		leftAnimation.tick();
		rightAnimation.tick();
		upAnimation.tick();
		idleAnimation.tick();
		
		getInput();
		getMouseInput();
		move();
		handler.getGameCamera().centerOnEntity(this); //centering camera on player
		checkAttacks();
		gui.update();
		inv.update();
		if(currentXP >= maxXP) lvlUp = true;
		if(lvlUp) levelUP();
	}
	
	public void dmgUp()
	{
		dmg += dmg;
	}
	
	private void levelUP()
	{
		lvl++;
		currentXP = currentXP - maxXP;
		maxXP = (int) (lvl * maxXP * 1.31415726);
		dmg = dmg * lvl;
		lvlUp = false;
	}
	//temp attack code
	private void checkAttacks()
	{
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		if(attackTimer < attackCooldown) {
			return;
		}
		
		if(inv.isActive()) return;
		
		Rectangle cb = getCollisionBounds(0,0);
		ar = new Rectangle();
		int arSize = 32;
		if(handler.getMouseManager().isLeftPressed() == true) 
		{
			if(pol[0].contains(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY()))
			{
				ar.width = arSize;
				ar.height = arSize * 3;
				ar.x = cb.x + cb.width / 2 - arSize /2;
				ar.y = cb.y - arSize * 3;
			}
			else if(pol[1].contains(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY()))
			{
				ar.width = arSize;
				ar.height = arSize * 3;
				ar.x = cb.x + cb.width / 2 - arSize /2;
				ar.y = cb.y + cb.height / 3 + arSize;
			}
			else if(pol[2].contains(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY()))
			{
				ar.width = arSize * 3;
				ar.height = arSize;
				ar.x = cb.x - arSize * 3;
				ar.y = cb.y + cb.height / 2 - arSize / 2;
			}
			else if(pol[3].contains(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY()))
			{
				ar.width = arSize * 3;
				ar.height = arSize;
				ar.x = cb.x + cb.width;
				ar.y = cb.y + cb.height / 2 - arSize /2;
			}
			else
			{
				return;
			}
			attackTimer = 0;
			
			for(Entity e : handler.getWorld().getEntityManager().getEntities())
			{
				if(e.equals(this)) continue;
				if(e.getCollisionBounds(0, 0).intersects(ar))
				{
					e.hurt(dmg);
					return;
				}
			}
		}
	}
	
	private void calculatePolygons()
	{
		centerX = (int)((x - handler.getGameCamera().getxOffset()) + (width/2));
		centerY = (int)((y - handler.getGameCamera().getyOffset()) + (height/2));
		
		ltX = centerX - handler.getWidth();
		ltY = centerY - handler.getWidth();
		rtX = centerX + handler.getWidth();
		rtY = centerY - handler.getWidth();
		ldX = centerX - handler.getWidth();
		ldY = centerY + handler.getWidth();
		rdX = centerX + handler.getWidth();
		rdY = centerY + handler.getWidth();
		
		pol[0] = new Polygon(new int[] {ltX, rtX, centerX}, new int[] {ltY, rtY, centerY}, 3); // top
		pol[1] = new Polygon(new int[] {ldX, rdX, centerX}, new int[] {ldY, rdY, centerY}, 3); // down
		pol[2] = new Polygon(new int[] {ltX, ldX, centerX}, new int[] {ltY, ldY, centerY}, 3); // left
		pol[3] = new Polygon(new int[] {rtX, rdX, centerX}, new int[] {rtY, rdY, centerY}, 3); // right
	}
	
	//updating movement variables
	private void getMouseInput()
	{
		if(inv.isActive()) return;
		if(handler.getMouseManager().isLeftPressed() == true) 
		{
			if(pol[0].contains(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY())) facing = "top";
			else if(pol[1].contains(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY())) facing = "down";
			else if(pol[2].contains(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY())) facing = "left";
			else if(pol[3].contains(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY())) facing = "right";
		}
	}
	
	private void getInput()
	{
		if(inv.isActive()) return;
		xMove = 0;
		yMove = 0;
		
		if(handler.getKeyManager().up) 
		{
			facing = "top";
			yMove = -speed;
		}
		if(handler.getKeyManager().down)
			{
				facing = "down";
				yMove = +speed;
			}
		if(handler.getKeyManager().left) 
			{
				facing = "left";
				xMove = -speed;
			}
		if(handler.getKeyManager().right)
			{
				facing = "right";
				xMove = +speed;
			}
	}

	@Override
	public void render(Graphics g) 
	{
		//current position player on map including offset
		g.drawImage(getCurrentAnimation(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height,  null); 
		//below debugging code
		g.setColor(java.awt.Color.red);
		g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
				(int) (y + bounds.y - handler.getGameCamera().getyOffset()),
				bounds.width, bounds.height);
		g.setColor(java.awt.Color.blue);
		if(ar.x != 0)g.fillRect(ar.x - (int) handler.getGameCamera().getxOffset(), ar.y - (int)handler.getGameCamera().getyOffset(), ar.width, ar.height);
	}
	
	public void postRender(Graphics g)
	{
		gui.render(g);
		inv.render(g);
	}

	//returns current frame of current animation
	private BufferedImage getCurrentAnimation()
	{
		if(yMove > 0){
			return downAnimation.getCurrentFrame();
		}else if(yMove < 0){
			return upAnimation.getCurrentFrame();
		}else if(xMove > 0 ){
			return rightAnimation.getCurrentFrame();
		}else if(xMove < 0){
			return leftAnimation.getCurrentFrame();
		}else if((xMove == 0 && yMove == 0)){
			if(facing.equals("down")) return Assets.player_down[0];
			else if(facing.equals("top")) return Assets.player_up[0];
			else if(facing.equals("left")) return Assets.player_left[0];
			else if(facing.equals("right")) return Assets.player_right[0];
		}else {
			return idleAnimation.getCurrentFrame();
		}
		return null;
		
	}
	
	public void addXP(int amount)
	{
		currentXP += amount;
	}
	
	public boolean isScrolling()
	{
		return false;
	}
	
	public String getFacing()
	{
		return facing;
	}


	public int getCenterX() {
		return centerX;
	}


	public int getCenterY() {
		return centerY;
	}
	
	public int getMaxHP()
	{
		return MAX_HP;
	}

	public Inventory getInv() {
		return inv;
	}

	public void setInv(Inventory inv) {
		this.inv = inv;
	}

	public int getLvl() {
		return lvl;
	}

	public int getCurrentXP() {
		return currentXP;
	}

	public int getMaxXP() {
		return maxXP;
	}

	public int getDmg() {
		return dmg;
	}

	public void setDmg(int dmg) {
		this.dmg = dmg;
	}
}
