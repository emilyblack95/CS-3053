import org.eclipse.swt.widgets.Display;
import Controller.Controller; 
import Model.Model; 
import View.View;

/** 
 * Driver for Assignment 3 - HCI
 * 2/15/17
 * @author Emily Black
 * Mathematical Functions:
 * 1) Multiplication
 * 2) Division
 * 3) Addition
 * 4) Subtraction
 * 5) Decimal Numbers (PDF says it would be considered function)
 */

public class Driver 
{
	public static void main(String[] args) 
	{
		Model m = new Model();
		Controller c = new Controller(m);
		
		try {
			Display display = Display.getDefault();
			View shell = new View(m, c, display);
			shell.open();
			shell.layout();
			while (!shell.isDisposed()) {

	            if (!display.readAndDispatch())
	                display.sleep();
	        }
	        display.dispose();
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}