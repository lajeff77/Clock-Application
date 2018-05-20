/**
 * <h1>StateManager class</h1>
 * 
 * <p>This class manages what state the application is in. 
 * This makes the application have the ability to switch between
 * screens. Upon changing the state the manager will only update 
 * and render the current state.</p>
 * 
 * <p>Created: 5/19/18</p>
 * @version 5/20/18
 * 
 * @author Lauryn Jefferson
 */
package states;

import java.awt.Graphics;

public class StateManager 
{
	//objects
	private static State currentState;//keeps track of what state we are in
	
	/**
	 * <h2>StateManager() constructor</h2>
	 * 
	 * <p>This default constructor sets the current
	 * state to null. Be sure to set the current state
	 * before rendering and updating or nothing will 
	 * result.</p>
	 */
	public StateManager()
	{
		this(null);
	}
	
	/**
	 * <h2>StateManager() constructor</h2>
	 * 
	 * <p>This constructor sets up the StateManager with 
	 * the first state that it will update and render.</p>
	 * 
	 * @param currentState
	 */
	public StateManager(State currentState)
	{
		StateManager.currentState = currentState;
	}
	
	/**
	 * <h2>changeState() method</h2>
	 * 
	 * <p>This method changes the current state
	 * of the manager to the given state. This will
	 * change what is updated and rendered in the 
	 * Application loop.</p>
	 * 
	 * @param newState the new state to set 
	 */
	public static void changeState(State newState)
	{
		currentState = newState;
	}
	
	/**
	 * <h2>update() method</h2>
	 * 
	 * <p>This method updates the current states
	 * conditions.</p>
	 */
	public static void update()
	{
		currentState.update();
	}
	
	/**
	 * <h2>render() method</h2>
	 * 
	 * <p>This method renders the current state
	 * given the graphics object of the window we
	 * want to render to. </p>
	 * 
	 * @param g Graphics object of drawing location
	 */
	public static void render(Graphics g)
	{
		currentState.render(g);
	}
	
	/**
	 * <h2>getState() method</h2>
	 * 
	 * <p>This method returns the current state that
	 * the application is in.</p>
	 *
	 * @return current state
	 */
	public static State getState()
	{
		return currentState;
	}
}
