/**
 * <h1>LocalTimeState class</h1>
 * 
 * <p>This state will serve to display the
 * local time in the application.</p>
 * 
 * <p>Created 5/20/18</p>
 * @version 5/29/18
 * 
 * @author Lauryn Jefferson
 */
package states;

import java.awt.Graphics;
import java.awt.Image;

import graphics.Clock;
import images.ResourceLoader;
import main.Window;

public class LocalTimeState implements State
{
	//objects
	private Clock localClock;
	private Image background;
	private String timeOfDay;
	
	//variables
	private double originalWidth, originalHeight;
	double ratio;
	
	/**
	 * <h2>LocalTimeState Constructor</h2>
	 * 
	 * <p>This constructor sets up the local time.</p>
	 */
	public LocalTimeState()
	{
		localClock = new Clock();
		originalWidth = Window.getWidth();
		originalHeight = Window.getHeight();
		timeOfDay = localClock.getTimeOfDay();
		setBackground();
		setRatio();
	}

	/**
	 * <h2>update() method</h2>
	 * 
	 * <p>This method will update the local time
	 * to keep it in time.</p>
	 */
	@Override
	public void update() 
	{
		localClock.update();
		//setRatio();
		
		//change background for what time of day it is
		if(timeOfDay.compareTo(localClock.getTimeOfDay()) != 0)
		{
			timeOfDay = localClock.getTimeOfDay();
			setBackground();
		}
			
	}

	/**
	 * <h2>render() method</h2>
	 * 
	 * <p>This method will render the local time
	 * to screen with the given graphic object.</p>
	 * 
	 * @param g the Graphics object of the location to draw to
	 */
	@Override
	public void render(Graphics g) 
	{
		//background
		g.drawImage(background, 0, 0, null);
		
		//clock
		localClock.render(g);
	}

	/**
	 * <h2>setRatio() method</h2>
	 * 
	 * <p>This method is a helper method that 
	 * determines the ratio that the screen was 
	 * resized based on which ratio is smaller, the
	 * x-axis or the y-axis.</p>
	 */
	private void setRatio()
	{
		//set the ratio of the current screen size
		double xRatio = Window.getWidth() / originalWidth;
		double yRatio = Window.getHeight() / originalHeight;

		//set ratio based on which one is smaller
		if(xRatio < yRatio)
			ratio = xRatio;
		else
			ratio = yRatio;
	}
	
	/**
	 * <h2>setBackground() method</h2>
	 * 
	 * <p>This helper method will set the background
	 * based on the time of day from the clock of the 
	 * local time. </p>
	 */
	private void setBackground()
	{
		switch(timeOfDay)
		{
		case "morning":
			background = ResourceLoader.getImage("/images/morning.png");
			break;
		case "day":
			background = ResourceLoader.getImage("/images/day.png");
			break;
		case "evening":
			background = ResourceLoader.getImage("/images/evening.png");
			break;
		case "night":
			background = ResourceLoader.getImage("/images/night.png");
			break;
		default:
			background = ResourceLoader.getImage("/images/day.png");
			break;
		}
	}
}
