package user.vaccinationstatus;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.AttributeSet.ColorAttribute;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import jdbc.DB;
import user.UserMain;
import user.vaccinationstatus.busan.Busan_vaccinationstatus;
import user.vaccinationstatus.chungbuk.Chungbuk_vaccinationstatus;
import user.vaccinationstatus.chungnam.Chungnam_vaccinationstatus;
import user.vaccinationstatus.daegu.Daegu_vaccinationstatus;
import user.vaccinationstatus.daejeon.Daejeon_vaccinationstatus;
import user.vaccinationstatus.gangwon.Gangwon_vaccinationstatus;
import user.vaccinationstatus.gwangju.Gwangju_vaccinationstatus;
import user.vaccinationstatus.gyeongbuk.Gyeongbuk_vaccinationstatus;
import user.vaccinationstatus.gyeonggi.Gyeonggi_vaccinationstatus;
import user.vaccinationstatus.gyeongnam.Gyeongnam_vaccinationstatus;
import user.vaccinationstatus.incheon.Incheon_vaccinationstatus;
import user.vaccinationstatus.jeju.Jeju_vaccinationstatus;
import user.vaccinationstatus.jeonbuk.Jeonbuk_vaccinationstatus;
import user.vaccinationstatus.jeonnam.Jeonnam_vaccinationstatus;
import user.vaccinationstatus.sejong.Sejong_vaccinationstatus;
import user.vaccinationstatus.seoul.Seoul_vaccinationstatus;
import user.vaccinationstatus.ulsan.Ulsan_vaccinationstatus;

public class Distancing_Map extends JFrame implements ActionListener {
	
