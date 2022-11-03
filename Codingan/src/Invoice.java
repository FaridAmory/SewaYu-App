import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class Invoice extends JFrame implements ActionListener {
 JLabel TitleLabel, userNameLabel, lapanganIdLabel, orderheaderIdLabel, priceLabel ;
 
 JTextField userNameText, lapanganIdText, orderHeaderIdText, priceText;
 JButton FinishButton;
 
 public Invoice() {
  FrameSettings();
  InitComponents();
  setVisible(true);
 }

 public void FrameSettings() {
 setTitle("BAD Final Project");
 setSize(575,700);
 setResizable(false);
 setLayout(null);
 setDefaultCloseOperation(EXIT_ON_CLOSE);
 setLocationRelativeTo(null);
  
 }

 public void InitComponents() {
  TitleLabel = new JLabel("Invoice");
  TitleLabel.setFont(new Font("Times new Roman", Font.PLAIN, 30));
  TitleLabel.setBounds(200, 80, 280, 30);
  add(TitleLabel);
  
  userNameLabel = new JLabel("Username");
  userNameLabel.setFont(new Font("Times new Roman", Font.PLAIN, 20));
  userNameLabel.setBounds(80, 200, 200, 30);
  add(userNameLabel);
   
  lapanganIdLabel = new JLabel("Lapangan ID");
  lapanganIdLabel.setFont(new Font("Time New Roman",Font.PLAIN,20));
  lapanganIdLabel.setBounds(80,260,200,30);
  add(lapanganIdLabel);
  
  orderheaderIdLabel = new JLabel("Orderheader ID");
  orderheaderIdLabel .setFont(new Font("Time New Roman",Font.PLAIN,20));
  orderheaderIdLabel .setBounds(80,320,200,30);
  add(orderheaderIdLabel); 
  
  priceLabel= new JLabel("Price");
  priceLabel .setFont(new Font("Time New Roman",Font.PLAIN,20));
  priceLabel.setBounds(80,380,200,30);
  add(priceLabel);
  
  userNameText = new JTextField();
  userNameText.setFont(new Font("times new roman", Font.PLAIN, 20));
  userNameText.setBounds(250, 200, 230, 30);
  add(userNameText);
  
  lapanganIdText = new JTextField();
  lapanganIdText.setFont(new Font("times new roman", Font.PLAIN, 20));
  lapanganIdText.setBounds(250, 260, 230, 30);
  add(lapanganIdText);
  
  orderHeaderIdText = new JTextField();
  orderHeaderIdText.setFont(new Font("times new roman", Font.PLAIN, 20));
  orderHeaderIdText.setBounds(250, 320, 230, 30);
  add(orderHeaderIdText);
  
  priceText = new JTextField();
  priceText.setFont(new Font("times new roman", Font.PLAIN, 20));
  priceText.setBounds(250, 380, 230, 30);
  add(priceText);
  
  FinishButton = new JButton("Finish");
  FinishButton.setFont(new Font("times new roman", Font.PLAIN, 20));
  FinishButton.setBounds(250, 450, 230, 30);
  add(FinishButton);
  
  
  
 }


 

 @Override
 public void actionPerformed(ActionEvent arg0) {
  
 
 }
 
}
