import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Pembayaran extends JFrame implements ActionListener {
	JLabel TitleLabel; 
	JButton nextButton, backButton;
	
	public Pembayaran() {

		FrameSettings();
		InitComponents();
		setVisible(true);

	}

	public void FrameSettings() {
		// condition
		setTitle("Bad Final Project");
		setSize(500, 500);
		setResizable(false);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

	}

	public void InitComponents() {
		TitleLabel = new JLabel("Transaction Success");
		TitleLabel.setFont(new Font("Times new Roman", Font.PLAIN, 35));
		TitleLabel.setBounds(100,150,300,30);
		add(TitleLabel);

		
		nextButton = new JButton("New Transaction");
		nextButton.setFont(new Font("times new roman", Font.PLAIN, 20));
		nextButton.setBounds(120,250, 250, 35);
		nextButton.addActionListener(this);
		add(nextButton);

		backButton = new JButton("Log-out");
		backButton.setFont(new Font("times new roman", Font.PLAIN, 20));
		backButton.setBounds(120,320, 250, 35);
		backButton.addActionListener(this);
		add(backButton);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
	
		if(e.getSource()==nextButton) {
			formPemesanan();
		}
		if (e.getSource()==backButton) {
			
			Login();
		}
	
}
	public void formPemesanan() {
		this.dispose();
		new  FormPemesanan(null);
	}
	public void Login () {
		this.dispose();
		new Login();
	}
}