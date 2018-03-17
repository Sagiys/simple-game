package main.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

public class PropertiesManager {

	//new instance of proprties class
	private Properties props = new Properties();

	File configFile;
	FileReader reader;
	FileWriter writer;
	
	public PropertiesManager()
	{
		 configFile = new File(System.getenv("APPDATA") + "/rogue-like/config.properties");
		 if(!configFile.exists())
		 {
			configFile.getParentFile().mkdir();
			try {
				configFile.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			 try {
				PrintWriter writer2 = new PrintWriter(configFile);
				writer2.println("width=1280");
				writer2.println("height=720");
				writer2.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		 }
		 try 
		 {
			reader = new FileReader(configFile);
		 } catch (FileNotFoundException e) 
		 {
	 		e.printStackTrace();
	     }
	}
	
	//file manipulation
	
	//opening properties
	public void openProperties()
	{
		try 
		{
			props.load(reader);
		} catch (IOException e1) 
		{
			e1.printStackTrace();
		}
	}
	
	//setting property
	public void setProperty(String key, String value)
	{
		props.setProperty(key, value);
		store(); //saving property to file
	}
	
	//returning property
	public String getProperty(String key)
	{
		return props.getProperty(key);
	}
	
	//storing properties to file
	public void store()
	{
		try 
		{
			writer = new FileWriter(configFile); //setting up writer
			props.store(writer, "configuration file for game"); //saving proprties t ofile
			writer.close(); //setting down writer
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
}
