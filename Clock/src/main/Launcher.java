/**
 * <h1>Launcher Class</h1>
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
		//sets up application with window size of 1920 by 1080
		Application clockApp = new Application(1920,1080);
		
		
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
