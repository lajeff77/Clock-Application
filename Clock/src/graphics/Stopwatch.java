/**
 * <h1>Stopwatch class</h1>
 * 
 * <p>This class creates a stopwatch
 * with a stop and start button and a 
 * lap mechanism.</p>
 * 
 * <p>Created:5/30/18</p>
 * @version 6/1/18
 * 
 * @author Lauryn Jefferson
 */
package graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import main.MouseManager;
import main.Window;

public class Stopwatch
{
	//constants
	private final Color MY_GREEN = new Color(0,111,60);
	private final Color MY_RED = new Color(191,33,47);
	
	
	//objects
	private ArrayList<String> laps;
	private String bestLap, worstLap;
	private String displayString;
	private Font watchFont, lapFont;

	
	//variables
	private int x,y;
	private long curr, start, accum;
	private boolean power;
	private boolean showLaps;
	private int lapLength;
	
	/**
	 * <h2>Stopwatch() constructor</h2>
	 * 
	 * <p>This default constructor sets up a new stopwatch
	 * in the center of the window.</p>
	 */
	public Stopwatch()
	{
		this(Window.getWidth()/2, Window.getHeight()/2);
	}
	
	/**
	 * <h2>Stopwatch() constructor</h2>
	 * 
	 * <p>This constructor sets x and y to be the center
	 * for the purposes of the stop watch display.</h2>
	 * 
	 * @param x x coordinate for center
	 * @param y y coordinate for center
	 */
	public Stopwatch(int x, int y)
	{
		this.x = x;
		this.y = y;
		power = false;
		showLaps = true;
		lapLength = 5;
		laps = new ArrayList<String>(20);
		accum = 0;
		
		watchFont = new Font("Arial",1,30);
		lapFont = new Font("Arial",1,18);
		
		bestLap = worstLap = "00:00:00";
		
	}
	
	/**
	 * <h2>update() method</h2>
	 * 
	 * <p>This method will update the stopwatch
	 * and ensure that it's calibrated to the
	 * correct time.</p>
	 */
	public void update()
	{
		
		if(MouseManager.isReleased())
		{
			Graphics g = Window.getCanvas().getGraphics();
			g.setFont(lapFont);
			
			//g.fillRect(x-g.getFontMetrics().stringWidth("START")-10,y - g.getFontMetrics().getHeight()*3  - 10, g.getFontMetrics().stringWidth("START")+10, g.getFontMetrics().getHeight()+10);
			
			//power button clicked
			if(MouseManager.getMouseX() > x-g.getFontMetrics().stringWidth("RESET")- 10 && MouseManager.getMouseX() < x -5 && MouseManager.getMouseY() > y - g.getFontMetrics().getHeight()*3  - 10 && MouseManager.getMouseY() < y+ g.getFontMetrics().getHeight()*2)   
			{
				power = !power;
				if(power)
					start();
				if(!power)
					stop();
			}
			
			//lap button clicked
			if(MouseManager.getMouseX() > x+5 && MouseManager.getMouseX() < x +g.getFontMetrics().stringWidth("RESET") +15 && MouseManager.getMouseY() > y- g.getFontMetrics().getHeight()*3  - 10 && MouseManager.getMouseY() < y+ g.getFontMetrics().getHeight()*2)   
			{
				if(power)
					lap();
				if(!power)
					reset();
			}
		}
		
		//run the timer
		if(power)
		{
			curr = System.currentTimeMillis() - start;
			curr += accum;
		}
		else
			curr = accum;
		displayString = getDisplayString(curr);
	}
	
	/**
	 * <h2>render() method</h2>
	 * 
	 * <p>This method renders the stop watch to the
	 * window of the given graphics object. Whether
	 * or not it has laps displayed(best, worst, ect.)
	 * depends on its properties.</p>
	 * 
	 * @param g the Graphics object of the location to draw to
	 */
	public void render(Graphics g)
	{
		
		//stopwatch
		g.setFont(watchFont);
		g.setColor(Color.BLACK);
		g.drawString(displayString, x - g.getFontMetrics().stringWidth(displayString)/2, y - g.getFontMetrics().getHeight()/2);
		
		//laps
		g.setFont(lapFont);
		if(showLaps)
		{
			//if there are at least five
			int pickupY = y + g.getFontMetrics().getHeight() +5;
			int tempCount = 1;
			
			if(laps.size() > lapLength)
			{
				for(int i = laps.size()-5; i < laps.size(); i ++)
				{
					String lapString = i+". "+laps.get(i);
					g.drawString(lapString, x - g.getFontMetrics().stringWidth(lapString)/2, y + g.getFontMetrics().getHeight()* tempCount + 5*tempCount + g.getFontMetrics().getHeight()/2);
					tempCount++;
					pickupY =  y + g.getFontMetrics().getHeight()* tempCount + 5*tempCount ;
				}
			}
			else
			{
				//if there are less than 5
				for(int i = 0; i < laps.size(); i++)
				{
					String lapString = (i+1)+". "+laps.get(i);
					g.drawString(lapString, x - g.getFontMetrics().stringWidth(lapString)/2, y + g.getFontMetrics().getHeight()* tempCount + 5*tempCount + g.getFontMetrics().getHeight()/2);
					tempCount++;
					pickupY =  y + g.getFontMetrics().getHeight()* tempCount + 5*tempCount ;
				}
			}
			//best and worst laps
			g.setColor(MY_RED);
			g.drawString(bestLap,x-g.getFontMetrics().stringWidth(bestLap)/2,pickupY+g.getFontMetrics().getHeight()/2 + 5);
			g.setColor(MY_GREEN);
			g.drawString(worstLap,x-g.getFontMetrics().stringWidth(worstLap)/2,pickupY +(int) (g.getFontMetrics().getHeight()*(3/2.0)) +10);
		}
		
		//start and stop button
		if(power)
		{
			//draw stop button
			g.setColor(MY_RED);
			g.fillRect(x-g.getFontMetrics().stringWidth("RESET")-10,y - g.getFontMetrics().getHeight()*3  - 10, g.getFontMetrics().stringWidth("RESET")+10, g.getFontMetrics().getHeight()+10);
			g.setColor(Color.BLACK);
			g.drawString("STOP",x-g.getFontMetrics().stringWidth("STOP")-5,y- g.getFontMetrics().getHeight()*2 - g.getFontMetrics().getHeight()/3 - 5);
		
			//lap button
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(x + 5,y - g.getFontMetrics().getHeight()*3  - 10, g.getFontMetrics().stringWidth("RESET")+10, g.getFontMetrics().getHeight()+10);
			g.setColor(Color.BLACK);
			g.drawString("LAP",x + g.getFontMetrics().stringWidth("LAP")-10,y- g.getFontMetrics().getHeight()*2 -g.getFontMetrics().getHeight()/3 - 5);	
		}
		else
		{
			//draw start button
			g.setColor(MY_GREEN);
			g.fillRect(x-g.getFontMetrics().stringWidth("RESET")-10,y - g.getFontMetrics().getHeight()*3  - 10, g.getFontMetrics().stringWidth("RESET")+10, g.getFontMetrics().getHeight()+10);
			g.setColor(Color.BLACK);
			g.drawString("START",x-g.getFontMetrics().stringWidth("START")-5,y- g.getFontMetrics().getHeight()*2 - g.getFontMetrics().getHeight()/3 - 5);
		
			//reset button
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(x + 5,y - g.getFontMetrics().getHeight()*3  - 10, g.getFontMetrics().stringWidth("RESET")+10, g.getFontMetrics().getHeight()+10);
			g.setColor(Color.BLACK);
			g.drawString("RESET",x + 10,y- g.getFontMetrics().getHeight()*2 -g.getFontMetrics().getHeight()/3 - 5);
		}
		
		
	}
	
