/**
 * <h1>Window class</h1>
 * 
 * <p>This class creates the window where all of 
 * the graphics are to be displayed.</p>
 * 
 * <p>Created:5/19/18</p>
 * @version 5/19/18
 * 
 * @author Lauryn Jefferson
 */
package main;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window 
{
	//objects
	private static JFrame frame;//to contain the canvas
	private static Canvas canvas;//for java.awt stuff
	private String title;//the title that goes on the frame
	
	//variables
	private static int width, height;//canvas dimensions
	
	/**
	 * <h2>Window constructor</h2>
	 * 
	 * <p>This constructor sets up the window with
	 * the given title and dimensions.</p>
	 * 
	 * @param title title that the frame will display
	 * @param width width of the canvas
	 * @param height height of the canvas
	 */
	
	public Window(String title, int width, int height)
	{
		this.title = title;
		Window.width = width;
		Window.height = height;
		
		init();
	}
	
	/**
	 * <h2>init() method</h2>
	 * 
	 * <p>This method initializes the window and
	 * sets all the details for the frame and the canvas
	 * to prepare the window for use.</p>
	 */
	private void init()
	{
		//create frame
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setVisible(true);
		
		//frame.setIconImage(Assets.icon);
		
		//create canvas
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width,height));
        canvas.setMinimumSize(new Dimension(width,height));
        canvas.setMaximumSize(new Dimension(width,height));
        
        //add canvas to frame
		frame.add(canvas);
		frame.pack();
	}
	
	/**
	 * <h2>getFrame() method</h2>
	 * 
	 * <p>This method returns the frame of 
	 * the window.</p>
	 * 
	 * @return the frame of the window
	 */
	public static JFrame getFrame() 
	{
		return frame;
	}

	/**
	 * <h2>getCanvas() method</h2>
	 * 
	 * <p>This method returns the canvas of 
	 * the window.</p>
	 * 
	 * @return the canvas of the window
	 */
	public static Canvas getCanvas() 
	{
		return canvas;
	}
	
	/**
	 * <h2>getWidth() method</p>
	 * 
	 * <p>This method returns the current width of 
	 * the canvas inside of the window.</p>
	 * 
	 * @return width of canvas
	 */
	public static int getWidth()
	{
		return canvas.getWidth();
	}
	
	/**
	 * <h2>getHeight() method</p>
	 * 
	 * <p>This method returns the current height of 
	 * the canvas inside of the window.</p>
	 * 
	 * @return height of canvas
	 */
	public static int getHeight()
	{
		return canvas.getHeight();
	}
}
