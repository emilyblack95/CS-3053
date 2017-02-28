package Model;

import java.util.ArrayList;
import java.util.List;

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

public class Model 
{
	private List<Listener> listeners = new ArrayList<Listener>();
	private String expression;
	
	public Model()
	{
		expression = "";
	}
	
	public String getExpression()
	{
		return expression;
	}
	
	public void setExpression(String input)
	{
		expression = input;
		notifyListeners();
	}
	
	public void addToExpression(String input)
	{
		expression = expression + input;
		notifyListeners();
	}
	
	public void clearExpression()
	{
		expression = "";
		notifyListeners();
	}
	
	public void calculateExpr()
	{
		String num1 = ""; //Leftmost number from operator
		String num2 = ""; //Rightmost number from operator
		String expr = ""; //Holds full expression
		double x = 0; //Holds num1 parsed into an int
		double y = 0; //Holds num2 parsed into an int
		double result = 0;
		
		//If the label is empty OR ends with operator
		if(expression.isEmpty() || expression.endsWith("*") || expression.endsWith("/") || expression.endsWith("+") || expression.endsWith("-") || expression.endsWith("."))
		{
			Exception e = new Exception("ERROR: Cannot compute: " + "'" + expression + "'" + ". " + "Cannot end expression in operator or null value.");
			e.printStackTrace();
		}
		//If the label is NOT empty
		else 
		{	
			while(expression.contains("*"))
			{
				int index = expression.indexOf("*");
				//Look left of operator
				for(int j = index-1; j >= 0; j--)
				{
					//While we haven't reached another operator
					if(expression.charAt(j) != '*' && expression.charAt(j) != '/' && expression.charAt(j) != '+' && expression.charAt(j) != '-')
					{
						num1 += expression.charAt(j);
					}
					else if(expression.charAt(j) == '*' || expression.charAt(j) == '/' || expression.charAt(j) == '+' || expression.charAt(j) == '-')
					{
						break;
					}
				}
				//Look right of operator
				for(int k = index+1; k < expression.length(); k++)
				{
					//While we haven't reached another operator
					if(expression.charAt(k) != '*' && expression.charAt(k) != '/' && expression.charAt(k) != '+' && expression.charAt(k) != '-')
					{
						num2 += expression.charAt(k);
					}
					else if(expression.charAt(k) == '*' || expression.charAt(k) == '/' || expression.charAt(k) == '+' || expression.charAt(k) == '-')
					{
						break;
					}
				}
				num1 = new StringBuilder(num1).reverse().toString();  //We reverse num1 because we read it left to right, not right to left
				x = Double.parseDouble(num1);
				y = Double.parseDouble(num2);
				result = x*y; //result contains result of operator
				expr = num1 + "*" + num2;
				expression = expression.replace(expr, Double.toString(result));
				num1 = "";
				num2 = "";
			}
			while(expression.contains("/"))
			{
				int index = expression.indexOf("/");
				//Look left of operator
				for(int j = index-1; j >= 0; j--)
				{
					//While we haven't reached another operator
					if(expression.charAt(j) != '*' && expression.charAt(j) != '/' && expression.charAt(j) != '+' && expression.charAt(j) != '-')
					{
						num1 += expression.charAt(j);
					}
					else if(expression.charAt(j) == '*' || expression.charAt(j) == '/' || expression.charAt(j) == '+' || expression.charAt(j) == '-')
					{
						break;
					}
				}
				//Look right of operator
				for(int k = index+1; k < expression.length(); k++)
				{
					//While we haven't reached another operator
					if(expression.charAt(k) != '*' && expression.charAt(k) != '/' && expression.charAt(k) != '+' && expression.charAt(k) != '-')
					{
						num2 += expression.charAt(k);
					}
					else if(expression.charAt(k) == '*' || expression.charAt(k) == '/' || expression.charAt(k) == '+' || expression.charAt(k) == '-')
					{
						break;
					}
				}
				num1 = new StringBuilder(num1).reverse().toString();  //We reverse num1 because we read it left to right, not right to left
				x = Double.parseDouble(num1);
				y = Double.parseDouble(num2);
				result = x/y; //result contains result of operator
				expr = num1 + "/" + num2;
				expression = expression.replace(expr, Double.toString(result));
				num1 = "";
				num2 = "";
			}
			while(expression.contains("+"))
			{
				int index = expression.indexOf("+");
				//Look left of operator
				for(int j = index-1; j >= 0; j--)
				{
					//While we haven't reached another operator
					if(expression.charAt(j) != '*' && expression.charAt(j) != '/' && expression.charAt(j) != '+' && expression.charAt(j) != '-')
					{
						num1 += expression.charAt(j);
					}
					else if(expression.charAt(j) == '*' || expression.charAt(j) == '/' || expression.charAt(j) == '+' || expression.charAt(j) == '-')
					{
						break;
					}
				}
				//Look right of operator
				for(int k = index+1; k < expression.length(); k++)
				{
					//While we haven't reached another operator
					if(expression.charAt(k) != '*' && expression.charAt(k) != '/' && expression.charAt(k) != '+' && expression.charAt(k) != '-')
					{
						num2 += expression.charAt(k);
					}
					else if(expression.charAt(k) == '*' || expression.charAt(k) == '/' || expression.charAt(k) == '+' || expression.charAt(k) == '-')
					{
						break;
					}
				}
				num1 = new StringBuilder(num1).reverse().toString();  //We reverse num1 because we read it left to right, not right to left
				x = Double.parseDouble(num1);
				y = Double.parseDouble(num2);
				result = x+y; //result contains result of operator
				expr = num1 + "+" + num2;
				expression = expression.replace(expr, Double.toString(result));
				num1 = "";
				num2 = "";
			}
			//While it does not start with - and contains atleast one -
			while(expression.contains("-") && !expression.startsWith("-"))
			{
				int index = expression.lastIndexOf("-");
				//Look left of operator
				for(int j = index-1; j >= 0; j--)
				{
					//While we haven't reached another operator
					if(expression.charAt(j) != '*' && expression.charAt(j) != '/' && expression.charAt(j) != '+' && expression.charAt(j) != '-')
					{
						num1 += expression.charAt(j);
					}
					else if(expression.charAt(j) == '*' || expression.charAt(j) == '/' || expression.charAt(j) == '+' || expression.charAt(j) == '-')
					{
						break;
					}
				}
				//Look right of operator
				for(int k = index+1; k < expression.length(); k++)
				{
					//While we haven't reached another operator
					if(expression.charAt(k) != '*' && expression.charAt(k) != '/' && expression.charAt(k) != '+' && expression.charAt(k) != '-')
					{
						num2 += expression.charAt(k);
					}
					else if(expression.charAt(k) == '*' || expression.charAt(k) == '/' || expression.charAt(k) == '+' || expression.charAt(k) == '-')
					{
						break;
					}
				}
				num1 = new StringBuilder(num1).reverse().toString();  //We reverse num1 because we read it left to right, not right to left
				x = Double.parseDouble(num1);
				y = Double.parseDouble(num2);
				result = x-y; //result contains result of operator
				expr = num1 + "-" + num2;
				expression = expression.replace(expr, Double.toString(result));
				num1 = "";
				num2 = "";
			}
			//If it contains more than one - and starts with -
			while(expression.indexOf("-") != expression.lastIndexOf("-") && expression.startsWith("-"))
			{
				int index = expression.lastIndexOf("-");
				//Look left of operator
				for(int j = index-1; j >= 0; j--)
				{
					//While we haven't reached another operator
					if(expression.charAt(j) != '*' && expression.charAt(j) != '/' && expression.charAt(j) != '+' && expression.charAt(j) != '-')
					{
						num1 += expression.charAt(j);
					}
					else if(expression.charAt(j) == '*' || expression.charAt(j) == '/' || expression.charAt(j) == '+' || expression.charAt(j) == '-')
					{
						break;
					}
				}
				//Look right of operator
				for(int k = index+1; k < expression.length(); k++)
				{
					//While we haven't reached another operator
					if(expression.charAt(k) != '*' && expression.charAt(k) != '/' && expression.charAt(k) != '+' && expression.charAt(k) != '-')
					{
						num2 += expression.charAt(k);
					}
					else if(expression.charAt(k) == '*' || expression.charAt(k) == '/' || expression.charAt(k) == '+' || expression.charAt(k) == '-')
					{
						break;
					}
				}
				num1 = new StringBuilder(num1).reverse().toString();  //We reverse num1 because we read it left to right, not right to left
				x = -x;
				x = Double.parseDouble(num1);
				y = Double.parseDouble(num2);
				result = x-y; //result contains result of operator
				expr = num1 + "-" + num2;
				expression = expression.replace(expr, Double.toString(result));
				num1 = "";
				num2 = "";
			}
			//If the user just enters a random number, give them back that random number
			if((expression.startsWith("-") || !expression.contains("-")) && !(expression.contains("*") && expression.contains("/") && expression.contains("+")))
			{
				result = Double.parseDouble(getExpression());
			}
		}
		expression = Double.toString(result);
		notifyListeners();
	}
	
	public void addListener(final Listener listener)
	{
		listeners.add( listener );
	}
	
	public void notifyListeners()
	{
		for(final Listener listener : listeners) 
		{
			listener.updated();
		}
	}
}
