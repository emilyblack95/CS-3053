package Controller;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;

import Model.Model;

/** 
 * Driver for Assignment 3 - HCI
 * 2/19/17
 * @author Emily Black
 * Mathematical Functions:
 * 1) Multiplication
 * 2) Division
 * 3) Addition
 * 4) Subtraction
 * 5) Decimal Numbers (PDF says it would be considered function)
 */

public class Controller implements SelectionListener 
{
	private final Model model;

	public Controller( final Model model )
	{
		this.model = model;
	}
	
	@Override
	public void widgetDefaultSelected(SelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void widgetSelected(SelectionEvent arg0) {
		/* Button 0 */
		if(((Button) arg0.getSource()).getText() == "0")
		{
			model.addToExpression("0");
		}
		/* Button 1 */
		else if(((Button) arg0.getSource()).getText() == "1")
		{
			model.addToExpression("1");
		}
		/* Button 2 */
		else if(((Button) arg0.getSource()).getText() == "2")
		{
			model.addToExpression("2");
		}
		/* Button 3 */
		else if(((Button) arg0.getSource()).getText() == "3")
		{
			model.addToExpression("3");
		}
		/* Button 4 */
		else if(((Button) arg0.getSource()).getText() == "4")
		{
			model.addToExpression("4");
		}
		/* Button 5 */
		else if(((Button) arg0.getSource()).getText() == "5")
		{
			model.addToExpression("5");
		}
		/* Button 6 */
		else if(((Button) arg0.getSource()).getText() == "6")
		{
			model.addToExpression("6");
		}
		/* Button 7 */
		else if(((Button) arg0.getSource()).getText() == "7")
		{
			model.addToExpression("7");
		}
		/* Button 8 */
		else if(((Button) arg0.getSource()).getText() == "8")
		{
			model.addToExpression("8");
		}
		/* Button 9 */
		else if(((Button) arg0.getSource()).getText() == "9")
		{
			model.addToExpression("9");
		}
		/* Decimal Button */
		else if(((Button) arg0.getSource()).getText() == ".")
		{
			//If the label is empty
			if(model.getExpression().isEmpty())
			{
				model.setExpression("0.");
			}
			//If it's not empty
			else
			{
				//If it contains an operator
				if((model.getExpression().contains("*") || model.getExpression().contains("/") || model.getExpression().contains("+") || model.getExpression().contains("-")) || model.getExpression().contains("."))
				{
					//If the label does NOT end with an operator
					if(!(model.getExpression().endsWith("*") || model.getExpression().endsWith("/") || model.getExpression().endsWith("+") || model.getExpression().endsWith("-") || model.getExpression().endsWith(".")))
					{
						//If last index of . is smaller than last index of operator
						if(model.getExpression().lastIndexOf(".") < model.getExpression().lastIndexOf("*") || model.getExpression().lastIndexOf(".") < model.getExpression().lastIndexOf("/") || model.getExpression().lastIndexOf(".") < model.getExpression().lastIndexOf("+") || model.getExpression().lastIndexOf(".") < model.getExpression().lastIndexOf("-"))
						{
							model.setExpression(model.getExpression() + ".");
						}
						else if(!(model.getExpression().contains("*") && model.getExpression().contains("/") && model.getExpression().contains("+") && model.getExpression().contains("-")) && model.getExpression().contains("."))
						{
							model.setExpression(model.getExpression() + ".");
						}
					}
					//If the label is NOT empty & ends with an operator
					else if((model.getExpression().endsWith("*") || model.getExpression().endsWith("/") || model.getExpression().endsWith("+") || model.getExpression().endsWith("-")))
					{
						model.setExpression(model.getExpression() + "0.");
					}
				}
				//If it doesnt contain an operator
				else
				{
					model.setExpression(model.getExpression() + ".");
				}
			}
		}
		/* Negate Button */
		else if(((Button) arg0.getSource()).getText() == "\u00B1")
		{
			//If the label is NOT empty
			if(!model.getExpression().equals(null))
			{
				//If label already contains negative sign, remove it
				if(model.getExpression().startsWith("-"))
				{
					model.setExpression(model.getExpression().substring(1));
				}
				//If label doesn't contain negative sign, add it
				else if(!(model.getExpression().startsWith("-")))
				{
					model.setExpression("-" + model.getExpression());
				}
			}
			else if(model.getExpression().equals(null))
			{
				model.setExpression("-");
			}
		}
		/* Clear Button */
		else if(((Button) arg0.getSource()).getText() == "Clear")
		{
			model.clearExpression();
		}
		/* Multiplication Button */
		else if(((Button) arg0.getSource()).getText() == "*")
		{
			//If the label is NOT empty
			if(!(model.getExpression().isEmpty()))
			{
				if(!(model.getExpression().endsWith("*") || model.getExpression().endsWith("/") || model.getExpression().endsWith("+") || model.getExpression().endsWith("-") || model.getExpression().endsWith(".")))
				{
					model.setExpression(model.getExpression() + "*");
				}
			}
		}
		/* Division Button */
		else if(((Button) arg0.getSource()).getText() == "/")
		{
			//If the label is NOT empty
			if(!(model.getExpression().isEmpty()))
			{
				if(!(model.getExpression().endsWith("*") || model.getExpression().endsWith("/") || model.getExpression().endsWith("+") || model.getExpression().endsWith("-") || model.getExpression().endsWith(".")))
				{
					model.setExpression(model.getExpression() + "/");
				}
			}
		}
		/* Addition Button */
		else if(((Button) arg0.getSource()).getText() == "+")
		{
			//If the label is NOT empty
			if(!(model.getExpression().isEmpty()))
			{
				if(!(model.getExpression().equals(null)) && !(model.getExpression().endsWith("*") || model.getExpression().endsWith("/") || model.getExpression().endsWith("+") || model.getExpression().endsWith("-") || model.getExpression().endsWith(".")))
				{
					model.setExpression(model.getExpression() + "+");
				}
			}
		}
		/* Subtraction Button */
		else if(((Button) arg0.getSource()).getText() == "-")
		{
			//If the label is NOT empty
			if(!(model.getExpression().isEmpty()))
			{
				if(!(model.getExpression().equals(null)) && !(model.getExpression().endsWith("*") || model.getExpression().endsWith("/") || model.getExpression().endsWith("+") || model.getExpression().endsWith("-") || model.getExpression().endsWith(".")))
				{
					model.setExpression(model.getExpression() + "-");
				}
			}
		}
		/* Equals Button */
		else if(((Button) arg0.getSource()).getText() == "=")
		{
			model.calculateExpr();
		}	
	}
}
