import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Random;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class FormPemesanan extends JFrame implements ActionListener, MouseListener {
	JPanel northPanel, centerPanel, westPanel, eastPanel, southPanel, userPanel, jenisLapPanel, durasiPanel, pricePanel,
			quantityPanel;
	JLabel titleLabel, jenisLapanganLabel, durasiPLabel, quantityLabel;
	JTextField quantityText;
	JComboBox<String> JenisLapangan, DurasiPemesanan, Quantity;
	JButton confirm, back, next, update, delete;
	DefaultTableModel dtm;
	JTable tableHasil;
	JScrollPane ScrollPane;
	int price, totalPrice;
	HashMap<String, Lapangan> mapLapangan;
	private DatabaseConnection con = new DatabaseConnection();

	Integer idSelectedforUpdate;
	Integer idSelectedforDelete;
	User user;

	public FormPemesanan(User user) {
		this.user = user;
		FrameSettings();
		InitComponents();
		initHargaLapangan();
		setVisible(true);
	}

	private void refreshTable() {
		dtm.setRowCount(0);
		String sql = "SELECT oh.order_HeaderID, l.Lapangan_Jenis, oh.durasi_Pemesanan, od.quantity, l.Lapangan_Price FROM orderheader oh JOIN orderdetail od ON oh.order_HeaderID = od.order_HeaderID \r\n"
				+ "JOIN lapangan l ON l.Lapangan_ID = od.Lapangan_ID";
		ResultSet rs = con.query(sql);

		try {
			while (rs.next()) {
				Object[] row = new Object[] { rs.getInt("order_HeaderID"), rs.getString("Lapangan_Jenis"),
						rs.getInt("durasi_Pemesanan"), rs.getInt("quantity"),
						(rs.getInt("Lapangan_Price") * rs.getInt("durasi_Pemesanan") * rs.getInt("quantity")) };

				dtm.addRow(row);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void FrameSettings() {
		setTitle("BAD FINAL PROJECT");
		setSize(1200, 600);
		setResizable(false);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setLocationRelativeTo(null);

	}

	public void InitComponents() {
		// panel komponen

		titleLabel = new JLabel("Form Pemesanan");
		titleLabel.setFont(new Font("times new roman", Font.PLAIN, 30));
		titleLabel.setBounds(500, 80, 230, 30);
		add(titleLabel);

		jenisLapanganLabel = new JLabel("Jenis Lapangan");
		jenisLapanganLabel.setFont(new Font("times new roman", Font.PLAIN, 22));
		jenisLapanganLabel.setBounds(100, 220, 230, 30);
		add(jenisLapanganLabel);

		durasiPLabel = new JLabel("Durasi Pemesanan");
		durasiPLabel.setFont(new Font("times new roman", Font.PLAIN, 22));
		durasiPLabel.setBounds(100, 280, 230, 30);
		add(durasiPLabel);

		quantityLabel = new JLabel("Quantity");
		quantityLabel.setFont(new Font("times new roman", Font.PLAIN, 22));
		quantityLabel.setBounds(100, 340, 230, 30);
		add(quantityLabel);

		// combo box
		String[] Lapangan = { "LapanganVoli", "BuluTangkis", "SepakBola" };

		JenisLapangan = new JComboBox<String>(Lapangan);

		JenisLapangan.setBounds(370, 220, 230, 30);
		add(JenisLapangan);

		String[] Durasi = { "1", "2", "3", "4", "5" };
		DurasiPemesanan = new JComboBox(Durasi);
		DurasiPemesanan.setBounds(370, 280, 230, 30);
		add(DurasiPemesanan);

		String[] QuantityLapangan = { "1", "2", "3", "4", "5" };
		Quantity = new JComboBox(QuantityLapangan);
		Quantity.setBounds(370, 340, 230, 30);
		add(Quantity);

		// button
		confirm = new JButton("Confirm");
		confirm.setBounds(500, 460, 100, 30);
		confirm.addActionListener(this);
		add(confirm);

		back = new JButton("Back");
		back.setBounds(770, 460, 100, 30);
		back.addActionListener(this);
		add(back);

		next = new JButton("Next");
		next.setBounds(910, 460, 100, 30);
		next.addActionListener(this);
		add(next);

		delete = new JButton("Delete");
		delete.setBounds(170, 460, 100, 30);
		delete.addActionListener(this);
		add(delete);

		update = new JButton("Update");
		update.setBounds(340, 460, 100, 30);
		update.addActionListener(this);
		add(update);

		dtm = new DefaultTableModel(new String[] { "OrderHeaderID", "Jenis lapangan", "Durasi", "Quantity", "Price" },
				0);
		tableHasil = new JTable(dtm);
		ScrollPane = new JScrollPane(tableHasil);
		tableHasil.setFillsViewportHeight(true);
		tableHasil.addMouseListener(this);
		ScrollPane.setBounds(650, 210, 500, 180);
		add(ScrollPane);
	}

	void initHargaLapangan() {
		mapLapangan = new HashMap<>();

		mapLapangan.put("LapanganVoli", new Lapangan(1, 5000, "Voli"));
		mapLapangan.put("BuluTangkis", new Lapangan(2, 10000, "Bulutangkis"));
		mapLapangan.put("SepakBola", new Lapangan(3, 10000, "SepakBola"));
	}

	@Override
	public void actionPerformed(ActionEvent e) {

//insert	 
		if (e.getSource() == confirm) {

			int randomNumberOrderHeaderID = new Random().nextInt(900000) + 100000;

			String Lapangan = (String) JenisLapangan.getSelectedItem();
			int kuantitas = Integer.parseInt(Quantity.getSelectedItem().toString());
			int durasi = Integer.parseInt(DurasiPemesanan.getSelectedItem().toString());
			String data[] = { String.valueOf(randomNumberOrderHeaderID), Lapangan, String.valueOf(durasi),
					String.valueOf(kuantitas),
					String.valueOf(mapLapangan.get(Lapangan).getPrice() * kuantitas * durasi) };
			// debuging
//			System.out.println(data[0]);
//			System.out.println(data[1]);
//			System.out.println(data[2]);
//			System.out.println(data[3]);
			dtm.addRow(data);

			int id = user.getId();
//			insert into orderheader (customer_ID, durasi_Pemesanan) values(7, 2)

			String insertIntoHeaderQuery = "INSERT INTO orderheader (order_HeaderID, customer_ID, durasi_Pemesanan) VALUES (?, ?, ?)";
//			String insertIntoLapanganQuery = "INSERT INTO lapangan VALUES (?)";

			PreparedStatement ps = con.prepare(insertIntoHeaderQuery);

			try {
				ps.setInt(1, randomNumberOrderHeaderID);
				ps.setInt(2, id);
				ps.setInt(3, durasi);
				ps.execute();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// ?
			int lapanganId = mapLapangan.get(Lapangan).getId();
			String getMaxHeaderId = "SELECT MAX(order_HeaderID) as id from orderheader";
			ResultSet rs = con.query(getMaxHeaderId);
			int orderHeaderLastId = 0;
			try {
				rs.first();
				orderHeaderLastId = rs.getInt("id");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// Insert to Detail
			String insertIntoDetailQuery = "INSERT INTO orderdetail VALUES ( " + randomNumberOrderHeaderID + ", "
					+ lapanganId + ", " + kuantitas + ")";

			con.executeUpdate(insertIntoDetailQuery);
			JOptionPane.showMessageDialog(null, "Insert Succes");
		} else if (e.getSource() == update) {

			// Update
			String Lapangan = (String) JenisLapangan.getSelectedItem();
			int kuantitas = Integer.parseInt(Quantity.getSelectedItem().toString());
			int durasi = Integer.parseInt(DurasiPemesanan.getSelectedItem().toString());
			int lapanganId = mapLapangan.get(Lapangan).getId();

			try {
				// Update Database
				String sql = "UPDATE orderheader SET durasi_Pemesanan = ? WHERE order_HeaderID = ?";

				PreparedStatement ps = con.prepare(sql);
				ps.setInt(1, durasi);
				ps.setInt(2, idSelectedforUpdate);
				ps.execute();

				sql = "UPDATE orderdetail SET Lapangan_ID = ?, quantity = ? WHERE order_HeaderID = ?";
				ps = con.prepare(sql);
				ps.setInt(1, lapanganId);
				ps.setInt(2, kuantitas);
				ps.setInt(3, idSelectedforUpdate);
				ps.execute();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			JOptionPane.showMessageDialog(null, "Update Success");
			refreshTable();

		} else if (e.getSource() == delete) {

			String Sql = "DELETE from orderdetail where order_HeaderID=? ";
			String Sql2 = "DELETE from orderheader where order_HeaderID=? ";

			PreparedStatement ps = con.prepare(Sql);
			try {

				ps.setInt(1, idSelectedforDelete);
				ps.execute();

				ps = con.prepare(Sql2);
				ps.setInt(1, idSelectedforDelete);
				ps.execute();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "Delete Success");
			refreshTable();
		}
		
		else if (e.getSource()==back) {
			this.dispose();
			 new Login();
		}if (e.getSource()==next) {
			JOptionPane.showMessageDialog(null,"Transaction Success");
			this.dispose();
			 new Pembayaran();
		}
	}

	public void Login() {
		this.dispose();
		new Login();
	}

	public void Pembayaran() {
		this.dispose();
		new Pembayaran();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Integer rowIndex = tableHasil.getSelectedRow(); // ngambil row index
		System.out.println("Row: " + rowIndex);

		idSelectedforUpdate = Integer.parseInt((String) dtm.getValueAt(rowIndex, 0));
		idSelectedforDelete = Integer.parseInt((String) dtm.getValueAt(rowIndex, 0));
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}