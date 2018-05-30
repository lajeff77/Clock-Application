/**
 * <h1>MouseManager class</h1>
 * 
 * <p>This class manages all of the mouse
 * events.</p>
 * 
 * <p>Created: 5/26/18</p>
 * @version 5/29/18
 * 
 * @author Lauryn Jefferson
 * 
 */
package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseManager implements MouseListener, MouseMotionListener
{	
	//variables
	private static boolean pressed, clicked, released, dragging;
	private static int mouseX, mouseY;

	/**
	 * <h2>MouseManager() constructor</h2>
	 * 
	 * <p>This constructor sets up the mouse
	 * manager to be used.</p>
	 */
	public MouseManager()
	{
		mouseX = 0;
		mouseY = 0;

		pressed = false;
		clicked = false;
		released = false;
		dragging = false;
	}

	public static void update()
	{
		pressed = false;
		clicked = false;
		released = false;
		dragging = false;
	}
	
	
	
	/**
	 * <h2>mouseClicked method</h2>
	 * 
	 * <p>Whenever the mouse is clicked, this method
	 * handles the actions the program takes.</p>
	 * 
	 * @param e mouseClicked event
	 */
	@Override
	public void mouseClicked(MouseEvent e)
	{
		clicked = true;
	}

	/**
	 * <h2>mouseEntered method</h2>
	 * 
	 * <p>Whenever the mouse is entered, this method
	 * handles the actions the program takes.</p>
	 * 
	 * @param e mouseEntered event
	 */
	@Override
	public void mouseEntered(MouseEvent e)
	{
		// TODO Auto-generated method stub
	}

	/**
	 * <h2>mouseExited method</h2>
	 * 
	 * <p>Whenever the mouse is exited, this method
	 * handles the actions the program takes.</p>
	 * 
	 * @param e mouseExited event
	 */
	@Override
	public void mouseExited(MouseEvent e)
	{
		// TODO Auto-generated method stub
	}

	/**
	 * <h2>mousePressed method</h2>
	 * 
	 * <p>Whenever the mouse is pressed, this method
	 * handles the actions the program takes.</p>
	 * 
	 * @param e mousePressed event
	 */
	@Override
	public void mousePressed(MouseEvent e) 
	{
		pressed = true;
	}

	/**
	 * <h2>mouseReleased method</h2>
	 * 
	 * <p>Whenever the mouse is released, this method
	 * handles the actions the program takes.</p>
	 * 
	 * @param e mouseReleased event
	 */
	@Override
	public void mouseReleased(MouseEvent e)
	{
		pressed = false;
		clicked = false;
		released = true;
	}

	/**
	 * <h2>getMouseX() method</h2>
	 * 
	 * <p>This method gets the x position 
	 * of the mouse pointer</p>
	 * 
	 * @return mouse x coordinate
	 */
	public static int getMouseX()
	{
		return mouseX;
	}

	/**
	 * <h2>getMouseY() method</h2>
	 * 
	 * <p>This method gets the y position 
	 * of the mouse pointer</p>
	 * 
	 * @return mouse y coordinate
	 */
	public static int getMouseY()
	{
		return mouseY;
	}

	/**
	 * <h2>mouseDraggged method</h2>
	 * 
	 * <p>Whenever the mouse is dragged, this method
	 * handles the actions the program takes.</p>
	 * 
	 * @param e mouseDragged event
	 */
	@Override
	public void mouseDragged(MouseEvent e) 
	{
		dragging = true;
	}

	/**
	 * <h2>mouseMoved method</h2>
	 * 
	 * <p>Whenever the mouse is moved, this method
	 * handles the actions the program takes.</p>
	 * 
	 * @param e mouseMovedevent
	 */
	@Override
	public void mouseMoved(MouseEvent e) 
	{
		mouseX = e.getX();
		mouseY = e.getY();	
	}

	/**
	 * <h2>isClicked() method</h2>
	 * 
	 * <p>This method returns true if
	 * the mouse is considered to be clicking.</p>
	 * 
	 * @return is mouse clicked
	 */
	public static boolean isClicked()
	{
		return clicked;
	}

	/**
	 * <h2>isPressed() method</h2>
	 * 
	 * <p>This method returns true if
	 * the mouse is considered to be pressing.</p>
	 * 
	 * @return is mouse pressed
	 */
	public static boolean isPressed()
	{
		return pressed;
	}
	
	/**
	 * <h2>isReleased() method</h2>
	 *
	 * 
	 * <p>This method returns true if
	 * the mouse is considered to be released.</p>
	 * 
	 * @return is mouse released
	 */
	public static boolean isReleased()
	{
		return released;
	}
	
	/**
	 * <h2>isDragging() method</h2>
	 *
	 * 
	 * <p>This method returns true if
	 * the mouse is considered to be dragging.</p>
	 * 
	 * @return is mouse dragging
	 */
	public static boolean isDragging()
	{
		return dragging;
	}
	

	/**
	 * <h2>setClicked() method</h2>
	 * 
	 * <p>This method sets the state of the mouse
	 * being clicked.</p>
	 * 
	 * @param clicked
	 */
	public static void setClicked(boolean clicked)
	{
		MouseManager.clicked = clicked;
	}

	/**
	 * <h2>setPressed() method</h2>
	 * 
	 * <p>This method sets the state of the mouse
	 * being pressed.</p>
	 * 
	 * @param pressed
	 */
	public static void setPressed(boolean pressed)
	{
		MouseManager.pressed = pressed;
	}
}

