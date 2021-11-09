package user.isolated;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class IsolatedMain extends JFrame implements ActionListener {
	
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
	//생활수칙
	private String LivingRules = "◆자가격리 대상자 생활수칙◆\n" 
			+ "  ▶감염 전파 방지를 위해 격리장소 바깥 외출금지\n"
			+ "  ▶독립된 공간에서 혼자 생활하기\n"
			+ "      -방문 닫은 채로 창문을 열어 자주 환기시키고, 식사는 혼자서 하기\n"
			+ "      -가능한 혼자만 사용할 수 있는 화장실과\n"
			+ "       세면대가 있는 공간 사용하기\n"
			+ "          *공용 화장실, 세면대를 사용한다면, 사용 후 소독\n"
			+ "           (락스 등 가정용소독제)하고 다른 사람이 사용하도록 합니다.\n"
			+ "  ▶진료 등 외출이 불가피할 경우 반드시 관할 보건소에\n" 
			+ "      먼저 연락하기\n"
			+ "  ▶가족 또는 함께 거주하는 분과 대화 등 접촉하지 않기\n"
			+ "      -불가피한 경우, 얼굴을 맞대지 않고\n"
			+ "       서로 마스크를 쓰고 2m이상의 거리를 두기\n"
			+ "  ▶개인용품(개인용 수건, 식기류, 휴대전화 등)으로 사용하기\n"
			+ "      -의복 및 침구류는 단독세탁\n"
			+ "      -식기류 등은 별도로 분리하여 깨끗이 씻기 전에\n"
			+ "       다른 사람이 사용하지 않도록 하기\n"
			+ "  ▶건강수칙 지키기\n"
			+ "      -비누로 30초이상 흐르는 물에 손 씻기, 기침 등 호흡기증상이\n"
			+ "       있을 경우 마스크 착용, 마스크가 없으면 소매로 가려 기침하며\n"
			+ "       기침, 재채기 후 손 씻거나 손 소독 실시하기";
	//동거인생활수칙
	private String FLivingRules = "◆자가격리대상자의 가족 및 동거인을 위한 생활수칙◆\n"
			+ "  ▶가족 또는 동거인은 최대한 자가격리대상자와\n"
			+ "      접촉하지 않도록 하기\n"
			+ "      -특히, 노인, 임산부, 소아, 만성질환, 암 등\n"
			+ "       면역력이 저하된 분은 접촉 금지하기\n"
			+ "      -외부인의 방문 제한하기\n"
			+ "  ▶자가격리대상자와 불가피하게 접촉할 경\n"
			+ "      마스크를 쓰고 서로 2m이상의 거리를 두기\n"
			+ "  ▶자가격리대상자와 독립된 공간에서 생활하시고,\n"
			+ "      공용으로 사용하는 공간은 자주 환기를 시키기\n"
			+ "  ▶비누 또는 손세정제를 이용하여\n"
			+ "      흐르는 물에 30초 이상 자주 손 씻기\n"
			+ "  ▶자가격리대상자와 생활용품을 구분하여 사용하기\n"
			+ "      -자가격리대상자의 의복 및 침구류는 단독세탁\n"
			+ "      -자가격리대상자의 식기류 등은 별도로 분리하여\n"
			+ "       깨끗이 씻기 전에 다른 사람이 사용하지 않도록 하기\n"
			+ "  ▶테이블 위, 문손잡이, 욕실기구, 키보드, 침대 옆 테이블 등\n"
			+ "      손길이 많이 닿는 곳의 표면 자주 닦기\n"
			+ "  ▶자가격리대상자의 건강상태를 주의 깊게 관찰하기";
	//코로나19주요증상
	private String CovidSymptom = "◆코로나19 주요 증상◆\n"
			+ "  ▶발열(37.5℃)\n"
			+ "  ▶기침, 호흡곤란\n"
			+ "  ▶오한, 근육통, 두통\n"
			+ "  ▶인후통\n"
			+ "  ▶후각, 미각소실\n"
			+ "  ▶폐렴 등";
	//자가모니터링
	private String SelfMonitoring = "◆자가모니터링 방법◆\n"
			+ "  ▶매일 아침, 저녁으로 체온 측정하기\n"
			+ "  ▶호흡기증상 등 감염 증상이 나타나는지 스스로 건강상태 확인\n"
			+ "  ▶보건소에서 1일 1회 이상 연락 시, 감염 증상 알려주기";
	
	//JFrame을 상속 받아 만드는 방법 << 이걸 더 선호함.
	public IsolatedMain(String title, int width, int height) {
		this.setTitle(title);
		setSize(width, height);
		setLocationRelativeTo(this); 	//화면 가운데 찍음
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //닫을수 있는 특정 상수값을 주었기 때문에 프레임 종료버튼이 클릭될때 프로그램도 같이 사라짐 
		
		
		//상단 패널
		p1 = new JPanel();
		p1.setLayout(new BorderLayout());
		p1.setBackground(Color.white);
		
		ImageIcon icon = new ImageIcon("images/logo2.JPG");
		lblicon = new JLabel(icon);
		lblicon.setToolTipText("밤낮없이 방역을 위해 고생하시는 관계자분들 응원합니다!!");
		//lblicon.setPreferredSize(new Dimension(150, 150));
		
		p1.add(lblicon,BorderLayout.WEST);
		
		//로그아웃 버튼
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
		
		
		
		//중앙패널
		color1 = new Color(0xD4F4FA);
		color2 = new Color(0xE6FFFF);
//		color2 = new Color(0xF8FFFE);
		
		p2 = new JPanel();
		p2.setLayout(new BorderLayout());
		p2.setBackground(Color.white);
		
		ta = new JTextArea();
		ta.setBackground(color2);
		ta.setEditable(false);
		
		ta.setFont(new Font("맑은고딕", Font.BOLD, 14));
		ta.setLineWrap(true);  //벽에 닿으면 줄바꿈
//		ta.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT); //오른쪽 정렬
		ta.setText("[SYSTEM]: '!명령어'를 입력하여 명령어를 확인 하세요" + "\n");
		
		
		JScrollPane sp = new JScrollPane(ta, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
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
		
		
		
		
		
		
		//채팅창 하단
		//하단 패널
		chatSouthPen = new JPanel();
		chatSouthPen.setBackground(Color.WHITE);
		
		//입력창
		tx1 = new JTextField(18);
		tx1.setFont(new Font("맑은 고딕", 0, 17));
		tx1.addActionListener(this);
		
		
		//전송 버튼
		b1 = new JButton("전송");
		b1.setBackground(color2);
		b1.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		b1.addActionListener(this);
		
		chatSouthPen.add(tx1);
		chatSouthPen.add(b1);
		
		p2.add(chatSouthPen, BorderLayout.SOUTH);
		
		
		
		//
		

		

		
		
		//모든 패널 붙이기
		last = new JPanel();
		last.setLayout(new BorderLayout());
		last.setBackground(Color.white);
		
		last.add(p1,BorderLayout.NORTH);
		last.add(p2);
		
		add(last);
		
		setVisible(true);
		tx1.requestFocus();
		
		
	}
	public void paint (Graphics g) {
		super.paint(g);
		g.drawLine(0, 98, 1050, 98);
		g.drawLine(0, 675, 1050, 675);
		

	}
	public static void main(String[] args) {
		new IsolatedMain("자가격리자", 510, 720);
	}
	
	// 이벤트
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == b1 || obj == tx1) {
			String msg = tx1.getText();
			
			if(!msg.equals("")) {
				ta.append("[USER]: "+ tx1.getText() + "\n");
				if(msg.equals("!명령어")) {
					ta.append("[SYSTEM]: '!생활수칙', '!동거인생활수칙', '!주요증상', '!모니터링'" + "\n");
				}else if(msg.equals("!생활수칙")) {
					ta.append("[SYSTEM]: " + LivingRules +"\n");
				}else if(msg.equals("!동거인생활수칙")) {
					ta.append("[SYSTEM]: " + FLivingRules + "\n");
				}else if(msg.equals("!주요증상")) {
					ta.append("[SYSTEM]: " + CovidSymptom + "\n");
				}else if(msg.equals("!모니터링")) {
					ta.append("[SYSTEM]: " + SelfMonitoring + "\n");
				}else {
					ta.append("[SYSTEM]: 올바른 명령어를 입력해주세요" + "\n");
				}
				
				tx1.setText("");
				tx1.requestFocus();
			}
			
		} else if(obj==btnLogout) {
			// 로그아웃 버튼
			this.dispose();
			
		}
		
	}

}
