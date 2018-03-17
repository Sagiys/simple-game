package main.utils;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

public class Utils {

	
	
	//loads txt files as string
	public static String loadFileAsString(String path)
	{
		StringBuilder builder = new StringBuilder();
		
		try{
			InputStream in = Utils.class.getResourceAsStream(path);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String line;
			while((line = br.readLine()) != null)
				builder.append(line + "\n");
			
			br.close();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		return builder.toString();
	}
	
	//returns string type number as integer
	public static int parseInt(String number)
	{
		try{
			return Integer.parseInt(number);
		}catch(NumberFormatException e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	public static Font loadFont(String s)
	{
		InputStream in = Utils.class.getResourceAsStream(s);
	
		try {
			return Font.createFont(Font.TRUETYPE_FONT, in);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static BufferedImage loadImage(String path)
	{
		try {
			return ImageIO.read(Utils.class.getResource(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			System.out.println("failed loading: " + path);
			System.exit(1);
		}
		return null;
	}

	
}
