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

public class IsolatedMonitoring extends JFrame implements ActionListener {
	private JPanel pUp, pCenter, pLast;
	private JLabel lblicon, lblMonitoring;
	private Color color;

// JFrame을 불러옴
	public IsolatedMonitoring() {
		this.setTitle("자가모니터링 방법");
		setSize(500, 400);
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
		
		lblMonitoring = new JLabel("<html>◆자가모니터링 방법◆<br/><br/><br/>"
				+ "▶ 매일 아침, 저녁으로 체온 측정하기<br/><br/><br/>"
				+ "▶ 호흡기증상 등 감염 증상이 나타나는지 스스로 건강상태 확인<br/><br/><br/>"
				+ "▶ 보건소에서 1일 1회 이상 연락 시, 감염 증상 알려주기<br/></html>");
		lblMonitoring.setFont(new Font("맑은고딕", Font.BOLD, 14));
		pCenter.add(lblMonitoring);

		// 모든 패널 붙이기
		pLast = new JPanel();
		pLast.setLayout(new BorderLayout());
		pLast.setBackground(Color.white);

		pLast.add(pUp, BorderLayout.NORTH);
		pLast.add(pCenter, BorderLayout.CENTER);

		add(pLast);

		this.setVisible(true); // 화면에 보여지게 함
	}


	public static void main(String[] args) {
		new IsolatedMonitoring(); 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
