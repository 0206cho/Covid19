package user.vaccinationstatus.gyeongbuk;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

import jdbc.DB;

public class Gyeongbuk_vaccinationstatus extends JFrame implements ActionListener {

	private JPanel p1;
	private JLabel lblicon;
	private JPanel p2;
	private DefaultTableModel model;
	private JTable table;
	private JScrollPane scroll;
	private JButton b1;
	private JPanel p3;
	private JLabel label1;
	private JButton b2;
	private JPanel p4;
	private JPanel p5;
	private JPanel p6;
	private JPanel last;
	private JLabel lbltest;
	private JTextField tx1;
	private JButton b3;
	private DefaultTableCellRenderer tbCellRender;
 
	// JFrame을 상속 받아 만드는 방법 << 이걸 더 선호함.
	public Gyeongbuk_vaccinationstatus(String title, int width, int height) {
		this.setTitle(title);
		setSize(width, height);
		setLocationRelativeTo(this); // 화면 가운데 찍음
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //닫을수 있는 특정 상수값을 주었기 때문에 프레임
		// 종료버튼이 클릭될때 프로그램도 같이 사라짐

		p1 = new JPanel();
		p1.setLayout(new BorderLayout());
		p1.setBackground(Color.white);
		ImageIcon icon = new ImageIcon("images/logo2.JPG");
		lblicon = new JLabel(icon);
		lblicon.setToolTipText("밤낮없이 방역을 위해 고생하시는 관계자분들 응원합니다!!");
		p1.add(lblicon, BorderLayout.WEST);

		p2 = new JPanel();
		p2.setBackground(Color.white);
		p2.setLayout(new FlowLayout());
		p2.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
		b1 = new JButton("검색");
		b1.setBorderPainted(false);
		b1.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		b1.setBackground(Color.white);
		b1.setFocusPainted(false);
		b1.addActionListener(this);
		b2 = new JButton("경북방역지침");
		b2.setBorderPainted(false);
		b2.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		b2.setBackground(Color.white);
		b2.setFocusPainted(false);
		b2.addActionListener(this);
		b3 = new JButton("처음으로");
		b3.setBorderPainted(false);
		b3.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		b3.setBackground(Color.white);
		b3.setFocusPainted(false);
		b3.addActionListener(this);
		//p2.add(b2);
		p2.add(b3);
		p2.add(b1);

		p3 = new JPanel();
		p3.setBackground(Color.white);
		p3.setLayout(new BorderLayout());
		p3.add(p1, BorderLayout.WEST);
		p3.add(p2, BorderLayout.EAST);

		p4 = new JPanel();
		p4.setBackground(Color.white);
		String[] header = { "보건소", "1차-당일접종", "1차-당일누계", "2차-당일접종", "2차-당일누계", "부스터샷-당일접종", "부스터샷-당일누계" };
		model = new DefaultTableModel(header, 0){
			public boolean isCellEditable(int i, int c) {		
				return false;										// jtable 더블클릭으로 수정 금지
			}	
		};
		table = new JTable(model);
		scroll = new JScrollPane(table);
		scroll.setPreferredSize(new Dimension(858, 500));
		
		// DefaultCellHeaderRender 생성 (가운데 정렬을 위한)
		tbCellRender = new DefaultTableCellRenderer();
		// DefaultTableCellHeaderRender의 정렬을 가운데 정렬로 지정
		tbCellRender.setHorizontalAlignment(SwingConstants.CENTER);
		// 정렬한 테이블의 ColumnModel을 가져옴
		TableColumnModel tbColModel = table.getColumnModel();
		// 반복문을 이용하여 테이블을 가운데 정렬로 지정
		for (int i = 0; i < tbColModel.getColumnCount(); i++) {
			tbColModel.getColumn(i).setCellRenderer(tbCellRender);
		}

		// 머리글(컬럼헤더) 클릭시 필드를 기준으로 오름차순, 내림차순
		table.setAutoCreateRowSorter(true);
		TableRowSorter tablesorter = new TableRowSorter(table.getModel());
		table.setRowSorter(tablesorter);
		table.getTableHeader().setReorderingAllowed(false); // 테이블 열 이동 불가
		//컬럼 길이조절
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(0);
		table.getColumnModel().getColumn(2).setPreferredWidth(0);
		table.getColumnModel().getColumn(3).setPreferredWidth(0);
		table.getColumnModel().getColumn(4).setPreferredWidth(0);
		table.getColumnModel().getColumn(5).setPreferredWidth(0);
		table.getColumnModel().getColumn(6).setPreferredWidth(0);

		p4.add(scroll);

		last = new JPanel();
		last.setBackground(Color.white);
		last.setLayout(new BorderLayout());
		last.add(p3, BorderLayout.NORTH);
		last.add(p4);
		add(last);

		useraddAll(model);
		setResizable(false);
		setVisible(true);
	}

	// 테이블에 db가져옴,, 반드시 기존테이블에 있는거 다 삭제하고 db에 가져오는식으로? 해야 깔끔하다
	public void useraddAll(DefaultTableModel model) {
		String sql = "select * from vaccinationstatus where name LIKE '%경상북도%'";
		try {
			ResultSet rs = DB.getResultSet(sql);
			for (int i = 0; i < model.getRowCount();) {
				model.removeRow(0);
			}

			while (rs.next()) {
				String data[] = { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7) };
				model.addRow(data);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//지역 검색하는 메소드 생성
	public void getSearch(DefaultTableModel model, String combo, String word) throws Exception {
//		String sql = "select * from confirmed where PERSON LIKE '%부산%' AND " + combo.trim() + " LIKE '" + word.trim()
//				+ "'";
		
		String sql = "SELECT * FROM vaccinationstatus WHERE name LIKE '%경상북도%' AND name LIKE '%" + word + "%'";

		try {
			ResultSet rs = DB.getResultSet(sql);
			for (int i = 0; i < model.getRowCount();) {
				model.removeRow(0);
			}
			while (rs.next()) {
				String data[] = { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7) };
				model.addRow(data);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.drawLine(0, 100, 1050, 100);
	}

	public static void main(String[] args) {
		try {
			DB.init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new Gyeongbuk_vaccinationstatus("경북 보건소 현황", 880, 615);
	}

	public DefaultTableModel getModel() {
		return model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == b3) {
			useraddAll(model);
		} else if (obj == b1) {
			new Gyeongbuk_Search("경북 보건소 검색", 350, 250, this);
		}
	}

}
