package user.medicalstation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
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

public class Medicalstation extends JFrame implements ActionListener {

	private JPanel p1;
	private JLabel lblicon;
	private JPanel p2;
	private JLabel lbl1;
	private JPanel p3;
	private JPanel p4;
	private JPanel last;
	private JPanel p5;
	private DefaultTableModel model;
	private JTable table;
	private JScrollPane scroll;
	private DefaultTableCellRenderer tbCellRender;
	private JPanel p6;
	private JLabel lbl2;
	private JPanel p7;
	private JTextField tx;
	private JPanel center;
	private JButton b1;
	private JButton b2;
	private JLabel lbl3;
	private JPanel p8;
	private Statement st;
	private ResultSet rs;

	// JFrame을 상속 받아 만드는 방법 << 이걸 더 선호함.
	public Medicalstation(String title, int width, int height) {
		this.setTitle(title);
		setSize(width, height);
		setLocationRelativeTo(this); // 화면 가운데 찍음

		p1 = new JPanel();
		p1.setLayout(new BorderLayout());
		ImageIcon icon = new ImageIcon("images/logo2.JPG");
		lblicon = new JLabel(icon);
		lblicon.setToolTipText("밤낮없이 방역을 위해 고생하시는 관계자분들 응원합니다!!");
		p1.setBackground(Color.white);
		p1.add(lblicon, BorderLayout.WEST);

		p2 = new JPanel();
		p2.setBackground(Color.white);
		p2.setLayout(new FlowLayout(FlowLayout.LEFT));
		p2.setBorder(BorderFactory.createEmptyBorder(15, 25, 0, 0));
		lbl1 = new JLabel("코로나19 선별진료소 현황");
		lbl1.setFont(new Font("맑은 고딕", Font.BOLD, 19));
		p2.add(lbl1);

		p3 = new JPanel();
		p3.setBackground(Color.white);
		p3.setLayout(new BorderLayout());
		p3.add(p2);

		p4 = new JPanel();
		p4.setLayout(new BorderLayout());
		p4.add(p1, BorderLayout.WEST);
		p4.add(p3);

		p5 = new JPanel();
		p5.setBackground(Color.white);
		String[] header = { "연번", "시도", "시군구", "의료기관명", "주소", "평일 운영시간", "토요일 운영시간", "공휴일 운영시간", "대표 전화번호" };
		model = new DefaultTableModel(header, 0);
		table = new JTable(model);
		scroll = new JScrollPane(table);
		scroll.setBackground(Color.white);
		scroll.setPreferredSize(new Dimension(1750, 590));

		// 렌더는 보여주는 역할이고, 수정 또는 변화를 주기위해서는 에디터 설정해줘야함
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
		// 컬럼 길이조절
		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		table.getColumnModel().getColumn(1).setPreferredWidth(0);
		table.getColumnModel().getColumn(2).setPreferredWidth(0);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setPreferredWidth(350);
		table.getColumnModel().getColumn(5).setPreferredWidth(0);
		table.getColumnModel().getColumn(6).setPreferredWidth(0);
		table.getColumnModel().getColumn(7).setPreferredWidth(0);
		table.getColumnModel().getColumn(8).setPreferredWidth(0);

		// 머리글(컬럼헤더) 클릭시 필드를 기준으로 오름차순, 내림차순
		table.setAutoCreateRowSorter(true);
		TableRowSorter tablesorter = new TableRowSorter(table.getModel());
		table.setRowSorter(tablesorter);
		
		
		
		useraddAll(model);

		p5.add(scroll);

		p6 = new JPanel();
		p6.setLayout(new FlowLayout());
		p6.setBackground(Color.white);
		p6.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
		lbl2 = new JLabel(
				"<HTML>코로나19 증상 의심시에는 먼저 관할 보건소 또는 1339 콜센터 등의 상담을 받으신 후 선별진료소를 방문하시기 바랍니다.<br> &nbsp &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp <U>※ 평일 18시 이후, 토요일, 일요일/공휴일에 선별진료소 방문시 해당기관의 운영시간 유선 확인 필요</U></HTML>");
		lbl2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lbl2.setForeground(Color.RED);
		lbl2.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		p6.add(lbl2);

		p7 = new JPanel();
		p7.setLayout(new FlowLayout());
		p7.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
		p7.setBackground(Color.white);
		tx = new JTextField(45);
		tx.setPreferredSize(new Dimension(0, 35));
		b1 = new JButton("검색");
		b1.setBackground(new Color(0, 0, 128));
		b1.setForeground(Color.white);
		b1.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		b1.setPreferredSize(new Dimension(70, 35));
		b1.setFocusPainted(false);
		b1.setBorderPainted(false);
		b1.addActionListener(this);

		b2 = new JButton("전체목록");
		b2.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		b2.setBackground(Color.DARK_GRAY);
		b2.setForeground(Color.white);
		b2.setPreferredSize(new Dimension(110, 35));
		b2.setFocusPainted(false);
		b2.setBorderPainted(false);
		b2.addActionListener(this);
		p7.add(tx);
		p7.add(b1);
		p7.add(b2);

		p8 = new JPanel();
		p8.setLayout(new FlowLayout());
		p8.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
		p8.setBackground(Color.white);
		lbl3 = new JLabel(
				"<html>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp시도 및 시군구, 선별진료소, 전화번호를 통합하여 검색합니다.<br> 검색어 예시 : '<U>서울</U>' 또는 '<U>중구</U>' 또는 '<U>보건소</U>' 또는 '<U>051</U>'(전화번호부 일부) </html>");
		lbl3.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		p8.add(lbl3);

		center = new JPanel();
		center.setLayout(new BorderLayout());
		center.add(p6, BorderLayout.NORTH);
		center.add(p7);
		center.add(p8, BorderLayout.SOUTH);

		last = new JPanel();
		last.setBackground(Color.white);
		last.setLayout(new BorderLayout());
		last.add(p4, BorderLayout.NORTH);
		last.add(center);
		last.add(p5, BorderLayout.SOUTH);
		add(last);

		// 실행 간격 지정(3초)
		int Sec = 3;
		// 시간 출력 포맷
		final SimpleDateFormat fmt = new SimpleDateFormat(
				"코로나19 선별진료소 현황 " + "(" + "yy" + "년 " + "MM" + "월 " + "dd" + "일 " + "h" + "시 " + "기준)");
		// 주기적인 작업을 위한
		final ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);

