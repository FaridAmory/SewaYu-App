import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Home extends JFrame implements ActionListener {
	JPanel northPanel, centerPanel, loginPanel, registerPanel;
	JButton login, register;
	JLabel tittleLabel;
	JMenuBar Menubar;
	JMenuItem masuk,masuk2,keluar;
	JMenu Start;
	public Home() {
		FrameSettings();
		InitComponents();
		setVisible(true);
	}

	public void FrameSettings() {
		setTitle("BAD FINAL PROJECT");
		setSize(500, 200);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);

	}

	public void InitComponents() {
		Menubar = new JMenuBar();
		 Start= new JMenu("Menu");
		
		 masuk= new JMenuItem("Login");
		 masuk2= new JMenuItem("Register");
		 keluar=new JMenuItem("Exit");
		Start.add(masuk);
		Start.add(masuk2);
		Start.add(keluar);
		Menubar.add(Start); 
		
		masuk.addActionListener(this);
		masuk2.addActionListener(this);
		keluar.addActionListener(this);
		
		this.setJMenuBar(Menubar);
		
		
		
		// Seluruh Panel
		// Panel border layout untuk posisi
		northPanel = new JPanel(new FlowLayout());
		centerPanel = new JPanel();

		// Panel komponen

		loginPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		registerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		// Label komponen
		tittleLabel = new JLabel("Welcome");
		tittleLabel.setFont(new Font("times new roman", Font.PLAIN, 30));// done

		// button komponen
//		login = new JButton("Login");
//		login.setFont(new Font("times new roman", Font.PLAIN, 25));// done
//		login.setPreferredSize(new Dimension(230, 50));
//		login.addActionListener(this);
//		loginPanel.add(login);

//		register = new JButton("Register");
//		register.setFont(new Font("times new roman", Font.PLAIN, 25));// done
//		register.setPreferredSize(new Dimension(230, 50));
//		register.addActionListener(this);
//		registerPanel.add(register);

		// MULAI ADD ADD
//		northPanel.add(tittleLabel);
//		add(northPanel, BorderLayout.NORTH);
		centerPanel.add(tittleLabel);
		add(centerPanel,BorderLayout.CENTER);

//		centerPanel.add(loginPanel);
//		centerPanel.add(registerPanel);

		add(centerPanel, BorderLayout.CENTER);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource()== masuk) {
			login();
		} else if (e.getSource()==masuk2) {
			register();
		
		}else if (e.getSource()==keluar) {
			JOptionPane.showMessageDialog(null, "exit succesfull");
			System.exit(0);
		}

//		if (e.getSource() == register) {
//			register();
//			
//		}else if (e.getSource()== login) {
//			login();
//		}
		
	}
	public void login() {
		this.dispose();
		 new Login();
	}
	public void register () {
		this.dispose();
		 new Register();
	}

}
