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

public class IsolatedTogether extends JFrame implements ActionListener {
	private JPanel pUp, pCenter, pLast;
	private JLabel lblicon, lblTogether;
	private Color color;

// JFrame을 불러옴
	public IsolatedTogether() {
		this.setTitle("자가격리대상자의 가족 및 동거인을 위한 생활수칙");
		setSize(480, 600);
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
		
		lblTogether = new JLabel("<HTML>◆자가격리대상자의 가족 및 동거인을 위한 생활수칙◆<BR/><BR/>"
				+ "  ▶ 가족 또는 동거인은 최대한 자가격리대상자와<BR/>"
				+ "      접촉하지 않도록 하기<BR/>"
				+ "      - 특히, 노인, 임산부, 소아, 만성질환, 암 등<BR/>"
				+ "       면역력이 저하된 분은 접촉 금지하기<BR/>"
				+ "      - 외부인의 방문 제한하기<BR/>"
				+ "  ▶ 자가격리대상자와 불가피하게 접촉할 경우<BR/>"
				+ "      마스크를 쓰고 서로 2m이상의 거리를 두기<BR/>"
				+ "  ▶ 자가격리대상자와 독립된 공간에서 생활하시고,<BR/>"
				+ "      공용으로 사용하는 공간은 자주 환기를 시키기<BR/>"
				+ "  ▶ 비누 또는 손세정제를 이용하여<BR/>"
				+ "      흐르는 물에 30초 이상 자주 손 씻기<BR/>"
				+ "  ▶ 자가격리대상자와 생활용품을 구분하여 사용하기<BR/>"
				+ "      - 자가격리대상자의 의복 및 침구류는 단독세탁<BR/>"
				+ "      - 자가격리대상자의 식기류 등은 별도로 분리하여<BR/>"
				+ "       깨끗이 씻기 전에 다른 사람이 사용하지 않도록 하기<BR/>"
				+ "  ▶ 테이블 위, 문손잡이, 욕실기구, 키보드, 침대 옆 테이블 등<BR/>"
				+ "      손길이 많이 닿는 곳의 표면 자주 닦기<BR/>"
				+ "  ▶ 자가격리대상자의 건강상태를 주의 깊게 관찰하기<BR/></HTML>");
		
		lblTogether.setFont(new Font("맑은고딕", Font.BOLD, 14));
		pCenter.add(lblTogether);

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
		new IsolatedTogether(); // MyFrame() 생성자 불러옴
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
