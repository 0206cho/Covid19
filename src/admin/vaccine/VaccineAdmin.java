package admin.vaccine;

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

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

import jdbc.DB;

public class VaccineAdmin extends JFrame implements ActionListener {
	
	private JPanel p1;
	private JLabel lblicon;
	private JPanel p2;
	private JButton b1;
	private JPanel last;
	private JPanel p3;
	private JPanel p4;
	private DefaultTableModel model;
	private JTable table;
	private JScrollPane scroll;
	private DefaultTableCellRenderer tbCellRender;
	private Statement st;
	private ResultSet rs;
	private JButton b2;
	private JButton b3;

	//JFrame을 상속 받아 만드는 방법 << 이걸 더 선호함.
	public VaccineAdmin(String title, int width, int height) {
		this.setTitle(title);
		setSize(width, height);
		setLocationRelativeTo(this); 	//화면 가운데 찍음
		
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
		
		
		b1 = new JButton("삭제");
		b1.setBorderPainted(false);
		b1.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		b1.setBackground(Color.white);
		b1.setFocusPainted(false);
		b1.addActionListener(this);
		p2.add(b1);
		
		b2 = new JButton("수정");
		b2.setBorderPainted(false);
		b2.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		b2.setBackground(Color.white);
		b2.setFocusPainted(false);
		b2.addActionListener(this);
		p2.add(b2);
		
		
		p3 = new JPanel();
		p3.setBackground(Color.white);
		p3.setLayout(new BorderLayout());
		p3.add(p1, BorderLayout.WEST);
		p3.add(p2, BorderLayout.EAST);
		
		
		p4 = new JPanel();
		p4.setBackground(Color.white);
		String[] header = { "이름", "주민등록번호", "휴대폰번호", "접종날짜", "의료기관" };
		model = new DefaultTableModel(header, 0){
			public boolean isCellEditable(int i, int c) {		
				return false;										// jtable 더블클릭으로 수정 금지
			}	
		};
		table = new JTable(model);
		scroll = new JScrollPane(table);
		scroll.setPreferredSize(new Dimension(580, 500));
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
		getuseradd(model);
		

		p4.add(scroll);
		
		
		last = new JPanel();
		last.setBackground(Color.white);
		last.setLayout(new BorderLayout());
		last.add(p3, BorderLayout.NORTH);
		last.add(p4);
		add(last);
		
		setVisible(true);
		
		
	}
	
	public static void main(String[] args) {
		try {
			DB.init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new VaccineAdmin("백신 관리자", 600, 410);
	}
	
	public void getuseradd(DefaultTableModel model) {
		String sql = "select * from vaccine";
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

	public void paint(Graphics g) {
		super.paint(g);
		g.drawLine(0, 100, 1050, 100);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == b1) {
			new VaccineDelete("백신 예약 취소", 300, 300,this);
		}
		else if(obj == b2) {
			new VaccineModify("백신 예약 수정", 500, 420,this);
		}
	}

}
