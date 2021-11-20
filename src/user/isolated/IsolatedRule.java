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

public class IsolatedRule extends JFrame implements ActionListener {
	private JPanel pUp, pCenter, pLast;
	private JLabel lblicon, lblRule;
	private Color color;

// JFrame을 불러옴
	public IsolatedRule() {
		this.setTitle("자가격리 대상자 생활수칙");
		setSize(510, 700);
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
		
		lblRule = new JLabel("<html><span style = 'font:bold 20px;'>◆자가격리 대상자 생활수칙◆</span><br/><br/><br/>"
				+ "▶ 감염 전파 방지를 위해 격리장소 바깥 외출금지<br/><br/>"
				+ "▶ 독립된 공간에서 혼자 생활하기<br/>"
				+ "      - 방문 닫은 채로 창문을 열어 자주 환기시키고, 식사는 혼자서 하기<br/>"
				+ "      - 가능한 혼자만 사용할 수 있는 화장실과 세면대가 있는 공간 사용하기<br/>"
				+ "          *공용 화장실, 세면대를 사용한다면, 사용 후 소독<br/>"
				+ "           (락스 등 가정용소독제)하고 다른 사람이 사용하도록 합니다.<br/><br/>"
				+ "  ▶ 진료 등 외출이 불가피할 경우 반드시 관할 보건소에<br/>" 
				+ "      먼저 연락하기<br/><br/>"
				+ "  ▶ 가족 또는 함께 거주하는 분과 대화 등 접촉하지 않기<br/>"
				+ "      - 불가피한 경우, 얼굴을 맞대지 않고<br/>"
				+ "       서로 마스크를 쓰고 2m이상의 거리를 두기<br/><br/>"
				+ "  ▶ 개인용품(개인용 수건, 식기류, 휴대전화 등)으로 사용하기<br/>"
				+ "      - 의복 및 침구류는 단독세탁<br/>"
				+ "      - 식기류 등은 별도로 분리하여 깨끗이 씻기 전에<br/>"
				+ "       다른 사람이 사용하지 않도록 하기<br/><br/>"
				+ "  ▶ 건강수칙 지키기<br/>"
				+ "      - 비누로 30초이상 흐르는 물에 손 씻기, 기침 등 호흡기증상이<br/>"
				+ "       있을 경우 마스크 착용, 마스크가 없으면 소매로 가려 기침하며<br/>"
				+ "       기침, 재채기 후 손 씻거나 손 소독 실시하기</html>");
		
		lblRule.setFont(new Font("맑은고딕", Font.PLAIN, 14));
		pCenter.add(lblRule, BorderLayout.CENTER);

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
		new IsolatedRule(); 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
