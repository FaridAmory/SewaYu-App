import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Register extends JFrame implements ActionListener {
	JPanel northPanel, centerPanel, registerPanel, genderPanel, genderButtonPanel, usernamePanel, passwordPanel,
			termsPanel, addressPanel, mobilePhonePanel, backPanel;
	JPasswordField passwordText;
	JLabel titleLabel, usernameLabel, mobilePhoneLabel, genderLabel, addressLabel, passwordLabel;
	JTextField usernameText, mobilePhoneText, addressText;
	
	JRadioButton maleButton, femaleButton;
	JButton register, reset, back;
	JCheckBox terms;
	ButtonGroup buttonGroup1;
	DatabaseConnection db;

	Vector<User> UserVector = new Vector<>();

	public Register() {
		FrameSettings();
		InitComponents();
		setVisible(true);
		db = new DatabaseConnection();
	}

	public void FrameSettings() {
		setTitle("BAD FINAL PROJECT");
		setSize(500, 550);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);

	}

	public void InitComponents() {
		// Seluruh Panel
		// Panel border layout untuk posisi
		northPanel = new JPanel(new FlowLayout());
		centerPanel = new JPanel();

		// Panel komponen

		usernamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		passwordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		genderButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		termsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		mobilePhonePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		registerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		addressPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		// Label komponen
		titleLabel = new JLabel("Register");
		titleLabel.setFont(new Font("times new roman", Font.PLAIN, 30));// done

		usernameLabel = new JLabel("Username");
		usernameLabel.setFont(new Font("times new roman", Font.PLAIN, 20));
		usernamePanel.add(usernameLabel);
		usernameLabel.setPreferredSize(new Dimension(80, 30));

		passwordLabel = new JLabel("Password");
		passwordLabel.setFont(new Font("times new roman", Font.PLAIN, 20));
		passwordPanel.add(passwordLabel);
		passwordLabel.setPreferredSize(new Dimension(80, 30));

		genderLabel = new JLabel("Gender");
		genderLabel.setFont(new Font("times new roman", Font.PLAIN, 20));
		genderPanel.add(genderLabel);
		genderLabel.setPreferredSize(new Dimension(110, 30));

		mobilePhoneLabel = new JLabel("Phone");
		mobilePhoneLabel.setFont(new Font("times new roman", Font.PLAIN, 20));
		mobilePhonePanel.add(mobilePhoneLabel);
		mobilePhoneLabel.setPreferredSize(new Dimension(80, 30));

		// Radio button components
		// grouping
		buttonGroup1 = new ButtonGroup();
		buttonGroup1.add(maleButton);
		// baru masukin
		maleButton = new JRadioButton("Male");
		maleButton.setPreferredSize(new Dimension(80, 30));
		genderButtonPanel.add(maleButton);
		buttonGroup1.add(maleButton);

		femaleButton = new JRadioButton("Female");
		femaleButton.setPreferredSize(new Dimension(80, 30));
		genderButtonPanel.add(femaleButton);
		buttonGroup1.add(femaleButton);

		genderPanel.add(genderButtonPanel);

		addressLabel = new JLabel("Address");
		addressLabel.setFont(new Font("times new roman", Font.PLAIN, 20));
		addressLabel.setPreferredSize(new Dimension(80, 30));
		addressPanel.add(addressLabel);

		// Checkbox Components
		terms = new JCheckBox("Terms And Condition");
		terms.setFont(new Font("times new roman", Font.PLAIN, 12));
		terms.setPreferredSize(new Dimension(180, 30));
		termsPanel.add(terms);

		// TextField
		usernameText = new JTextField();
		usernameText.setPreferredSize(new Dimension(200, 30));
		usernamePanel.add(usernameText);

		passwordText = new JPasswordField();
		passwordText.setPreferredSize(new Dimension(200, 30));
		passwordPanel.add(passwordText);

		addressText = new JTextField();
		addressText.setPreferredSize(new Dimension(200, 30));
		addressPanel.add(addressText);

		mobilePhoneText = new JTextField();
		mobilePhoneText.setPreferredSize(new Dimension(200, 30));
		mobilePhonePanel.add(mobilePhoneText);

		// button
		register = new JButton("Register");
		register.setPreferredSize(new Dimension(150, 30));
		register.addActionListener(this);
		registerPanel.add(register);

		reset = new JButton("Reset");
		reset.setPreferredSize(new Dimension(150, 30));
		reset.addActionListener(this);
		registerPanel.add(reset);

		back = new JButton("Back");
		back.setPreferredSize(new Dimension(150, 30));
		back.addActionListener(this);
		backPanel.add(back);

		// MULAI ADD ADD
		northPanel.add(titleLabel);
		add(northPanel, BorderLayout.NORTH);

		centerPanel.add(usernamePanel);
		centerPanel.add(passwordPanel);
		centerPanel.add(mobilePhonePanel);
		centerPanel.add(genderPanel);
		centerPanel.add(addressPanel);
		centerPanel.add(termsPanel);
		centerPanel.add(registerPanel);
		centerPanel.add(back);

		add(centerPanel, BorderLayout.CENTER);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == register) {
			if (terms.isSelected()) {
				register();// pangil method yg bawah
			} else {
				JOptionPane.showMessageDialog(this, "please confirm Terms&Condition");

			}

		} else if (e.getSource() == reset) {
			usernameText.setText("");
			passwordText.setText("");
			mobilePhoneText.setText("");
			addressText.setText("");

		} else if (e.getSource()==back) {
			back();
		}  

	}
	
	public void back() {
		this.dispose();
		 new Home();
	}

	public boolean validUser(User user) {
		boolean isAllValid = true;
		String message = "";

		if (user.getUsername().isEmpty()) {
			isAllValid = false;
			message = "Name Empty!";
		} else if (user.getPhoneNumber().isEmpty()) {
			isAllValid = false;
			message = "Phone Number Empty!";
		} else if (user.getPassword().isEmpty()) {
			isAllValid = false;
			message = "Password Empty!";

		}else if (user.getAddress().isEmpty()) {
			isAllValid = false;
			message = "Address Empty!";
		}
		
			//else if (!(user.getPassword().equals(user.getPassword()))) {
//			isAllValid = false;
//			message = "Confirm Password Invalid";
//		}

		if (isAllValid == false) {
			JOptionPane.showMessageDialog(this, message, "Invalid", JOptionPane.ERROR_MESSAGE);
		}
		return isAllValid;
	}

	public void register() {
		String username = usernameText.getText();
		String password = String.valueOf(passwordText.getPassword());// casting ganti tipe data
		String mobilePhone = mobilePhoneText.getText();
		String gender = maleButton.isSelected() ? "M" : "F";
		String address = addressText.getText();
		User newUser = new User(0, username, mobilePhone, gender, password, address);

		if (validUser(newUser) == true) {
			db.insertUser(newUser);
			UserVector.add(newUser);
			JOptionPane.showMessageDialog(this, "Successfully Registered");
			new Login();
			this.dispose();
		}
	}
}
	