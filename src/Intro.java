

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import admin.AdminLogin;
import jdbc.DB;
import user.UserMain;

public class Intro extends JFrame implements ActionListener {

	private JButton b1;
	private JButton b2;

	public Intro(String title, int width, int height) {
		setTitle(title); //타이틀
		setSize(width, height); //크기
//		setLocation(500, 500); //실행위치
		setLocationRelativeTo(this); //모니터 가운데
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//프로그램 종료
		setResizable(false);
		
		//레이아웃
		setLayout(new BorderLayout());
		
		
		ImageIcon icon = new ImageIcon("images/KDCA.png");
		JLabel lblimg = new JLabel(icon);
		lblimg.setToolTipText("밤낮없이 방역을 위해 고생하시는 관계자분들 응원합니다!!");
		
		
		JPanel cenPen = new JPanel();
		cenPen.setLayout(null);
		lblimg.setSize(1200, 600);
		
		
		
		JLabel lbl1 = new JLabel("코로나19 종합 관리 시스템");
		lbl1.setFont(new Font("맑은 고딕", Font.PLAIN, 60));
		lbl1.setBounds(300, 30, 800, 100);
		
		
		cenPen.add(lbl1);
		
		
		
		b1 = new JButton("이용자");
		b1.setBounds(325, 470, 240, 80);
		b1.setFont(new Font("맑은 고딕", Font.PLAIN, 45));
		b1.setBackground(Color.WHITE);
		b1.setFocusPainted(false);
		b1.addActionListener(this);
		cenPen.add(b1);
		
		
		
		b2 = new JButton("관리자");
		b2.setBounds(665, 470, 240, 80);
		b2.setFont(new Font("맑은 고딕", Font.PLAIN, 45));
		b2.setBackground(Color.WHITE);
		b2.setFocusPainted(false);
		b2.addActionListener(this);
		cenPen.add(b2);
		cenPen.add(lblimg);
		add(cenPen);
		

		
		
		
		setVisible(true); //보이게
		
	}
	


	public static void main(String[] args) {
		try {
			DB.init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new Intro("코로나 종합 관리 시스템", 1210, 630);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == b1) {
			new UserMain("이용자", 1020, 420);
		}
		else if (obj == b2) {
			new AdminLogin("관리자 로그인", 710, 520);
		}
	}

}
