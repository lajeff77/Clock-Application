/**
 * <h1>StopwatchState class</h1>
 * 
 * <p>This state will serve as as stopwatch
 * in the application.</p>
 * 
 * <p>Created 5/20/18</p>
 * @version 6/1/18
 * 
 * @author Lauryn Jefferson
 */
package states;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import graphics.Animation;
import graphics.Stopwatch;
import images.ResourceLoader;

public class StopwatchState implements State 
{
	//objects
	private Stopwatch stopwatch;
	private Image background;
	private Animation road;
	
	/**
	 * <h2>StopwatchState() constructor</h2>
	 * 
	 * <p>This constructor sets up the stopwatch state.</p>
	 */
	public StopwatchState()
	{
		stopwatch = new Stopwatch();
		setUpAnimation();
	}
	
	/**
	 * <h2>update() method</h2>
	 * 
	 * <p>This method will update the stopwatch 
	 * to keep it in time.</p>
	 */
	@Override
	public void update() 
	{
		stopwatch.update();
		
		if(!stopwatch.isStopped())
			road.update();
		background = road.getCurrentFrame();
	}

	/**
	 * <h2>render() method</h2>
	 * 
	 * <p>This method will render the stopwatch
	 * to screen with the given graphic object.</p>
	 * 
	 * @param g the Graphics object of the location to draw to
	 */
	@Override
	public void render(Graphics g) 
	{
		g.drawImage(background, 0, 0, null);
		stopwatch.render(g);
	}
	
	/**
	 * <h2>setUpAnimation() method</h2>
	 * 
	 * <p>This helper method loads all of the frames
	 * of the animation and makes the animation object
	 * for the background.</p>
	 */
	private void setUpAnimation()
	{
		BufferedImage[] images = new BufferedImage[5];
		images[0] = ResourceLoader.getBufferedImage("/images/road1.png");
		images[1] = ResourceLoader.getBufferedImage("/images/road2.png");
		images[2] = ResourceLoader.getBufferedImage("/images/road3.png");
		images[3] = ResourceLoader.getBufferedImage("/images/road4.png");
		images[4] = ResourceLoader.getBufferedImage("/images/road5.png");
		road = new Animation(100, images);
	}

}
