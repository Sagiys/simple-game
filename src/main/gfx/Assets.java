package main.gfx;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import main.utils.Utils;

public class Assets {
	
	// options for combobox in overstate
	public static String[] options = {"640x360", "1280x720", "1366x768", "1920x1080"};
	
	// font
	public static Font menuFont;
	
	//default dimensions
	private static final int TILE64HEIGHT = 64;
	private static final int TILE64WIDTH = 64;
	private static final int CHAR32WIDTH = 32;
	private static final int CHAR48HEIGHT = 48;
	private static final int TILE32WIDTH = 32;
	private static final int TILE32HEIGHT = 32;
	
	//images
	public static BufferedImage gui;
	public static BufferedImage rect;
	public static BufferedImage wood;
	//tiles graphics
	public static BufferedImage grass, dirt, tree, rock;
	// menu graphics
	public static BufferedImage overStateBackground;
	public static BufferedImage menuBackground;
	public static BufferedImage logo;
	public static BufferedImage[] guard_right;
	public static BufferedImage[] guard_left;
	// buttons graphics
	public static BufferedImage[] denyButton;
	public static BufferedImage[] applyButton;
	// combobox graphics
	public static BufferedImage[] comboBoxActivatingButton;
	public static BufferedImage[] comboBoxList;
	// players graphics
	public static BufferedImage[] player_up;
	public static BufferedImage[] player_down;
	public static BufferedImage[] player_left;
	public static BufferedImage[] player_right;
	public static BufferedImage[] player_idle;
	
	public static void init()
	{
		//loading spritesSheets
		SpriteSheet sheet = new SpriteSheet(Utils.loadImage("/textures/sheet1_64.png"));
		SpriteSheet playerSheet = new SpriteSheet(Utils.loadImage("/textures/char1_32x48.png"));
		SpriteSheet guardSheet = new SpriteSheet(Utils.loadImage("/textures/menuGuard32x48.png"));
		SpriteSheet comboBoxSheet = new SpriteSheet(Utils.loadImage("/textures/ComboBoxSheet.png"));
		
		// loading options graphcis
		overStateBackground = Utils.loadImage("/textures/overStateBackground.png");
		menuBackground = Utils.loadImage("/textures/menuBackground.png");
		
		//loading logo for game
		logo = Utils.loadImage("/textures/logo.png");
		
		//loading x button graphics
		denyButton = new BufferedImage[2];
		denyButton[0] = sheet.part(TILE64WIDTH * 1, TILE64HEIGHT * 2, TILE32WIDTH, TILE32HEIGHT);
		denyButton[1] = sheet.part(TILE64WIDTH * 1 + TILE32WIDTH, TILE64HEIGHT * 2, TILE32WIDTH, TILE32HEIGHT);
		
		//loading graphcis for combobox
		comboBoxActivatingButton = new BufferedImage[2];
		comboBoxList = new BufferedImage[2];
		comboBoxActivatingButton[0] = comboBoxSheet.part(0, 0, 100, 20);
		comboBoxActivatingButton[1] = comboBoxSheet.part(0, 20, 100, 20);
		comboBoxList[0] = comboBoxSheet.part(0, 40, 100, 20);
		comboBoxList[1] = comboBoxSheet.part(0, 60, 100, 20);
		
		//parting whole sheets to get actual graphics of player
		player_down = new BufferedImage[4];
		for(int x = 0; x < player_down.length; x++) player_down[x] = playerSheet.part(CHAR32WIDTH * x , CHAR48HEIGHT * 0, CHAR32WIDTH, CHAR48HEIGHT);
		player_left = new BufferedImage[4];
		for(int x = 0; x < player_left.length; x++) player_left[x] = playerSheet.part(CHAR32WIDTH * x , CHAR48HEIGHT * 1, CHAR32WIDTH, CHAR48HEIGHT);
		player_right = new BufferedImage[4];
		for(int x = 0; x < player_right.length; x++) player_right[x] = playerSheet.part(CHAR32WIDTH * x , CHAR48HEIGHT * 2, CHAR32WIDTH, CHAR48HEIGHT);
		player_up = new BufferedImage[4];
		for(int x = 0; x < player_up.length; x++) player_up[x] = playerSheet.part(CHAR32WIDTH * x , CHAR48HEIGHT * 3, CHAR32WIDTH, CHAR48HEIGHT);
		player_idle = new BufferedImage[2];
		for(int x = 0; x < player_idle.length; x++) player_idle[x] = playerSheet.part(CHAR32WIDTH * x , CHAR48HEIGHT * 4, CHAR32WIDTH, CHAR48HEIGHT);
		
		//parting whole guardian sheet to get accual graphics of guardian
		guard_left = new BufferedImage[4];
		for(int x = 0; x < guard_left.length; x++) guard_left[x] = guardSheet.part(CHAR32WIDTH * x, CHAR48HEIGHT * 0, CHAR32WIDTH, CHAR48HEIGHT);
		guard_right = new BufferedImage[4];
		for(int x = 0; x < guard_right.length; x++) guard_right[x] = guardSheet.part(CHAR32WIDTH * x, CHAR48HEIGHT * 1, CHAR32WIDTH, CHAR48HEIGHT);
		
		//creating tile from sheet
		grass = sheet.part(TILE64WIDTH * 1, TILE64HEIGHT * 0, TILE64WIDTH, TILE64HEIGHT);
		dirt = sheet.part(TILE64WIDTH * 2, TILE64HEIGHT * 0, TILE64WIDTH, TILE64HEIGHT);
		tree = sheet.part(TILE64WIDTH * 3, TILE64HEIGHT * 0, TILE64WIDTH, TILE64HEIGHT);
		rock = sheet.part(TILE64WIDTH * 0, TILE64HEIGHT * 1, TILE64WIDTH, TILE64HEIGHT);
		
		gui = Utils.loadImage("/textures/gui.png");
		rect = Utils.loadImage("/textures/rect.png");
		wood = Utils.loadImage("/textures/wood.png");
		
		//loading font
		menuFont = Utils.loadFont("/fonts/Gputeks-Regular.ttf");
	}
	
}
