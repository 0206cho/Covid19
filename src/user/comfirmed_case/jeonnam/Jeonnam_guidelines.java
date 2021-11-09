package user.comfirmed_case.jeonnam;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Jeonnam_guidelines extends JFrame {
	
	private JLabel lblicon;
	private JPanel p1;

	//JFrame을 상속 받아 만드는 방법 << 이걸 더 선호함.
	public Jeonnam_guidelines(String title, int width, int height) {
		this.setTitle(title);
		setSize(width, height);
		setLocationRelativeTo(this); 	//화면 가운데 찍음
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //닫을수 있는 특정 상수값을 주었기 때문에 프레임 종료버튼이 클릭될때 프로그램도 같이 사라짐 
		
		p1 = new JPanel();
		p1.setBackground(Color.white);
		ImageIcon icon = new ImageIcon("images/1.0.JPG");
		lblicon = new JLabel(icon);
		lblicon.setToolTipText("밤낮없이 방역을 위해 고생하시는 관계자분들 응원합니다!!");
		p1.add(lblicon);
		
		add(p1);
		setVisible(true);
		
		
	}
	
	public static void main(String[] args) {
		new Jeonnam_guidelines("전남 방역지침", 535, 570);
	}

}
