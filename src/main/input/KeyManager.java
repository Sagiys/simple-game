//todo make dynamic list instead of static array

package main.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener{

	private boolean[] keys, justPressed, cantPress;
	public boolean up, down, left , right, pause, k;
	
	public KeyManager()
	{
		keys = new boolean[256];
		justPressed = new boolean[keys.length];
		cantPress = new boolean[keys.length];
	}
	
	public void update()
	{
		for(int i = 0; i < keys.length; i++)
		{
			if(cantPress[i] && !keys[i])
			{
				cantPress[i] = false;
			}else if(justPressed[i])
			{
				cantPress[i] = true;
				justPressed[i] = false;
			}
			if(!cantPress[i] && keys[i])
			{
				justPressed[i] = true;
			}
		}
		
		up = keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_D];
		pause = keys[KeyEvent.VK_ESCAPE];
		k = keys[KeyEvent.VK_K];
	}
	
	@Override
	public void keyPressed(KeyEvent e) 
	{
		if(e.getKeyCode() < 0 || e.getKeyCode() > keys.length)
			return;
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		keys[e.getKeyCode()] = false;
	}
	
	public boolean keyJustPressed(int keyCode)
	{
		if(keyCode < 0 || keyCode > keys.length) return false;
		return justPressed[keyCode];
	}

	@Override
	public void keyTyped(KeyEvent e) 
	{
		
	}

}
