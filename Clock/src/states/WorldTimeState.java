/**
 * <h1>WorldTimeState class</h1>
 * 
 * <p>This state will serve to display the 
 * local time and compare it to a different 
 * time anywhere in the world.</p>
 * 
 * <p>Created 5/20/18</p>
 * @version 5/29/18
 * 
 * @author Lauryn Jefferson
 */
package states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.time.ZoneId;
import java.util.TimeZone;

import graphics.Clock;
import images.ResourceLoader;
import main.MenuBar;
import main.Selector;

public class WorldTimeState implements State
{
	//constants
	private final Color MY_GREEN = new Color(0,111,60);
	private final Color MY_RED = new Color(191,33,47);
	
	//objects
	private Image background;
	private Selector selectorOne, selectorTwo;
	private Clock clockOne, clockTwo;
	
	/**
	 * <h2>WorldTimeState Constructor</h2>
	 * 
	 * <p>This constructor sets up the local time.</p>
	 */
	public WorldTimeState()
	{
		String[] zones = new String[ZoneId.getAvailableZoneIds().size()];
		ZoneId.getAvailableZoneIds().toArray(zones);
		background = ResourceLoader.getImage("/images/world.png");
		selectorOne = new Selector(zones,91,465,150,30);
		selectorTwo = new Selector(zones,491,465,150,30);
		clockOne = new Clock(TimeZone.getDefault().getID(),200,300,200,200);
		clockTwo = new Clock(TimeZone.getDefault().getID(),600,300,200,200);
		clockOne.setOneColor(true);
		clockTwo.setOneColor(true);
		setClockColors(clockOne.compareTo(clockTwo));
	}
	
	/**
	 * <h2>update() method</h2>
	 * 
	 * <p>This method will update the world time 
	 * to keep it in time.</p>
	 */
	@Override
	public void update() 
	{
		selectorOne.update();
		selectorTwo.update();
		
		//change clock if selection made
		if(selectorOne.isSelected())
		{
			clockOne = new Clock(selectorOne.getSelection(),200,300,200,200);
			clockOne.setOneColor(true);
			setClockColors(clockOne.compareTo(clockTwo));
		}
		if(selectorTwo.isSelected())
		{
			clockTwo = new Clock(selectorTwo.getSelection(),600,300,200,200);
			clockTwo.setOneColor(true);
			setClockColors(clockOne.compareTo(clockTwo));
		}
		
		//hide menu bar when drop downs are out
		if(selectorOne.isDropped() || selectorTwo.isDropped())
			MenuBar.hide();
		else 
			MenuBar.show();
		
		clockOne.update();
		clockTwo.update();
	}

	/**
	 * <h2>render() method</h2>
	 * 
	 * <p>This method will render the world time
	 * to screen with the given graphic object.</p>
	 * 
	 * @param g the Graphics object of the location to draw to
	 */
	@Override
	public void render(Graphics g) 
	{
		//draw background
		g.drawImage(background, 0, 0, null);
		
		//draw selector
		selectorOne.render(g);
		selectorTwo.render(g);
		
		//draw clocks
		clockOne.render(g);
		clockTwo.render(g);
	}

	/**
	 * <h2>setClockColors() method</h2>
	 * 
	 * <p>This method sets the colors based on the the
	 * time difference between the two clocks.</p>
	 * 
	 * @param diff the difference between the clock times
	 */
	private void setClockColors(int diff)
	{
		//clock one is ahead
		if(diff > 0)
		{
			clockOne.setHourColor(MY_GREEN);
			clockTwo.setHourColor(MY_RED);
		}
		
		//the times are the same
		if(diff == 0)
		{
			clockOne.setHourColor(Color.BLACK);
			clockTwo.setHourColor(Color.BLACK);
		}
		
		//clock two is ahead
		if(diff < 0)
		{
			clockOne.setHourColor(MY_RED);
			clockTwo.setHourColor(MY_GREEN);
		}
	}
}
