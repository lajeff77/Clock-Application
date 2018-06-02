/**
 * <h1>ResourceLoader class</h1>
 * 
 * <p>This class is used to load all the resources
 * for the application.</p>
 * 
 * <p>Created:5/24/18</p>
 * @version 6/1/18
 * 
 * @author Lauryn Jefferson
 */
package images;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ResourceLoader 
{
	//objects
	static ResourceLoader rl = new ResourceLoader();
	
	/**
	 * <h2>getImage() method</h2>
	 * 
	 * <p>This method loads an image in a way
	 * that is friendly to jars.</p>
	 * 
	 * @param filename path of image
	 * @return image loaded
	 */
	public static Image getImage(String filename)
	{
		return Toolkit.getDefaultToolkit().getImage(rl.getClass().getResource(filename));
	}
	
	/**
	 * <h2>getBufferedImage() method</h2>
	 * 
	 * <p>This method loads a buffered image in
	 * a way that is friendly to jars.</p>
	 * 
	 * @param filename path of image
	 * @return buffered image loaded
	 */
	public static BufferedImage getBufferedImage(String filename)
	{
		try 
		{
			return ImageIO.read(rl.getClass().getResourceAsStream(filename));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
}
