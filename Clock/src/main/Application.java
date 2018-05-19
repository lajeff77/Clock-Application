/**
 * <h1>Application class</h1>
 * <p>This class oversees the whole production of the application. It
 * manages all aspects of interaction and contains the thread for the
 * application.</p>
 * 
 * Created:5/19/18
 * @version 5/19/18
 * 
 * @author Lauryn Jefferson
 */
package main;

public class Application
{
	//constants
	private static final int DEFAULT_WIDTH = 800;
	private static final int DEFAULT_HEIGHT = 800;
	
	//objects
	
	//variables
	private int width, height;
	
	/**
	 * <h2>Application Constructor</h2>
	 * 
	 * <p>This constructor serves a default constructor
	 * for the application. It will set up the window with
	 * the default width and height of 800.</p>
	 */
	public Application()
	{
		this(DEFAULT_WIDTH,DEFAULT_HEIGHT);
	}
	
	/**
	 * <h2>Application Constructor</h2>
	 * 
	 * <p>This constructor sets up the application with the
	 * given width and height for the window.</p>
	 * 
	 * @param width width of window
	 * @param height height of window
	 */
	public Application(int width, int height)
	{
		//set window width and height
		this.width = width;
		this.height = height;
	}
}
