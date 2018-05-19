/**
 * <h1>Application class</h1>
 * <p>This class oversees the whole production of the application. It
 * manages all aspects of interaction and contains the thread for the
 * application.</p>
 * 
 * <p>Created:5/19/18</p>
 * @version 5/19/18
 * 
 * @author Lauryn Jefferson
 */
package main;

public class Application implements Runnable
{
	//constants
	private static final int DEFAULT_WIDTH = 800;
	private static final int DEFAULT_HEIGHT = 800;
	
	//objects
	private Thread thread;
	
	//variables
	private int width, height;//for window dimensions
	boolean running;//whether its running or not
	
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
		
		//set conditions
		running = false;
	}
	
	public void run()
	{
		init();
		int fps = 60;
		double updateTime = 1000000000 / fps;
		double change = 0;
		long currentTime;
		long beginningTime = System.nanoTime();
		
		while(running)
		{
			currentTime = System.nanoTime();
			change += (currentTime - beginningTime) / updateTime;
			beginningTime = currentTime;
			
			if(change >= 1)
			{
				update();
				render();
				change--;
			}
		}
		
	}
	
	/**
	 * <h2>init() method</h2>
	 * 
	 * <p>This method initializes the necessary managers
	 * and listeners for the Application to function properly.</p>
	 */
	private void init()
	{
		//Initialize stuff
	}
	
	/**
	 * <h2>start() method</h2>
	 * 
	 * <p>This method creates and starts the thread 
	 * for the application.</p>
	 */
	public void start() throws AlreadyRunningException
	{
		//check if its running already
		if(running)
			throw new AlreadyRunningException();
		
		//setup if not running
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	/**
	 * <h2>stop() method</h2>
	 * 
	 * <p>This method properly shuts down the thread
	 * for the application.</p>
	 */
	public void stop()
	{
		running = false;
	}
	
	/**
	 * <h2>update() method</h2>
	 * 
	 * <p>This method maintains all of the elements
	 * of the Application and keeps them up to speed
	 * with the cycle of the Application.</p>
	 */
	private void update()
	{
		//update stuff
	}
	
	/**
	 * <h2>render() method</h2>
	 * 
	 * <p>This method renders all of the graphical
	 * elements of the application to the screen after
	 * they've been updated.</p>
	 */
	private void render()
	{
		//render stuff
	}
	
	/**
	 * <h1>AlreadyRunningException class</h1>
	 * 
	 * <p>An exception that deals with when the user attempts 
	 * to run the thread when its already running.</p>
	 * 
	 * <p>Created:5/19/18</p>
	 * @version 5/19/19
	 * 
	 * @author Lauryn Jefferson
	 */
	protected class AlreadyRunningException extends Exception
	{
		//because Exceptions are Serializable
		private static final long serialVersionUID = 1L;

		/**
		 * <h2>AlreadyRunningException() constructor</h2>
		 * 
		 * <p>This default constructor uses "The Application 
		 * thread is already running." as the error message
		 * for the Exception.</p>
		 */
		public AlreadyRunningException()
		{
			this("The Application thread is already running.");
		}
		
		/**
		 * <h2>AlreadyRunningException() constructor</h2>
		 * 
		 * <p>This constructor creates the Exception and sets 
		 * it's method.</p>
		 * 
		 * @param message the error message that displays
		 */
		public AlreadyRunningException(String message)
	    {
	        super(message);
	    }
	}
}
