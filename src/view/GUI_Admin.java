package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;

import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import database.JDBCUtil;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JSpinner;
import javax.swing.ListSelectionModel;
import javax.swing.DropMode;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Toolkit;

public class GUI_Admin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private DefaultTableModel dm;
	private JTable table;
	private JScrollPane js;
	private JPanel jpanel_qlSach;
	private JPanel jpanel_qlDocGia;
	private JPanel jpanel_qlPhieuMuon;
	private JPanel jpanel_thongKe;
	private JLabel jlabel_qlSach;
	private JLabel jlabel_qlDocGia;
	private JLabel jlabel_qlPhieuMuon;
	private JPanel jpn3;
	private JPanel jpn2;
	private JPanel jpn1;
	private JPanel cardPanel;
	private JPanel jpn_5;
	private JLabel jlabel_trangChu;
	private JMenuItem dangXuat;
	private JMenuItem thoat;
	private ArrayList connectedClients;
	private Socket clientSocket;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws Exception {
		String str = "com.jtattoo.plaf.texture.TextureLookAndFeel";
		UIManager.setLookAndFeel(str);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_Admin frame = new GUI_Admin();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI_Admin() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\DACS1\\icon and picture\\lb.png"));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		setLocationRelativeTo(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(211, 211, 211));
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Hệ thống");
		mnNewMenu.setIcon(new ImageIcon("D:\\DACS1\\icon and picture\\heThong.png"));
		mnNewMenu.setForeground(new Color(0, 0, 0));
		mnNewMenu.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar.add(mnNewMenu);

		dangXuat = new JMenuItem("Đăng xuất");
		dangXuat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				BatDau bd = new BatDau();
				bd.setVisible(true);
			}
		});
		mnNewMenu.add(dangXuat);

		thoat = new JMenuItem("Thoát");
		thoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnNewMenu.add(thoat);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(154, 205, 50));
		panel.setBounds(0, 0, 191, 541);
		contentPane.add(panel);

		JLabel lblNewLabel = new JLabel("Library system");
		lblNewLabel.setForeground(new Color(240, 255, 240));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 25));
		lblNewLabel.setBounds(9, 11, 181, 43);
		panel.add(lblNewLabel);

		JSeparator separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		separator.setBounds(38, 52, 105, 2);
		panel.add(separator);

		jpn1 = new JPanel();
		jpn1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				jpn1.setBackground(Color.yellow);
				jpn2.setBackground(new Color(128, 128, 0));
				jpn3.setBackground(new Color(128, 128, 0));
				jpn_5.setBackground(new Color(128, 128, 0));
				jlabel_qlSach.setForeground(Color.green);
				jlabel_qlDocGia.setForeground(new Color(240, 255, 240));
				jlabel_qlPhieuMuon.setForeground(new Color(240, 255, 240));
				jlabel_trangChu.setForeground(new Color(240, 255, 240));
				CardLayout cl = (CardLayout) cardPanel.getLayout();
				cl.show(cardPanel, "Sach");
				QuanLiSach qlSach = new QuanLiSach();
				qlSach.displayAllBook();
			}
		});
		jpn1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jpn1.setLayout(null);
		jpn1.setForeground(new Color(128, 128, 0));
		jpn1.setBackground(new Color(128, 128, 0));
		jpn1.setBounds(0, 160, 190, 37);
		panel.add(jpn1);

		jlabel_qlSach = new JLabel("Quản lí sách");
		jlabel_qlSach.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jlabel_qlSach.setForeground(new Color(240, 255, 240));
		jlabel_qlSach.setFont(new Font("Arial", Font.PLAIN, 15));
		jlabel_qlSach.setBounds(24, 0, 101, 37);
		jpn1.add(jlabel_qlSach);

		jpn2 = new JPanel();
		jpn2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jpn2.setLayout(null);
		jpn2.setForeground(new Color(128, 128, 0));
		jpn2.setBackground(new Color(128, 128, 0));
		jpn2.setBounds(0, 218, 190, 37);
		jpn2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jpn2.setBackground(Color.yellow);
				jpn1.setBackground(new Color(128, 128, 0));
				jpn3.setBackground(new Color(128, 128, 0));
				jpn_5.setBackground(new Color(128, 128, 0));
				jlabel_qlDocGia.setForeground(Color.green);
				jlabel_qlSach.setForeground(new Color(240, 255, 240));
				jlabel_qlPhieuMuon.setForeground(new Color(240, 255, 240));
				jlabel_trangChu.setForeground(new Color(240, 255, 240));
				CardLayout cl = (CardLayout) cardPanel.getLayout();
				cl.show(cardPanel, "DocGia");
			}
		});
		panel.add(jpn2);

		jlabel_qlDocGia = new JLabel("Quản lí độc giả");
		jlabel_qlDocGia.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jlabel_qlDocGia.setForeground(new Color(240, 255, 240));
		jlabel_qlDocGia.setFont(new Font("Arial", Font.PLAIN, 15));
		jlabel_qlDocGia.setBounds(25, 0, 109, 37);
		jpn2.add(jlabel_qlDocGia);

		jpn3 = new JPanel();
		jpn3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jpn3.setLayout(null);
		jpn3.setForeground(new Color(128, 128, 0));
		jpn3.setBackground(new Color(128, 128, 0));
		jpn3.setBounds(0, 273, 190, 37);
		jpn3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jpn3.setBackground(Color.yellow);
				jpn1.setBackground(new Color(128, 128, 0));
				jpn2.setBackground(new Color(128, 128, 0));
				jpn_5.setBackground(new Color(128, 128, 0));
				jlabel_qlPhieuMuon.setForeground(Color.green);
				jlabel_qlSach.setForeground(new Color(240, 255, 240));
				jlabel_qlDocGia.setForeground(new Color(240, 255, 240));
				jlabel_trangChu.setForeground(new Color(240, 255, 240));
				CardLayout cl = (CardLayout) cardPanel.getLayout();
				cl.show(cardPanel, "PhieuMuon");
			}
		});
		panel.add(jpn3);

		jlabel_qlPhieuMuon = new JLabel("Quản lí phiếu mượn");
		jlabel_qlPhieuMuon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jlabel_qlPhieuMuon.setForeground(new Color(240, 255, 240));
		jlabel_qlPhieuMuon.setFont(new Font("Arial", Font.PLAIN, 15));
		jlabel_qlPhieuMuon.setBounds(26, 0, 135, 37);
		jpn3.add(jlabel_qlPhieuMuon);

		jpn_5 = new JPanel();
		jpn_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jpn_5.setBackground(Color.yellow);
				jpn1.setBackground(new Color(128, 128, 0));
				jpn2.setBackground(new Color(128, 128, 0));
				jpn3.setBackground(new Color(128, 128, 0));
				jlabel_trangChu.setForeground(Color.green);
				jlabel_qlSach.setForeground(new Color(240, 255, 240));
				jlabel_qlDocGia.setForeground(new Color(240, 255, 240));
				jlabel_qlPhieuMuon.setForeground(new Color(240, 255, 240));
				CardLayout cl = (CardLayout) cardPanel.getLayout();
				cl.show(cardPanel, "TrangChu");
			}
		});
		jpn_5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jpn_5.setLayout(null);
		jpn_5.setForeground(new Color(128, 128, 0));
		jpn_5.setBackground(new Color(128, 128, 0));
		jpn_5.setBounds(0, 102, 190, 37);
		panel.add(jpn_5);

		jlabel_trangChu = new JLabel("Trang chủ");
		jlabel_trangChu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jlabel_trangChu.setForeground(new Color(240, 255, 240));
		jlabel_trangChu.setFont(new Font("Arial", Font.PLAIN, 15));
		jlabel_trangChu.setBounds(24, 0, 101, 37);
		jpn_5.add(jlabel_trangChu);

		CardLayout cardLayout = new CardLayout();
		cardPanel = new JPanel(cardLayout);
		cardPanel.setBounds(190, 0, 805, 541);
		contentPane.add(cardPanel);
//		
		TrangChu trangChu = new TrangChu();
		cardPanel.add(trangChu.getContentPane(), "TrangChu");
		QuanLiSach qlSach = new QuanLiSach();
		cardPanel.add(qlSach.getContentPane(), "Sach");
		QuanLiDocGia qlDocGia = new QuanLiDocGia();
		cardPanel.add(qlDocGia.getContentPane(), "DocGia");
		QuanLiPhieuMuon qlPhieuMuon = new QuanLiPhieuMuon();
		cardPanel.add(qlPhieuMuon.getContentPane(), "PhieuMuon");

	}

}
