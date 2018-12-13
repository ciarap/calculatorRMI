/**
 * CLASS DETAILS
 * 
 * This class is the calculator client model.
 * As per MVC guidelines given in the resource provided in class, this model
 * holds data about the state of the calculator, and any functions that modifies
 * the data.
 *
 * Fields:
 *  - reg
 *  - decimalPointInNum
 *  - infix
 *  - postfix
 *  - opCount
 *  - clCount
 *  - delims
 *  
 *  
 * There are both public and private functions within this class.
 * 
 * Public Functions:
 * 	- All Getters and Setters
 *  - CalcClient (constructor)
 *  - reset
 *  - invalidOperatorPlacement
 *  - invalidNumberPlacement
 *  - validNegationPlacement
 *  - invalidDecimalPlacement
 *  - invalidFormat
 *  - infix_postfix
 *  - evaluatePostfixCalculation
 *  - increaseClCount
 *  - increaseOpCount
 *  - balancedBracketCount
 *  
 * Private Functions:
 *  - connectReg
 *  - isOperator
 *  - precedence
 *  
 * FEATURE DETAILS
 *  - Input Validation
 *  - Infix to Postfix conversion
 *  - Postfix equation evaluation
 *  - Store calculator state - infix entered, postfix, bracket count
 * 
 * @author Ciara Power - 20072488
 * 
 * @version 1.0
 * 
 * @since 03/12/2018
 * 
 */
package models;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Stack;

public class CalcClient {

	// **************************************************
	// Fields
	// **************************************************
	private CalculatorInterface reg;
	private boolean decimalPointInNum = false;
	private String infix = "";
	private String postfix = "";
	private int opCount = 0;
	private int clCount = 0;
	private String delims=" ";

	// **************************************************
	// Constructors
	// **************************************************

	/**
	 * This is the constructor for the CalcClient.
	 * A method is called to connect to the registry for future RMI method calls.
	 *  
	 * @return CalcClient object
	 * 
	 */
	public CalcClient() {
		connectReg();
	}

	// **************************************************
	// Private Functions
	// **************************************************

	/**
	 * This function connects to the registry for the Calculator, created by the server.
	 * 
	 * @return void
	 */
	private void connectReg() {
		try {
			reg = (CalculatorInterface) Naming.lookup("//"
					+ "localhost"
					+ "/Calculator");
			setReg(reg);
			System.out.println(reg.initialConnection());
		}
		catch (Exception e) {
			System.out.println("CalculatorClient exception: "
					+ e.getMessage());
		}
	}
	
	/**
	 * This function checks if the String passed in is an operator.
	 * 
	 * @param ch
	 * 
	 * @return true if ch is an operator
	 * @return false if ch is not an operator
	 */
	private boolean isOperator(String ch) {
		switch(ch){
		case "+": case "-": case "/": case "*": case "^": return true;
		default: return false;
		}
	}

	/**
	 * This function returns the precedence value for calculator input.
	 * 
	 * @param str
	 * 
	 * @return -1 for brackets
	 * @return 1 for - and +
	 * @return 2 for * and /
	 * @return 3 for ^
	 * @return 0 for everything else (numeric)
	 */
	private int precedence(String str) {
		switch(str) {
		case "(": case ")": return -1;
		case "-": case "+": return 1;
		case "*": case "/": return 2;
		case "^": return 3;
		default: return 0;
		}
	}

	// **************************************************
	// Public Functions
	// **************************************************
	
	/** 
	 * This function returns the reg field.
	 * 
	 * @return CalculatorInterface
	 */
	public CalculatorInterface getReg() {
		return reg;
	}

	/** 
	 * This function sets the reg field.
	 * 
	 * @param CalculatorInterface reg
	 * 
	 * @return void
	 * 
	 */
	public void setReg(CalculatorInterface reg) {
		this.reg = reg;
	}

	/** 
	 * This function returns the decimalPointInNum field.
	 * 
	 * @return boolean
	 */
	public boolean isDecimalPointInNum() {
		return decimalPointInNum;
	}

	/** 
	 * This function sets the decimalPointInNum field.
	 * 
	 * @param boolean decimalPointInNum
	 * 
	 * @return void
	 * 
	 */
	public void setDecimalPointInNum(boolean decimalPointInNum) {
		this.decimalPointInNum = decimalPointInNum;
	}

	/** 
	 * This function returns the infix field.
	 * 
	 * @return String
	 */
	public String getInfix() {
		return infix;
	}

	/** 
	 * This function sets the infix field.
	 * 
	 * @param String infix
	 * 
	 * @return void
	 * 
	 */
	public void setInfix(String infix) {
		this.infix = infix;
	}

	/** 
	 * This function returns the postfix field.
	 * 
	 * @return String
	 */
	public String getPostfix() {
		return postfix;
	}

	/** 
	 * This function sets the postfix field.
	 * 
	 * @param String postfix
	 * 
	 * @return void
	 * 
	 */
	public void setPostfix(String postfix) {
		this.postfix = postfix;
	}

	/** 
	 * This function returns the opCount field.
	 * 
	 * @return int
	 */
	public int getOpCount() {
		return opCount;
	}

