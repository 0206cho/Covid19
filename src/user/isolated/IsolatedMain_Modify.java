package user.isolated;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.time.LocalDate;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import jdbc.DB;

public class IsolatedMain_Modify extends JFrame implements ActionListener, MouseListener {

	private JPanel pUp, pCenter, pDown, pLast;
	private JLabel lblicon, lbl_rule, lbl_togetherRule, lbl_symptoms, lbl_monitoring;
	private JTextField txtInput;
	private JButton btnSend, btnLogout;
	private Color color1, color2;
	private String iso_id, iso_name;

	// JFrame을 상속 받아 만드는 방법 << 이걸 더 선호함.
	public IsolatedMain_Modify(String title, int width, int height) {
		this.setTitle(title);
		setSize(width, height);
		setLocationRelativeTo(this); // 화면 가운데 찍음
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //닫을수 있는 특정 상수값을 주었기 때문에 프레임
		// 종료버튼이 클릭될때 프로그램도 같이 사라짐
		setResizable(false); // 실행후 화면크기 변경 불가

		// 상단 패널
		pUp = new JPanel();
		pUp.setLayout(new BorderLayout());
		pUp.setBackground(Color.white);

		// 로고
		ImageIcon icon = new ImageIcon("images/logo2.JPG");
		lblicon = new JLabel(icon);
		lblicon.setToolTipText("밤낮없이 방역을 위해 고생하시는 관계자분들 응원합니다!!");
		// lblicon.setPreferredSize(new Dimension(150, 150));

		pUp.add(lblicon, BorderLayout.WEST);

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

		pUp.add(penLogout);

		// 중앙패널
		color1 = new Color(0xD4F4FA);
		color2 = new Color(0xE6FFFF);

		pCenter = new JPanel();
		pCenter.setLayout(new FlowLayout());
		pCenter.setBackground(color2);

		// 현재 날짜 구하기 (시스템 시계, 시스템 타임존)
		LocalDate now = LocalDate.now();

		// 자가격리 날짜
		String isolatedDate = "SELECT DATEDIFF('" + now
				+ "', (select isolatedDate from isolated where name Like '김민솔'))";
		
//		String isolatedName = "SELECT name "
//				+ "FROM isolated"
//				+ "WHERE isolatedID LIKE 'ISO001'";
		// -> 로그인한 사용자 id마다 이름, 자가격리 날짜 다르게 수정,,

		try {
			ResultSet rs = DB.getResultSet(isolatedDate);
			while (rs.next()) {
				iso_id = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		JLabel lbl_day = new JLabel("자가격리 " + iso_id + "일째");
		lbl_day.setFont(new Font("맑은고딕", Font.BOLD, 20));
		lbl_day.setForeground(Color.RED);
		pCenter.add(lbl_day);

		// 생활수칙
		lbl_rule = new JLabel("<HTML><U>자가격리 대상자 생활수칙</U></HTML>");
		lbl_rule.setFont(new Font("맑은고딕", Font.BOLD, 18));
		lbl_rule.setForeground(Color.BLUE);
		lbl_rule.addMouseListener(this);
		pCenter.add(lbl_rule);

		// 동거인 생활 수칙
		lbl_togetherRule = new JLabel("<HTML><U>자가격리대상자의 가족 및 동거인을 위한 생활수칙</U></HTML>");
		lbl_togetherRule.setFont(new Font("맑은고딕", Font.BOLD, 18));
		lbl_togetherRule.setForeground(Color.BLUE);
		lbl_togetherRule.addMouseListener(this);
		pCenter.add(lbl_togetherRule);

		// 주요 증상
		lbl_symptoms = new JLabel("<HTML><U>코로나19 주요 증상</U></HTML>");
		lbl_symptoms.setFont(new Font("맑은고딕", Font.BOLD, 18));
		lbl_symptoms.setForeground(Color.BLUE);
		lbl_symptoms.addMouseListener(this);
		pCenter.add(lbl_symptoms);

		// 모니터링
		lbl_monitoring = new JLabel("<HTML><U>자가모니터링 방법</U></HTML>");
		lbl_monitoring.setFont(new Font("맑은고딕", Font.BOLD, 18));
		lbl_monitoring.setForeground(Color.BLUE);
		lbl_monitoring.addMouseListener(this);
		pCenter.add(lbl_monitoring);

		// 채팅창 하단
		// 하단 패널
		pDown = new JPanel();
		pDown.setBackground(Color.WHITE);

		// 입력창
		txtInput = new JTextField(18);
		txtInput.setFont(new Font("맑은 고딕", 0, 17));
		txtInput.addActionListener(this);

		// 전송 버튼
		btnSend = new JButton("전송");
		btnSend.setBackground(color2);
		btnSend.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		btnSend.addActionListener(this);

		pDown.add(txtInput);
		pDown.add(btnSend);

		// 모든 패널 붙이기
		pLast = new JPanel();
		pLast.setLayout(new BorderLayout());
		pLast.setBackground(Color.white);

		pLast.add(pUp, BorderLayout.NORTH);
		pLast.add(pCenter, BorderLayout.CENTER);
		pLast.add(pDown, BorderLayout.SOUTH);

		add(pLast);

		setVisible(true);
		txtInput.requestFocus();

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

	@Override
	public void mouseClicked(MouseEvent e) {
		Object obj = e.getSource();
		if (obj == lbl_rule) {
			lbl_rule.setForeground(new Color(0x8B008B));
			new IsolatedRule();
		} else if (obj == lbl_togetherRule) {
			lbl_togetherRule.setForeground(new Color(0x8B008B));
			new IsolatedTogether();
		}  else if (obj == lbl_symptoms) {
			lbl_symptoms.setForeground(new Color(0x8B008B));
			new IsolatedSympthoms();
		}  else if (obj == lbl_monitoring) {
			lbl_monitoring.setForeground(new Color(0x8B008B));
			new IsolatedMonitoring();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}