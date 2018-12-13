/**
 * CLASS DETAILS
 * 
 * This class is the calculator server view.
 * As per MVC guidelines in the resource provided in class, this view
 * acts as a GUI only, it does not perform any calculations or functions. 
 * This is to promote reusability, as the view may be plugged into any MVC program
 * that contains other MVC components.
 * 
 *
 * Fields:
 *  - serialVersionUID
 *  - text_messages
 *  
 *  
 * There are both public and private functions within this class.
 * 
 * Public Functions:
 * 	- All Getters and Setters
 *  - ServerView (constructor)
 *  - appendMessage
 * 
 * Private Functions:
 *  - initialize
 *  
 * 
 * FEATURE DETAILS

 *  - Scrollable messages pane: shows conversation with clients
 * 
 * @author Ciara Power - 20072488
 * 
 * @version 1.0
 * 
 * @since 03/12/2018
 * 
 */
package views;

import java.awt.Color;
import java.awt.Font;
import java.rmi.RemoteException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class CalcServerView extends JFrame  {


	// **************************************************
	// Fields
	// **************************************************
	private static final long serialVersionUID = 1L;
	private JTextArea text_messages;

	// **************************************************
	// Constructors
	// **************************************************

	/**
	 * This is the constructor for the Server View GUI.
	 * 
	 * @return CalcServerView GUI object
	 * 
	 */
	public CalcServerView() throws RemoteException {
		initialize();
	}

	// **************************************************
	// Private methods
	// **************************************************

	/**
	 * Initialize the contents of the frame.
	 * 
	 * This function takes no parameters.
	 * 
	 * @return void
	 * 
	 */
	private void initialize() {
		setTitle("Server");
		setBounds(1000, 100, 592, 427);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JPanel messages = new JPanel();
		messages.setBackground(new Color(0, 139, 139));
		messages.setBounds(0, 0, 582, 380);
		getContentPane().add(messages);
		messages.setLayout(null);

		JScrollPane scrollPane_messages = new JScrollPane();
		scrollPane_messages.setBounds(12, 52, 547, 315);
		messages.add(scrollPane_messages);

		text_messages = new JTextArea();
		text_messages.setFont(new Font("Tahoma", Font.PLAIN, 16));
		text_messages.setEditable(false);
		scrollPane_messages.setViewportView(text_messages);

		JLabel lblMessages = new JLabel("Messages");
		lblMessages.setForeground(new Color(245, 255, 250));
		lblMessages.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblMessages.setBounds(224, 13, 126, 33);
		messages.add(lblMessages);

		setVisible(true);
	}

	// **************************************************
	// Public Functions
	// **************************************************

	/**
	 * This function appends a message to the display.
	 * 
	 * @param message
	 * 
	 * @return void 
	 * 
	 */
	public void appendMessage(String message) {
		text_messages.append(message);
	}

	/**
	 * This function displays an error dialog box.
	 * 
	 * @param errorMessage
	 * 
	 * @return void
	 */
	public void displayError(String errorMessage) {
		JOptionPane.showMessageDialog(this, errorMessage, "Error!", JOptionPane.ERROR_MESSAGE);
	}

}
