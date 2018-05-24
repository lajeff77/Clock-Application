/**
 * <h1>Clock class</h2>
 * 
 * <p>The clock given a time will display the given
 * time. The display includes the time zone, the date,
 * an analog clock, and the digital display. This clock
 * must be implemented with a system that utilizes a graphics
 * object for the GUI implementation.</p>
 * 
 * <p>Created: 5/20/18</p>
 * @version 5/24/18
 * 
 * @author Lauryn Jefferson
 */
package graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.TimeZone;

import main.Window;

public class Clock 
{
	//constants
	private final static int DEFAULT_WIDTH = 400;
	private final static int DEFAULT_HEIGHT = 400;
	
	//objects 
	private Color hourColor, minuteColor, secondColor;
	private ZonedDateTime time;
	private String hour,minute,second;
	private String date, zone, zoneDisplay;
	
	//variables
	private int centerX, centerY;
	private int cornerX, cornerY;
	private double originalWidth, originalHeight;
	private int clockWidth, clockHeight;
	private double ratio;
	private double hourAngle, minuteAngle, secondAngle;
	private boolean oneColor, digitalDisplay, plain;
	private int hourRadius, minuteRadius, secondRadius;
	
	/**
	 * <h2>Clock() constructor</h2>
	 * 
	 * <p>This default constructor will set up the clock 
	 * to be displayed in the center of the screen with 
	 * the local time zone and default width and height of
	 * 400.</p>
	 */
	public Clock()
	{
		this(TimeZone.getDefault().getID(),Window.getWidth()/2, Window.getHeight()/2, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	
	/**
	 * <h2>Clock() constructor</h2>
	 * 
	 * <p>This constructor will center the clock at the given 
	 * coordinates and set it's time zone to the given zone. The
	 * width and height will be the default of 400.</p>
	 * 
	 * @param time
	 * @param centerX
	 * @param centerY
	 */
	public Clock(String zone,int centerX, int centerY)
	{
		this(zone,centerX, centerY, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	
	/**
	 * <h2>Clock() constructor</h2>
	 * 
	 * <p>This constructor will center the clock at the given 
	 * coordinates, display the given time zone, and size the 
	 * clock at the given width and height.</p>
	 * 
	 * @param timeZone zone to display on the clock
	 * @param centerX center x coordinate of clock
	 * @param centerY center y coordinate of clock
	 * @param clockWidth width of clock display
	 * @param clockHeight height of clock display
	 */
	public Clock(String zone,int centerX, int centerY, int clockWidth, int clockHeight)
	{
		//set zone time
		this.time = ZonedDateTime.now(TimeZone.getTimeZone(ZoneId.of(zone)).toZoneId());
		
		this.zone = zone;
		
		zoneDisplay = TimeZone.getTimeZone(zone).getDisplayName();
		
		//set center
		this.centerX = centerX;
		this.centerY = centerY;
		
		//determine top right corner of clock
		cornerX = centerX - clockWidth/2;
		cornerY = centerY - clockHeight/2;
		
		//set clock dimensions
		this.clockWidth = clockWidth;
		this.clockHeight = clockHeight;
		
		//set for ratio balancing
		originalWidth = Window.getWidth();
		originalHeight = Window.getHeight();
		
		//set colors
		hourColor = new Color(38,75,150);
		minuteColor = new Color(0,111,60);
		secondColor = new Color(191,33,47);
		
		//set clock hand radiuses
		hourRadius  = 130;
		minuteRadius = 170;
		secondRadius = 180;
		
		//set clock properties
		oneColor = false;
		digitalDisplay = true;
	}
	
	/**
	 * <h2>update() method</h2>
	 * 
	 * <p>This method will update the clock
	 * and ensure that it's calibrated to the
	 * correct time.</p>
	 */
	public void update()
	{
		time = ZonedDateTime.now(TimeZone.getTimeZone(zone).toZoneId());
		setRatio();
		setAngles();
		setStrings();
	}
		
	/**
	 * <h2>render() method</h2>
	 * 
	 * <p>This method renders the clock to the
	 * window of the given graphics object. Whether
	 * or not it has a full display or just an analog
	 * is determined by its properties.</p>
	 * 
	 * @param g the Graphics object of the location to draw to
	 */
	public void render(Graphics g)
	{
		//updated values based on the current ratio
		int cx = (int)(centerX * ratio);
		int cy = (int)(centerY * ratio);
		int cw = (int)(clockWidth * ratio);
		int ch = (int)(clockHeight * ratio);
		int spacing = (int)(8*ratio);
		int fontSize = (int)(30*ratio);
		int fontSize1 = (int)(18*ratio);
		int two = (int)(2*ratio);
		int cnrX = (int)(cornerX * ratio);
		int cnrY = (int)(cornerY * ratio);
		int hr = (int)(hourRadius * ratio);
		int mr = (int)(minuteRadius * ratio);
		int sr = (int)(secondRadius * ratio);
		//set font based on ratio
		g.setFont(new Font("Brussels",1 ,fontSize));
		//font width and heights
		int sw12 = g.getFontMetrics().stringWidth("12")/two;
		int sw9 = g.getFontMetrics().stringWidth("9")/two;
		int sw6 = g.getFontMetrics().stringWidth("6")/two;
		int sw3 = g.getFontMetrics().stringWidth("3")/two;
		int sh = (int)(30 * ratio);
		int sh1 = (int)(18 * ratio);
		
		//clock body
		g.setColor(Color.BLACK);
		g.fillOval(cnrX, cnrY, cw, ch);
		
		g.setColor(Color.WHITE);
		g.fillOval(cnrX + spacing, cnrY + spacing, cw - spacing*two, ch - spacing*two);
		
		//draw the major hours
		g.setColor(Color.black);
		
		g.drawString("12",cx - sw12 ,cy - ch/two + sh + spacing*two);
		g.drawString("3",cx - sw3*two + cw/two - spacing*two ,cy+ sh/two );
		g.drawString("9", cx + sw9 - cw/two + spacing, cy + sh/two);
		g.drawString("6", cx - sw6, cy + ch/two -sh + spacing);
		
		if(!plain)
		{
			//new font
			g.setFont(new Font("Arial",1,fontSize1));

			//draw the time zone
			g.drawString(zoneDisplay, cx - g.getFontMetrics().stringWidth(zoneDisplay)/2, cy - ch/2 - sh1*2 - spacing);

			//draw what day it is
			g.drawString(date, cx - g.getFontMetrics().stringWidth(date)/2, cy - ch/2 - sh1);

			//new font
			g.setFont(new Font("Arial",1,fontSize));

			//display of the time in numerical form
			if(digitalDisplay)
			{
				//hour
				g.setColor(hourColor);
				g.drawString(hour, cx - g.getFontMetrics().stringWidth(minute)/2 - g.getFontMetrics().stringWidth(":") - g.getFontMetrics().stringWidth(hour),cy + ch/2 + sh + spacing);
				g.setColor(Color.BLACK);
				g.drawString(":", cx - g.getFontMetrics().stringWidth(minute)/2 - g.getFontMetrics().stringWidth(":") , cy + ch/2 + sh + spacing);
				
				//minute
				if(!oneColor)
					g.setColor(minuteColor);
				else
					g.setColor(hourColor);
				g.drawString(minute, cx - g.getFontMetrics().stringWidth(minute)/2,cy + ch/2 + sh + spacing);
				g.setColor(Color.BLACK);
				g.drawString(":",  cx + g.getFontMetrics().stringWidth(minute)/2, cy + ch/2 + sh + spacing);
				
				//second
				if(!oneColor)
					g.setColor(secondColor);
				else
					g.setColor(hourColor);
				g.drawString(second, cx + g.getFontMetrics().stringWidth(minute)/2 + g.getFontMetrics().stringWidth(":"),cy + ch/2 + sh + spacing);
			}
		}
		
		
		
		Graphics2D g2 = (Graphics2D) g;
	    g2.setColor(Color.BLACK);
	    
		//hour hand
		if(!oneColor)
			g2.setColor(hourColor);
		g2.setStroke(new BasicStroke(5));
		g2.drawLine(cx, cy,(int)(cx +(Math.sin(hourAngle) * hr)),(int)(cy - (Math.cos(hourAngle) * hr)));
		
		//minute hand
		if(!oneColor)
			g2.setColor(minuteColor);
		g2.setStroke(new BasicStroke(4));
		g2.drawLine(cx, cy,(int)(cx +(Math.sin(minuteAngle) * mr)),(int)(cy - (Math.cos(minuteAngle) * mr)));
		
		//second hand
		if(!oneColor)
			g2.setColor(secondColor);
		g2.setStroke(new BasicStroke(2));
		g2.drawLine(cx, cy,(int)(cx +(Math.sin(secondAngle) * sr)),(int)(cy - (Math.cos(secondAngle) * sr)));
		
		//draw a cap on top of the hands
		g.setColor(Color.BLACK);
		g.fillOval(cx - spacing + two, cy - spacing + two, (spacing - two)*2, (spacing - two)*2);
	}
	
	/**
	 * <h2>setStrings() method</h2>
	 *
	 * <p>This method is a helper method that
	 * sets all of the strings for the time and
	 * date for the display.</p>
	 */
	private void setStrings()
	{
		//convert hour into 12s
		int temp = time.getHour();
		if(temp > 12)
			temp -=12;
		//set hour
		hour =  Integer.toString(temp);
		
		//set minute
		minute = Integer.toString(time.getMinute());
		if(minute.length() == 1)
			minute = "0" + minute;
		
		//set second
		second = Integer.toString(time.getSecond());
		if(second.length() == 1)
			second = "0" + second;

		int dom = time.getDayOfMonth();
		String suffix;

		//determine the suffix
		if(dom == 1 || dom == 21 || dom == 31)
			suffix = "st";
		else
			if(dom == 2 || dom == 22)
				suffix = "nd";
			else 
				if(dom == 3 || dom == 23)
					suffix = "rd";
				else
					suffix = "th";

		//concatenate the date string
		date = time.getDayOfWeek() + " " +  time.getMonth() + " " + dom + suffix + " " + time.getYear();
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
	 * <h2>setAnlges() method</h2>
	 * 
	 * <p>This method is a helper method that sets 
	 * the angles of the clock hands based on the
	 * current time.</p>
	 */
	private void setAngles()
	{
		//deal with hour case
		int hour = time.getHour();
		if(hour > 12)
			hour = hour -12;
		
		//compute angles
		hourAngle = Math.toRadians(hour/12.0 * 360);
		minuteAngle = Math.toRadians(time.getMinute()/60.0 * 360);
		secondAngle = Math.toRadians(time.getSecond()/60.0 * 360);
	}
	
	/**
	 * <h2>setOneColor() method</h2>
	 * 
	 * <p>This method will set the property of the
	 * clock of how many colors the clock hands will
	 * be and the corresponding analog display(if set).
	 * If it is set then the color displayed on digital
	 * will be the hour color. The clock hands will be 
	 * set to the color black.</p>
	 * 
	 * @param oneColor whether it should be one color
	 */
	public void setOneColor(boolean oneColor)
	{
		this.oneColor = oneColor;
	}
	
	/**
	 * <h2>setDigitalDisplay() method<h2>
	 * 
	 * <p>This method will determine if the clock also displays 
	 * a digital version of the time along with the analog display.
	 * This property is default to show the digital display.</p>
	 * 
	 * @param digitalDisplay whether analog time is shown
	 */
	public void setDigitalDisplay(boolean digitalDisplay)
	{
		this.digitalDisplay = digitalDisplay;
	}
	
	/**
	 * <h2>setPlain() method</h2>
	 * 
	 * <p>This method will determine if the clock has just 
	 * the analog, which is considered to be plain, or if it 
	 * includes all of the details such as time zone and date.
	 * This property is set to not plain on default.</p>
	 * 
	 * @param plain
	 */
	public void setPlain(boolean plain)
	{
		this.plain = plain;
	}
	
	/**
	 * <h2>toggleOneColor() method</h2>
	 * 
	 * <p>This method flips the property of the color
	 * of the analog display and hands opposite of 
	 * what it is. If the clock time and hands are already 
	 * one color then it will flip back to multiple colors.
	 * Otherwise the opposite effect will happen.</p>
	 */
	public void toggleOneColor()
	{
		oneColor = !oneColor;
	}
	
	/**
	 * <h2>toggleDigitalDisplay() method</h2>
	 * 
	 * <p>This method flips whether the digital
	 * display is on or off. If there is a digital
	 * display currently this method will disable it
	 * and vice versa.</p>
	 */
	public void toggleDigitalDisplay()
	{
		digitalDisplay = !digitalDisplay;
	}
	
	/**
	 * <h2>togglePlain() method</h2>
	 * 
	 * <p>This method flips whether the clock is dressed
	 * plainly or not. If the clock is currently plain
	 * this method will flip it and vice versa.</p>
	 */
	public void togglePlain()
	{
		plain = !plain;
	}
	
	/**
	 * <h2>setHourColor() method</h2>
	 * 
	 * <p>This method changes the color that the hours are
	 * displayed in. This will affect both the analog 
	 * display(if it is enabled) and the clock hands. If
	 * the one color property is enabled, this will be the 
	 * color of the analog display. The default will be blue.</p>
	 * 
	 * @param hourColor color for hours/ one color
	 */
	public void setHourColor(Color hourColor)
	{
		this.hourColor = hourColor;
	}
	
	/**
	 * <h2>setMinuteColor() method</h2>
	 * 
	 * <p>This method changes the color that the minutes are
	 * displayed in. This will affect both the analog 
	 * display(if it is enabled) and the clock hands unless
	 * the one color property is enabled.The default will be 
	 * green.</p>
	 * 
	 * @param minuteColor color for minutes
	 */
	public void setMinuteColor(Color minuteColor)
	{
		this.minuteColor = minuteColor;
	}
	
	/**
	 * <h2>setSecondColor() method</h2>
	 * 
	 * <p>This method changes the color that the seconds are 
	 * displayed in. This will affect both the analog 
	 * display(if it is enabled) and the clock hands unless
	 * the one color property is enabled.The default will be 
	 * red.</p>
	 * 
	 * @param secondColor color for minutes
	 */
	public void setSecondColor(Color secondColor)
	{
		this.secondColor = secondColor;
	}
}
