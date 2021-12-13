package user.isolated;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import basic.Client;

public class IsolatedCheckList extends JFrame implements ActionListener {

	private JPanel pUp, pCenter, pLast;
	private JLabel lblicon, lblCheck, lblCheck_2, lblHeat, lblCough, lblST, lblDyspnea, lblNote, lblBT, lbltext;
	private Color color, colorbtn;
	private JButton btnHeatYes, btnHeatNo, btnCoughYes, btnCoughNo, btnSTYes, btnSTNo, btnDyspneaYes, btnDyspneaNo,
			btnExit;
	private hint taNotee;
	
	private boolean heat, cough, st, dyspnea = false;
	
	private JTextField tfBT;
	private LineBorder bb = new LineBorder(Color.black, 1, true);
	private String formatedNow;
	private String iso_id;
	private IsolatedMain isolatedMain;
	
	public String getIso_id() {
		return isolatedMain.getGet_id();
	}

	public String getFormatedNow() {
		return formatedNow;
	}

	public hint getTaNotee() {
		return taNotee;
	}

	public JTextField getTfBT() {
		return tfBT;
	}

	public boolean isHeat() {
		return heat;
	}

	public boolean isCough() {
		return cough;
	}

	public boolean isSt() {
		return st;
	}

	public boolean isDyspnea() {
		return dyspnea;
	}

	

//JFrame을 상속 받아 만드는 방법 << 이걸 더 선호함.
	public IsolatedCheckList(IsolatedMain isolatedMain) {
		this.isolatedMain = isolatedMain;
		this.setTitle("자가진단하기");
		setSize(510, 750);
		setLocationRelativeTo(this); // 화면 가운데 찍음
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 닫을수 있는 특정 상수값을 주었기 때문에 프레임 종료버튼이 클릭될때 프로그램도 같이 사라짐
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

		colorbtn = new Color(0xFF87CEFA);

		// 중앙패널
		color = new Color(0xE6FFFF);

		pCenter = new JPanel();
		pCenter.setLayout(null);
//   		pCenter.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		pCenter.setBackground(color);

		lblCheck = new JLabel("자가진단 하기");

		lblCheck.setFont(new Font("맑은고딕", Font.BOLD, 23));
		lblCheck.setBounds(45, 10, 400, 25); // 위치지정
		lblCheck.setHorizontalAlignment(JLabel.CENTER); // 가운데 정렬
		pCenter.add(lblCheck);

		lblCheck_2 = new JLabel("오늘 발생한 증상을 선택하세요.");
		lblCheck_2.setBounds(45, 40, 400, 25); // 위치지정
		lblCheck_2.setFont(new Font("맑은고딕", Font.BOLD, 19));
		lblCheck_2.setHorizontalAlignment(JLabel.CENTER); // 가운데 정렬
		pCenter.add(lblCheck_2);

		// 증상 - 열
		lblHeat = new JLabel("열(37.5°C 이상) 또는 발열감");
		lblHeat.setBounds(20, 85, 280, 25); // 위치지정
		lblHeat.setFont(new Font("맑은고딕", Font.BOLD, 18));
		pCenter.add(lblHeat);

		btnHeatYes = new JButton("예");
		btnHeatYes.setBackground(color.WHITE);
		btnHeatYes.addActionListener(this);
		btnHeatYes.setFont(new Font("맑은고딕", Font.BOLD, 18));
		btnHeatYes.setBounds(20, 110, 230, 45); // 위치지정
		pCenter.add(btnHeatYes);

		btnHeatNo = new JButton("아니오");
		btnHeatNo.setBackground(color.WHITE);
		btnHeatNo.addActionListener(this);
		btnHeatNo.setFont(new Font("맑은고딕", Font.BOLD, 18));
		btnHeatNo.setBounds(250, 110, 230, 45); // 위치지정
		pCenter.add(btnHeatNo);

		lblBT = new JLabel(" 체온 :                 °C");
		lblBT.setOpaque(true);
		lblBT.setBackground(color.white); // 배경색 지정
		lblBT.setBorder(bb);
		lblBT.setBounds(20, 160, 460, 30); // 위치지정
		lblBT.setFont(new Font("맑은고딕", Font.BOLD, 18));
		pCenter.add(lblBT);

		tfBT = new JTextField();
		tfBT.setBounds(80, 163, 80, 25);
		tfBT.setFont(new Font("맑은고딕", Font.BOLD, 18));
		tfBT.setHorizontalAlignment(JTextField.RIGHT);
		pCenter.add(tfBT);

		// 증상 - 기침
		lblCough = new JLabel("기침(Cough)");
		lblCough.setBounds(20, 205, 280, 25); // 위치지정
		lblCough.setFont(new Font("맑은고딕", Font.BOLD, 18));
		pCenter.add(lblCough);

		btnCoughYes = new JButton("예");
		btnCoughYes.setBackground(color.WHITE);
		btnCoughYes.addActionListener(this);
		btnCoughYes.setFont(new Font("맑은고딕", Font.BOLD, 18));
		btnCoughYes.setBounds(20, 230, 230, 45); // 위치지정
		pCenter.add(btnCoughYes);

		btnCoughNo = new JButton("아니오");
		btnCoughNo.setBackground(color.WHITE);
		btnCoughNo.addActionListener(this);
		btnCoughNo.setFont(new Font("맑은고딕", Font.BOLD, 18));
		btnCoughNo.setBounds(250, 230, 230, 45); // 위치지정
		pCenter.add(btnCoughNo);

		// 증상 - 인후통
		lblST = new JLabel("인후통(목아픔)(Sore Throat)");
		lblST.setBounds(20, 285, 280, 25); // 위치지정
		lblST.setFont(new Font("맑은고딕", Font.BOLD, 18));
		pCenter.add(lblST);

		btnSTYes = new JButton("예");
		btnSTYes.setBackground(color.WHITE);
		btnSTYes.addActionListener(this);
		btnSTYes.setFont(new Font("맑은고딕", Font.BOLD, 18));
		btnSTYes.setBounds(20, 310, 230, 45); // 위치지정
		pCenter.add(btnSTYes);

		btnSTNo = new JButton("아니오");
		btnSTNo.setBackground(color.WHITE);
		btnSTNo.addActionListener(this);
		btnSTNo.setFont(new Font("맑은고딕", Font.BOLD, 18));
		btnSTNo.setBounds(250, 310, 230, 45); // 위치지정
		pCenter.add(btnSTNo);

		// 증상 - 호흡곤란
		lblDyspnea = new JLabel("호흡곤란(숨가쁨)(Dyspnea)");
		lblDyspnea.setBounds(20, 365, 280, 25); // 위치지정
		lblDyspnea.setFont(new Font("맑은고딕", Font.BOLD, 18));
		pCenter.add(lblDyspnea);

		btnDyspneaYes = new JButton("예");
		btnDyspneaYes.setBackground(color.WHITE);
		btnDyspneaYes.addActionListener(this);
		btnDyspneaYes.setFont(new Font("맑은고딕", Font.BOLD, 18));
		btnDyspneaYes.setBounds(20, 390, 230, 45); // 위치지정
		pCenter.add(btnDyspneaYes);

		btnDyspneaNo = new JButton("아니오");
		btnDyspneaNo.setBackground(color.WHITE);
		btnDyspneaNo.addActionListener(this);
		btnDyspneaNo.setFont(new Font("맑은고딕", Font.BOLD, 18));
		btnDyspneaNo.setBounds(250, 390, 230, 45); // 위치지정
		pCenter.add(btnDyspneaNo);

		// 특이사항
		lblNote = new JLabel("특이사항");
		lblNote.setBounds(20, 450, 280, 25); // 위치지정
		lblNote.setFont(new Font("맑은고딕", Font.BOLD, 18));
		pCenter.add(lblNote);

		taNotee = new hint("최대 200자");
		taNotee.setBounds(20, 475, 450, 60); // 위치지정
		pCenter.add(taNotee);

		// 설명
		lbltext = new JLabel("※ 자가진단결과 및 특이사항은 전담공무원에게 전달됩니다.");
		lbltext.setBounds(20, 530, 450, 60); // 위치지정
		lbltext.setFont(new Font("맑은고딕", Font.BOLD, 16));
		pCenter.add(lbltext);

		// 마침
		btnExit = new JButton("마침");
		btnExit.setBackground(colorbtn);
		btnExit.addActionListener(this);
		btnExit.setFont(new Font("맑은고딕", Font.BOLD, 18));
		btnExit.setBounds(20, 580, 450, 45); // 위치지정
		pCenter.add(btnExit);

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
	}

