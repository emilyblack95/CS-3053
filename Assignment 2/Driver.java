import Controller.Controller; 
import Model.Model; 
import View.View;

/** 
 * Driver for Assignment 2 - HCI
 * 2/15/17
 * @author Emily Black
 * Navigation Ways:
 * 1) Buttons
 * 2) ComboBox
 * 3) Keybindings (Control Left/Right arrow)
 * 4) Click on images (Left/Right mouse button)
 * 5) Slideshow
 */

public class Driver 
{
	public static void main(String[] args) 
	{
		Model m = new Model();
		Controller c = new Controller(m);
		View v = new View(m, c);
		v.setVisible(true);
	}
}