		exec.scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				try {
					Calendar cal = Calendar.getInstance();
					// 라벨에 현재 시 출력
					lbl1.setText(fmt.format(cal.getTime()));
				} catch (Exception e) {
					e.printStackTrace();

					// 에러 발생시 중지시킴
					exec.shutdown();
				}
			}
		}, 0, Sec, TimeUnit.SECONDS);

		
		
		setResizable(false);
		setVisible(true);
	}

//	public static void main(String[] args) {
//		try {
//			DB.init();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		new Medicalstation("선별 진료소 목록", 1800, 870);
//	}

	public void useraddAll(DefaultTableModel model) {
		String sql = "select * from medicalstation";
		try {
			ResultSet rs = DB.getResultSet(sql);
			for (int i = 0; i < model.getRowCount();) {
				model.removeRow(0);
			}

			while (rs.next()) {
				String data[] = { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9) };
				model.addRow(data);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.drawLine(0, 100, 1850, 100);
		g.drawLine(210, 40, 210, 88);
	}

	public void getSearch(DefaultTableModel model, String word) {
		String sql = "select * from medicalstation where address1 LIKE '%" + word.trim() + "%" + "' or address2 LIKE '%"
				+ word.trim() + "%' or medicalname LIKE '%" + word.trim() + "%' or callnumber LIKE '%" + word.trim()
				+ "%'";
		try {
			st = DB.conn.createStatement();
			rs = st.executeQuery(sql);

			for (int i = 0; i < model.getRowCount();) {
				model.removeRow(0);
			}
			while (rs.next()) {
				String data[] = { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9) };
				model.addRow(data);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == b1) {
			getSearch(model, tx.getText());
		} else if (obj == b2) {
			useraddAll(model);
		}
	}

}