	/** 
	 * This function sets the opCount field.
	 * 
	 * @param int opCount
	 * 
	 * @return void
	 * 
	 */
	public void setOpCount(int opCount) {
		this.opCount = opCount;
	}

	/** 
	 * This function returns the clCount field.
	 * 
	 * @return int
	 */
	public int getClCount() {
		return clCount;
	}

	/** 
	 * This function sets the clCount field.
	 * 
	 * @param int clCount
	 * 
	 * @return void
	 * 
	 */
	public void setClCount(int clCount) {
		this.clCount = clCount;
	}

	/**
	 * This function resets the calculator state stored in the model.
	 * 
	 * @return void
	 */
	public void reset() {
		infix = "";
		postfix = "";
		setDecimalPointInNum(false);
	}

	/**
	 * This function checks if the infix will be valid to add an operator to.
	 * 
	 * @return boolean
	 */
	public boolean invalidOperatorPlacement() {
		String eq = infix.trim();
		int sum_len = eq.length();
		if (sum_len==0 || isOperator(""+eq.charAt(sum_len-1)) && precedence(""+eq.charAt(sum_len-1))!=-1) {
			return true;
		}
		return false;
	}

	/**
	 * This function checks if the infix will be valid to add a number to.
	 * 
	 * @return boolean
	 */
	public boolean invalidNumberPlacement() {
		String eq = infix.trim();
		int sum_len = eq.length();
		if (sum_len==0) {
			return false;
		} else if (eq.charAt(sum_len-1)!=')') {
			return false;
		}
		return true;
	}

	/**
	 * This function checks if the infix will be valid to add a negation to.
	 * 
	 * @return boolean
	 */
	public boolean validNegationPlacement() {
		int sum_len = infix.length();
		if (sum_len==0 || infix.charAt(sum_len-1)==' ') {
			return true;
		}
		return false;
	}

	/**
	 * This function checks if the infix will be valid to add a decimal to.
	 * 
	 * @return boolean
	 */
	public boolean invalidDecimalPlacement() {
		String eq = infix.trim();
		int sum_len = eq.length();
		if (sum_len==0 || isOperator(""+eq.charAt(sum_len-1)) || decimalPointInNum || eq.charAt(sum_len-1)==')') {
			return true;
		}
		return false;
	}

	/**
	 * This function checks if the complete infix equation is valid.
	 * 
	 * @return boolean
	 */
	public boolean invalidFormat() {
		String eq = infix.trim();
		if (eq.length()==0) {
			return true;
		} else if (isOperator(""+eq.charAt(eq.length()-1)) || !balancedBracketCount() || infix.isEmpty()) {
			return true;
		}
		return false;
	}

	/**
	 * This function converts the current infix value to postfix.
	 * 
	 * @return void
	 */
	public void infix_postfix() {
		postfix = "";
		Stack<String> operators = new Stack<String>();
		String tokens[] = infix.split(delims);
		for (int i=0; i<tokens.length; i++) {
			String str = tokens[i];			
			if (str.equals("(")) {
				operators.push(str);
			} else if (str.equals(")")) {
				while (!operators.isEmpty() && !operators.peek().equals("(")) {
					postfix += operators.pop() +delims;
				}
				// Pop the last opening parenthesis
				if (!operators.isEmpty()) {
					operators.pop();
				}
			} else if (!isOperator(str)) {
				postfix += str+delims;
			} else {
				while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(str)) {
					String op = operators.pop();
					if (!op.equals("(")) // dont add the bracket to the postfix
						postfix += op+delims;
				}
				operators.push(str);
			}
		}
		// pop any remaining operator
		while (!operators.isEmpty())
			postfix += operators.pop()+delims;
	}

	/**
	 * This function evaluates the postfix function to give a result.
	 * RMI methods are called here to evaluate portions of the postfix.
	 * 
	 * @return double
	 */
	public double evaluatePostfixCalculation() 
	{ 
		Stack<Double> stack=new Stack<>(); 
		String tokens[] = postfix.trim().split(delims);
		for (int i=0; i<tokens.length; i++)
		{ 
			String c=tokens[i];
			if(!isOperator(c)) {
				stack.push( Double.parseDouble(c)); 
			} else
			{ 
				double num1 = stack.pop(); 
				double num2 = stack.pop(); 
				try {
					switch(c) 
					{ 
					case "+": 
						stack.push(reg.add(num1, num2));
						break; 

					case "-": 
						stack.push(reg.subtract(num2, num1)); 
						break; 

					case "/": 
						stack.push(reg.divide(num2, num1)); 
						break; 

					case "*": 
						stack.push(reg.multiply(num1, num2));  
						break; 

					case "^": 
						stack.push(reg.powerOf(num2, num1));  
						break; 
					} 
				} catch (RemoteException e) {
					System.out.println("Error: "+e.getMessage());
				} 
			} 
		} 
		return stack.pop();     
	}

	/**
	 * This function increases the opening bracket count.
	 * 
	 * @return void
	 */
	public void increaseOp() {
		opCount++;
	}

	/**
	 * This function increases the closing bracket count.
	 * 
	 * @return void
	 */
	public void increaseCl() {
		clCount++;
	}

	/**
	 * This function checks if the bracket count is balanced.
	 * 
	 * @return boolean
	 */
	public boolean balancedBracketCount() {
		return opCount==clCount;
	}

}
