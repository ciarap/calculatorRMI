/**
 * CLASS DETAILS
 * 
 * This class is the calculator server controller.
 * As per MVC guidelines given in the resource provided in class, this controller 
 * updates the server view, and contains the RMI functions.
 *
 * Fields:
 *  - serialVersionUID
 *  - view
 *  
 *  
 * There are both public and private functions within this class.
 * 
 * Public Functions:
 * 	- All Getters and Setters
 *  - CalcServerContr (constructor)
 *  - getClient
 *  - initialConnection (RMI)
 *  - add (RMI)
 *  - subtract (RMI)
 *  - divide (RMI)
 *  - multiply (RMI)
 *  - powerOf (RMI)
 *  
 * Private Functions:
 *  - displayCalc
 *  - displayIp
 *  - displayDetails
 *  - createReg
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

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;

import models.CalculatorInterface;
import views.CalcServerView;

public class CalcServerContr extends UnicastRemoteObject implements CalculatorInterface {

	// **************************************************
	// Fields
	// **************************************************
	private static final long serialVersionUID = 1L;
	private CalcServerView view;

	// **************************************************
	// Constructors
	// **************************************************

	/**
	 * This is the constructor for the CalcServerContr.
	 * Within this, the global view field is set.
	 * A method is called to create the Registry, and a message is
	 * sent to the view for display.
	 * 
	 * @param CalcServerView view
	 * 
	 * @return CalcServerContr object
	 * 
	 */
	public CalcServerContr(CalcServerView view) throws RemoteException {
		this.view = view;
		createReg();
		view.appendMessage("Calculator bound in registry");
	}

	// **************************************************
	// Private Functions
	// **************************************************

	/**
	 * This function updates the view with details of the calculation completed.
	 * 
	 * @param num1
	 * @param num2
	 * @param op
	 * 
	 * @return void
	 */
	private void displayCalc(double num1, double num2, String op, double result) {
		view.appendMessage("\nOperand 1: "+num1+"\nOperand 2: "+ num2+"\nOperator: "+op+"\nResult: " + result);
	}

	/**
	 * This function updates the view with client details, when an RMI method is invoked. 
	 * 
	 * @return void
	 */
	private void displayIP() {
		try{
			view.appendMessage("\n\nMessage from: " + getClient()); 
		}catch(Exception e){}
	}

	/**
	 * This function displays all details when a client requests a calculation to be performed.
	 * 
	 * @param num1
	 * @param num2
	 * @param operator
	 * @param result
	 * 
	 * @return void
	 */
	private void displayDetails(double num1, double num2, String operator, double result) {
		displayIP();
		displayCalc(num1, num2, operator, result);
	}

	/**
	 * This functions creates the registry for the calculator.
	 * 
	 * @return void 
	 */
	private void createReg() {
		try {
			// Bind this object instance to the name "Calculator".
			Registry registry = LocateRegistry.createRegistry( 1099 );
			registry.rebind( "Calculator", this);
		}
		catch (Exception e) {
			view.displayError("CalculatorServer error: " + e.getMessage());
			System.exit(0); // exit if the server cannot create the registry ( may occur if server already running)
		}
	}

	// **************************************************
	// Public Functions
	// **************************************************

	/** 
	 * This function returns the view field.
	 * 
	 * @return CalcServerView
	 */
	public CalcServerView getView() {
		return view;
	}

	/** 
	 * This function sets the view field.
	 * 
	 * @param CalcServerView view
	 * 
	 * @return void
	 * 
	 */
	public void setView(CalcServerView view) {
		this.view = view;
	}

	/**
	 * This function is an RMI function inherited from the CalculatorInterface.
	 * It adds two numbers passed in, and returns the result.
	 * A method is called which will display calculation details on the server view window.
	 * 
	 * @param double num1
	 * @param double num2
	 * 
	 * @return double
	 */
	@Override
	public double add(double num1, double num2) {
		double result = num1+num2;
		displayDetails(num1, num2, "+", result);
		return result;
	}

	/**
	 * This function is an RMI function inherited from the CalculatorInterface.
	 * It subtracts two numbers passed in, and returns the result.
	 * A method is called which will display calculation details on the server view window.
	 * 
	 * @param double num1
	 * @param double num2
	 * 
	 * @return double
	 */
	@Override
	public double subtract(double num1, double num2) {
		double result = num1-num2;
		displayDetails(num1, num2, "-", result);
		return result;
	}

	/**
	 * This function is an RMI function inherited from the CalculatorInterface.
	 * It multiplies two numbers passed in, and returns the result.
	 * A method is called which will display calculation details on the server view window.
	 * 
	 * @param double num1
	 * @param double num2
	 * 
	 * @return double
	 */
	@Override
	public double multiply(double num1, double num2) {
		double result = num1*num2;
		displayDetails(num1, num2, "*", result);
		return result;
	}

	/**
	 * This function is an RMI function inherited from the CalculatorInterface.
	 * It divides two numbers passed in, and returns the result.
	 * A method is called which will display calculation details on the server view window.
	 * 
	 * @param double num1
	 * @param double num2
	 * 
	 * @return double
	 */
	@Override
	public double divide(double num1, double num2) {
		double result = num1/num2;
		displayDetails(num1, num2, "/", result);
		return result;
	}

	/**
	 * This function is an RMI function inherited from the CalculatorInterface.
	 * It calculates one number to the power of another, and returns the result.
	 * A method is called which will display calculation details on the server view window.
	 * 
	 * @param double num1
	 * @param double num2
	 * 
	 * @return double
	 */
	@Override
	public double powerOf(double num1, double num2) {
		double result = (double) Math.pow(num1, num2);
		displayDetails(num1, num2, "^", result);
		return result;
	}

	/**
	 * This function is an RMI function inherited from the CalculatorInterface.
	 * It updates the server view to notify that a client has connected.
	 * A String is returned to the client to notify them that they are now connected successfully.
	 * 
	 * @return String
	 */
	@Override
	public String initialConnection() throws RemoteException {
		displayIP();
		view.appendMessage(": Client Successfully Connected!");
		return "\nClient successfully connected to Server!";
	}

	/**
	 * This function returns the client IP address connected to the server.
	 * 
	 * @return String
	 */
	public String getClient() {
		try {
			return getClientHost();
		} catch (ServerNotActiveException e) {
			System.out.println("\nError: "+e.getMessage());
			return "";
		}
	}
}
