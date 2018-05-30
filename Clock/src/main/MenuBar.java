/**
 * <h1>MenuBar class<h1>
 * 
 * <p>This class handles the menu bar at the
 *  top of the window. It creates the bar and
 *  handles it's events.</p>
 *  
 * <p>Created:5/26/18</p>
 * @version 5/29/18
 *  
 * @author Lauryn Jefferson
 */
package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import states.LocalTimeState;
import states.StateManager;
import states.StopwatchState;
import states.TimerState;
import states.WorldTimeState;

public class MenuBar 
{
	//objects
	private static Font menuFont;
	private static String[] labels = {"Local Clock","World Clock","Timer", "Stopwatch","Exit"};
	
	//variables
	private static int menuWidth, menuHeight;
	private static int highlight;
	private static boolean hidden;
	
	/**
	 * <h2>MenuBar() Constructor</h2>
	 * 
	 * <p>This constructor sets up the menu bar
	 * to be used.</p>
	 */
	public MenuBar()
	{
		
		menuFont = new Font("Arial",1,18);
		highlight = -1;
		hidden = false;
		getMenuDimensions();
	}
	
	/**
	 * <h2>update() method</h2>
	 * 
	 * <p>This method will make sure the menu
	 * bar appears when needed.</p>
	 */
	public static void update()
	{
		//if it's hidden we don't want to check for anything
		if(!hidden)
		{
			
			
			//temps to check each spot
			int lastX = Window.getWidth()/2 - menuWidth/2;
			Graphics g = Window.getCanvas().getGraphics();
			g.setFont(menuFont);
			
			//temp for highlight
			boolean set = false;
			
			//traverse through each spot
			for(int i = 1; i <= labels.length; i++)
			{
				int offSet = 21 + g.getFontMetrics().stringWidth(labels[i-1]);
				if(MouseManager.getMouseX() > lastX && MouseManager.getMouseX() < lastX+offSet && MouseManager.getMouseY() > Window.getHeight() - (menuHeight+22)   && MouseManager.getMouseY() < Window.getHeight()-18 )
				{	
					if(MouseManager.isReleased())
						determineAction(i);//if clicked spot determine what action to take
					highlight = i-1;
					set = true;
				}
				lastX += offSet;//to next spot
			}

			//reset highlight
			if(!set && highlight != -1)
				highlight = -1;
		}
	}
	
	/**
	 * <h2>render() method</h2>
	 * 
	 * <p>This method will render the menu bar
	 * to screen with the given graphic object.</p>
	 * 
	 * @param g the Graphics object of the location to draw to
	 */
	public static void render(Graphics g)
	{
		//don't show if hidden
		if(!hidden)
		{
			//make the box with outline
			g.setFont(menuFont);
			g.setColor(Color.BLACK);
			g.fillRoundRect(Window.getWidth()/2 - (menuWidth+4)/2, Window.getHeight()- (menuHeight+22), menuWidth+4, menuHeight+4,40,40);
			g.setColor(Color.WHITE);
			g.fillRoundRect(Window.getWidth()/2 - menuWidth/2, Window.getHeight()- (menuHeight+20), menuWidth, menuHeight,40,40);

			//print all the labels
			int lastX = Window.getWidth()/2 - menuWidth/2;
			for(int i = 0; i < labels.length; i++)
			{
				//if hovering highlight it
				if(i == highlight)
					g.setColor(Color.GRAY);
				else 
					g.setColor(Color.BLACK);
				lastX += 10;
				g.drawString(labels[i], lastX, Window.getHeight() - menuHeight );
				lastX += 11 + g.getFontMetrics().stringWidth(labels[i]);
			}
		}
	}
	
	/**
	 * <h2>determineAction() method</h2>
	 * 
	 * <p>This helper method determines the action of
	 * the selected menu item when the user clicks on it.</p>
	 * 
	 * @param actionCode the number that decides which action to take
	 */
	private static void determineAction(int actionCode)
	{
		switch(actionCode)
		{
			//local time
			case 1:
				StateManager.changeState(new LocalTimeState());
				StateManager.update();
				break;
			//world time
			case 2:
				StateManager.changeState(new WorldTimeState());
				StateManager.update();
				break;
			//timer
			case 3:
				StateManager.changeState(new TimerState());
				StateManager.update();
				break;
			//stop watch
			case 4:
				StateManager.changeState(new StopwatchState());
				StateManager.update();
				break;
			//exit program
			case 5:
				System.exit(0);
				break;
			//don't do anything
			default:
				break;
		}
	}
	
	/**
	 * <h2>getMenuDimensions()</h2>
	 * 
	 * <p>This helper method sets up the dimensions for the
	 * menu bar. It determines the width and height of the bar.</p>
	 */
	private void getMenuDimensions()
	{
		menuWidth = 0;
		
		Graphics g = Window.getCanvas().getGraphics();
		g.setFont(menuFont);
		
		//figure out width of each label 
		for(int i = 0; i < labels.length; i++)
			menuWidth += 21 + g.getFontMetrics().stringWidth(labels[i]);
		menuHeight = g.getFontMetrics().getHeight()+ 10;

	}
	
	/**
	 * <h2>hide() method</h2>
	 * 
	 * <p>This method will hide the menu bar.</p>
	 */
	public static void hide()
	{
		hidden = true;
	}
	
	/**
	 * <h2>show() method</h2>
	 * 
	 * <p>This method will show the menu bar.</p>
	 */
	public static void show()
	{
		hidden = false;
	}
	
	/**
	 * <h2>toggleHidden() method</h2>
	 * 
	 * <p>This method will make the menu bar opposite
	 * visibility meaning if it is hidden the bar will 
	 * show and vice versa.</p>
	 */
	public static void toggleHidden()
	{
		hidden = ! hidden;
	}
}
