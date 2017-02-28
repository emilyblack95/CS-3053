package View;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import Controller.Controller;
import Model.Listener;
import Model.Model;

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

public class View extends Shell implements Listener 
{
	private static Model model;
	Label lblNumberdisplay = new Label(this, SWT.BORDER | SWT.WRAP);
	
	/**
	 * Create the shell.
	 * @param display
	 * @param i 
	 */
	@SuppressWarnings("static-access")
	public View(Model model, Controller controller, final Display display)
	{
		super(display, SWT.SHELL_TRIM & (~SWT.RESIZE)); //makes shell NON-resizable
		setLayout(new FormLayout());
		
		this.model = model;
		model.addListener(this);
		
		/* Calculator display */
		FontData fontData = lblNumberdisplay.getFont().getFontData()[0];
		Font font = new Font(display, new FontData(fontData.getName(), 15, SWT.BOLD));
		lblNumberdisplay.setFont(font);
		lblNumberdisplay.setBackground(this.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		FormData fd_lblNumberdisplay = new FormData();
		fd_lblNumberdisplay.right = new FormAttachment(100, -10);
		fd_lblNumberdisplay.left = new FormAttachment(0, 7);
		fd_lblNumberdisplay.top = new FormAttachment(0, 10);
		fd_lblNumberdisplay.bottom = new FormAttachment(0, 70);
		lblNumberdisplay.setLayoutData(fd_lblNumberdisplay);
		
		/* Number 7 button */
		Button button7 = new Button(this, SWT.NONE);
		button7.addSelectionListener(controller);
		FormData fd_button7 = new FormData();
		fd_button7.top = new FormAttachment(lblNumberdisplay, 6);
		fd_button7.left = new FormAttachment(0, 7);
		button7.setLayoutData(fd_button7);
		button7.setText("7");
		
		/* Number 8 button */
		Button button8 = new Button(this, SWT.NONE);
		fd_button7.right = new FormAttachment(100, -328);
		button8.addSelectionListener(controller);
		FormData fd_button8 = new FormData();
		fd_button8.left = new FormAttachment(button7, 7);
		fd_button8.top = new FormAttachment(lblNumberdisplay, 6);
		button8.setLayoutData(fd_button8);
		button8.setText("8");
		
		/* Number 9 button */
		Button button9 = new Button(this, SWT.NONE);
		fd_button8.right = new FormAttachment(button9, -7);
		button9.addSelectionListener(controller);
		FormData fd_button9 = new FormData();
		fd_button9.top = new FormAttachment(lblNumberdisplay, 6);
		fd_button9.left = new FormAttachment(0, 219);
		button9.setLayoutData(fd_button9);
		button9.setText("9");
		
		/* Division operator */
		Button buttonDiv = new Button(this, SWT.NONE);
		buttonDiv.addSelectionListener(controller);
		FormData fd_buttonDiv = new FormData();
		fd_buttonDiv.right = new FormAttachment(100, -10);
		buttonDiv.setLayoutData(fd_buttonDiv);
		buttonDiv.setText("/");
		
		/* Number 4 button */
		Button button4 = new Button(this, SWT.NONE);
		fd_button7.bottom = new FormAttachment(button4, -2);
		button4.addSelectionListener(controller);
		FormData fd_button4 = new FormData();
		fd_button4.left = new FormAttachment(0, 7);
		fd_button4.top = new FormAttachment(0, 107);
		button4.setLayoutData(fd_button4);
		button4.setText("4");
		
		/* Number 5 button */
		Button button5 = new Button(this, SWT.NONE);
		fd_button4.right = new FormAttachment(button5, -7);
		fd_button8.bottom = new FormAttachment(button5, -2);
		button5.addSelectionListener(controller);
		fd_button4.bottom = new FormAttachment(100, -70);
		FormData fd_button5 = new FormData();
		fd_button5.top = new FormAttachment(0, 107);
		fd_button5.left = new FormAttachment(0, 113);
		button5.setLayoutData(fd_button5);
		button5.setText("5");
		
		/* Number 6 button */
		Button button6 = new Button(this, SWT.NONE);
		fd_button5.right = new FormAttachment(button6, -7);
		fd_button9.bottom = new FormAttachment(button6, -2);
		button6.addSelectionListener(controller);
		FormData fd_button6 = new FormData();
		fd_button6.left = new FormAttachment(0, 219);
		fd_button6.top = new FormAttachment(0, 107);
		button6.setLayoutData(fd_button6);
		button6.setText("6");
		
		/* Multiplication operator */
		Button buttonMulti = new Button(this, SWT.NONE);
		fd_button6.right = new FormAttachment(buttonMulti, -7);
		fd_buttonDiv.left = new FormAttachment(buttonMulti, 13);
		buttonMulti.addSelectionListener(controller);
		FormData fd_buttonMulti = new FormData();
		fd_buttonMulti.right = new FormAttachment(100, -66);
		fd_buttonMulti.left = new FormAttachment(0, 325);
		buttonMulti.setLayoutData(fd_buttonMulti);
		buttonMulti.setText("*");
		
		/* Number 1 button */
		Button button1 = new Button(this, SWT.NONE);
		button1.addSelectionListener(controller);
		FormData fd_button1 = new FormData();
		fd_button1.top = new FormAttachment(button4, 2);
		fd_button1.left = new FormAttachment(0, 7);
		button1.setLayoutData(fd_button1);
		button1.setText("1");
		
		/* Number 2 button */
		Button button2 = new Button(this, SWT.NONE);
		fd_button1.right = new FormAttachment(button2, -7);
		fd_button5.bottom = new FormAttachment(button2, -2);
		button2.addSelectionListener(controller);
		FormData fd_button2 = new FormData();
		fd_button2.left = new FormAttachment(0, 113);
		fd_button2.top = new FormAttachment(0, 138);
		button2.setLayoutData(fd_button2);
		button2.setText("2");
		
		/* Number 3 button */
		Button button3 = new Button(this, SWT.NONE);
		fd_button2.right = new FormAttachment(button3, -7);
		button3.addSelectionListener(controller);
		fd_button6.bottom = new FormAttachment(100, -70);
		FormData fd_button3 = new FormData();
		fd_button3.left = new FormAttachment(0, 219);
		fd_button3.top = new FormAttachment(button6, 2);
		button3.setLayoutData(fd_button3);
		button3.setText("3");
		
		/* Subtraction operator */
		Button buttonSub = new Button(this, SWT.NONE);
		fd_buttonDiv.bottom = new FormAttachment(buttonSub, -2);
		buttonSub.addSelectionListener(controller);
		FormData fd_buttonSub = new FormData();
		fd_buttonSub.right = new FormAttachment(100, -10);
		fd_buttonSub.top = new FormAttachment(0, 138);
		buttonSub.setLayoutData(fd_buttonSub);
		buttonSub.setText("-");
		
		/* Number 0 button */
		Button button0 = new Button(this, SWT.NONE);
		fd_button1.bottom = new FormAttachment(button0, -6);
		button0.addSelectionListener(controller);
		FormData fd_button0 = new FormData();
		fd_button0.top = new FormAttachment(0, 173);
		fd_button0.bottom = new FormAttachment(100, -4);
		fd_button0.left = new FormAttachment(0, 7);
		button0.setLayoutData(fd_button0);
		button0.setText("0");
		
		/* Decimal button */
		Button buttonDecimal = new Button(this, SWT.NONE);
		fd_button2.bottom = new FormAttachment(buttonDecimal, -6);
		fd_button0.right = new FormAttachment(buttonDecimal, -7);
		buttonDecimal.addSelectionListener(controller);
		FormData fd_buttonDecimal = new FormData();
		fd_buttonDecimal.left = new FormAttachment(0, 113);
		fd_buttonDecimal.top = new FormAttachment(0, 173);
		fd_buttonDecimal.bottom = new FormAttachment(100, -4);
		buttonDecimal.setLayoutData(fd_buttonDecimal);
		buttonDecimal.setText(".");
		
		/* Addition operator */
		Button buttonAdd = new Button(this, SWT.NONE);
		fd_buttonMulti.bottom = new FormAttachment(buttonAdd, -2);
		fd_buttonSub.left = new FormAttachment(buttonAdd, 13);
		fd_button3.right = new FormAttachment(buttonAdd, -7);
		buttonAdd.addSelectionListener(controller);
		FormData fd_buttonAdd = new FormData();
		fd_buttonAdd.top = new FormAttachment(0, 138);
		fd_buttonAdd.right = new FormAttachment(100, -66);
		fd_buttonAdd.left = new FormAttachment(0, 325);
		buttonAdd.setLayoutData(fd_buttonAdd);
		buttonAdd.setText("+");
		
		/* Equals operator */
		Button buttonCalculate = new Button(this, SWT.NONE);
		fd_buttonAdd.bottom = new FormAttachment(buttonCalculate, -6);
		fd_buttonSub.bottom = new FormAttachment(100, -39);
		buttonCalculate.addSelectionListener(controller);
		FormData fd_buttonCalculate = new FormData();
		fd_buttonCalculate.top = new FormAttachment(buttonSub, 6);
		fd_buttonCalculate.bottom = new FormAttachment(100, -4);
		fd_buttonCalculate.right = new FormAttachment(100, -10);
		buttonCalculate.setLayoutData(fd_buttonCalculate);
		buttonCalculate.setText("=");
		
		/* Clear button */
		Button buttonClear = new Button(this, SWT.NONE);
		fd_buttonMulti.top = new FormAttachment(buttonClear, 2);
		fd_buttonDiv.top = new FormAttachment(buttonClear, 2);
		fd_button9.right = new FormAttachment(100, -116);
		buttonClear.addSelectionListener(controller);
		FormData fd_btnClear = new FormData();
		fd_btnClear.bottom = new FormAttachment(100, -101);
		fd_btnClear.right = new FormAttachment(100, -10);
		fd_btnClear.left = new FormAttachment(button9, 7);
		fd_btnClear.top = new FormAttachment(lblNumberdisplay, 6);
		buttonClear.setLayoutData(fd_btnClear);
		buttonClear.setText("Clear");
		
		/* Negate button */
		Button buttonNegate = new Button(this, SWT.NONE);
		fd_button3.bottom = new FormAttachment(buttonNegate, -6);
		fd_buttonCalculate.left = new FormAttachment(buttonNegate, 7);
		fd_buttonDecimal.right = new FormAttachment(buttonNegate, -7);
		buttonNegate.addSelectionListener(controller);
		FormData fd_buttonNegate = new FormData();
		fd_buttonNegate.top = new FormAttachment(0, 173);
		fd_buttonNegate.bottom = new FormAttachment(100, -4);
		fd_buttonNegate.left = new FormAttachment(0, 219);
		fd_buttonNegate.right = new FormAttachment(100, -116);
		buttonNegate.setLayoutData(fd_buttonNegate);
		buttonNegate.setText("\u00B1");
		
		setText("Emily's Awesome Calculator");
		setSize(440, 235);
		
	}
	
	@Override
	public void updated() {
		lblNumberdisplay.setText(model.getExpression());
	}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
