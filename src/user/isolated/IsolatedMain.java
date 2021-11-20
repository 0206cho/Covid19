package user.isolated;

import java.awt.BorderLayout;
import java.awt.Color;
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

import jdbc.DB;

public class IsolatedMain extends JFrame implements ActionListener, MouseListener {

	private JPanel pUp, pCenter, pLast;
	private JLabel lblicon, lbl_rule, lbl_togetherRule, lbl_symptoms, lbl_monitoring;
	private JButton btnSend, btnLogout;
	private Color color;
	private String iso_id, iso_name, get_id;
	private IsolatedLogin isolatedLogin;

	// JFrame을 상속 받아 만드는 방법 << 이걸 더 선호함.
	public IsolatedMain(String title, int width, int height, IsolatedLogin isolatedLogin) {
		this.isolatedLogin = isolatedLogin;
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
		color = new Color(0xE6FFFF);

		pCenter = new JPanel();
		pCenter.setLayout(null);
		pCenter.setBackground(color);

		// 현재 날짜 구하기 (시스템 시계, 시스템 타임존)
		LocalDate now = LocalDate.now();

		// 자가격리 날짜
		get_id = isolatedLogin.getTx1().getText();
		System.out.println(get_id);

		String isolatedDate = "SELECT name, DATEDIFF('" + now
				+ "', (select isolatedDate from isolated WHERE isolatedID LIKE '" + get_id + "')) "
				+ "from isolated WHERE isolatedID LIKE '" + get_id + "'";

		// -> 로그인한 사용자 id마다 이름, 자가격리 날짜 다르게 수정,,

		try {
			ResultSet rs = DB.getResultSet(isolatedDate);
			while (rs.next()) {
				iso_name = rs.getString(1);
				iso_id = rs.getString(2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		JLabel lbl_day = new JLabel("※" + iso_name + "님은 현재 자가격리 " + iso_id + "일차 입니다※");
		lbl_day.setFont(new Font("맑은고딕", Font.BOLD, 20));
		lbl_day.setForeground(Color.RED);
		lbl_day.setBounds(45, 30, 450, 50);
		pCenter.add(lbl_day);

		// 생활수칙
		lbl_rule = new JLabel("<HTML><U>자가격리 대상자 생활수칙</U></HTML>");
		lbl_rule.setFont(new Font("맑은고딕", Font.BOLD, 18));
		lbl_rule.setForeground(Color.BLUE);
		lbl_rule.setBounds(40, 130, 380, 50);
		lbl_rule.addMouseListener(this);
		pCenter.add(lbl_rule);

		// 동거인 생활 수칙
		lbl_togetherRule = new JLabel("<HTML><U>자가격리대상자의 가족 및 동거인을 위한 생활수칙</U></HTML>");
		lbl_togetherRule.setFont(new Font("맑은고딕", Font.BOLD, 18));
		lbl_togetherRule.setForeground(Color.BLUE);
		lbl_togetherRule.setBounds(40, 230, 420, 50);
		lbl_togetherRule.addMouseListener(this);
		pCenter.add(lbl_togetherRule);

		// 주요 증상
		lbl_symptoms = new JLabel("<HTML><U>코로나19 주요 증상</U></HTML>");
		lbl_symptoms.setFont(new Font("맑은고딕", Font.BOLD, 18));
		lbl_symptoms.setForeground(Color.BLUE);
		lbl_symptoms.setBounds(40, 330, 420, 50);
		lbl_symptoms.addMouseListener(this);
		pCenter.add(lbl_symptoms);

		// 모니터링
		lbl_monitoring = new JLabel("<HTML><U>자가모니터링 방법</U></HTML>");
		lbl_monitoring.setFont(new Font("맑은고딕", Font.BOLD, 18));
		lbl_monitoring.setForeground(Color.BLUE);
		lbl_monitoring.setBounds(40, 430, 420, 50);
		lbl_monitoring.addMouseListener(this);
		pCenter.add(lbl_monitoring);

		// 모든 패널 붙이기
		pLast = new JPanel();
		pLast.setLayout(new BorderLayout());
		pLast.setBackground(Color.white);

		pLast.add(pUp, BorderLayout.NORTH);
		pLast.add(pCenter, BorderLayout.CENTER);

		add(pLast);

		setVisible(true);
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
//		new IsolatedMain_Modify("자가격리자", 510, 720, this);
	}

	// 이벤트
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == btnLogout) {
			// 로그아웃 버튼
			this.dispose();
		}
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
		} else if (obj == lbl_symptoms) {
			lbl_symptoms.setForeground(new Color(0x8B008B));
			new IsolatedSympthoms();
		} else if (obj == lbl_monitoring) {
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
