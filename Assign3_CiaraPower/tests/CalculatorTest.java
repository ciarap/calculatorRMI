/**
 * CLASS DETAILS
 * 
 * This class is the calculator tester.
 * Various functions are tested from the CalcClient and CalcServerContr.
 * 
 *
 * Fields:
 *  - server
 *  - client
 *  - posnum1
 *  - negnum1
 *  - posdecnum1
 *  - negdecnum1
 *  - infix1
 *  - infix2
 *  - postfix1
 *  - postfix2
 *  - result1
 *  - result2
 *  
 * There are many functions in this class.
 * 
 * Before Class functions:
 *  - OnSetUp
 * 
 * Test functions:
 *  - testAdd
 *  - testSubtract
 *  - testDivide
 *  - testMultiply
 *  - testPowerOf
 *  - testInfixToPostfix
 *  - testEvaluatePostfix
 *  - testInvalidOperatorPlacement
 *  - testInvalidNumberPlacement
 *  - testValidNegationPlacement
 *  - testInvalidDecimalPlacement
 *  - testInvalidFormat
 * 
 * 
 * @author Ciara Power - 20072488
 * 
 * @version 1.0
 * 
 * @since 03/12/2018
 * 
 */
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.rmi.RemoteException;

import org.junit.Test;

import controllers.CalcServerContr;
import models.CalcClient;

import org.junit.BeforeClass;

import views.CalcServerView;

public class CalculatorTest {

	// **************************************************
	// Fields
	// **************************************************
	private static CalcServerContr server;
	private static CalcClient client;
	double posnum1=2;
	double negnum1=-4;
	double posdecnum1=2.2;
	double negdecnum1=-4.4;
	String infix1 ="6 ^ 5 * 3 + ( 5 - 3 / 2 ) + 99.6";
	String infix2 ="9 ^ 3 / 4.6 * ( 6 ^ 2 - 5.5 )";
	String postfix1 = "6 5 ^ 3 * 5 3 2 / - + 99.6 + ";
	String postfix2 = "9 3 ^ 4.6 / 6 2 ^ 5.5 - * ";
	double result1= 23431.1;
	double result2 = 4833.58;

	// **************************************************
	// Public Functions
	// **************************************************

	/**
	 * This functions sets up the objects to be tested.
	 */
	@BeforeClass
	public static void OnSetUp() {
		try {
			server = new CalcServerContr(new CalcServerView());
			client = new CalcClient();
		} catch (RemoteException e) {
			System.out.println("\nError: "+e.getMessage());
		}
	}

	/**
	 * This function tests the add method with various combinations of positive,
	 * negative, decimal or whole numbers.
	 */
	@Test 
	public void testAdd() {
		assertEquals(4.0, server.add(posnum1, posnum1), 0.1);
		assertEquals(-2.0, server.add(posnum1, negnum1), 0.1);
		assertEquals(-8.0, server.add(negnum1, negnum1), 0.1);
		assertEquals(4.4, server.add(posdecnum1, posdecnum1), 0.1);
		assertEquals(-2.2, server.add(posdecnum1, negdecnum1), 0.1);
		assertEquals(-8.8, server.add(negdecnum1, negdecnum1), 0.1);
		assertEquals(4.2, server.add(posnum1, posdecnum1), 0.1);
		assertEquals(-2.4, server.add(posnum1, negdecnum1), 0.1);
		assertEquals(-1.8, server.add(negnum1, posdecnum1), 0.1);
		assertEquals(-8.4, server.add(negnum1, negdecnum1), 0.1);
	}

	/**
	 * This function tests the subtract method with various combinations of positive,
	 * negative, decimal or whole numbers.
	 */
	@Test 
	public void testSubtract() {
		assertEquals(0, server.subtract(posnum1, posnum1), 0.1);
		assertEquals(6.0, server.subtract(posnum1, negnum1), 0.1);
		assertEquals(0, server.subtract(negnum1, negnum1), 0.1);
		assertEquals(0, server.subtract(posdecnum1, posdecnum1), 0.1);
		assertEquals(6.6, server.subtract(posdecnum1, negdecnum1), 0.1);
		assertEquals(0, server.subtract(negdecnum1, negdecnum1), 0.1);
		assertEquals(-0.2, server.subtract(posnum1, posdecnum1), 0.1);
		assertEquals(6.4, server.subtract(posnum1, negdecnum1), 0.1);
		assertEquals(-6.2, server.subtract(negnum1, posdecnum1), 0.1);
		assertEquals(0.4, server.subtract(negnum1, negdecnum1), 0.1);
	}

	/**
	 * This function tests the multiply method with various combinations of positive,
	 * negative, decimal or whole numbers.
	 */
	@Test 
	public void testMultiply() {
		assertEquals(4.0, server.multiply(posnum1, posnum1), 0.1);
		assertEquals(-8.0, server.multiply(posnum1, negnum1), 0.1);
		assertEquals(16.0, server.multiply(negnum1, negnum1), 0.1);
		assertEquals(4.84, server.multiply(posdecnum1, posdecnum1), 0.1);
		assertEquals(-9.68, server.multiply(posdecnum1, negdecnum1), 0.1);
		assertEquals(19.36, server.multiply(negdecnum1, negdecnum1), 0.1);
		assertEquals(4.4, server.multiply(posnum1, posdecnum1), 0.1);
		assertEquals(-8.8, server.multiply(posnum1, negdecnum1), 0.1);
		assertEquals(-8.8, server.multiply(negnum1, posdecnum1), 0.1);
		assertEquals(17.6, server.multiply(negnum1, negdecnum1), 0.1);
	}

