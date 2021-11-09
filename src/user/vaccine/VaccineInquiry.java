package user.vaccine;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

import jdbc.DB;

public class VaccineInquiry extends JFrame implements ActionListener {

	private JPanel p1;
	private JLabel lblicon;
	private JPanel p4;
	private JLabel lbl1;
	private JPanel p5;
	private JButton b3;
	private JButton b4;
	private JPanel p6;
	private JPanel p3;
	private JPanel last;
	private JPanel p7;
	private DefaultTableModel model;
	private JTable table;
	private JScrollPane scroll;
	private Statement st;
	private ResultSet rs;
	private DefaultTableCellRenderer tbCellRender;

	// JFrame을 상속 받아 만드는 방법 << 이걸 더 선호함.
	public VaccineInquiry(String title, int width, int height) {
		this.setTitle(title);
		setSize(width, height);
		setLocationRelativeTo(this); // 화면 가운데 찍음

		p1 = new JPanel();
		p1.setLayout(new BorderLayout());
		ImageIcon icon = new ImageIcon("images/logo2.JPG");
		lblicon = new JLabel(icon);
		lblicon.setToolTipText("밤낮없이 방역을 위해 고생하시는 관계자분들 응원합니다!!");
		p1.setBackground(Color.white);
		// lblicon.setPreferredSize(new Dimension(150, 150));
		p1.add(lblicon, BorderLayout.WEST);

		p4 = new JPanel();
		p4.setBackground(Color.white);
		p4.setLayout(new FlowLayout(FlowLayout.LEFT));
		p4.setBorder(BorderFactory.createEmptyBorder(15, 25, 0, 0));
		lbl1 = new JLabel("코로나19 예방접종 사전예약 시스템");
		lbl1.setFont(new Font("맑은 고딕", Font.BOLD, 19));
		p4.add(lbl1);

		p5 = new JPanel();
		p5.setBackground(Color.white);
		p5.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
		p5.setLayout(new FlowLayout(FlowLayout.RIGHT));
		b3 = new JButton("처음으로");
		b3.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		b3.setBorderPainted(false);
		b3.setBackground(Color.white);
		b3.setFocusPainted(false);
		b3.addActionListener(this);
		b4 = new JButton("예약자 검색/취소");
		b4.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		b4.setBorderPainted(false);
		b4.setBackground(Color.white);
		b4.setFocusPainted(false);
		b4.addActionListener(this);
		// p5.add(b3);
		p5.add(b4);

		p6 = new JPanel();
		p6.setBackground(Color.white);
		p6.setLayout(new BorderLayout());
		p6.add(p4, BorderLayout.WEST);
		p6.add(p5, BorderLayout.EAST);

		p3 = new JPanel();
		p3.setLayout(new BorderLayout());
		p3.add(p1, BorderLayout.WEST);
		p3.add(p6);

		p7 = new JPanel();
		p7.setBackground(Color.white);
		String[] header = { "이름", "주민등록번호", "휴대폰번호", "접종날짜", "의료기관" };
		model = new DefaultTableModel(header, 0);
		table = new JTable(model);
		scroll = new JScrollPane(table);
		scroll.setBackground(Color.white);
		scroll.setPreferredSize(new Dimension(580, 500));
		
		
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

		// 머리글(컬럼헤더) 클릭시 필드를 기준으로 오름차순, 내림차순
		table.setAutoCreateRowSorter(true);
		TableRowSorter tablesorter = new TableRowSorter(table.getModel());
		table.setRowSorter(tablesorter);

		// 컬럼 길이조절
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);


		p7.add(scroll);

		last = new JPanel();
		last.setBackground(Color.white);
		last.setLayout(new BorderLayout());
		last.add(p3, BorderLayout.NORTH);
		last.add(p7);
		add(last);

		setVisible(true);

	}

//	public static void main(String[] args) {
//		new VaccineInquiry("백신 예약 조회", 800, 650);
//	}
	public void paint(Graphics g) {
		super.paint(g);
		g.drawLine(0, 100, 1050, 100);
		g.drawLine(210, 40, 210, 88);
	}

	public void getBackidSearch(DefaultTableModel model, String BackNum) {
		String sql = "select * from vaccine where residentBackID" + " LIKE '" + BackNum.trim() + "'";
		try {
			st = DB.conn.createStatement();
			rs = st.executeQuery(sql);

			for (int i = 0; i < model.getRowCount();) {
				model.removeRow(0);
			}
			while (rs.next()) {
				String data[] = { rs.getString(2), rs.getString(3) + "-" + rs.getString(1), rs.getString(4),
						rs.getString(5), rs.getString(6) };
				model.addRow(data);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public DefaultTableModel getModel() {
		return model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == b4) {
			new VaccineSearch("백신 예약자 검색", 400, 250, this);
		}
	}

}
