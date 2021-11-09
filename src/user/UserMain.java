package user;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import jdbc.DB;
import user.comfirmed_case.Distancing_Map;
import user.isolated.IsolatedLogin;
import user.medicalstation.Medicalstation;
import user.vaccine.VaccineIntro;

public class UserMain extends JFrame implements ActionListener  {
	
	
	
	private JLabel lblicon;
	private JPanel p1;
	private JPanel p2;
	private Graphics Graphics;
	private java.awt.Graphics g;
	private JButton b1;
	private JButton b2;
	private JButton b3;
	private JButton b4;
	private JButton b5;

	//JFrame을 상속 받아 만드는 방법 << 이걸 더 선호함.
	public UserMain(String title, int width, int height) {
		this.setTitle(title);
		setSize(width, height);
		setLocationRelativeTo(this); 	//화면 가운데 찍음
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //닫을수 있는 특정 상수값을 주었기 때문에 프레임 종료버튼이 클릭될때 프로그램도 같이 사라짐 
		
		p1 = new JPanel();
		p1.setLayout(new BorderLayout());
		ImageIcon icon = new ImageIcon("images/logo2.JPG");
		lblicon = new JLabel(icon);
		lblicon.setToolTipText("밤낮없이 방역을 위해 고생하시는 관계자분들 응원합니다!!");
		//lblicon.setPreferredSize(new Dimension(150, 150));
		p1.setBackground(Color.white);
		p1.add(lblicon,BorderLayout.WEST);
		
		
		
		
		p2 = new JPanel();
		p2.setBackground(Color.WHITE);
		p2.setLayout(null);
		
		
		
		b1 = new JButton("확진자현황");
		b1.setBounds(230, 100, 120, 100);
		b1.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		b1.setFocusPainted(false);
		b1.setBackground(Color.white);
		b1.addActionListener(this);
		
		b2 = new JButton("백신예약");
		b2.setBounds(380, 100, 120, 100);
		b2.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		b2.setFocusPainted(false);
		b2.setBackground(Color.white);
		b2.addActionListener(this);
		
		b3 = new JButton("선별진료소");
		b3.setBounds(530, 100, 120, 100);
		b3.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		b3.setFocusPainted(false);
		b3.setBackground(Color.white);
		b3.addActionListener(this);
		
		
		b5 = new JButton("자가격리자");
		b5.setBounds(680, 100, 120, 100);
		b5.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		b5.setFocusPainted(false);
		b5.setBackground(Color.white);
		b5.addActionListener(this);
		
		
		p2.add(b1);
		p2.add(b2);
		p2.add(b3);
		p2.add(b5);
		
		
		
		add(p1,BorderLayout.NORTH);
		add(p2);
		setVisible(true);
		
		
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
		new UserMain("이용자", 1020, 420);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
				if(obj == b1) {
					new Distancing_Map("지역별 거리두기 단계", 665, 830);
				}
				else if(obj == b2) {
					new VaccineIntro("백신 예약 및 조회", 800, 600);
				}
				else if(obj == b3) {
					new Medicalstation("선별 진료소 목록", 1800, 870);
				}
				else if(obj == b5) {
					new IsolatedLogin("자가격리자 로그인", 710, 520);
				}
	}

}
