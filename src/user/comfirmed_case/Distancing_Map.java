package user.comfirmed_case;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.AttributeSet.ColorAttribute;

import jdbc.DB;
import user.UserMain;
import user.comfirmed_case.busan.Busan_Comfirmed;
import user.comfirmed_case.chungbuk.Chungbuk_Comfirmed;
import user.comfirmed_case.chungnam.Chungnam_Comfirmed;
import user.comfirmed_case.daegu.Daegu_Comfirmed;
import user.comfirmed_case.daejeon.Daejeon_Comfirmed;
import user.comfirmed_case.gangwon.Gangwon_Comfirmed;
import user.comfirmed_case.gwangju.Gwangju_Comfirmed;
import user.comfirmed_case.gyeongbuk.Gyeongbuk_Comfirmed;
import user.comfirmed_case.gyeonggi.Gyeonggi_Comfirmed;
import user.comfirmed_case.gyeongnam.Gyeongnam_Comfirmed;
import user.comfirmed_case.incheon.Incheon_Comfirmed;
import user.comfirmed_case.jeju.Jeju_Comfirmed;
import user.comfirmed_case.jeonbuk.Jeonbuk_Comfirmed;
import user.comfirmed_case.jeonnam.Jeonnam_Comfirmed;
import user.comfirmed_case.sejong.Sejong_Comfirmed;
import user.comfirmed_case.seoul.Seoul_Comfirmed;
import user.comfirmed_case.ulsan.Ulsan_Comfirmed;

public class Distancing_Map extends JFrame implements ActionListener {
	
	private JPanel p1;
	private JLabel lblicon;
	private JButton b1;
	private JButton b2;
	private JButton b3;
	private JLabel lbl1;
	private JPanel p2;
	private JButton b4;
	private JButton b5;
	private JButton b6;
	private String localName;
	private String lvlNum;
	private JButton b7;
	private JButton b8;
	private JButton b9;
	private JButton b10;
	private JButton b11;
	private JButton b12;
	private JButton b13;
	private AbstractButton b14;
	private AbstractButton b15;
	private JButton b16;
	private JButton b17;
	