	/**
	 * This function tests the divide method with various combinations of positive,
	 * negative, decimal or whole numbers.
	 */
	@Test 
	public void testDivide() {
		assertEquals(1.0, server.divide(posnum1, posnum1), 0.1);
		assertEquals(-0.5, server.divide(posnum1, negnum1), 0.1);
		assertEquals(1.0, server.divide(negnum1, negnum1), 0.1);
		assertEquals(1.0, server.divide(posdecnum1, posdecnum1), 0.1);
		assertEquals(-0.5, server.divide(posdecnum1, negdecnum1), 0.1);
		assertEquals(1.0, server.divide(negdecnum1, negdecnum1), 0.1);
		assertEquals(0.91, server.divide(posnum1, posdecnum1), 0.1);
		assertEquals(-0.45, server.divide(posnum1, negdecnum1), 0.1);
		assertEquals(-1.8, server.divide(negnum1, posdecnum1), 0.1);
		assertEquals(0.91, server.divide(negnum1, negdecnum1), 0.1);
	}

	/**
	 * This function tests the powerOf method with various combinations of positive,
	 * negative, decimal or whole numbers.
	 */
	@Test 
	public void testPowerOf() {
		assertEquals(4.0, server.powerOf(posnum1, posnum1), 0.1);
		assertEquals(0.1, server.powerOf(posnum1, negnum1), 0.1);
		assertEquals(0, server.powerOf(negnum1, negnum1), 0.1);
		assertEquals(5.7, server.powerOf(posdecnum1, posdecnum1), 0.1);
		assertEquals(0, server.powerOf(posdecnum1, negdecnum1), 0.1);
		assertEquals(4.6, server.powerOf(posnum1, posdecnum1), 0.1);
		assertEquals(0, server.powerOf(posnum1, negdecnum1), 0.1);
	}

	/**
	 * This function tests the infix_postfix method with the above infix equations.
	 */
	@Test
	public void testInfixToPostfix() {
		client.setInfix(infix1);
		client.infix_postfix();
		assertEquals(postfix1, client.getPostfix());

		client.setInfix(infix2);
		client.infix_postfix();
		assertEquals(postfix2, client.getPostfix());
	}

	/**
	 * This function tests the evaluatePostfixCalculation method with the above postfix equations.
	 */
	@Test
	public void testEvaluatePostfix() {
		client.setPostfix(postfix1);
		assertEquals(result1, client.evaluatePostfixCalculation(), 0.2);

		client.setPostfix(postfix2);
		assertEquals(result2, client.evaluatePostfixCalculation(), 0.2);
	}

	/**
	 * This function tests the validity of adding an operator to the infix equation.
	 */
	@Test
	public void testInvalidOperatorPlacement() {
		// invalid to begin the equation with an operator
		client.setInfix("");
		assertTrue(client.invalidOperatorPlacement());

		// invalid to add an operator directly after another operator
		client.setInfix("+");
		assertTrue(client.invalidOperatorPlacement());

		// valid to add an operator after a closing bracket
		client.setInfix(")");
		assertFalse(client.invalidOperatorPlacement());

		// valid to add an operator after a number
		client.setInfix("3");
		assertFalse(client.invalidOperatorPlacement());
	}

	/**
	 * This function tests the validity of adding a number to the infix equation.
	 */
	@Test
	public void testInvalidNumberPlacement() {
		// invalid to add a number directly after closing bracket
		client.setInfix(")");
		assertTrue(client.invalidNumberPlacement());

		// valid to add a number to begin an equation
		client.setInfix("");
		assertFalse(client.invalidNumberPlacement());

		// valid to add a number after an operator
		client.setInfix("+ ");
		assertFalse(client.invalidNumberPlacement());

		// valid to add a number after another number (numerous digit number)
		client.setInfix("3");
		assertFalse(client.invalidNumberPlacement());
	}

	/**
	 * This function tests the validity of adding a negation to the infix equation.
	 */
	@Test
	public void testValidNegationPlacement() {
		// valid to begin equation with negation
		client.setInfix("");
		assertTrue(client.validNegationPlacement());

		// valid to add negation after operator
		client.setInfix("+ ");
		assertTrue(client.validNegationPlacement());

		// invalid to add negation after another negation
		client.setInfix("2 * -");
		assertFalse(client.validNegationPlacement());

		// invalid to add negation after closing bracket
		client.setInfix(")");
		assertFalse(client.validNegationPlacement());
	}

	/**
	 * This function tests the validity of adding a decimal to the infix equation.
	 */
	@Test
	public void testInvalidDecimalPlacement() {
		// invalid to begin the equation with a decimal
		client.setInfix("");
		assertTrue(client.invalidDecimalPlacement());

		// invalid to add decimal after operator
		client.setInfix("+");
		assertTrue(client.invalidDecimalPlacement());

		// invalid to add decimal when a decimal is already present in number
		client.setDecimalPointInNum(true);
		assertTrue(client.invalidDecimalPlacement());
		client.setDecimalPointInNum(false);

		// valid to add decimal to number
		client.setInfix("3");
		assertFalse(client.invalidDecimalPlacement());

		// invalid to add decimal after closing bracket
		client.setInfix(")");
		assertTrue(client.invalidDecimalPlacement());
	}

	/**
	 * This function tests the validity of a complete infix equation.
	 */
	@Test
	public void testInvalidFormat() {
		// empty equation is invalid
		client.setInfix("");
		assertTrue(client.invalidFormat());

		// equation ending with operator is invalid
		client.setInfix("+");
		assertTrue(client.invalidFormat());

		// valid equation
		client.setInfix("2 + 2 ");
		assertFalse(client.invalidFormat());

		// invalid equation has unbalanced bracket count
		client.setOpCount(3);
		client.setClCount(2);
		assertTrue(client.invalidFormat());
	}
}
