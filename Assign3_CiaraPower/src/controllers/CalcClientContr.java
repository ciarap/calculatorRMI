/**
 * CLASS DETAILS
 * 
 * This class is the calculator client controller.
 * As per MVC guidelines given in the resource provided in class, this controller acts
 * only as a link between the model and view.
 * This class updates the model data, and makes use of model calculation functions,
 * and updates the view accordingly. 
 * The Listener class for the view buttons is implemented here also. 
 * This allows for reusability with other MVC components.
 *
 * Fields:
 *  - view
 *  - model
 *  - delims
 *  
 *  
 * There are both public and private functions within this class.
 * 
 * Public Functions:
 * 	- All Getters and Setters
 *  - CalcClientContr (constructor)
 *  
 * Private Classes:
 *  - CalculatorListener
 *  
 * 
 * @author Ciara Power - 20072488
 * 
 * @version 1.0
 * 
 * @since 03/12/2018
 * 
 */
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.CalcClient;
import views.CalcClientView;

public class CalcClientContr {

	// **************************************************
	// Fields
	// **************************************************
	private CalcClientView view;
	private CalcClient model;
	private String delims=" ";

	// **************************************************
	// Constructors
	// **************************************************

	/**
	 * This is the constructor for the CalcClientContr.
	 * Within this, the global view and model fields are set.
	 * A view method is called which adds button listeners for the calculator view.
	 * 
	 * @param CalcClientView view
	 * @param CalcClient model
	 * 
	 * @return CalcClientContr object
	 * 
	 */
	public CalcClientContr (CalcClientView view, CalcClient model) {

		this.view = view;
		this.model = model;

		// Tell the View that when ever buttons
		// are clicked to execute the actionPerformed method
		// in the CalculatorListener inner class
		this.view.addCalculatorListener(new CalculatorListener());
	}

	// **************************************************
	// Public Functions
	// **************************************************

	/** 
	 * This function returns the view field.
	 * 
	 * @return CalcClientView
	 */
	public CalcClientView getView() {
		return view;
	}

	/** 
	 * This function sets the view field.
	 * 
	 * @param CalcClientView view
	 * 
	 * @return void
	 * 
	 */
	public void setView(CalcClientView view) {
		this.view = view;
	}

	/** 
	 * This function returns the model field.
	 * 
	 * @return CalcClient
	 */
	public CalcClient getModel() {
		return model;
	}

	/** 
	 * This function sets the model field.
	 * 
	 * @param CalcClient model
	 * 
	 * @return void
	 * 
	 */
	public void setModel(CalcClient model) {
		this.model = model;
	}

	// **************************************************
	// Private Classes
	// **************************************************
	
	/**
	 * This class represents the CalculatorListener model.
	 * 
	 * The ActionListener interface is implemented by this class.
	 * 
	 * This class handles actions such as buttons pressed on the calculator GUI.
	 *  
	 * Public Functions:
	 *  - actionPerformed
	 *
	 * @author Ciara Power - 20072488
	 * 
	 * @version 1.0
	 * 
	 * @since 19/11/18
	 *
	 */
	private class CalculatorListener implements ActionListener {

		/**
		 * This function deals with an action event such as a button pressed.
		 * 
		 * @param e
		 * 
		 * @return void
		 * 
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
			case "=":
				// Get the equation from the text field and set model infix
				model.setInfix(view.getEquationInput().trim());
				if (model.invalidFormat()) {
					view.displayError("Cannot end equation with operator, or without closing all brackets, and equation cannot be empty!");
					return;
				}
				model.infix_postfix(); // convert the infix to postfix
				view.addMessage("\n\nEquation: "+ model.getInfix()+"\nResult: "+model.evaluatePostfixCalculation()); 
				view.resetInput();
				model.reset();
				break;
			case "C":
				view.resetInput();
				model.reset();
				break;
			case "+":
			case "*":
			case "/":
			case "^":
				if (model.invalidOperatorPlacement()) {
					view.displayError("Incorrect operator placement!");
					return;
				}
				view.appendEquation(delims+e.getActionCommand()+delims);
				model.setDecimalPointInNum(false); // reset decimal point status, operator added here completes the previous number
				break;
			case "-":
				if (model.validNegationPlacement()) { // for negative numbers
					view.appendEquation("-");
				} else if (model.invalidOperatorPlacement()) { // check subtraction placement is valid
					view.displayError("Incorrect operator placement!");
					return;
				} else view.appendEquation(" - ");
				model.setDecimalPointInNum(false); 
				break;
			case "(":
				if (!model.invalidOperatorPlacement()) {
					view.displayError("Incorrect bracket placement!");
					return;
				}
				view.appendEquation("( ");
				model.increaseOp(); // counter increase in model for opening bracket count
				model.setDecimalPointInNum(false);
				break;
			case ")":
				if (model.invalidOperatorPlacement() || model.balancedBracketCount()) {
					view.displayError("Incorrect bracket placement!");
					return;
				}
				view.appendEquation(" )");
				model.increaseCl(); // counter increase in model for closing bracket count
				model.setDecimalPointInNum(false);
				break;
			case ".":
				if (model.invalidDecimalPlacement()) {
					view.displayError("Incorrect decimal placement!");
					return;
				}
				view.appendEquation(".");
				model.setDecimalPointInNum(true);
				break;
			default: // if none of the above cases match, the button pressed was a numeric button
				if (model.invalidNumberPlacement()) {
					view.displayError("Incorrect number placement!");
					return;
				}
				view.appendEquation(e.getActionCommand());
				break;
			}
			model.setInfix(view.getEquationInput()); // update the model infix
		}
	}
}
