package admin.comfirmed;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import jdbc.DB;

public class ComfirmedAdmin extends JFrame implements ActionListener {
	
	private JPanel p1;
	private JLabel lblicon;
	private JPanel last;
	private JPanel pCen;
	private JButton b1;
	private JButton b2;
	private DefaultTableModel model;
	private JTable table;
	private JPanel p3;
	private JTextField tfID;
	private JTextField tfName;
	private JTextField tfPW;
	private JTextField tfLocal;
	private JTextField tfPhone;
	private JTextField tfDate;
	private JScrollPane sp;
	private JPanel pNor;
	private JPanel p2;
	private JButton btnAdd;
	private JButton btnDelete;
	private JButton btnModify;
	private JButton btnLevel;

	//JFrame을 상속 받아 만드는 방법 << 이걸 더 선호함.
	public ComfirmedAdmin(String title, int width, int height) {
		this.setTitle(title);
		setSize(width, height);
		setLocationRelativeTo(this); 	//화면 가운데 찍음
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //닫을수 있는 특정 상수값을 주었기 때문에 프레임 종료버튼이 클릭될때 프로그램도 같이 사라짐 
		
		
		//상단 패널
		p1 = new JPanel();
		p1.setLayout(new BorderLayout());
		ImageIcon icon = new ImageIcon("images/logo2.JPG");
		lblicon = new JLabel(icon);
		lblicon.setToolTipText("밤낮없이 방역을 위해 고생하시는 관계자분들 응원합니다!!");
		//lblicon.setPreferredSize(new Dimension(150, 150));
		p1.setBackground(Color.white);
		p1.add(lblicon,BorderLayout.WEST);
		
		//상단 버튼 패널
		p2 = new JPanel();
		p2.setBackground(Color.WHITE);
		p2.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
		
		btnAdd = new JButton("삽입");
		btnAdd.setBorderPainted(false);
		btnAdd.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		btnAdd.setBackground(Color.white);
		btnAdd.setFocusPainted(false);
		btnAdd.addActionListener(this);
		p2.add(btnAdd);
		
		btnDelete = new JButton("삭제");
		btnDelete.setBorderPainted(false);
		btnDelete.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		btnDelete.setBackground(Color.white);
		btnDelete.setFocusPainted(false);
		btnDelete.addActionListener(this);
		p2.add(btnDelete);
		
		btnModify = new JButton("수정");
		btnModify.setBorderPainted(false);
		btnModify.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		btnModify.setBackground(Color.white);
		btnModify.setFocusPainted(false);
		btnModify.addActionListener(this);
		p2.add(btnModify);
		
		btnLevel = new JButton("단계조절");
		btnLevel.setBorderPainted(false);
		btnLevel.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		btnLevel.setBackground(Color.white);
		btnLevel.setFocusPainted(false);
		btnLevel.addActionListener(this);
		p2.add(btnLevel);
		
		pNor = new JPanel();
		pNor.setLayout(new BorderLayout());
		pNor.add(p1, BorderLayout.WEST);
		pNor.add(p2, BorderLayout.EAST);
		pNor.setBackground(Color.WHITE);
		
		
		//중앙패널
		pCen = new JPanel();
//		p2.setLayout(new BorderLayout());
		pCen.setBackground(Color.white);
		
		
		String [] column = { "번호", "날짜", "지역", "접촉력", "노출여부"};
		
		model = new DefaultTableModel(column, 0);
		table = new JTable(model);
		sp = new JScrollPane(table);
		sp.setPreferredSize(new Dimension(580, 500));
		
		SelectAll(model);
		
		pCen.add(sp);
		
		
		


		
		
		//모든 패널 붙이기
		last = new JPanel();
		last.setLayout(new BorderLayout());
		last.setBackground(Color.white);
		last.add(pNor,BorderLayout.NORTH);
		last.add(pCen);
		add(last);
		
		setVisible(true);
		
		
	}
	public void SelectAll(DefaultTableModel model) {
		String sql = "select * from confirmed";
		
		try {
			ResultSet rs = DB.getResultSet(sql);
			//전체데이터 삭제후 다시 출력
			for(int i = 0; i < model.getRowCount();) {
				model.removeRow(0);
			}
			
			while(rs.next()) {
				String data[] = { rs.getString(1), rs.getString(2), rs.getString(3), 
						rs.getString(4), rs.getString(5)
				};
				model.addRow(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public DefaultTableModel getModel() {
		return model;
	}
	
	public void paint (Graphics g) {
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
		
		new ComfirmedAdmin("확진자명단괸리", 600, 620);
	}
	
	//이벤트 처리
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == btnAdd) {
			new ComfirmedAdd("확진자삽입", 300, 320, this);
		}else if(obj == btnDelete) {
			new ComfirmedDelete("확진자삭제", 300, 300, this);
		}else if(obj == btnModify) {
			new ComfirmedModify("확진자수정", 300, 320, this);
		}else if(obj==btnLevel) {
			new ComfirmedLevel("지역별거리단계관리", 300, 400);
		}
		
	}

}