	private JPanel p1;
	private JLabel lblicon;
	private JButton seoul_btn;
	private JButton incheon_btn;
	private JButton gyeonggi_btn;
	private JLabel lbl1;
	private JPanel p2;
	private JButton chungnam_btn;
	private JButton seojong_btn;
	private JButton daejeon_btn;
	private String localName;
	private String lvlNum;
	private JButton jeonbuk_btn;
	private JButton gwangju_btn;
	private JButton jeonnam_btn;
	private JButton jeju_btn;
	private JButton gyeongnam_btn;
	private JButton busan_btn;
	private JButton ulsan_btn;
	private AbstractButton daegu_btn;
	private AbstractButton gyeongbuk_btn;
	private JButton chungbuk_btn;
	private JButton gangwon_btn;
	String url = "https://ncv.kdca.go.kr/mainStatus.es?mid=a11702000000";
	Document doc;
	Elements seoul_elm,busan_elm;
	private JLabel right_lbl;
	//JFrame??? ?????? ?????? ????????? ?????? << ?????? ??? ?????????.
	public Distancing_Map(String title, int width, int height) {
		this.setTitle(title);
		setSize(width, height);
		setLocationRelativeTo(this); 	//?????? ????????? ??????
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //????????? ?????? ?????? ???????????? ????????? ????????? ????????? ??????????????? ???????????? ??????????????? ?????? ????????? 
		
		
		
		p2 = new JPanel();
		p2.setLayout(new BorderLayout());
		ImageIcon icon = new ImageIcon("images/logo2.JPG");
		lblicon = new JLabel(icon);
		lblicon.setToolTipText("???????????? ????????? ?????? ??????????????? ??????????????? ???????????????!!");
		p2.setBackground(Color.white);
		right_lbl = new JLabel("<html><body><center>" +"<br>"+"<br>"+ "* ????????? 2??? ?????? ????????? ???????????????.&nbsp&nbsp"+ "</body></html>");

		right_lbl.setFont(new Font("?????? ??????", Font.BOLD, 13));
		p2.add(lblicon,BorderLayout.WEST);
		p2.add(right_lbl,BorderLayout.EAST);
		
		
		
		p1 = new JPanel();
		p1.setLayout(null);
		ImageIcon icon2 = new ImageIcon("images/map.JPG");
		lblicon = new JLabel(icon2);
		lblicon.setSize(650, 720);
		
		
		
		//??????
		localName = "??????";
		String lvlSeoul = setLevel(localName);
		seoul_btn = new JButton();
		seoul_btn.setBounds(70, 35, 120, 40);
		seoul_btn.setFocusPainted(false);
		seoul_btn.setFont(new Font("?????? ??????", Font.BOLD, 15));
		seoul_btn.addActionListener(this);
		seoul_btn.setBackground(new Color(0x87cefa));
		
		
		
		//??????
		localName = "??????";
		String lvlInC = setLevel(localName);
		incheon_btn = new JButton();
		incheon_btn.setBounds(35, 94, 120, 40);
		incheon_btn.setFocusPainted(false);
		incheon_btn.setFont(new Font("?????? ??????", Font.BOLD, 15));
		incheon_btn.addActionListener(this);
		incheon_btn.setBackground(new Color(0x87cefa));
		
		//??????
		localName = "??????";
		String lvlGG = setLevel(localName);
		gyeonggi_btn = new JButton();
		gyeonggi_btn.setBounds(28, 165, 113, 40);
		gyeonggi_btn.setFocusPainted(false);
		gyeonggi_btn.setFont(new Font("?????? ??????", Font.BOLD, 15));
		gyeonggi_btn.addActionListener(this);
		gyeonggi_btn.setBackground(new Color(0x87cefa));
		
		//??????
		localName = "????????????";
		String lvlCN = setLevel(localName);
		chungnam_btn = new JButton();
		chungnam_btn.setBounds(20, 220, 113, 40);
		chungnam_btn.setFocusPainted(false);
		chungnam_btn.setFont(new Font("?????? ??????", Font.BOLD, 15));
		chungnam_btn.addActionListener(this);
		chungnam_btn.setBackground(new Color(0x87cefa));
		
		//??????
		localName = "???????????????";
		String lvlSJ = setLevel(localName);
		seojong_btn = new JButton();
		seojong_btn.setBounds(7, 280, 113, 40);
		seojong_btn.setFocusPainted(false);
		seojong_btn.setFont(new Font("?????? ??????", Font.BOLD, 15));
		seojong_btn.addActionListener(this);
		seojong_btn.setBackground(new Color(0x87cefa));
		
		
		
		//??????
		localName = "???????????????";
		String lvlDJ = setLevel(localName);
		
		daejeon_btn = new JButton();
		daejeon_btn.setBounds(7, 340, 113, 40);
		daejeon_btn.setFocusPainted(false);
		daejeon_btn.setFont(new Font("?????? ??????", Font.BOLD, 15));
		daejeon_btn.addActionListener(this);
		daejeon_btn.setBackground(new Color(0x87cefa));
		
		//??????
		localName = "????????????";
		String lvlJB = setLevel(localName);
		
		jeonbuk_btn = new JButton();
		jeonbuk_btn.setBounds(7, 400, 113, 40);
		jeonbuk_btn.setFocusPainted(false);
		jeonbuk_btn.setFont(new Font("?????? ??????", Font.BOLD, 15));
		jeonbuk_btn.addActionListener(this);
		jeonbuk_btn.setBackground(new Color(0x87cefa));
		
		//??????
		localName = "???????????????";
		String lvlGJ = setLevel(localName);
		
		gwangju_btn = new JButton();
		gwangju_btn.setBounds(7, 485, 113, 40);
		gwangju_btn.setFocusPainted(false);
		gwangju_btn.setFont(new Font("?????? ??????", Font.BOLD, 15));
		gwangju_btn.addActionListener(this);
		gwangju_btn.setBackground(new Color(0x87cefa));
		
		//??????
		localName = "????????????";
		String lvlJN = setLevel(localName);
		
		jeonnam_btn = new JButton();
		jeonnam_btn.setBounds(70, 570, 113, 40);
		jeonnam_btn.setFocusPainted(false);
		jeonnam_btn.setFont(new Font("?????? ??????", Font.BOLD, 15));
		jeonnam_btn.addActionListener(this);
		jeonnam_btn.setBackground(new Color(0x87cefa));
		
		//??????
		localName = "?????????";
		String lvlJJ = setLevel(localName);
		
		jeju_btn = new JButton();
		jeju_btn.setBounds(268, 675, 113, 40);
		jeju_btn.setFocusPainted(false);
		jeju_btn.setFont(new Font("?????? ??????", Font.BOLD, 15));
		jeju_btn.addActionListener(this);
		jeju_btn.setBackground(new Color(0x87cefa));
		
		//??????
		localName = "????????????";
		String lvlGN = setLevel(localName);
		
		gyeongnam_btn = new JButton();
		gyeongnam_btn.setBounds(320, 559, 113, 40);
		gyeongnam_btn.setFocusPainted(false);
		gyeongnam_btn.setFont(new Font("?????? ??????", Font.BOLD, 15));
		gyeongnam_btn.addActionListener(this);
		gyeongnam_btn.setBackground(new Color(0x87cefa));
		
		//??????
		localName = "??????";
		String lvlBS = setLevel(localName);
		
		busan_btn = new JButton();
		busan_btn.setBounds(400, 515, 113, 40);
		busan_btn.setFocusPainted(false);
		busan_btn.setFont(new Font("?????? ??????", Font.BOLD, 15));
		busan_btn.addActionListener(this);
		busan_btn.setBackground(new Color(0x87cefa));
		
		//??????
		localName = "??????";
		String lvlUS = setLevel(localName);
		
		ulsan_btn = new JButton();
		ulsan_btn.setBounds(501, 464, 113, 40);
		ulsan_btn.setFocusPainted(false);
		ulsan_btn.setFont(new Font("?????? ??????", Font.BOLD, 15));
		ulsan_btn.addActionListener(this);
		ulsan_btn.setBackground(new Color(0x87cefa));
		
		//??????
		localName = "??????";
		String lvlDK = setLevel(localName);
		
		daegu_btn = new JButton();
		daegu_btn.setBounds(528, 360, 113, 40);
		daegu_btn.setFocusPainted(false);
		daegu_btn.setFont(new Font("?????? ??????", Font.BOLD, 15));
		daegu_btn.addActionListener(this);
		daegu_btn.setBackground(new Color(0x87cefa));
		
		//??????
		localName = "????????????";
		String lvlGB = setLevel(localName);
		
		gyeongbuk_btn = new JButton();
		gyeongbuk_btn.setBounds(528, 248, 113, 40);
		gyeongbuk_btn.setFocusPainted(false);
		gyeongbuk_btn.setFont(new Font("?????? ??????", Font.BOLD, 15));
		gyeongbuk_btn.addActionListener(this);
		gyeongbuk_btn.setBackground(new Color(0x87cefa));
		
		//??????
		localName = "????????????";
		String lvlCB = setLevel(localName);
		
		chungbuk_btn = new JButton();
		chungbuk_btn.setBounds(507, 141, 113, 40);
		chungbuk_btn.setFocusPainted(false);
		chungbuk_btn.setFont(new Font("?????? ??????", Font.BOLD, 15));
		chungbuk_btn.addActionListener(this);
		chungbuk_btn.setBackground(new Color(0x87cefa));
		
		//??????
		localName = "?????????";
		String lvlGWD = setLevel(localName);
		
		gangwon_btn = new JButton();
		gangwon_btn.setBounds(454, 49, 113, 40);
		gangwon_btn.setFocusPainted(false);
		gangwon_btn.setFont(new Font("?????? ??????", Font.BOLD, 15));
		gangwon_btn.addActionListener(this);
		gangwon_btn.setBackground(new Color(0x87cefa));
//		if(lvlGWD.equals("1.0")) {
//			//0xE6FFFF
//			gangwon_btn.setBackground(new Color(0xE6FFFF));
//		}else if(lvlGWD.equals("1.5")) {
//			//5AAEFF
//			gangwon_btn.setBackground(new Color(0x5AAEFF));
//		}else if(lvlGWD.equals("2.0")) {
//			//FFE400
//			gangwon_btn.setBackground(new Color(0xFFE400));
//		}else if(lvlGWD.equals("2.5")) {
//			//FFBB00
//			gangwon_btn.setBackground(new Color(0xFFBB00));
//		}else if(lvlGWD.equals("3.0")) {
//			//FF0000
//			gangwon_btn.setBackground(new Color(0xFF0000));
//		}
		
		// ?????? ?????? ??????(3600???)
		int sec = 3600;
		// ???????????? ????????? ??????
		final ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);
		exec.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				vaccine_Status();
				// ?????? ????????? ????????????
				exec.shutdown();
			}
			}, 0, sec, TimeUnit.SECONDS);
		
		
		
		
		
		p1.add(seoul_btn);
		p1.add(incheon_btn);
		p1.add(gyeonggi_btn);
		p1.add(chungnam_btn);
		p1.add(seojong_btn);
		p1.add(daejeon_btn);
		p1.add(jeonbuk_btn);
		p1.add(gwangju_btn);
		p1.add(jeonnam_btn);
		p1.add(jeju_btn);
		p1.add(gyeongnam_btn);
		p1.add(busan_btn);
		p1.add(ulsan_btn);
		p1.add(daegu_btn);
		p1.add(gyeongbuk_btn);
		p1.add(chungbuk_btn);
		p1.add(gangwon_btn);
		
		p1.add(lblicon);
		
		
		add(p2,BorderLayout.NORTH);
		add(p1);
		
		setResizable(false);
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
		new Distancing_Map("????????? ?????? ?????? ??????", 665, 830);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == seoul_btn) {
			new Seoul_vaccinationstatus("?????? ?????? ?????? ??????", 880, 615);
		}else if(obj == incheon_btn) {
			new Incheon_vaccinationstatus("?????? ?????? ?????? ??????", 880, 615);
		}else if(obj == gyeonggi_btn) {
			new Gyeonggi_vaccinationstatus("?????? ?????? ?????? ??????", 880, 615);
		}else if(obj == chungnam_btn) {
			new Chungnam_vaccinationstatus("?????? ?????? ?????? ??????", 880, 615);
		}else if(obj == seojong_btn) {
			new Sejong_vaccinationstatus("?????? ?????? ?????? ??????", 880, 615);
		}else if(obj == daejeon_btn) {
			new Daejeon_vaccinationstatus("?????? ?????? ?????? ??????", 880, 615);
		}else if(obj == jeonbuk_btn) {
			new Jeonbuk_vaccinationstatus("?????? ?????? ?????? ??????", 880, 615);
		}else if(obj == gwangju_btn) {
			new Gwangju_vaccinationstatus("?????? ?????? ?????? ??????", 880, 615);
		}else if(obj == jeonnam_btn) {
			new Jeonnam_vaccinationstatus("?????? ?????? ?????? ??????", 880, 615);
		}else if(obj == jeju_btn) {
			new Jeju_vaccinationstatus("?????? ?????? ?????? ??????", 880, 615);
		}else if(obj == gyeongnam_btn) {
			new Gyeongnam_vaccinationstatus("?????? ?????? ?????? ??????", 880, 615);
		}else if(obj == busan_btn) {
			new Busan_vaccinationstatus("?????? ?????? ?????? ??????", 880, 615);
		}else if(obj == ulsan_btn) {
			new Ulsan_vaccinationstatus("?????? ?????? ?????? ??????", 880, 615);
		}else if(obj == daegu_btn) {
			new Daegu_vaccinationstatus("?????? ?????? ?????? ??????", 880, 615);
		}else if(obj == gyeongbuk_btn) {
			new Gyeongbuk_vaccinationstatus("?????? ?????? ?????? ??????", 880, 615);
		}else if(obj == chungbuk_btn) {
			new Chungbuk_vaccinationstatus("?????? ?????? ?????? ??????", 880, 615);
		}else if(obj == gangwon_btn) {
			new Gangwon_vaccinationstatus("?????? ?????? ?????? ??????", 880, 615);
		}
	}

	public void vaccine_Status() {
		Document doc;
		try {
			doc = Jsoup.connect(url).get();
			seoul_elm = doc.select("#content > div.data_table.tbl_scrl_t > table > tbody > tr:nth-child(2) > td:nth-child(5)");
			busan_elm = doc.select("#content > div.data_table.tbl_scrl_t > table > tbody > tr:nth-child(3) > td:nth-child(5)");
			Elements daegu_elm = doc.select("#content > div.data_table.tbl_scrl_t > table > tbody > tr:nth-child(4) > td:nth-child(5)");
			Elements incheon_elm = doc.select("#content > div.data_table.tbl_scrl_t > table > tbody > tr:nth-child(5) > td:nth-child(5)");
			Elements gwangju_elm = doc.select("#content > div.data_table.tbl_scrl_t > table > tbody > tr:nth-child(6) > td:nth-child(5)");
			Elements daejeon_elm = doc.select("#content > div.data_table.tbl_scrl_t > table > tbody > tr:nth-child(7) > td:nth-child(5)");
			Elements ulsan_elm = doc.select("#content > div.data_table.tbl_scrl_t > table > tbody > tr:nth-child(8) > td:nth-child(5)");
			Elements seojong_elm = doc.select("#content > div.data_table.tbl_scrl_t > table > tbody > tr:nth-child(9) > td:nth-child(5)");
			Elements gyeonggi_elm = doc.select("#content > div.data_table.tbl_scrl_t > table > tbody > tr:nth-child(10) > td:nth-child(5)");
			Elements gangwon_elm = doc.select("#content > div.data_table.tbl_scrl_t > table > tbody > tr:nth-child(11) > td:nth-child(5)");
			Elements chungbuk_elm = doc.select("#content > div.data_table.tbl_scrl_t > table > tbody > tr:nth-child(12) > td:nth-child(5)");
			Elements chungnam_elm = doc.select("#content > div.data_table.tbl_scrl_t > table > tbody > tr:nth-child(13) > td:nth-child(5)");
			Elements jeonbuk_elm = doc.select("#content > div.data_table.tbl_scrl_t > table > tbody > tr:nth-child(14) > td:nth-child(5)");
			Elements jeonnam_elm = doc.select("#content > div.data_table.tbl_scrl_t > table > tbody > tr:nth-child(15) > td:nth-child(5)");
			Elements gyeongbuk_elm = doc.select("#content > div.data_table.tbl_scrl_t > table > tbody > tr:nth-child(16) > td:nth-child(5)");
			Elements gyeongnam_elm = doc.select("#content > div.data_table.tbl_scrl_t > table > tbody > tr:nth-child(17) > td:nth-child(5)");
			Elements jeju_elm = doc.select("#content > div.data_table.tbl_scrl_t > table > tbody > tr:nth-child(18) > td:nth-child(5)");
			
			
			String seoul_vaccine = seoul_elm.text();
			seoul_vaccine = seoul_vaccine.format("%.2f%%%n", Double.parseDouble(seoul_vaccine.replace(",",""))  / 9505868 * 100.0);
			String busan_vaccine = busan_elm.text();
			busan_vaccine = busan_vaccine.format("%.2f%%%n", Double.parseDouble(busan_vaccine.replace(",",""))  / 3356587 * 100.0);
			String daegu_vaccine = daegu_elm.text();
			daegu_vaccine = daegu_vaccine.format("%.2f%%%n", Double.parseDouble(daegu_vaccine.replace(",",""))  / 2401110 * 100.0);
			String incheon_vaccine = incheon_elm.text();
			incheon_vaccine = incheon_vaccine.format("%.2f%%%n", Double.parseDouble(incheon_vaccine.replace(",",""))  / 2915839 * 100.0);
			String gwangju_vaccine = gwangju_elm.text();
			gwangju_vaccine = gwangju_vaccine.format("%.2f%%%n", Double.parseDouble(gwangju_vaccine.replace(",",""))  / 1441552 * 100.0);
			String daejeon_vaccine = daejeon_elm.text();
			daejeon_vaccine = daejeon_vaccine.format("%.2f%%%n", Double.parseDouble(daejeon_vaccine.replace(",",""))  / 1454011	 * 100.0);
			String ulsan_vaccine = ulsan_elm.text();
			ulsan_vaccine = ulsan_vaccine.format("%.2f%%%n", Double.parseDouble(ulsan_vaccine.replace(",",""))  / 1130315 * 100.0);
			String seojong_vaccine = seojong_elm.text();
			seojong_vaccine = seojong_vaccine.format("%.2f%%%n", Double.parseDouble(seojong_vaccine.replace(",",""))  / 354705 * 100.0);
			String gyeonggi_vaccine = gyeonggi_elm.text();
			gyeonggi_vaccine = gyeonggi_vaccine.format("%.2f%%%n", Double.parseDouble(gyeonggi_vaccine.replace(",",""))  / 13315895 * 100.0);
			String gangwon_vaccine = gangwon_elm.text();
			gangwon_vaccine = gangwon_vaccine.format("%.2f%%%n", Double.parseDouble(gangwon_vaccine.replace(",",""))  / 1529586 * 100.0);
			String chungbuk_vaccine = chungbuk_elm.text();
			chungbuk_vaccine = chungbuk_vaccine.format("%.2f%%%n", Double.parseDouble(chungbuk_vaccine.replace(",",""))  / 1591009 * 100.0);
			String chungnam_vaccine = chungnam_elm.text();
			chungnam_vaccine = chungnam_vaccine.format("%.2f%%%n", Double.parseDouble(chungnam_vaccine.replace(",",""))  / 2120347 * 100.0);
			String jeonbuk_vaccine = jeonbuk_elm.text();
			jeonbuk_vaccine = jeonbuk_vaccine.format("%.2f%%%n", Double.parseDouble(jeonbuk_vaccine.replace(",",""))  / 1792694 * 100.0);
			String jeonnam_vaccine = jeonnam_elm.text();
			jeonnam_vaccine = jeonnam_vaccine.format("%.2f%%%n", Double.parseDouble(jeonnam_vaccine.replace(",",""))  / 1839432 * 100.0);
			String gyeongbuk_vaccine = gyeongbuk_elm.text();
			gyeongbuk_vaccine = gyeongbuk_vaccine.format("%.2f%%%n", Double.parseDouble(gyeongbuk_vaccine.replace(",",""))  / 2623028 * 100.0);
			String gyeongnam_vaccine = gyeongnam_elm.text();
			gyeongnam_vaccine = gyeongnam_vaccine.format("%.2f%%%n", Double.parseDouble(gyeongnam_vaccine.replace(",",""))  / 3342831 * 100.0);
			String jeju_vaccine = jeju_elm.text();
			jeju_vaccine = jeju_vaccine.format("%.2f%%%n", Double.parseDouble(jeju_vaccine.replace(",",""))  / 669177 * 100.0);
			
			//System.out.println(seoul_vaccine);
			//System.out.println(busan_vaccine);
			
			
			seoul_btn.setText("<html><body><center>"+ "??????" +"<br>"+ seoul_vaccine + "</body></html>");
			busan_btn.setText("<html><body><center>"+ "??????" +"<br>"+ busan_vaccine + "</body></html>");
			daegu_btn.setText("<html><body><center>"+ "??????" +"<br>"+ daegu_vaccine + "</body></html>");
			incheon_btn.setText("<html><body><center>"+ "??????" +"<br>"+ incheon_vaccine + "</body></html>");
			gwangju_btn.setText("<html><body><center>"+ "??????" +"<br>"+ gwangju_vaccine + "</body></html>");
			daejeon_btn.setText("<html><body><center>"+ "??????" +"<br>"+ daejeon_vaccine + "</body></html>");
			ulsan_btn.setText("<html><body><center>"+ "??????" +"<br>"+ ulsan_vaccine + "</body></html>");
			seojong_btn.setText("<html><body><center>"+ "??????" +"<br>"+ seojong_vaccine + "</body></html>");
			gyeonggi_btn.setText("<html><body><center>"+ "??????" +"<br>"+ gyeonggi_vaccine + "</body></html>");
			gangwon_btn.setText("<html><body><center>"+ "?????????" +"<br>"+ gangwon_vaccine + "</body></html>");
			chungbuk_btn.setText("<html><body><center>"+ "????????????" +"<br>"+ chungbuk_vaccine + "</body></html>");
			chungnam_btn.setText("<html><body><center>"+ "????????????" +"<br>"+ chungnam_vaccine + "</body></html>");
			jeonbuk_btn.setText("<html><body><center>"+ "????????????" +"<br>"+ jeonbuk_vaccine + "</body></html>");
			jeonnam_btn.setText("<html><body><center>"+ "????????????" +"<br>"+ jeonnam_vaccine + "</body></html>");
			gyeongbuk_btn.setText("<html><body><center>"+ "????????????" +"<br>"+ gyeongbuk_vaccine + "</body></html>");
			gyeongnam_btn.setText("<html><body><center>"+ "????????????" +"<br>"+ gyeongnam_vaccine + "</body></html>");
			jeju_btn.setText("<html><body><center>"+ "?????????" +"<br>"+ jeju_vaccine + "</body></html>");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void data_1(Elements elm) {
		for (int i = 2; i <= 3; i++) {
			elm = doc.select("#content > div.data_table.tbl_scrl_t > table > tbody > tr:nth-child("+i+") > td:nth-child(5)");
		}
	}
	

}
