/**
 * <h1>Launcher class</h1>
 * 
 * <p>This class is the launching point for the whole project.</p>
 * 
 * <p>Created:5/19/18</p>
 * @version 5/19/18
 * 
 * @author Lauryn Jefferson
 */
package main;

import main.Application.AlreadyRunningException;

public class Launcher 
{
	/**
	 * <h2>main() method</h2>
	 * 
	 * <p>This method is the starting point that the JVM
	 * recognizes when running this program.</p>
	 * 
	 * @param args arguments for the starting conditions of the program
	 */
	public static void main(String[] args)
	{
		//sets up application with a title and window size of 640 by 480
		Application clockApp = new Application("Clock Application",640,480);
		
		
		//hands over the duties to the clock application thread
		try
		{
			//try to start up the clock application
			clockApp.start();
		}
		catch(AlreadyRunningException e)
		{
			//error in starting up the app
			e.printStackTrace();
		}
	}
}
