package Controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import Model.Model;

/** 
 * Controller for Assignment 2 - HCI
 * 2/15/17
 * @author Emily Black
 * Navigation Ways:
 * 1) Buttons
 * 2) ComboBox
 * 3) Keybindings (Control Left/Right arrow)
 * 4) Click on images (Left/Right mouse button)
 * 5) Slideshow
 */

public class Controller implements ActionListener, ComponentListener, MouseListener
{

	private final Model model;
	private Timer timer; /* Timer for slideshow */
	@SuppressWarnings({ "rawtypes", "unused" })
	private JComboBox comboBox;
	
	public Controller( final Model model )
	{
		this.model = model;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		/* Help Button Clicked */
		if((((Component) e.getSource()).getName()).equals("help"))
		{
			JOptionPane.showMessageDialog(((Component) e.getSource()).getParent(), "Click the import button at the top right to import a directory and get started." + '\n'
					+ "The 5 different navigation techniques include:" + '\n' + '\n' 
					+ "1) Buttons" + '\n' + "2) ComboBox" + '\n' + "3) Keybindings (Control Left/Right Arrow)" + '\n' + 
					"4) Click on Image (Left/Right Click)" + '\n' + "5) Slideshow (Click to Toggle ON/OFF)");
		}
		/* Left Button Clicked */
		else if((((Component) e.getSource()).getName()).equals("left"))
		{
			/* Left mouse button */
			if(model.getFilteredImages() != null)
			{
				if(model.getPointer() != 0)
				{
					model.setPointer(model.getPointer()-1);
					model.notifyListeners();
				}
				/* If we have reached the far left */
				else if(model.getPointer() == 0)
				{
					model.setPointer(model.getFilteredImages().length-1);
					model.notifyListeners();
				}
			}
		}
		/* Left Keybind Pressed */
		else if((((Component) e.getSource()).getName()).equals("nextKeybind"))
		{
			/* Left mouse button */
			if(model.getFilteredImages() != null)
			{
				if(model.getPointer() != 0)
				{
					model.setPointer(model.getPointer()-1);
					model.notifyListeners();
				}
				/* If we have reached the far left */
				else if(model.getPointer() == 0)
				{
					model.setPointer(model.getFilteredImages().length-1);
					model.notifyListeners();
				}
			}
		}
		/* Right Button Clicked */
		else if((((Component) e.getSource()).getName()).equals("right"))
		{
			/* Right mouse button */
			if(model.getFilteredImages() != null)
			{
				/* If we haven't reached the far right */
				if(model.getPointer() != model.getFilteredImages().length-1)
				{
					model.setPointer(model.getPointer()+1);
					model.notifyListeners();
				}
				/* If we have reached the far right */
				else if(model.getPointer() == model.getFilteredImages().length-1)
				{
					model.setPointer(0);
					model.notifyListeners();
				}
			}
		}
		/* Right Keybind Pressed */
		else if((((Component) e.getSource()).getName()).equals("prevKeybind"))
		{
			/* Right mouse button */
			if(model.getFilteredImages() != null)
			{
				/* If we haven't reached the far right */
				if(model.getPointer() != model.getFilteredImages().length-1)
				{
					model.setPointer(model.getPointer()+1);
					model.notifyListeners();
				}
				/* If we have reached the far right */
				else if(model.getPointer() == model.getFilteredImages().length-1)
				{
					model.setPointer(0);
					model.notifyListeners();
				}
			}
		}
		/* Slideshow Button Clicked */
		else if((((Component) e.getSource()).getName()).equals("slideshow"))
		{	
			if(model.getSlideShowToggle() == false && model.getFilteredImages() != null)
			{
				 timer = new Timer(1000, new ActionListener() {
			            @Override
			            public void actionPerformed(ActionEvent e) {
			                /* If we haven't reached the far right */
							if(model.getPointer() != model.getFilteredImages().length-1)
							{
								model.setPointer(model.getPointer()+1);
								model.notifyListeners();
							}
							/* If we have reached the far right */
							else if(model.getPointer() == model.getFilteredImages().length-1)
							{
								model.setPointer(0);
								model.notifyListeners(); 
							}
			            }
			        });
			        timer.start();
			        model.setSlideShowToggle(true);
			}
			else if(model.getSlideShowToggle() == true)
			{
				timer.stop();
				model.setSlideShowToggle(false);
			}
		}
		else if((((Component) e.getSource()).getName()).equals("comboBox"))
		{
			@SuppressWarnings("rawtypes")
			String imageName = (String) ((JComboBox) e.getSource()).getSelectedItem();
	        int result = model.containsFile(imageName);
	        if(result != -1)
	        {
	        	model.setPointer(result);
	        }
	        model.notifyListeners();
		}
		/* Import Button Clicked */
		else if((((Component) e.getSource()).getName()).equals("import"))
		{
			JFileChooser importImage = new JFileChooser();
			importImage.setBounds(100, 100, 600, 475);
			
			importImage.setCurrentDirectory(new java.io.File(".")); /* Starts user off where they are at */
			importImage.setAcceptAllFileFilterUsed(false); /* Gets rid of all files option */
			importImage.setDialogTitle("Emily's Awesome Image Viewer");						
			importImage.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			
			int status = importImage.showOpenDialog(null);
			if (status == JFileChooser.APPROVE_OPTION) 
			{
				File[] images = importImage.getSelectedFile().listFiles();
				int counter = 0; /* Number of images in directory */
				for(File x: images)
				{
					if (!x.isDirectory()) {
						if(x.getName().toLowerCase().endsWith(".jpg") || x.getName().toLowerCase().endsWith(".png") || x.getName().toLowerCase().endsWith(".gif"))
						{
							counter++;
						}
					}
				}
				
				/* Size of how many images there are in the directory */
				model.setFilteredImagesSize(counter);
				int i = 0; /* Cannot be greater than counter */
				for(File x: images)
				{
					if(!x.isDirectory())
					{
						if(x.getName().toLowerCase().endsWith(".jpg") || x.getName().toLowerCase().endsWith(".png") || x.getName().toLowerCase().endsWith(".gif"))
						{
							model.setSpecificElementFilteredImages(i, x.getPath());
							i++;
						}
					}
				}
				model.notifyListeners();
			}
			else if(status == JFileChooser.CANCEL_OPTION)
			{
				importImage.cancelSelection();
			}
		}
	}
	
	@Override
	public void componentResized(ComponentEvent e)
	{
		if(model.getFilteredImages() != null)
		{
			model.notifyListeners(); //resizes imagelabel
		}
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		int buttonClicked = e.getButton();
		
		/* Left mouse button */
		if(buttonClicked == 1 && (model.getFilteredImages() != null))
		{
			if(model.getPointer() != 0)
			{
				model.setPointer(model.getPointer()-1);
				model.notifyListeners();
			}
			/* If we have reached the far left */
			else if(model.getPointer() == 0)
			{
				model.setPointer(model.getFilteredImages().length-1);
				model.notifyListeners();
			}
		}
		
		/* Right mouse button */
		else if(buttonClicked == 3 && (model.getFilteredImages() != null))
		{
			/* If we haven't reached the far right */
			if(model.getPointer() != model.getFilteredImages().length-1)
			{
				model.setPointer(model.getPointer()+1);
				model.notifyListeners();
			}
			/* If we have reached the far right */
			else if(model.getPointer() == model.getFilteredImages().length-1)
			{
				model.setPointer(0);
				model.notifyListeners();
			}
		}
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
