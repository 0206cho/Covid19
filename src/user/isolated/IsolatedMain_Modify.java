package user.isolated;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.time.LocalDate;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import jdbc.DB;

public class IsolatedMain_Modify extends JFrame implements ActionListener {

	private JPanel p1;
	private JLabel lblicon;
	private JPanel last;
	private JPanel p2;
	private JTextField tx1;
	private JButton b1;
	private JTextArea ta;
	private JPanel chatSouthPen;
	private JButton btnLogout;
	private Color color1;
	private Color color2;

	// JFrame을 상속 받아 만드는 방법 << 이걸 더 선호함.
	public IsolatedMain_Modify(String title, int width, int height) {
		this.setTitle(title);
		setSize(width, height);
		setLocationRelativeTo(this); // 화면 가운데 찍음
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //닫을수 있는 특정 상수값을 주었기 때문에 프레임
		// 종료버튼이 클릭될때 프로그램도 같이 사라짐

		// 상단 패널
		p1 = new JPanel();
		p1.setLayout(new BorderLayout());
		p1.setBackground(Color.white);

		ImageIcon icon = new ImageIcon("images/logo2.JPG");
		lblicon = new JLabel(icon);
		lblicon.setToolTipText("밤낮없이 방역을 위해 고생하시는 관계자분들 응원합니다!!");
		// lblicon.setPreferredSize(new Dimension(150, 150));

		p1.add(lblicon, BorderLayout.WEST);

		// 로그아웃 버튼
		ImageIcon imgLogout = new ImageIcon("images/logout6.png");

		JPanel penLogout = new JPanel();
		penLogout.setLayout(null);
//		penLogout.setBounds(10, 10, 10, 10);
		penLogout.setBackground(Color.WHITE);

		btnLogout = new JButton(imgLogout);
		btnLogout.setBackground(Color.WHITE);
		btnLogout.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		btnLogout.setFocusPainted(false);
		btnLogout.setBounds(240, 10, 60, 50);
		btnLogout.addActionListener(this);

		penLogout.add(btnLogout);

		p1.add(penLogout);

		// 중앙패널
		color1 = new Color(0xD4F4FA);
		color2 = new Color(0xE6FFFF);
//		color2 = new Color(0xF8FFFE);

		p2 = new JPanel();
		p2.setLayout(new BorderLayout());
		p2.setBackground(Color.white);

		ta = new JTextArea();
		ta.setBackground(color2);
		ta.setEditable(false);

		// 현재 날짜 구하기 (시스템 시계, 시스템 타임존)
		LocalDate now = LocalDate.now();
		String s_now = now.toString();
		System.out.println(now);

		// 자가격리 날짜
		String isolatedDate = "SELECT DATEDIFF('" + now
				+ "', (select isolatedDate from isolated where name Like '김민솔'))";
		
		try {
			ResultSet rs = DB.getResultSet(isolatedDate);
			System.out.println(isolatedDate);
			while (rs.next()) {
				String iso_id = rs.getString("isolatedID");
				String iso_name = rs.getString("name");
				String iso_pw = rs.getString("isolatedPW");
				String iso_local = rs.getString("local");
				String iso_phone = rs.getString("phone");
				String iso_date = rs.getString("isolatedDate");
				
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		


		ta.setFont(new Font("맑은고딕", Font.BOLD, 14));
		ta.setLineWrap(true); // 벽에 닿으면 줄바꿈
//		ta.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT); //오른쪽 정렬
		ta.setText("자가격리 " + " " + "일째\n" + "생활수칙\n" + "");

		JScrollPane sp = new JScrollPane(ta, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		sp.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		p2.add(sp);

		// 테두리
		JPanel penSide1 = new JPanel();
		penSide1.setBackground(color2);
		p2.add(penSide1, BorderLayout.WEST);

		JPanel penSide2 = new JPanel();
		penSide2.setBackground(color2);
		p2.add(penSide2, BorderLayout.EAST);

		JPanel penSide3 = new JPanel();
		penSide3.setBackground(color2);
		p2.add(penSide3, BorderLayout.NORTH);

		// 채팅창 하단
		// 하단 패널
		chatSouthPen = new JPanel();
		chatSouthPen.setBackground(Color.WHITE);

		// 입력창
		tx1 = new JTextField(18);
		tx1.setFont(new Font("맑은 고딕", 0, 17));
		tx1.addActionListener(this);

		// 전송 버튼
		b1 = new JButton("전송");
		b1.setBackground(color2);
		b1.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		b1.addActionListener(this);

		chatSouthPen.add(tx1);
		chatSouthPen.add(b1);

		p2.add(chatSouthPen, BorderLayout.SOUTH);

		//

		// 모든 패널 붙이기
		last = new JPanel();
		last.setLayout(new BorderLayout());
		last.setBackground(Color.white);

		last.add(p1, BorderLayout.NORTH);
		last.add(p2);

		add(last);

		setVisible(true);
		tx1.requestFocus();

	}

	public void paint(Graphics g) {
		super.paint(g);
		g.drawLine(0, 98, 1050, 98);
		g.drawLine(0, 675, 1050, 675);

	}

	public static void main(String[] args) {
		try {
			DB.init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new IsolatedMain_Modify("자가격리자", 510, 720);
	}

	// 이벤트
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
	}

}
