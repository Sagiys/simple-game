package main.display;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;

public class Display {
	
	//windows stuff
	private JFrame frame;
	private Canvas canvas;

	private String title;
	private int width, height;
	
	public Display(String title, int width, int height)
	{
		
		this.title = title;
		this.width = width;
		this.height = height;
		
		createDisplay();
		
	}
	
	public void createDisplay()
	{
		
		//frame stuff
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
//		frame.setUndecorated(true);
		frame.setVisible(true);
		
		//canvas stuff
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setFocusable(false);
		canvas.setBackground(Color.BLACK);

		frame.add(canvas);
		frame.pack();
		
	}
	
	
	public Canvas getCanvas()
	{
		return canvas;
	}

	public JFrame getFrame()
	{
		return frame;
	}
	
}