	public static void main(String[] args) {
		//new IsolatedCheckList(IsolatedMain isolatedMain);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object obj = e.getSource();

		if (obj == btnHeatYes) {
			btnHeatYes.setBackground(colorbtn);
			btnHeatNo.setBackground(color.WHITE);
			heat = true;
		}
		if (obj == btnHeatNo) {
			btnHeatNo.setBackground(colorbtn);
			btnHeatYes.setBackground(color.WHITE);
			heat = false;
		}

		if (obj == btnCoughYes) {
			btnCoughYes.setBackground(colorbtn);
			btnCoughNo.setBackground(color.WHITE);
			cough = true;
		}
		if (obj == btnCoughNo) {
			btnCoughNo.setBackground(colorbtn);
			btnCoughYes.setBackground(color.WHITE);
			cough = false;
		}

		if (obj == btnSTYes) {
			btnSTYes.setBackground(colorbtn);
			btnSTNo.setBackground(color.WHITE);
			st = true;
		}
		if (obj == btnSTNo) {
			btnSTNo.setBackground(colorbtn);
			btnSTYes.setBackground(color.WHITE);
			st = false;
		}

		if (obj == btnDyspneaYes) {
			btnDyspneaYes.setBackground(colorbtn);
			btnDyspneaNo.setBackground(color.WHITE);
			dyspnea = true;
		}
		if (obj == btnDyspneaNo) {
			btnDyspneaNo.setBackground(colorbtn);
			btnDyspneaYes.setBackground(color.WHITE);
			dyspnea = false;
		}

		if (obj == btnExit) {
			// 현재 날짜 시간
			LocalDateTime now = LocalDateTime.now();
			
			// 포맷팅
			formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd / HH:mm"));
			
			// 포맷팅 현재 날짜/시간 출력
			//System.out.println(formatedNow); // 년-월-일 / 시:분

			new Client(this);			
			dispose();

		}
	}

}