import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame implements ActionListener {
	DatabaseConnection db = new DatabaseConnection();

	JLabel TitleLabel, UserNameLabel, PasswordLabel;
	JTextField Tusername;
	JPasswordField TPassword;
	JButton PLogin, PReset, PBack;

	public Login() {
		FrameSettings();
		InitComponents();
		setVisible(true);
	}

	public void FrameSettings() {
		// condition
		setTitle("Bad Final Project");
		setSize(500, 550);
		setResizable(false);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

	}

	public void InitComponents() {

		TitleLabel = new JLabel("Login");
		TitleLabel.setFont(new Font("Times new Roman", Font.PLAIN, 30));
		TitleLabel.setBounds(180, 80, 280, 30);
		add(TitleLabel);

		UserNameLabel = new JLabel("Username");
		UserNameLabel.setFont(new Font("Times new Roman", Font.PLAIN, 20));
		UserNameLabel.setBounds(80, 175, 200, 30);
		add(UserNameLabel);

		PasswordLabel = new JLabel("Password");
		PasswordLabel.setFont(new Font("Times new Roman", Font.PLAIN, 20));
		PasswordLabel.setBounds(80, 225, 200, 30);
		add(PasswordLabel);

		Tusername = new JTextField();
		Tusername.setBounds(180, 175, 200, 30);
		add(Tusername);

		TPassword = new JPasswordField();
		TPassword.setBounds(180, 225, 200, 30);
		add(TPassword);

		PLogin = new JButton("Login");
		PLogin.setBounds(180, 300, 200, 30);
		PLogin.addActionListener(this);
		add(PLogin);

		PReset = new JButton("Reset");
		PReset.setBounds(180, 350, 200, 30);
		PReset.addActionListener(this);
		add(PReset);

		PBack = new JButton("Back");
		PBack.setBounds(180, 400, 200, 30);
		PBack.addActionListener(this);
		add(PBack);
	}

	public boolean validInput(User user, String password) {
		boolean inputIsValid = true;
		String message = "";
		System.out.println(user.getPassword());
		System.out.println(password);
		if (user.getUsername().isEmpty()) {
			inputIsValid = false;
			message = "Username is empty!";
		} else if (user.getPassword().isEmpty()) {
			inputIsValid = false;
			message = "Password is empty!";
		} else if (!user.getPassword().equals(password)) {
			inputIsValid = false;
			message = "Password is wrong!";
		}

//		if (inputIsValid == false) {			
//			System.out.println("Tes");
//		}

		return inputIsValid;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == PBack) {
			register();

		} else if (e.getSource() == PReset) {
			Tusername.setText("");
			TPassword.setText("");

		}

		else if (e.getSource() == PLogin) {

			String username = Tusername.getText();
			String password = String.valueOf(TPassword.getPassword());
			;

			if (username.equals("") || password.equals("")) {
				JOptionPane.showMessageDialog(this, "object is empty");

			} else {

				try {
					db.getUserData(username, password);
					while (db.resultSet.next()) {
						int Id = Integer.parseInt(db.resultSet.getString("customer_ID"));
						String Username = db.resultSet.getString("customer_Name");
						String PhoneNumber = db.resultSet.getString("customer_PhoneNumber");
						String Gender = db.resultSet.getString("customer_Gender");
						String Address = db.resultSet.getString("customer_Address");
						String Password = db.resultSet.getString("customer_Password");
						User user1 = new User(Id, Username, PhoneNumber, Gender,Password, Address);

						System.out.println(user1.getPassword());
						System.out.println(TPassword.getText());
						
						if(user1.getPassword().equals(TPassword.getText())) {
							new FormPemesanan(user1);
						}else {
							JOptionPane.showMessageDialog(null, "Password error");
						}

					}

				}

				catch (Exception e2) {

				}
			}
		} else {
			this.dispose();
			new Home();
		}

	}

	public void register() {
		this.dispose();
		new Register();
	}

}