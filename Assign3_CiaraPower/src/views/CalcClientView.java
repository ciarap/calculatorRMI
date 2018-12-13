/**
 * CLASS DETAILS
 * 
 * This class is the calculator client view.
 * As per MVC guidelines in the resource provided in class, this view
 * acts as a GUI only, it does not perform any calculations or functions. 
 * This is to promote reusability, as the view may be plugged into any MVC program
 * that contains other MVC controller and model components.
 * 
 *
 * Fields:
 *  - serialVersionUID
 *  - equationInput
 *  - messages
 *  - btn_0
 *  - btn_1
 *  - btn_2
 *  - btn_3
 *  - btn_4
 *  - btn_5 
 *  - btn_6
 *  - btn_7
 *  - btn_8
 *  - btn_9
 *  - btn_add
 *  - btn_sub
 *  - btn_mult
 *  - btn_div
 *  - btn_pow
 *  - btn_dec
 *  - btn_opbr
 *  - btn_clbr
 *  - btn_eq
 *  - btn_C  
 *  
 *  
 * There are both public and private functions within this class.
 * 
 * Public Functions:
 * 	- All Getters and Setters
 *  - ServerView (constructor)
 *  - getEquationInput
 *  - addMessage
 *  - addCalculatorListener
 *  - displayError
 *  - resetInput
 *  - appendEquation
 * 
 * Private Functions:
 *  - initializeGUI
 *  - createNewButton
 *  
 * 
 * FEATURE DETAILS
 *  - Scrollable messages pane: shows equations solved and results
 * 
 * @author Ciara Power - 20072488
 * 
 * @version 1.0
 * 
 * @since 03/12/2018
 * 
 */
package views;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;


import javax.swing.border.EtchedBorder;
import java.awt.Color;

