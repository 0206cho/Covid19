package user.isolated;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class IsolatedSympthoms extends JFrame implements ActionListener {
	private JPanel pUp, pCenter, pLast;
	private JLabel lblicon, lblSymptom;
	private Color color;

// JFrame을 불러옴
	public IsolatedSympthoms() {
		this.setTitle("코로나19 주요 증상");
		setSize(500, 430);
		setLocationRelativeTo(this); // 내 자신으로부터 상대적인 위치 잡음
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
	

		// 중앙패널
		color = new Color(0xE6FFFF);

		pCenter = new JPanel();
		pCenter.setLayout(new BorderLayout());
		pCenter.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));
		pCenter.setBackground(color);
		
		lblSymptom = new JLabel("<HTML><span style = 'font:bold 20px;'>◆코로나19 주요 증상◆</span><BR/><BR/><BR/>"
				+ "  ▶ 발열(37.5℃)<BR/><BR/>"
				+ "  ▶ 기침, 호흡곤란<BR/><BR/>"
				+ "  ▶ 오한, 근육통, 두통<BR/><BR/>"
				+ "  ▶ 인후통<BR/><BR/>"
				+ "  ▶ 후각, 미각소실<BR/><BR/>"
				+ "  ▶ 폐렴 등<BR/></HTML>");
		
		lblSymptom.setFont(new Font("맑은고딕", Font.PLAIN, 14));
		pCenter.add(lblSymptom);

		// 모든 패널 붙이기
		pLast = new JPanel();
		pLast.setLayout(new BorderLayout());
		pLast.setBackground(Color.white);

		pLast.add(pUp, BorderLayout.NORTH);
		pLast.add(pCenter, BorderLayout.CENTER);

		add(pLast);

		this.setVisible(true);
	}


	public static void main(String[] args) {
		new IsolatedSympthoms(); // MyFrame() 생성자 불러옴
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
