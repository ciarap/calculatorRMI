/**
 * CLASS DETAILS
 * 
 * This class is the Driver of the program, it contains the main function.
 * As per MVC guidelines, this class creates the server view and controller when started,
 * and creates the client model, view and controller when the user presses the Driver GUI button.
 *
 * Fields:
 *  - serialVersionUID
 *  - btnNewCalcClient
 *  
 *  
 * There are both public and private functions within this class.
 * 
 * Public Functions:
 * 	- main
 *  
 * Private Functions:
 *  - Driver (constructor)
 *  
 * Private Classes:
 *  - DriverListener
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

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import models.CalcClient;
import views.CalcClientView;
import views.CalcServerView;

public class Driver extends JFrame {

	// **************************************************
	// Fields
	// **************************************************
	private static final long serialVersionUID = 1L;
	private JButton btnNewCalcClient;

	// **************************************************
	// Constructors
	// **************************************************

	/**
	 * This is the constructor for the Driver.
	 * Within this, the small GUI is created.
	 * 
	 */
	private Driver() {
		getContentPane().setBackground(new Color(245, 255, 250));
		setTitle("Calculator Program");
		setBounds(100, 100, 412, 168);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		btnNewCalcClient = new JButton("Create New Calculator Client");
		btnNewCalcClient.setBackground(new Color(32, 178, 170));
		btnNewCalcClient.setBounds(42, 40, 310, 68);
		btnNewCalcClient.addActionListener(new DriverListener());
		btnNewCalcClient.setFont(new Font("Tahoma", Font.PLAIN, 20));
		getContentPane().add(btnNewCalcClient);

		JLabel lblCalculatorServer = new JLabel("Calculator Server has started!");
		lblCalculatorServer.setBounds(42, 13, 230, 16);
		lblCalculatorServer.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(lblCalculatorServer);

		setVisible(true);
	}

	// **************************************************
	// Public Functions
	// **************************************************
	
	/**
	 * This is the main entry point for the program. The Driver is created, and a server is created.
	 * 
	 * @param args
	 */
	public static void main(String args[])
	{
		Driver driver = new Driver();
		try {
			new CalcServerContr(new CalcServerView());
		} catch (RemoteException e) {
			JOptionPane.showMessageDialog(driver, e.toString(), "Error!", JOptionPane.ERROR_MESSAGE);
		}
	}

	// **************************************************
	// Private Classes
	// **************************************************

	/**
	 * This class represents the Listener model.
	 * 
	 * The ActionListener interface is implemented by this class.
	 * 
	 * This class handles actions such as buttons pressed on the GUI.
	 *  
	 * Public Functions:
	 *  - actionPerformed
	 *
	 * @author Ciara Power - 20072488
	 * 
	 * @version 1.0
	 * 
	 * @since 07/12/18
	 *
	 */
	private class DriverListener implements ActionListener {

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
			if(e.getSource()==btnNewCalcClient) {
				new CalcClientContr(new CalcClientView(), new CalcClient());
			}
		}
	}

}