import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class CalcClientView extends JFrame{

	// **************************************************
	// Fields
	// **************************************************
	private static final long serialVersionUID = 1L;
	private JTextField equationInput;
	private JTextArea messages;
	private JButton btn_0 = new JButton();
	private JButton btn_1 = new JButton();
	private JButton btn_2= new JButton();
	private JButton btn_3= new JButton();
	private JButton btn_4= new JButton();
	private JButton btn_5= new JButton();
	private JButton btn_6= new JButton();
	private JButton btn_7= new JButton();
	private JButton btn_8= new JButton();
	private JButton btn_9= new JButton();
	private JButton btn_add= new JButton();
	private JButton btn_sub= new JButton();
	private JButton btn_mult= new JButton();
	private JButton btn_div= new JButton();
	private JButton btn_pow= new JButton();
	private JButton btn_dec= new JButton();
	private JButton btn_opbr= new JButton();
	private JButton btn_clbr= new JButton();
	private JButton btn_eq= new JButton();
	private JButton btn_C= new JButton();

	// **************************************************
	// Constructors
	// **************************************************

	/**
	 * This is the constructor for the Client View GUI.
	 * 
	 * @return CalcClientView GUI object
	 * 
	 */
	public CalcClientView() {
		initializeGUI();
	}

	// **************************************************
	// Private methods
	// **************************************************

	/**
	 * This function initializes the client calculator GUI.
	 * 
	 * @return void
	 */
	private void initializeGUI() {
		getContentPane().setBackground(new Color(153, 204, 204));
		setTitle("Calculator");
		setBounds(100, 300, 515, 590);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		equationInput = new JTextField();
		equationInput.setFont(new Font("Tahoma", Font.PLAIN, 25));
		equationInput.setBackground(new Color(245, 255, 250));
		equationInput.setEditable(false);
		equationInput.setBounds(12, 13, 473, 69);
		getContentPane().add(equationInput);
		equationInput.setColumns(10);

		JPanel button_panel = new JPanel();
		button_panel.setBackground(new Color(211, 211, 211));
		button_panel.setBounds(12, 95, 338, 175);
		getContentPane().add(button_panel);
		button_panel.setLayout(new GridLayout(3, 4, 0, 0));

		createNewButton("1", 28, button_panel, btn_1);
		createNewButton("2", 28, button_panel, btn_2);
		createNewButton("3", 28, button_panel, btn_3);
		createNewButton("4", 28, button_panel, btn_4);
		createNewButton("5", 28, button_panel, btn_5);
		createNewButton("6", 28, button_panel, btn_6);
		createNewButton("7", 28, button_panel, btn_7);
		createNewButton("8", 28, button_panel, btn_8);
		createNewButton("9", 28, button_panel, btn_9);

		JPanel messages_panel = new JPanel();
		messages_panel.setBackground(new Color(153, 204, 204));
		messages_panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Messages", TitledBorder.CENTER, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 18), null));
		messages_panel.setBounds(12, 349, 473, 187);
		getContentPane().add(messages_panel);
		messages_panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(12, 25, 449, 149);
		messages_panel.add(scrollPane);

		messages = new JTextArea();
		messages.setFont(new Font("Tahoma", Font.PLAIN, 16));
		messages.setEditable(false);
		scrollPane.setViewportView(messages);

		JPanel operators_panel = new JPanel();
		operators_panel.setBackground(new Color(211, 211, 211));
		operators_panel.setBounds(362, 95, 123, 241);
		getContentPane().add(operators_panel);
		operators_panel.setLayout(new GridLayout(5, 2, 0, 0));

		createNewButton("+", 28, operators_panel, btn_add);
		createNewButton("-", 43, operators_panel, btn_sub);
		createNewButton("*", 28, operators_panel, btn_mult);
		createNewButton("/", 28, operators_panel, btn_div);
		createNewButton("^", 28, operators_panel, btn_pow);
		createNewButton(".", 43, operators_panel, btn_dec);
		createNewButton("(", 28, operators_panel, btn_opbr);
		createNewButton(")", 28, operators_panel, btn_clbr);
		createNewButton("=", 28, operators_panel, btn_eq);
		createNewButton("C", 28, operators_panel, btn_C);

		btn_0 = new JButton("0");
		btn_0.setForeground(Color.WHITE);
		btn_0.setBackground(new Color(0, 139, 139));
		btn_0.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 28));
		btn_0.setBounds(128, 271, 107, 56);
		getContentPane().add(btn_0);

		setVisible(true);
	}

	/**
	 * This function sets button details using parameters.
	 * 
	 * @param text
	 * @param fontSize
	 * @param panel
	 * @param btn
	 * 
	 * @return void
	 */
	private void createNewButton(String text, int fontSize, JPanel panel, JButton btn) {
		btn.setText(text);
		btn.setForeground(Color.WHITE);
		btn.setBackground(new Color(0, 139, 139));
		btn.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, fontSize));
		panel.add(btn);
	}

	// **************************************************
	// Public methods
	// **************************************************
	
	/**
	 * This function returns the equation input into the calculator display.
	 * 
	 * @return String
	 */
	public String getEquationInput() {
		return equationInput.getText();
	}

	/**
	 * This function appends a message to the text area on the calculator.
	 * 
	 * @param message
	 * 
	 * @return void
	 */
	public void addMessage(String message) {
		messages.append(message);
	}

	/** 
	 * This function adds listeners to all the buttons on the calculator.
	 * 
	 * @param listenForButtonPress
	 * 
	 * @return void
	 */
	public void addCalculatorListener(ActionListener listenForButtonPress) {
		btn_0.addActionListener(listenForButtonPress);
		btn_1.addActionListener(listenForButtonPress);
		btn_2.addActionListener(listenForButtonPress);
		btn_3.addActionListener(listenForButtonPress);
		btn_4.addActionListener(listenForButtonPress);
		btn_5.addActionListener(listenForButtonPress);
		btn_6.addActionListener(listenForButtonPress);
		btn_7.addActionListener(listenForButtonPress);
		btn_8.addActionListener(listenForButtonPress);
		btn_9.addActionListener(listenForButtonPress);
		btn_add.addActionListener(listenForButtonPress);
		btn_sub.addActionListener(listenForButtonPress);
		btn_div.addActionListener(listenForButtonPress);
		btn_mult.addActionListener(listenForButtonPress);
		btn_pow.addActionListener(listenForButtonPress);
		btn_dec.addActionListener(listenForButtonPress);
		btn_opbr.addActionListener(listenForButtonPress);
		btn_clbr.addActionListener(listenForButtonPress);
		btn_C.addActionListener(listenForButtonPress);
		btn_eq.addActionListener(listenForButtonPress);
	}

	/**
	 * This function displays a dialog with an error message.
	 * 
	 * @param errorMessage
	 * 
	 * @return void
	 */
	public void displayError(String errorMessage) {
		JOptionPane.showMessageDialog(this, errorMessage, "Error!", JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * This function resets the calculator display.
	 * 
	 * @return void
	 */
	public void resetInput() {
		equationInput.setText("");
	}

	/**
	 * This function appends text to the equation input display.
	 * @param text
	 */
	public void appendEquation(String text) {
		equationInput.setText(equationInput.getText()+text);
	}
}
