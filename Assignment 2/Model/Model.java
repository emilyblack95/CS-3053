package Model;

import java.util.ArrayList;
import java.util.List;

/** 
 * Model for Assignment 2 - HCI
 * 2/15/17
 * @author Emily
 * Navigation Ways:
 * 1) Buttons
 * 2) ComboBox
 * 3) Keybindings (Control Left/Right arrow)
 * 4) Click on images (Left/Right mouse button)
 * 5) Slideshow
 */

public class Model 
{
	
	private String[] filteredImages; /* Images filtered from directory */
	private int pointer; /* Used to index filteredImages */
	private List<Listener> listeners = new ArrayList<Listener>();
	private boolean slideShowOn;
	
	public Model() 
	{
		filteredImages = null;
		pointer = 0;
		slideShowOn = false;
	}
	
	public void setFilteredImages(String[] filteredImages)
	{
		this.filteredImages = filteredImages;
		notifyListeners();
	}
	
	public void setSpecificElementFilteredImages(int input, String input1)
	{
		filteredImages[input] = input1;
		notifyListeners();
	}

	public void setPointer(int pointer)
	{
		this.pointer = pointer;
		notifyListeners();
	}
	
	public void setSlideShowToggle(boolean input)
	{
		slideShowOn = input;
		notifyListeners();
	}
	
	public void setFilteredImagesSize(int input)
	{
		filteredImages = new String[input];
	}
	
	public boolean getSlideShowToggle()
	{
		return slideShowOn;
	}
	
	public int containsFile(String input)
	{
		if(filteredImages != null)
		{
			for(int i = 0; i < filteredImages.length; i++)
			{
				if(filteredImages[i].equals(input))
				{
					return i;
				}
			}
		}
		return -1;  //returns -1 if images cannot be found or if filteredImages is null
	}

	public String[] getFilteredImages()
	{
		return filteredImages;
	}
	
	public String getFilteredImage(int index)
	{
		return filteredImages[index];
	}

	public int getPointer()
	{
		return pointer;
	}
	
	public void addListener( final Listener listener )
	{
		listeners.add( listener );
	}
	
	public void notifyListeners()
	{
		for( final Listener listener : listeners ) 
		{
			listener.updated();
		}
	}
}