	//JFrame을 상속 받아 만드는 방법 << 이걸 더 선호함.
	public Distancing_Map(String title, int width, int height) {
		this.setTitle(title);
		setSize(width, height);
		setLocationRelativeTo(this); 	//화면 가운데 찍음
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //닫을수 있는 특정 상수값을 주었기 때문에 프레임 종료버튼이 클릭될때 프로그램도 같이 사라짐 
		
		
		
		p2 = new JPanel();
		p2.setLayout(new BorderLayout());
		ImageIcon icon = new ImageIcon("images/logo2.JPG");
		lblicon = new JLabel(icon);
		lblicon.setToolTipText("밤낮없이 방역을 위해 고생하시는 관계자분들 응원합니다!!");
		p2.setBackground(Color.white);
		p2.add(lblicon,BorderLayout.WEST);
		
		
		
		
		p1 = new JPanel();
		p1.setLayout(null);
		ImageIcon icon2 = new ImageIcon("images/map.JPG");
		lblicon = new JLabel(icon2);
		lblicon.setSize(650, 720);
		
		
		
		//서울
		localName = "서울";
		String lvlSeoul = setLevel(localName);
		b1 = new JButton("<html><body><center>"+ localName +"<br>"+ lvlSeoul + "</body></html>");
		b1.setBounds(70, 35, 120, 40);
		b1.setFocusPainted(false);
		b1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		b1.addActionListener(this);
		if(lvlSeoul.equals("1.0")) {
			//0xE6FFFF
			b1.setBackground(new Color(0xE6FFFF));
		}else if(lvlSeoul.equals("1.5")) {
			//5AAEFF
			b1.setBackground(new Color(0x5AAEFF));
		}else if(lvlSeoul.equals("2.0")) {
			//FFE400
			b1.setBackground(new Color(0xFFE400));
		}else if(lvlSeoul.equals("2.5")) {
			//FFBB00
			b1.setBackground(new Color(0xFFBB00));
		}else if(lvlSeoul.equals("3.0")) {
			//FF0000
			b1.setBackground(new Color(0xFF0000));
		}
		
		
		
		//인천
		localName = "인천";
		String lvlInC = setLevel(localName);
		b2 = new JButton("<html><body><center>"+ localName +"<br>"+ lvlInC + "</body></html>");
		b2.setBounds(35, 94, 120, 40);
		b2.setFocusPainted(false);
		b2.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		b2.addActionListener(this);
		if(lvlInC.equals("1.0")) {
			//0xE6FFFF
			b2.setBackground(new Color(0xE6FFFF));
		}else if(lvlInC.equals("1.5")) {
			//5AAEFF
			b2.setBackground(new Color(0x5AAEFF));
		}else if(lvlInC.equals("2.0")) {
			//FFE400
			b2.setBackground(new Color(0xFFE400));
		}else if(lvlInC.equals("2.5")) {
			//FFBB00
			b2.setBackground(new Color(0xFFBB00));
		}else if(lvlInC.equals("3.0")) {
			//FF0000
			b2.setBackground(new Color(0xFF0000));
		}
		
		//경기
		localName = "경기";
		String lvlGG = setLevel(localName);
		b3 = new JButton("<html><body><center>"+localName+"<br>"+ lvlGG + "</body></html>");
		b3.setBounds(28, 165, 113, 40);
		b3.setFocusPainted(false);
		b3.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		b3.addActionListener(this);
		if(lvlGG.equals("1.0")) {
			//0xE6FFFF
			b3.setBackground(new Color(0xE6FFFF));
		}else if(lvlGG.equals("1.5")) {
			//5AAEFF
			b3.setBackground(new Color(0x5AAEFF));
		}else if(lvlGG.equals("2.0")) {
			//FFE400
			b3.setBackground(new Color(0xFFE400));
		}else if(lvlGG.equals("2.5")) {
			//FFBB00
			b3.setBackground(new Color(0xFFBB00));
		}else if(lvlGG.equals("3.0")) {
			//FF0000
			b3.setBackground(new Color(0xFF0000));
		}
		
		//충남
		localName = "충청남도";
		String lvlCN = setLevel(localName);
		b4 = new JButton("<html><body><center>"+localName+"<br>"+ lvlCN + "</body></html>");
		b4.setBounds(20, 220, 113, 40);
		b4.setFocusPainted(false);
		b4.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		b4.addActionListener(this);
		if(lvlCN.equals("1.0")) {
			//0xE6FFFF
			b4.setBackground(new Color(0xE6FFFF));
		}else if(lvlCN.equals("1.5")) {
			//5AAEFF
			b4.setBackground(new Color(0x5AAEFF));
		}else if(lvlCN.equals("2.0")) {
			//FFE400
			b4.setBackground(new Color(0xFFE400));
		}else if(lvlCN.equals("2.5")) {
			//FFBB00
			b4.setBackground(new Color(0xFFBB00));
		}else if(lvlCN.equals("3.0")) {
			//FF0000
			b4.setBackground(new Color(0xFF0000));
		}
		
		//세종
		localName = "세종자치시";
		String lvlSJ = setLevel(localName);
		b5 = new JButton("<html><body><center>"+localName+"<br>"+ lvlSJ + "</body></html>");
		b5.setBounds(7, 280, 113, 40);
		b5.setFocusPainted(false);
		b5.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		b5.addActionListener(this);
		if(lvlSJ.equals("1.0")) {
			//0xE6FFFF
			b5.setBackground(new Color(0xE6FFFF));
		}else if(lvlSJ.equals("1.5")) {
			//5AAEFF
			b5.setBackground(new Color(0x5AAEFF));
		}else if(lvlSJ.equals("2.0")) {
			//FFE400
			b5.setBackground(new Color(0xFFE400));
		}else if(lvlSJ.equals("2.5")) {
			//FFBB00
			b5.setBackground(new Color(0xFFBB00));
		}else if(lvlSJ.equals("3.0")) {
			//FF0000
			b5.setBackground(new Color(0xFF0000));
		}
		
		
		
		//대전
		localName = "대전광역시";
		String lvlDJ = setLevel(localName);
		
		b6 = new JButton("<html><body><center>"+ localName +"<br>"+ lvlDJ + "</body></html>");
		b6.setBounds(7, 340, 113, 40);
		b6.setFocusPainted(false);
		b6.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		b6.addActionListener(this);
		if(lvlDJ.equals("1.0")) {
			//0xE6FFFF
			b6.setBackground(new Color(0xE6FFFF));
		}else if(lvlDJ.equals("1.5")) {
			//5AAEFF
			b6.setBackground(new Color(0x5AAEFF));
		}else if(lvlDJ.equals("2.0")) {
			//FFE400
			b6.setBackground(new Color(0xFFE400));
		}else if(lvlDJ.equals("2.5")) {
			//FFBB00
			b6.setBackground(new Color(0xFFBB00));
		}else if(lvlDJ.equals("3.0")) {
			//FF0000
			b6.setBackground(new Color(0xFF0000));
		}
		
		//전북
		localName = "전라북도";
		String lvlJB = setLevel(localName);
		
		b7 = new JButton("<html><body><center>"+ localName +"<br>"+ lvlJB + "</body></html>");
		b7.setBounds(7, 400, 113, 40);
		b7.setFocusPainted(false);
		b7.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		b7.addActionListener(this);
		if(lvlJB.equals("1.0")) {
			//0xE6FFFF
			b7.setBackground(new Color(0xE6FFFF));
		}else if(lvlJB.equals("1.5")) {
			//5AAEFF
			b7.setBackground(new Color(0x5AAEFF));
		}else if(lvlJB.equals("2.0")) {
			//FFE400
			b7.setBackground(new Color(0xFFE400));
		}else if(lvlJB.equals("2.5")) {
			//FFBB00
			b7.setBackground(new Color(0xFFBB00));
		}else if(lvlJB.equals("3.0")) {
			//FF0000
			b7.setBackground(new Color(0xFF0000));
		}
		
		//광주
		localName = "광주광역시";
		String lvlGJ = setLevel(localName);
		
		b8 = new JButton("<html><body><center>"+ localName +"<br>"+ lvlGJ + "</body></html>");
		b8.setBounds(7, 485, 113, 40);
		b8.setFocusPainted(false);
		b8.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		b8.addActionListener(this);
		if(lvlGJ.equals("1.0")) {
			//0xE6FFFF
			b8.setBackground(new Color(0xE6FFFF));
		}else if(lvlGJ.equals("1.5")) {
			//5AAEFF
			b8.setBackground(new Color(0x5AAEFF));
		}else if(lvlGJ.equals("2.0")) {
			//FFE400
			b8.setBackground(new Color(0xFFE400));
		}else if(lvlGJ.equals("2.5")) {
			//FFBB00
			b8.setBackground(new Color(0xFFBB00));
		}else if(lvlGJ.equals("3.0")) {
			//FF0000
			b8.setBackground(new Color(0xFF0000));
		}
		
		//전남
		localName = "전라남도";
		String lvlJN = setLevel(localName);
		
		b9 = new JButton("<html><body><center>"+ localName +"<br>"+ lvlJN + "</body></html>");
		b9.setBounds(70, 570, 113, 40);
		b9.setFocusPainted(false);
		b9.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		b9.addActionListener(this);
		if(lvlJN.equals("1.0")) {
			//0xE6FFFF
			b9.setBackground(new Color(0xE6FFFF));
		}else if(lvlJN.equals("1.5")) {
			//5AAEFF
			b9.setBackground(new Color(0x5AAEFF));
		}else if(lvlJN.equals("2.0")) {
			//FFE400
			b9.setBackground(new Color(0xFFE400));
		}else if(lvlJN.equals("2.5")) {
			//FFBB00
			b9.setBackground(new Color(0xFFBB00));
		}else if(lvlJN.equals("3.0")) {
			//FF0000
			b9.setBackground(new Color(0xFF0000));
		}
		
		//제주
		localName = "제주도";
		String lvlJJ = setLevel(localName);
		
		b10 = new JButton("<html><body><center>"+ localName +"<br>"+ lvlJJ + "</body></html>");
		b10.setBounds(268, 675, 113, 40);
		b10.setFocusPainted(false);
		b10.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		b10.addActionListener(this);
		if(lvlJJ.equals("1.0")) {
			//0xE6FFFF
			b10.setBackground(new Color(0xE6FFFF));
		}else if(lvlJJ.equals("1.5")) {
			//5AAEFF
			b10.setBackground(new Color(0x5AAEFF));
		}else if(lvlJJ.equals("2.0")) {
			//FFE400
			b10.setBackground(new Color(0xFFE400));
		}else if(lvlJJ.equals("2.5")) {
			//FFBB00
			b10.setBackground(new Color(0xFFBB00));
		}else if(lvlJJ.equals("3.0")) {
			//FF0000
			b10.setBackground(new Color(0xFF0000));
		}
		
		//경남
		localName = "경상남도";
		String lvlGN = setLevel(localName);
		
		b11 = new JButton("<html><body><center>"+ localName +"<br>"+ lvlGN + "</body></html>");
		b11.setBounds(320, 559, 113, 40);
		b11.setFocusPainted(false);
		b11.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		b11.addActionListener(this);
		if(lvlGN.equals("1.0")) {
			//0xE6FFFF
			b11.setBackground(new Color(0xE6FFFF));
		}else if(lvlGN.equals("1.5")) {
			//5AAEFF
			b11.setBackground(new Color(0x5AAEFF));
		}else if(lvlGN.equals("2.0")) {
			//FFE400
			b11.setBackground(new Color(0xFFE400));
		}else if(lvlGN.equals("2.5")) {
			//FFBB00
			b11.setBackground(new Color(0xFFBB00));
		}else if(lvlGN.equals("3.0")) {
			//FF0000
			b11.setBackground(new Color(0xFF0000));
		}
		
		//부산
		localName = "부산";
		String lvlBS = setLevel(localName);
		
		b12 = new JButton("<html><body><center>"+ localName +"<br>"+ lvlBS + "</body></html>");
		b12.setBounds(400, 515, 113, 40);
		b12.setFocusPainted(false);
		b12.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		b12.addActionListener(this);
		if(lvlBS.equals("1.0")) {
			//0xE6FFFF
			b12.setBackground(new Color(0xE6FFFF));
		}else if(lvlBS.equals("1.5")) {
			//5AAEFF
			b12.setBackground(new Color(0x5AAEFF));
		}else if(lvlBS.equals("2.0")) {
			//FFE400
			b12.setBackground(new Color(0xFFE400));
		}else if(lvlBS.equals("2.5")) {
			//FFBB00
			b12.setBackground(new Color(0xFFBB00));
		}else if(lvlBS.equals("3.0")) {
			//FF0000
			b12.setBackground(new Color(0xFF0000));
		}
		
		//울산
		localName = "울산";
		String lvlUS = setLevel(localName);
		
		b13 = new JButton("<html><body><center>"+ localName +"<br>"+ lvlUS + "</body></html>");
		b13.setBounds(501, 464, 113, 40);
		b13.setFocusPainted(false);
		b13.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		b13.addActionListener(this);
		if(lvlUS.equals("1.0")) {
			//0xE6FFFF
			b13.setBackground(new Color(0xE6FFFF));
		}else if(lvlUS.equals("1.5")) {
			//5AAEFF
			b13.setBackground(new Color(0x5AAEFF));
		}else if(lvlUS.equals("2.0")) {
			//FFE400
			b13.setBackground(new Color(0xFFE400));
		}else if(lvlUS.equals("2.5")) {
			//FFBB00
			b13.setBackground(new Color(0xFFBB00));
		}else if(lvlUS.equals("3.0")) {
			//FF0000
			b13.setBackground(new Color(0xFF0000));
		}
		
		//대구
		localName = "대구";
		String lvlDK = setLevel(localName);
		
		b14 = new JButton("<html><body><center>"+ localName +"<br>"+ lvlDK + "</body></html>");
		b14.setBounds(528, 360, 113, 40);
		b14.setFocusPainted(false);
		b14.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		b14.addActionListener(this);
		if(lvlDK.equals("1.0")) {
			//0xE6FFFF
			b14.setBackground(new Color(0xE6FFFF));
		}else if(lvlDK.equals("1.5")) {
			//5AAEFF
			b14.setBackground(new Color(0x5AAEFF));
		}else if(lvlDK.equals("2.0")) {
			//FFE400
			b14.setBackground(new Color(0xFFE400));
		}else if(lvlDK.equals("2.5")) {
			//FFBB00
			b14.setBackground(new Color(0xFFBB00));
		}else if(lvlDK.equals("3.0")) {
			//FF0000
			b14.setBackground(new Color(0xFF0000));
		}
		
		//경북
		localName = "경상북도";
		String lvlGB = setLevel(localName);
		
		b15 = new JButton("<html><body><center>"+ localName +"<br>"+ lvlGB + "</body></html>");
		b15.setBounds(528, 248, 113, 40);
		b15.setFocusPainted(false);
		b15.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		b15.addActionListener(this);
		if(lvlGB.equals("1.0")) {
			//0xE6FFFF
			b15.setBackground(new Color(0xE6FFFF));
		}else if(lvlGB.equals("1.5")) {
			//5AAEFF
			b15.setBackground(new Color(0x5AAEFF));
		}else if(lvlGB.equals("2.0")) {
			//FFE400
			b15.setBackground(new Color(0xFFE400));
		}else if(lvlGB.equals("2.5")) {
			//FFBB00
			b15.setBackground(new Color(0xFFBB00));
		}else if(lvlGB.equals("3.0")) {
			//FF0000
			b15.setBackground(new Color(0xFF0000));
		}
		
		//충북
		localName = "충청북도";
		String lvlCB = setLevel(localName);
		
		b16 = new JButton("<html><body><center>"+ localName +"<br>"+ lvlCB + "</body></html>");
		b16.setBounds(507, 141, 113, 40);
		b16.setFocusPainted(false);
		b16.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		b16.addActionListener(this);
		if(lvlCB.equals("1.0")) {
			//0xE6FFFF
			b16.setBackground(new Color(0xE6FFFF));
		}else if(lvlCB.equals("1.5")) {
			//5AAEFF
			b16.setBackground(new Color(0x5AAEFF));
		}else if(lvlCB.equals("2.0")) {
			//FFE400
			b16.setBackground(new Color(0xFFE400));
		}else if(lvlCB.equals("2.5")) {
			//FFBB00
			b16.setBackground(new Color(0xFFBB00));
		}else if(lvlCB.equals("3.0")) {
			//FF0000
			b16.setBackground(new Color(0xFF0000));
		}
		
		//강원
		localName = "강원도";
		String lvlGWD = setLevel(localName);
		
		b17 = new JButton("<html><body><center>"+ localName +"<br>"+ lvlGWD + "</body></html>");
		b17.setBounds(454, 49, 113, 40);
		b17.setFocusPainted(false);
		b17.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		b17.addActionListener(this);
		if(lvlGWD.equals("1.0")) {
			//0xE6FFFF
			b17.setBackground(new Color(0xE6FFFF));
		}else if(lvlGWD.equals("1.5")) {
			//5AAEFF
			b17.setBackground(new Color(0x5AAEFF));
		}else if(lvlGWD.equals("2.0")) {
			//FFE400
			b17.setBackground(new Color(0xFFE400));
		}else if(lvlGWD.equals("2.5")) {
			//FFBB00
			b17.setBackground(new Color(0xFFBB00));
		}else if(lvlGWD.equals("3.0")) {
			//FF0000
			b17.setBackground(new Color(0xFF0000));
		}
		
		p1.add(b1);
		p1.add(b2);
		p1.add(b3);
		p1.add(b4);
		p1.add(b5);
		p1.add(b6);
		p1.add(b7);
		p1.add(b8);
		p1.add(b9);
		p1.add(b10);
		p1.add(b11);
		p1.add(b12);
		p1.add(b13);
		p1.add(b14);
		p1.add(b15);
		p1.add(b16);
		p1.add(b17);
		
		p1.add(lblicon);
		
		
		
		
		
		add(p2,BorderLayout.NORTH);
		add(p1);
		
		
		
		
		
		setVisible(true);
		
		
	}
	
