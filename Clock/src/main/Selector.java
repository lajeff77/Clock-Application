/**
 * <h1>Selector class</h1>
 * 
 * <p>This class will take an array, or list of strings 
 * and make it into a drop down list at the given 
 * coordinates.</p>
 * 
 * <p>Created: 5/27/18</p>
 * @version 5/29/18
 * 
 * @author Lauryn Jefferson
 */
package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

public class Selector 
{
	//constants
	private final int MIN_SLIDER_HEIGHT = 5;
	
	//objects
	private String selection;
	private String[] list;
	private Font selectorFont;
	
	//variables
	private boolean selected, dropped;
	private int index;
	private int x,y;
	private int width,height;
	private int displayNum;
	private double jumpSize;
	private int sliderHeight;
	private int sliderY;
	
	/**
	 * <h2>Selector() constructor</h2>
	 * 
	 * <p>This constructor sets up the selector with
	 * a list of strings.</p>
	 * 
	 * @param list list of strings
	 */
	public Selector(List<String> list, int x, int y, int width, int height)
	{
		this((String[]) list.toArray(),x,y,width,height);
	}
	
	/**
	 * <h2>Selector() constructor</h2>
	 * 
	 * <p>This constructor sets up the selector with
	 * an array of strings.</p>
	 * 
	 * @param list array of strings
	 */
	public Selector(String[] list, int x, int y, int width, int height)
	{
		this.list = list;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		selectorFont = new Font("Arial", 1, 12);
		
		index = 0;
		selection = list[0];
		selected = false;
		dropped = false;
		
		displayNum = 3;
		setUpSlider();
		determineWidth();
	}
	
	/**
	 * <h2>update() method</h2>
	 * 
	 * <p>This method will update the selector
	 * to ensure that it displays the right options</p>
	 */
	public void update()
	{
		//if clicked
		if(MouseManager.isReleased())
		{
	
			//arrow clicked
			if(MouseManager.getMouseX() > x+width - 25 && MouseManager.getMouseX() < x+ width && MouseManager.getMouseY() > y && MouseManager.getMouseY()< y+ height)
				dropped = !dropped;
			
			if(dropped)
			{
				//check if selected and item
				for(int i = 0; i < displayNum; i++)
				{
					if(MouseManager.getMouseX() > x && MouseManager.getMouseX() < x+ width - height/2 && MouseManager.getMouseY() > y + height*(i+1) && MouseManager.getMouseY()< y+ height*(i+2))
					{
						selected = true;
						selection = list[index+i];
					}
				}
				
				//is its inside the scroll box
				if(MouseManager.getMouseX() > x+ width - height/2 && MouseManager.getMouseY() > y+height && MouseManager.getMouseX() < x+ width && MouseManager.getMouseY() < y+height + height*displayNum)
				{
					
					int change = MouseManager.getMouseY() - sliderY;
					sliderY = MouseManager.getMouseY();
					if(change/jumpSize != 0)
						index = (int)((sliderY - (height+y))/jumpSize);
					if(index > list.length-displayNum)
						index = list.length - displayNum;
					if(index < 0)
						index = 0;
				}
			}
		}
	}
	
	
	/**
	 * <h2>render() method</h2>
	 * 
	 * <p>This method renders the selector to the
	 * window of the given graphics object.</p>
	 * 
	 * @param g the Graphics object of the location to draw to
	 */
	public void render(Graphics g)
	{
		//top selection
		g.setFont(selectorFont);
		g.setColor(Color.GRAY);
		g.fillRect(x,y,width,height);
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(x+1,y+1,width-2,height-2);
		g.setColor(Color.BLACK);
		g.drawString(selection,x+2, y+2 + 12);
		
		Graphics2D g2 = (Graphics2D)g;
		g2.setStroke(new BasicStroke(3));
		
		//arrow
		g2.drawLine(x+width-20, y+10, x+width-15, y+15);
		g2.drawLine(x+width-10, y+10, x+width-15, y+15);
		
		//drop down
		if(dropped)
		{
			//display num amount of choices
			for(int i = 0; i < displayNum; i ++)
			{
				g.setColor(Color.GRAY);
				g.fillRect(x,height*(i+1) + y,width,height);
				g.setColor(Color.LIGHT_GRAY);
				g.fillRect(x+1,height*(i+1) + y+1,width-2,height-2);
				g.setColor(Color.BLACK);
				g.drawString(list[index+i],x+2, height*(i+1) + y+2*i + 12);
			}
			
			//scroll bar
			g.setColor(Color.GRAY);
			g.fillRect(x+width - height/2,y+height,height/2,height * displayNum);
			g.setColor(new Color(225,225,225));
			g.fillRect(x+width - height/2,sliderY,height/2,sliderHeight);
		}
	}
	
	/**
	 * <h2>setUpSlider() method</h2>
	 * 
	 * <p>This helper method sets up the slider
	 * bar and its dimensions.</p>
	 */
	private void setUpSlider()
	{
		//set slider height
		sliderHeight = (int)((displayNum/(double)(list.length))*(height*displayNum));
		
		if(sliderHeight < MIN_SLIDER_HEIGHT)
			sliderHeight = MIN_SLIDER_HEIGHT;
		
		//how many it will move per pixel
		jumpSize = (1.0/list.length)*(height*displayNum - sliderHeight);
			
		sliderY = y+height;
	}
	
	/**
	 * <h2>determineWidth() method</h2>
	 * 
	 * <p>This helper method determines the width
	 * of the selector based on the longest element
	 * in the given list</p>
	 */
	private void determineWidth()
	{
		Graphics g = Window.getCanvas().getGraphics();
		g.setFont(selectorFont);
		
		//find longest String and accommodate it
		for(int i = 0; i < list.length; i++)
			if(g.getFontMetrics().stringWidth(list[i]) + height/2 > width)
				width = g.getFontMetrics().stringWidth(list[i]) + height/2;
	
	}
	
	/**
	 * <h2>isSeleceted() method</h2>
	 * 
	 * <p>This method determines whether an element
	 * has been selected from the list.</p>
	 * 
	 * @return is something selected
	 */
	 public boolean isSelected()
	 {
		 if(selected)
		 {
			 selected = false;//set it back
			 return true;	
		 }
		 return false;
	 }
	 
	 /**
	  * <h2>getSelection() method</h2>
	  * 
	  * <p>This method returns the current
	  * item of selection.</p>
	  * 
	  * @return what the selection is
	  */
	 public String getSelection()
	 {
		 return selection;
	 }

	 /**
	  * <h2>isDropped() method</h2>
	  * 
	  * <p>This method signals whether the
	  * selector is dropped.<p>
	  * 
	  * @return whether its dropped
	  */
	public boolean isDropped() 
	{
		return dropped;
	}
	 
}