	/**
	 * <h2>getDisplayString() method</h2>
	 * 
	 * <p>This helper method takes an amount
	 * of milliseconds and turns it into a
	 * formated string (hh:mm:dd).<p>
	 * 
	 * @param millis time in milliseconds
	 * @return time in string form
	 */
	private String getDisplayString(long millis)
	{ 
		String dString = "";
		long hours = TimeUnit.MILLISECONDS.toHours(millis);
		millis -= TimeUnit.HOURS.toMillis(hours);
	    long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
		millis -= TimeUnit.HOURS.toMillis(minutes);
	    long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);
	    	
	    //string for hours
		if(hours == 0)
			dString += "00:";
		else
			if(hours < 10)
				dString += "0"+hours+":";
			else
				dString += hours+":";
		
		//string for minutes
		if(minutes == 0)
			dString += "00:";
		else 
			if(minutes < 10)
				dString +="0"+minutes+":";
			else
				dString += minutes+":";
		
		//string for seconds
		if(seconds == 0)
			dString += "00";
		else
			if(seconds < 10)
				dString +="0"+seconds;
			else dString += seconds;
		
		return dString;
	}
	
	/**
	 * <h2>start() method</h2>
	 * 
	 * <p>This helper method starts the stop watch.</p>
	 */
	private void start()
	{
		start = System.currentTimeMillis();
		power = true;
	}
	
	
	/**
	 * <h2>stop() method</h2>
	 * 
	 * <p>This helper method stops the stop watch.</p>
	 */
	private void stop()
	{
		accum += curr - accum;
		curr = 0;
		start = 0;
		power = false;
	}
	
	private void reset()
	{
		accum = 0;
		laps.clear();
		bestLap = worstLap = "00:00:00";
	}
	
	/**
	 * <h2>lap() method</h2>
	 *
	 * <p>This helper method helps add the lap
	 * data to the lap list.</p>
	 */
	private void lap()
	{
		//determine current lap
		long thisLap = curr;
		
		for(int i = 0; i < laps.size(); i++)
			
			thisLap -= lapTotal(laps.get(i));
		//determine best and worst lap
		if(laps.isEmpty())
			bestLap = worstLap = getDisplayString(thisLap);
		if(thisLap > lapTotal(bestLap))
			bestLap = getDisplayString(thisLap);
		if((thisLap < lapTotal(worstLap)))
			worstLap = getDisplayString(thisLap);
		
		//add this current lap
		laps.add(getDisplayString(thisLap));
	}
	
	/**
	 * <h2>lapTotal() method</h2>
	 * 
	 * <p>This helper method determines the total time
	 * of a lap based on its string and returns 
	 * that time in milliseconds.</p>
	 * 
	 * @param l lap in String from
	 * @return lap in millis
	 */
	private long lapTotal(String l)
	{
		//accounts if hours are over 100
		int hourLen =  l.length()-8;

		long lapTotal = TimeUnit.HOURS.toMillis(Long.valueOf(l.substring(0,hourLen + 2)));//hours to millis
		lapTotal += TimeUnit.MINUTES.toMillis(Long.valueOf(l.substring(hourLen+3, hourLen+5)));//minutes to millis
		lapTotal += TimeUnit.SECONDS.toMillis(Long.valueOf(l.substring(hourLen + 6)));//seconds to millis
		return lapTotal;
	}
	
	/**
	 * <h2>isStopped() method</h2>
	 * 
	 * <p>This method returns whether the 
	 * stop watch is stopped or not.</h2>
	 * 
	 * @return stopped or not
	 */
	public boolean isStopped()
	{
		return !power;
	}
	
	
	
	
}