	private String setLevel(String local) {
		String sql = "SELECT levelNum FROM level WHERE local='" + local + "' ";
		try {
			ResultSet rs = DB.getResultSet(sql);
			
			while(rs.next()) {
				lvlNum = rs.getString(1);
			}

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lvlNum;
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
		new Distancing_Map("지역별 거리두기 단계", 665, 830);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == b1) {
			new Seoul_Comfirmed("서울 확진자 현황", 800, 615);
		}else if(obj == b2) {
			new Incheon_Comfirmed("인천 확진자 현황", 800, 615);
		}else if(obj == b3) {
			new Gyeonggi_Comfirmed("경기 확진자 현황", 800, 615);
		}else if(obj == b4) {
			new Chungnam_Comfirmed("충남 확진자 현황", 800, 615);
		}else if(obj == b5) {
			new Sejong_Comfirmed("세종 확진자 현황", 800, 615);
		}else if(obj == b6) {
			new Daejeon_Comfirmed("대전 확진자 현황", 800, 615);
		}else if(obj == b7) {
			new Jeonbuk_Comfirmed("전북 확진자 현황", 800, 615);
		}else if(obj == b8) {
			new Gwangju_Comfirmed("광주 확진자 현황", 800, 615);
		}else if(obj == b9) {
			new Jeonnam_Comfirmed("전남 확진자 현황", 800, 615);
		}else if(obj == b10) {
			new Jeju_Comfirmed("제주 확진자 현황", 800, 615);
		}else if(obj == b11) {
			new Gyeongnam_Comfirmed("경남 확진자 현황", 800, 615);
		}else if(obj == b12) {
			new Busan_Comfirmed("부산 확진자 현황", 800, 615);
		}else if(obj == b13) {
			new Ulsan_Comfirmed("울산 확진자 현황", 800, 615);
		}else if(obj == b14) {
			new Daegu_Comfirmed("대구 확진자 현황", 800, 615);
		}else if(obj == b15) {
			new Gyeongbuk_Comfirmed("경북 확진자 현황", 800, 615);
		}else if(obj == b16) {
			new Chungbuk_Comfirmed("충북 확진자 현황", 800, 615);
		}else if(obj == b17) {
			new Gangwon_Comfirmed("강원 확진자 현황", 800, 615);
		}
	}

}
