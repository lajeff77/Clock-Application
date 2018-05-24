/**
 * <h1>LocalTimeState class</h1>
 * 
 * <p>This state will serve to display the
 * local time in the application.</p>
 * 
 * <p>Created 5/20/18</p>
 * @version 5/23/18
 * 
 * @author Lauryn Jefferson
 */
package states;

import java.awt.Color;
import java.awt.Graphics;

import graphics.Clock;
import main.Window;

public class LocalTimeState implements State
{
	private Clock localClock;
	private Color backgroundColor;
	
	public LocalTimeState()
	{
		localClock = new Clock();
		backgroundColor = new Color(220,220,222);
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
		g.setColor(backgroundColor);
		g.fillRect(0, 0, Window.getWidth(), Window.getHeight());
		
		//clock
		localClock.render(g);
	}

}
