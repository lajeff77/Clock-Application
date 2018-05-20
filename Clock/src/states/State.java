/**
 * <h1>State class</h1>
 * 
 * <p>This class defines what it means to be a state 
 * and creates an outline for all states to follow. All 
 * states must inherit from this class.</p>
 * 
 * <p>Created: 5/19/18</p>
 * @version 5/20/18
 * 
 * @author Lauryn Jefferson
 */
package states;

import java.awt.Graphics;

public interface State 
{
	
	/**
	 * <h2>update() method</h2>
	 * 
	 * <p>This method maintains that a state must be 
	 * updated to properly run the process of being 
	 * looped and rendered to the window.</p>
	 */
	public abstract void update();
	
	/**
	 * <h2>render() method</h2>
	 * 
	 * <p>This method maintains that a state must be
	 * rendered with the graphics object of the location
	 * in which we wish to draw to.</p>
	 * 
	 * @param g Graphics object of drawing location
	 */
	public abstract void render(Graphics g);
}
