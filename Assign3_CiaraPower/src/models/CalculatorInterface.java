/**
 * CLASS DETAILS
 * 
 * This class is the calculator registry, it acts as the remote interface.
 * It contains function declarations which will be called by the clients, through the
 * registry created by the server.
 *  
 * There are both public and private functions within this class.
 * 
 * Public Functions:
 * 	- add
 *  - subtract
 *  - multiply
 *  - divide
 *  - powerOf
 *  - initialConnection
 * 
 * @author Ciara Power - 20072488
 * 
 * @version 1.0
 * 
 * @since 03/12/2018
 * 
 */
package models;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CalculatorInterface extends Remote {
	double add(double num1, double num2) throws RemoteException;
	double subtract(double num1, double num2) throws RemoteException;
	double multiply(double num1, double num2) throws RemoteException;
	double divide(double num1, double num2) throws RemoteException;
	double powerOf(double num1, double num2)  throws RemoteException;
	String initialConnection() throws RemoteException;
}