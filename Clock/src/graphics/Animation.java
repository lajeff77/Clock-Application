/**
 * <h1>Animation class</h1>
 * 
 * <p>This class holds a group of frames
 * and animates them frame.</p>
 * 
 * <p>Created: 5/30/18</p>
 * @version 6/1/18
 * 
 * @author Lauryn Jefferson
 */
package graphics;

import java.awt.image.BufferedImage;

public class Animation 
{
	//objects
	private BufferedImage[] frames;
	
	//variables
	private int speed, index;
	private long lastTime, timer;

	/**
	 * <h2>Animation constructor</h2>
	 * 
	 * <p>This constructor requires a speed and an
	 * amount of frames to be set up.</p>
	 * 
	 * @param speed speed at which the frames will be animated in terms of milliseconds 
	 * @param frames images to be animated
	 */
	public Animation(int speed, BufferedImage[] frames)
	{
		this.speed = speed;
		this.frames = frames;
		index = 0;
		timer = 0;
		lastTime = System.currentTimeMillis();//timestamp
	}

	/**
	 * <h2>update() method</h2>
	 * 
	 * <p>This method keeps track of what frame the animation is on. 
	 * this method should be called when the animation is playing.</p>
	 */
	public void update()
	{
		//update times
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();

		if(timer > speed)
		{
			//change the frame
			index++;
			timer = 0;
			if(index >= frames.length)
				index = 0;
		}
	}



	/**
	 * <h2>getCurrentFrame() method</h2>
	 * 
	 * <p>This method returns the BufferedImage that
	 * the animation should currently be displaying.</p>
	 * 
	 * @return current frame
	 */
	public BufferedImage getCurrentFrame()
	{
		return frames[index];
	}

}

